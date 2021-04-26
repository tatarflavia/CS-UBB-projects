package Domain;

import java.util.ArrayList;

public class Matrix {
    private ArrayList<ArrayList<Integer>> elements;
    private int rowCount;
    private int columnCount;




    public Matrix(int rowCount, int columnCount,ArrayList<ArrayList<Integer>> elements) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.elements=elements;
    }

    public int getElementFromPosition(int row, int col){
        return this.elements.get(row).get(col);
    }

    public void setElement(int row, int col, int value){
        this.elements.get(row).set(col, value);
    }

    public int getMatrixIndex(int row, int col){
        return row * this.columnCount + col;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public String toString() {
        StringBuilder result= new StringBuilder();
        for(int i=0;i<rowCount;i++){
            for (Integer element:this.elements.get(i)){
                result.append(element).append("  ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
