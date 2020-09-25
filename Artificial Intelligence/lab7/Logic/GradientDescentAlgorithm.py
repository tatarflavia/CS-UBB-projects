import math
from numpy import *


class GradientDescentAlgorithm(object):
    def __init__(self,xValues,yValues):
        self.functionCoefficients=[0,0,0,0,0,0] #we have 6 coefficients for the f function which is
                                                #f=c1*x^5 + c2*x^4 + c3*x^3 + c4*x^2 + c5*x + c6
        self.xValues=xValues
        self.yValues=yValues
        self.m=6 #len of coeffs
        self.learningRate=0.0003
        self.error=100   #sum of errors
        #self.functionFoundTillNow=[0,0,0,0,0,0] #coefficients of best found function
        self.currentTrial=0


    def computeErrorForCurrentCoefficients(self):
        #calculate the current error and change the attribute
        #take all points from database and sum of squares of errors for every row from the data
        totalError=0
        for i in range(len(self.yValues)):
            outputForRow=0
            for j in range(self.m):
                if j<=4:
                    outputForRow+=self.functionCoefficients[j]*self.xValues[i][j]
                else:
                    outputForRow+=self.functionCoefficients[j]
            totalError+=(self.yValues[i]-outputForRow)**2

        return totalError/len(self.yValues)

    def getFunctionValForARow(self,i):
        #for row i returns the current function value
        outputForRow = 0
        for j in range(self.m):
            if j <= 4:
                outputForRow += self.functionCoefficients[j] * self.xValues[i][j]
            else:
                outputForRow += self.functionCoefficients[j]
        return outputForRow

    def changingTheCoefficients(self):
        #gradient descent method
        weightsModifiers = [0, 0, 0, 0, 0, 0]  # keeps the value with which we modify the coeffs after every row
        for i in range(len(self.yValues)):
            #for every row
            for j in range(self.m):
                #we take the gradients for every rowin order to modify after the weights
                if j<=4:
                    prev=weightsModifiers[j]
                    weightsModifiers[j]= prev + (-2)*(1/len(self.yValues))*(self.yValues[i]-self.getFunctionValForARow(i))*self.xValues[i][j]
                else:
                    prev = weightsModifiers[j]
                    weightsModifiers[j] = prev + (-2) * (1 / len(self.yValues)) * (self.yValues[i] - self.getFunctionValForARow(i))
        for i in range(self.m):
            prev_coeff=self.functionCoefficients[i]
            self.functionCoefficients[i]=prev_coeff-(self.learningRate*weightsModifiers[i])



    def main(self):
        while self.currentTrial<10000  and self.error>0.4:
            print("iteration number:"+str(self.currentTrial))
            print("current error:"+str(self.error))
            print()
            print()
            self.changingTheCoefficients()
            self.error = self.computeErrorForCurrentCoefficients()
            self.currentTrial+=1

        array=[]
        array.append(self.error)
        array.append(self.functionCoefficients)
        return array



