package Main;

import Domain.Matrix;
import Thread.*;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolMain {
    private Matrix firstMatrix;
    private Matrix secondMatrix;
    private int numberOfTasks;
    private int numberOfThreads;
    private int resultMatrixColumnSize;
    private int resultMatrixRowSize;

    public ThreadPoolMain (Matrix firstMatrix, Matrix secondMatrix, int numberOfTasks,int numberOfThreads) {
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.numberOfTasks = numberOfTasks;
        this.resultMatrixColumnSize=Math.min(firstMatrix.getColumnCount(),secondMatrix.getColumnCount());
        this.resultMatrixRowSize=Math.min(firstMatrix.getRowCount(),secondMatrix.getRowCount());
        this.numberOfThreads=numberOfThreads;
    }

    private ArrayList<ArrayList<Integer>> getNewEmptyMatrix(){
        ArrayList<ArrayList<Integer>> matrix=new ArrayList<>();
        for(int i=0;i<resultMatrixRowSize;i++){
            ArrayList<Integer> row=new ArrayList<>();
            for(int j=0;j<resultMatrixColumnSize;j++){
                row.add(0);
            }
            matrix.add(row);
        }
        return matrix;
    }


    public Matrix getMatrixResultForFirstDivision(){
        ArrayList<Task> tasks=new ArrayList<>();
        Matrix resultMatrix=new Matrix(resultMatrixRowSize,resultMatrixColumnSize,getNewEmptyMatrix());
        for(int i=0;i<numberOfTasks;i++){
            tasks.add(new Task(i,firstMatrix,secondMatrix,resultMatrix));
        }
        //give tasks
        int k=0; //to know which task we are adding to
        int indexCounter=0;//to know when to go to next task
        int maxIndexForATask=(int)((resultMatrixColumnSize*resultMatrixRowSize)/numberOfTasks-1);
        for (int i = 0; i < resultMatrixRowSize; i++){
            for(int j = 0; j < resultMatrixColumnSize; j++) {
                if (indexCounter <= maxIndexForATask) {
                    tasks.get(k).addPositionToTask(new AbstractMap.SimpleEntry<>(i,j));
                }
                else{
                    if(k==numberOfTasks-1){
                        //it's the end indexes case
                        tasks.get(k).addPositionToTask(new AbstractMap.SimpleEntry<>(i,j));
                    }
                    else{
                        k++;
                        tasks.get(k).addPositionToTask(new AbstractMap.SimpleEntry<>(i,j));
                        indexCounter=0;
                    }
                }
                indexCounter++;
            }
        }
        //thread pool init plus start
        ExecutorService pool = Executors.newFixedThreadPool(numberOfThreads);

        for (Task task : tasks){
            pool.execute(task);
        }

        pool.shutdown();
        try{
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        }
        catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        return resultMatrix;
    }

    public Matrix getMatrixResultForSecondDivision(){
        ArrayList<Task> tasks=new ArrayList<>();
        Matrix resultMatrix=new Matrix(resultMatrixRowSize,resultMatrixColumnSize,getNewEmptyMatrix());
        for(int i=0;i<numberOfTasks;i++){
            tasks.add(new Task(i,firstMatrix,secondMatrix,resultMatrix));
        }
        //give tasks
        int k=0; //to know which task we are adding to
        int indexCounter=0;//to know when to go to next task
        int maxIndexForATask=(int)((resultMatrixColumnSize*resultMatrixRowSize)/numberOfTasks-1);
        for (int j = 0; j < resultMatrixColumnSize; j++){
            for(int i = 0; i < resultMatrixRowSize; i++) {
                if (indexCounter <= maxIndexForATask) {
                    tasks.get(k).addPositionToTask(new AbstractMap.SimpleEntry<>(i,j));
                }
                else{
                    if(k==numberOfTasks-1){
                        //it's the end indexes case
                        tasks.get(k).addPositionToTask(new AbstractMap.SimpleEntry<>(i,j));
                    }
                    else{
                        k++;
                        tasks.get(k).addPositionToTask(new AbstractMap.SimpleEntry<>(i,j));
                        indexCounter=0;
                    }
                }
                indexCounter++;
            }
        }
        //thread pool init plus start
        ExecutorService pool = Executors.newFixedThreadPool(numberOfThreads);

        for (Task task : tasks){
            pool.execute(task);
        }

        pool.shutdown();
        try{
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        }
        catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        return resultMatrix;
    }

    public Matrix getMatrixResultForThirdDivision(){
        ArrayList<Task> tasks=new ArrayList<>();
        Matrix resultMatrix=new Matrix(resultMatrixRowSize,resultMatrixColumnSize,getNewEmptyMatrix());
        for(int i=0;i<numberOfTasks;i++){
            tasks.add(new Task(i,firstMatrix,secondMatrix,resultMatrix));
        }
        //give tasks
        for (int i = 0; i < resultMatrixRowSize; i++){
            for(int j = 0; j < resultMatrixColumnSize; j++){
                tasks.get(resultMatrix.getMatrixIndex(i, j) % numberOfTasks).addPositionToTask(new AbstractMap.SimpleEntry<>(i,j));
            }
        }
        //thread pool init plus start
        ExecutorService pool = Executors.newFixedThreadPool(numberOfThreads);

        for (Task task : tasks){
            pool.execute(task);
        }

        pool.shutdown();
        try{
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        }
        catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        return resultMatrix;

    }



}
