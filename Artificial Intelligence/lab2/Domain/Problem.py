from copy import deepcopy
from Domain.State import State

class Problem(object):
    def __init__(self):
        self.values=None
        self.initialStateOfProblem=None
        self.finalStateOfProblem=None

    def getInitialStateOfProblem(self):
        return self.initialStateOfProblem

    def setInitialStateOfProblem(self,initialState):
        self.initialStateOfProblem=initialState


    def expandNode(self, currentState):
        #returns a list of child nodes:
        ## basically we observe where in the currentState there are empty pos and then one by one we put a 1 there
        positionsWhereEmpty=[]
        currentVals=currentState.get_vals()
        numberOf1=0
        for i in range(len(currentVals)):
            for j in range(len(currentVals[i])):
                if currentVals[i][j] == 0:
                    positionsWhereEmpty.append((i, j))  #keeps positions where empty: 0
                else:
                    numberOf1 += 1 #it keeps the number of ones
        if numberOf1 >= len(currentVals): #too many ones=> not ok =>can't go further
            return []

        childList=[]
        for coordinates in positionsWhereEmpty:
            auxState = deepcopy(currentState)  # copy the parent matrix to make a child
            auxState.addAValToMatrix(coordinates[0],coordinates[1])  # practic peste tot unde a fost un 0,pune pe rand cate un 1
            childList.append(auxState)  # put the child in the list
        return childList


    def heuristicFunction(self,stateOfProblem):
        # tells us how close stateOfProb is to the sol:how many empty positions you have,the smaller, the close you are to the sol
        # h(x) = Estimate of distance of node x from the goal node.
        # Lower the value of h(x), closer is the node from the goal.
        numberOfEmptyPositions=0
        if self.checksIfPotential(stateOfProblem) == False:
            return 100  #if it's not potential return a big number=big heurestic=far from the solution
        stateOfProblemVals = stateOfProblem.get_vals()
        for lin in stateOfProblemVals:   #i takes rows
            for val in lin: #j takes the values from the rows
                if val == 0:
                    numberOfEmptyPositions += 1 #result is = number of empty positions, so that the smaller the better, it means you don't have to search so much more
        return numberOfEmptyPositions

    def checksIfPotential(self,stateOfProb):
        # check if this state is a potential sol so far:it checks that the sum is no more than 1 on every row and every col
        matrixVals=stateOfProb.get_vals()
        # checks if the sum of every row is more than 1, which means more than a queen per row => can't be a sol
        for line in matrixVals:
            if sum(line) > 1:
                return False  # sum on rows
        # checks for columns the same thing
        for col in range(len(matrixVals)):
            sumOfCol = 0
            for lin in range(len(matrixVals)):
                sumOfCol += matrixVals[lin][col]
            if sumOfCol > 1:
                return False  # sum on columns
        return True

    def checksIfSolutionNode(self, stateOfProblem):
        #boolean : checks if the stateOfProb contains a solution matrix:if the sum on every row, every col, every diag is 1
        matrixVals=stateOfProblem.get_vals()
        for line in matrixVals:
            if sum(line) != 1:
                return False  # sum on rows
        for col in range(len(matrixVals)):
            sumOfCol = 0
            for lin in range(len(matrixVals)):
                sumOfCol += matrixVals[lin][col]
            if sumOfCol != 1:
                return False  # sum on columns

        # check all the diagonals:
        for i in range(len(matrixVals) - 1):
            for j in range(len(matrixVals) - 1):
                for k in range(len(matrixVals)):
                    for l in range(len(matrixVals)):
                        if abs(i - k) - abs(j - l) == 0 and (i != k or j != l):
                            if matrixVals[i][j] == 1 and matrixVals[k][l]:
                                return False

        return True



    def readFromFile(self):
        file = open("numberOfLines.txt")
        numberOfLinesInMatrix = int(file.readline())
        file.close()
        return numberOfLinesInMatrix




