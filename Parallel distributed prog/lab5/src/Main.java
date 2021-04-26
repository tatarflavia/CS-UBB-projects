import Domain.Polynomial;
import Operations.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        Polynomial firstPol=new Polynomial(15);
        Polynomial secondPol=new Polynomial(15);
        int numberOfThreads=3;

        Scanner scan = new Scanner(System.in);
        System.out.print("1.regular O(n2) algorithm.\n");
        System.out.print("2.Karatsuba algorithm.\n");
        System.out.print("Enter an option: ");
        int num = scan.nextInt();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;  // milliseconds.
        scan.close();
        if(num==1){
            System.out.println("The first polynomial is: \n"+ firstPol+"\n");
            System.out.println("The second polynomial is: \n"+ secondPol+"\n");
            int size=firstPol.getDegree()+secondPol.getDegree()+1;
            System.out.println("The number of threads is: "+ numberOfThreads+", size of result polynomial: "+size+"\n");

            //get result for non parallel method
            long startTime1 = System.nanoTime();
            System.out.println("The result for non parallel method is: \n"+ Operation.doMultiplicationSimpleMethodNoParallelism(firstPol,secondPol));
            //Operation.doMultiplicationSimpleMethodNoParallelism(firstPol,secondPol);
            long endTime1 = System.nanoTime();
            long duration1 = (endTime1 - startTime1)/1000000+duration;  // milliseconds.
            System.out.println("Non parallel method(Regular O(n2) computation): The time passed is: "+duration1 +" milliseconds\n");

            //get result for parallel method
            long startTime2 = System.nanoTime();
            System.out.println("The result for parallel method is: \n"+ Operation.doMultiplicationSimpleMethodWithParallelism(firstPol,secondPol,numberOfThreads));
            //Operation.doMultiplicationSimpleMethodWithParallelism(firstPol,secondPol,numberOfThreads);
            long endTime2 = System.nanoTime();
            long duration2 = (endTime2 - startTime2)/1000000+duration;  // milliseconds.
            System.out.println("Parallel method: The time passed is: "+duration2 +" milliseconds\n");

        }
        else{
            System.out.println("The first polynomial is: \n"+ firstPol+"\n");
            System.out.println("The second polynomial is: \n"+ secondPol+"\n");
            int size=firstPol.getDegree()+secondPol.getDegree()+1;
            System.out.println("The number of threads is: "+ numberOfThreads+", size of result polynomial: "+size+"\n");

            //get result for non parallel method
            long startTime1 = System.nanoTime();
            System.out.println("The result for non parallel method is: \n"+ Operation.doMultiplicationKaratsubaAlgNoParallelism(firstPol,secondPol));
            //Operation.doMultiplicationKaratsubaAlgNoParallelism(firstPol,secondPol);
            long endTime1 = System.nanoTime();
            long duration1 = (endTime1 - startTime1)/1000000+duration;  // milliseconds.
            System.out.println("Non parallel method(Karatsuba algorithm): The time passed is: "+duration1 +" milliseconds\n");

            //get result for parallel method
            long startTime2 = System.nanoTime();
            try{
                System.out.println("The result for parallel method is: \n"+ Operation.doMultiplicationKaratsubaAlgWithParallelism(firstPol,secondPol,4));
                //Operation.doMultiplicationKaratsubaAlgWithParallelism(firstPol,secondPol,4);
                long endTime2 = System.nanoTime();
                long duration2 = (endTime2 - startTime2)/1000000+duration;  // milliseconds.
                System.out.println("Parallel method: The time passed is: "+duration2 +" milliseconds\n");
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }
}
