class State(object):
    #holds a matrix configuration
    def __init__(self, matrixValues):
        self.matrixValues = matrixValues #a matrix of nXn

    def get_vals(self):
        return self.matrixValues

    def addAValToMatrix(self,i,j):
        #puts 1 on pos
        self.matrixValues[i-1][j-1]=1

    def __str__(self):
        stringRepr=""
        for line in self.matrixValues:
            stringRepr += str(line)
            stringRepr += "\n"
        return stringRepr

    def __eq__(self, otherMatrix):
        otherMatrixVals=otherMatrix.get_vals()
        for lin in range(len(self.matrixValues)):
            for col in range(len(self.matrixValues)):
                if self.matrixValues[lin][col] != otherMatrixVals[lin][col]:
                    return False
        return True
