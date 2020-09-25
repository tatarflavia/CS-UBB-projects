from Service.PracticeService import DecisionTreeClass
from numpy import random
class Controller(object):
    def __init__(self,practicePercentage):
        self.practiceNrRows=int(practicePercentage/100*625)
        self.testingNrRows=625-self.practiceNrRows
        self.listOfDataForPracticing=[]
        self.listOfDataForTesting=[]
        self.readFromFile()

    def readFromFile(self):
        file1 = open('E:\\School\\inteligenta artificiala an 2 sem 2\\lab6\\tema6\\balance-scale.data', 'r')
        Lines = file1.readlines()
        bigArray=[]
        count = 0
        for line in Lines:
            rowInts=line.strip().split(",")
            array=[]
            array.append(str(rowInts[0]))
            for i in range(1,5):
                array.append(int(rowInts[i]))
            bigArray.append(array)
            count+=1
        random.shuffle(bigArray)
        for i in range(self.practiceNrRows):
            self.listOfDataForPracticing.append(bigArray[i])
        for i in range(self.practiceNrRows,625):
            self.listOfDataForTesting.append(bigArray[i])


    def decisionTreeFunction(self):
        #calls the decision tree making function and the testing function
        decisionTree=DecisionTreeClass(self.listOfDataForPracticing,self.listOfDataForTesting)
        decisionTree.mainForMakingDecisionTree()
        return decisionTree.mainForTestingThedata()


