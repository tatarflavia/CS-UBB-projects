package Main;

import Domain.Matrix;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static int getRandomInt(int min, int max) {
        return (int)(((Math.random() * (max - min)) + min));
    }

    private static ArrayList<ArrayList<Integer>> makeAMatrix(int rowSize,int columnSize){
        ArrayList<ArrayList<Integer>> matrix=new ArrayList<>();
        int k=1;
        for(int i=0;i<rowSize;i++){
            ArrayList<Integer> newArray=new ArrayList<>();
            for(int j=0;j<columnSize;j++){
                newArray.add(k);
                k++;
            }
            matrix.add(newArray);
        }
        return matrix;
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        int rowSize=3;
        int columnSize=3;
        Matrix matrixOne=new Matrix(rowSize,columnSize,makeAMatrix(rowSize,columnSize));
        Matrix matrixTwo=new Matrix(rowSize,columnSize,makeAMatrix(rowSize,columnSize));
        int numberOfTasks=5;
        int numberOfThreads=4;

        Scanner scan = new Scanner(System.in);
        System.out.print("1.Create an actual thread for each task.\n");
        System.out.print("2.Use a thread pool.\n");
        System.out.print("Enter an option: ");
        int num = scan.nextInt();
        ThreadsMain threadsMain=new ThreadsMain(matrixOne,matrixTwo,numberOfTasks);
        ThreadPoolMain threadPoolMain=new ThreadPoolMain(matrixOne,matrixTwo,numberOfTasks,numberOfThreads);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;  // milliseconds.
        System.out.println("Size of matrix: "+rowSize+"x"+columnSize+"\n");
        scan.close();
        if(num==1){
            System.out.println("The first matrix is: \n"+ matrixOne+"\n");
            System.out.println("The second matrix is: \n"+ matrixTwo+"\n");
            System.out.println("The number of tasks/threads is: "+ numberOfTasks+", size of matrix: "+rowSize+"x"+columnSize+"\n");

            //get result for first division
            long startTime1 = System.nanoTime();
            System.out.println("The result for first division is: \n"+ threadsMain.getMatrixResultForFirstDivision());
            long endTime1 = System.nanoTime();
            long duration1 = (endTime1 - startTime1)/1000000+duration;  // milliseconds.
            System.out.println("First division: The time passed is: "+duration1 +" milliseconds\n");

            //get res for second div
            long startTime2 = System.nanoTime();
            System.out.println("The result for second division is: \n"+ threadsMain.getMatrixResultForSecondDivision());
            long endTime2 = System.nanoTime();
            long duration2 = (endTime2 - startTime2)/1000000+duration;  // milliseconds.
            System.out.println("Second division: The time passed is: "+duration2 +" milliseconds\n");

            //get res for last div
            long startTime3 = System.nanoTime();
            System.out.println("The result for third division is: \n"+ threadsMain.getMatrixResultForThirdDivision());
            long endTime3 = System.nanoTime();
            long duration3 = (endTime3 - startTime3)/1000000+duration;  // milliseconds.
            System.out.println("Third division: The time passed is: "+duration3 +" milliseconds\n");
        }

        else{
            System.out.println("The first matrix is: \n"+ matrixOne+"\n");
            System.out.println("The second matrix is: \n"+ matrixTwo+"\n");
            System.out.println("The number of tasks is: "+ numberOfTasks+", number of threads is:" + numberOfThreads+", size of matrix: "+rowSize+"x"+columnSize+"\n");


            //get result for first division
            long startTime1 = System.nanoTime();
            System.out.println("The result for first division is: \n"+ threadPoolMain.getMatrixResultForFirstDivision());
            long endTime1 = System.nanoTime();
            long duration1 = (endTime1 - startTime1)/1000000+duration;  // milliseconds.
            System.out.println("First division: The time passed is: "+duration1 +" milliseconds\n");

            //get res for second div
            long startTime2 = System.nanoTime();
            System.out.println("The result for second division is: \n"+ threadPoolMain.getMatrixResultForSecondDivision());
            long endTime2 = System.nanoTime();
            long duration2 = (endTime2 - startTime2)/1000000+duration;  // milliseconds.
            System.out.println("Second division: The time passed is: "+duration2 +" milliseconds\n");

            //get res for last div
            long startTime3 = System.nanoTime();
            System.out.println("The result for third division is: \n"+ threadPoolMain.getMatrixResultForThirdDivision());
            long endTime3 = System.nanoTime();
            long duration3 = (endTime3 - startTime3)/1000000+duration;  // milliseconds.
            System.out.println("Third division: The time passed is: "+duration3 +" milliseconds\n");
        }

    }


}
