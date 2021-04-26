package Thread;

import Domain.Matrix;

import java.util.AbstractMap;
import java.util.ArrayList;

public class Task implements Runnable {
    private Matrix firstMatrix;
    private Matrix secondMatrix;
    private Matrix resultMatrix;
    private int id;

    ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> matrixResultsToBeComputed;

    public Task(int id,Matrix firstMatrix, Matrix secondMatrix, Matrix resultMatrix) {
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.resultMatrix = resultMatrix;
        this.matrixResultsToBeComputed = new ArrayList<>();
        this.id=id;
    }

    public void addPositionToTask(AbstractMap.SimpleEntry<Integer,Integer> newPosition){
        matrixResultsToBeComputed.add(newPosition);
    }

    private int computeElementOfTheResultMatrix(AbstractMap.SimpleEntry<Integer, Integer> point){
        int result = 0;
        for (int i = 0; i < resultMatrix.getRowCount(); i++){
            result += (firstMatrix.getElementFromPosition(point.getKey(), i) * secondMatrix.getElementFromPosition(i, point.getValue()));
        }
        return result;
    }



    @Override
    public void run() {
        for (AbstractMap.SimpleEntry<Integer, Integer> point : this.matrixResultsToBeComputed){
            int result = computeElementOfTheResultMatrix(point);
            System.out.println("Task number "+id+" added "+result+" to the result matrix on position ("+point.getKey()+","+point.getValue()+")\n");
            this.resultMatrix.setElement(point.getKey(), point.getValue(), result);
        }
    }
}
