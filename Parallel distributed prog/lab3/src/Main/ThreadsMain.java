package Main;

import Domain.Matrix;
import Thread.Task;

import java.util.AbstractMap;
import java.util.ArrayList;

public class ThreadsMain {
    private Matrix firstMatrix;
    private Matrix secondMatrix;
    private int numberOfTasks;
    private int resultMatrixColumnSize;
    private int resultMatrixRowSize;

    public ThreadsMain(Matrix firstMatrix, Matrix secondMatrix, int numberOfTasks) {
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.numberOfTasks = numberOfTasks;
        this.resultMatrixColumnSize=Math.min(firstMatrix.getColumnCount(),secondMatrix.getColumnCount());
        this.resultMatrixRowSize=Math.min(firstMatrix.getRowCount(),secondMatrix.getRowCount());
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
        //init for threads
        ArrayList<Thread> threads=new ArrayList<>();
        for(Task task:tasks){
            threads.add(new Thread(task));
        }
        //start them and join them
        for(Thread thread:threads){
            thread.start();
        }
        for(Thread thread:threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
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
        //init for threads
        ArrayList<Thread> threads=new ArrayList<>();
        for(Task task:tasks){
            threads.add(new Thread(task));
        }
        //start them and join them
        for(Thread thread:threads){
            thread.start();
        }
        for(Thread thread:threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
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
        //init for threads
        ArrayList<Thread> threads=new ArrayList<>();
        for(Task task:tasks){
            threads.add(new Thread(task));
        }
        //start them and join them
        for(Thread thread:threads){
            thread.start();
        }
        for(Thread thread:threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        return resultMatrix;

    }

}
