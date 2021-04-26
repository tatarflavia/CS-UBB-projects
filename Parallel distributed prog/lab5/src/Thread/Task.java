package Thread;


import Domain.Polynomial;

import java.util.AbstractMap;
import java.util.ArrayList;

public class Task implements Runnable {
    private Polynomial firstPolynomial;
    private Polynomial secondPolynomial;
    private Polynomial resultPolynomial;
    private int startIndexForComputingResult;
    private int endIndexForComputingResult;
    private int id;


    public Task(Polynomial firstPolynomial, Polynomial secondPolynomial, Polynomial resultPolynomial, int startIndexForComputingResult, int endIndexForComputingResult, int id) {
        this.firstPolynomial = firstPolynomial;
        this.secondPolynomial = secondPolynomial;
        this.resultPolynomial = resultPolynomial;
        this.startIndexForComputingResult = startIndexForComputingResult;
        this.endIndexForComputingResult = endIndexForComputingResult;
        this.id = id;
    }



    private void computeIndexOfTheResultPolynomial(int index){
        //find all pairs needed for computing the coefficient from this index in the result polynomial + change the coef from there
        for(int j=0;j<=index;j++){
            if(j<firstPolynomial.getCoefficientsSize() && (index-j)<secondPolynomial.getCoefficientsSize()){
                int valueToBeAddedToCoef=firstPolynomial.getCoefficients().get(j)*secondPolynomial.getCoefficients().get(index-j);
                int lastValFromCoef=resultPolynomial.getCoefficients().get(index);
                //set the new val by adding it to the last val from that index in the result polynomial
                resultPolynomial.getCoefficients().set(index,lastValFromCoef+valueToBeAddedToCoef);
            }
        }
    }





    @Override
    public void run() {
        //calculate coefficients for result polynomial from [start index till end index-1]
        for (int index=startIndexForComputingResult;index<endIndexForComputingResult;index++){
            if(index>resultPolynomial.getCoefficientsSize()){
                //index is too big for degree of the result
                return;
            }
            computeIndexOfTheResultPolynomial(index);
            System.out.println("Task number "+id+" finished calculation for index "+index+"\n");
        }
    }



}

