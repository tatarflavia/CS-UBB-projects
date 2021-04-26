package Domain;

import java.util.ArrayList;
import java.util.List;

public class Polynomial {
    //here we hold the coefficients of the polynomial, from x^0 to... x^degree of pol
    private List<Integer> coefficients;

    private int getRandomInt(int min, int max) {
        return (int)(((Math.random() * (max - min)) + min));
    }

    public Polynomial(List<Integer> coefficients) {
        this.coefficients = coefficients;
    }

    public Polynomial(int degreeOfNewPolynomial) {
        coefficients=new ArrayList<>();
        //we have degree+1 coeffs

        for(int i=0;i<degreeOfNewPolynomial;i++){
            coefficients.add(getRandomInt(0,10));
        }
        //then add a non zero coeff for power of x=degree
        coefficients.add(getRandomInt(1,10));
    }


    public List<Integer> getCoefficients() {
        return coefficients;
    }

    //degree as in the biggest power of last x in the polynomial
    public int getDegree(){
        return coefficients.size()-1;
    }

    public int getCoefficientsSize(){
        return coefficients.size();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(coefficients.get(0)).append(" + ");
        for(int i=1;i<getDegree();i++){
            stringBuilder.append(coefficients.get(i)).append("*x^").append(i).append(" + ");
        }
        stringBuilder.append(coefficients.get(getCoefficientsSize()-1)).append("x^").append(getDegree());
        return stringBuilder.toString();
    }


}
