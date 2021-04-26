package Operations;

import Domain.Polynomial;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;
import Thread.Task;

public class Operation {

    //Simple O(n^2) polynomial multiplication without parallelism
    public static Polynomial doMultiplicationSimpleMethodNoParallelism(Polynomial firstPolynomial, Polynomial secondPolynomial){
        //first get the size of result polynomial
        int sizeOfResultCoefficients=firstPolynomial.getDegree()+secondPolynomial.getDegree()+1;

        //prepare the coeffs with 0 in the beggining
        List<Integer> coeffs=new ArrayList<>();
        for(int i=0;i<sizeOfResultCoefficients;i++){
            coeffs.add(0);
        }
        //now we calculate with 2 fors the coefficients for  the result polynomial with formula res[i+j] = res[i+j] + A[i] * B[j]
        for(int i=0;i<firstPolynomial.getCoefficientsSize();i++){
            for(int j=0;j<secondPolynomial.getCoefficientsSize();j++){
                int valueToBePutAtIndex=firstPolynomial.getCoefficients().get(i)*secondPolynomial.getCoefficients().get(j);
                coeffs.set(i+j,coeffs.get(i+j)+valueToBePutAtIndex);
            }
        }

        //now return result polynomial with the coeffs calculated above
        return new Polynomial(coeffs);
    }

    //Simple O(n^2) polynomial multiplication with parallelism
    public static Polynomial doMultiplicationSimpleMethodWithParallelism(Polynomial firstPolynomial, Polynomial secondPolynomial,int numberOfThreads){
        //first get the size of result polynomial
        int sizeOfResultCoefficients=firstPolynomial.getDegree()+secondPolynomial.getDegree()+1;
        //prepare the coeffs with 0 in the beggining
        List<Integer> coeffs=new ArrayList<>();
        for(int i=0;i<sizeOfResultCoefficients;i++){
            coeffs.add(0);
        }
        //make the result polynomial
        Polynomial resultPolynomial=new Polynomial(coeffs);

        //prepare the thread pool and assign tasks
        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(numberOfThreads);
        //see how many tasks a thread should make
        int numberOfSteps=resultPolynomial.getCoefficientsSize()/numberOfThreads;
        if(numberOfSteps==0)
            numberOfSteps=1;
        System.out.println("Each thread will be given "+numberOfSteps+" tasks");
        int idCount=1;
        //now give the tasks to the threads and start execution form each thread
        for(int i=0;i<resultPolynomial.getCoefficientsSize();i+=numberOfSteps){
            //here we give each thread tasks, as in to calculate from an index to index+numberOfSteps in the result polynomial
            Task thread=new Task(firstPolynomial,secondPolynomial,resultPolynomial,i,i+numberOfSteps,idCount);
            idCount++;
            threadPool.execute(thread);
        }
        threadPool.shutdown();
        try{
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        }
        catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        return resultPolynomial;
    }

    // Karatsuba algorithm polynomial multiplication without parallelism
    public static Polynomial doMultiplicationKaratsubaAlgNoParallelism(Polynomial first, Polynomial second){
        //first we half those 2 polynomials
        if(first.getDegree()<2 || second.getDegree()<2){
            //this means we can't half them anymore
            return doMultiplicationSimpleMethodNoParallelism(first,second);
        }
        //half them
        int len=Math.max(first.getDegree(),second.getDegree())/2;
        Polynomial lowFirst=new Polynomial(first.getCoefficients().subList(0,len)); //from x^0 till x^ deg/2
        Polynomial highFirst=new Polynomial(first.getCoefficients().subList(len,first.getCoefficientsSize())); //rest
        Polynomial lowSecond=new Polynomial(second.getCoefficients().subList(0,len));
        Polynomial highSecond=new Polynomial(second.getCoefficients().subList(len,second.getCoefficientsSize()));

        //we prepare the formula for the multiplication
        //c0=low A * low B,     c1= high A * high B,      c2=(low A+ high A)*(low B+high B)-c0-c1
        Polynomial c0=doMultiplicationKaratsubaAlgNoParallelism(lowFirst,lowSecond);
        Polynomial c1=doMultiplicationKaratsubaAlgNoParallelism(highFirst,highSecond);
        Polynomial valueForMultiForC2=doMultiplicationKaratsubaAlgNoParallelism(add(lowFirst,highFirst),add(lowSecond,highSecond));
        Polynomial c2=subtract(subtract(valueForMultiForC2,c0),c1);

        //calc final result for multiplication by AXB=c1(shifted len*2) pos + c2(shifted len pos) + c0
        Polynomial r1=shift(c1,2*len);
        Polynomial r2=shift(c2,len);
        return add(add(r1,r2),c0);
    }

    // Karatsuba algorithm polynomial multiplication with parallelism
    public static Polynomial doMultiplicationKaratsubaAlgWithParallelism(Polynomial first, Polynomial second,int currentDepth) throws ExecutionException, InterruptedException {
        //first the get halves of the polynomials
        if(currentDepth>4){
            //if we already have 4 depth for,we stop the division
            return doMultiplicationKaratsubaAlgNoParallelism(first,second);
        }
        if(first.getDegree()<2 || second.getDegree()<2){
            //this means we can't half them anymore
            return doMultiplicationSimpleMethodNoParallelism(first,second);
        }
        //half them
        int len=Math.max(first.getDegree(),second.getDegree())/2;
        Polynomial lowFirst=new Polynomial(first.getCoefficients().subList(0,len)); //from x^0 till x^ deg/2
        Polynomial highFirst=new Polynomial(first.getCoefficients().subList(len,first.getCoefficientsSize())); //rest
        Polynomial lowSecond=new Polynomial(second.getCoefficients().subList(0,len));
        Polynomial highSecond=new Polynomial(second.getCoefficients().subList(len,second.getCoefficientsSize()));

        //we prepare the formula for the multiplication
        //c0=low A * low B,     c1= high A * high B,      c2=(low A+ high A)*(low B+high B)-c0-c1
        //start thread pool by giving each thread tasks(they have recursive calls to get the result for a multiplication)
        ThreadPoolExecutor  threadPool= (ThreadPoolExecutor) Executors.newCachedThreadPool();
        //make the tasks callable
        Callable<Polynomial> task0=()->doMultiplicationKaratsubaAlgWithParallelism(lowFirst,lowSecond,currentDepth+1);
        Callable<Polynomial> task1=()->doMultiplicationKaratsubaAlgWithParallelism(highFirst,highSecond,currentDepth);
        Callable<Polynomial> task2=()->doMultiplicationKaratsubaAlgWithParallelism(add(highFirst,lowFirst),add(highSecond,lowSecond),currentDepth+1);

        //use future for tasks to subscribe them to the pool, that way the pool knows that a result will be given for each task in the future
        Future<Polynomial> futureForC0=threadPool.submit(task0);
        Future<Polynomial> futureForC1=threadPool.submit(task1);
        Future<Polynomial> futureForC2=threadPool.submit(task2);

        //get the values from the futures
        threadPool.shutdown();

        Polynomial c0=futureForC0.get();
        Polynomial c1=futureForC1.get();
        Polynomial c2=futureForC2.get();

        try{
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        }
        catch (InterruptedException e){
            System.out.println(e.getMessage());
        }

        //calc final result for multiplication by AXB=c1(shifted len*2) pos + c2(shifted len pos) + c0
        Polynomial r1=shift(c1,2*len);
        Polynomial r2=shift(subtract(subtract(c2,c0),c1),len);
        return add(add(r1,r2),c0);

    }

    //increase the degree and add numberPositionsToBeShifted of 0's in lower part of the polynomial, in the x^0 part
    //so for p=6+2x+3x^2 and 3 positions, we will have p=0+0+0+6x^3+2x^4+3x^5
    private static Polynomial shift(Polynomial polynomial, int numberPositionsToBeShifted) {
        List<Integer> coeffs=new ArrayList<>();
        //add 0's in lower part
        for(int i=0;i<numberPositionsToBeShifted;i++){
            coeffs.add(0);
        }
        //add the rest of the polynomial
        for(int i=0;i<polynomial.getCoefficientsSize();i++){
            coeffs.add(polynomial.getCoefficients().get(i));
        }
        return new Polynomial(coeffs);

    }




    //addition for 2 polynomials
    private static Polynomial add(Polynomial p1, Polynomial p2) {
        int minDegree = Math.min(p1.getDegree(), p2.getDegree());
        int maxDegree = Math.max(p1.getDegree(), p2.getDegree());
        List<Integer> coefficients = new ArrayList<>(maxDegree + 1);

        //Add the 2 polynomials
        for (int i = 0; i <= minDegree; i++) {
            coefficients.add(p1.getCoefficients().get(i) + p2.getCoefficients().get(i));
        }

        addRemainingCoefficients(p1, p2, minDegree, maxDegree, coefficients);

        return new Polynomial(coefficients);
    }


    //complete remaining part with the given coeffs, taken from a polynomial with a bigger degree
    private static void addRemainingCoefficients(Polynomial p1, Polynomial p2, int minDegree, int maxDegree,
                                                 List<Integer> coefficients) {
        if (minDegree != maxDegree) {
            if (maxDegree == p1.getDegree()) {
                for (int i = minDegree + 1; i <= maxDegree; i++) {
                    coefficients.add(p1.getCoefficients().get(i));
                }
            } else {
                for (int i = minDegree + 1; i <= maxDegree; i++) {
                    coefficients.add(p2.getCoefficients().get(i));
                }
            }
        }
    }

    //subtraction of 2 polynomials
    public static Polynomial subtract(Polynomial p1, Polynomial p2) {
        int minDegree = Math.min(p1.getDegree(), p2.getDegree());
        int maxDegree = Math.max(p1.getDegree(), p2.getDegree());
        List<Integer> coefficients = new ArrayList<>(maxDegree + 1);

        //Subtract the 2 polynomials
        for (int i = 0; i <= minDegree; i++) {
            coefficients.add(p1.getCoefficients().get(i) - p2.getCoefficients().get(i));
        }

        addRemainingCoefficients(p1, p2, minDegree, maxDegree, coefficients);

        //remove coefficients starting from biggest power if coefficient is 0
        int i = coefficients.size() - 1;
        while (coefficients.get(i) == 0 && i > 0) {
            coefficients.remove(i);
            i--;
        }
        return new Polynomial(coefficients);
    }







}
