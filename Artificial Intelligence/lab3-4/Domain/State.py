class State(object):
    # holds a matrix configuration
    def __init__(self, matrixValues):
        self.matrixValues = matrixValues  # a matrix of nXn

    def get_vals(self):
        return self.matrixValues

    def set_vals(self,v):
        self.matrixValues=v

    def __str__(self):
        stringRepr = ""
        n=len(self.matrixValues)//2
        for i in range(n):
            j=n+i #it takes the second member of the pair
            s1=""
            for k in range(n):
                s1 += "("+str(self.matrixValues[i][k])+","+str(self.matrixValues[j][k])+")"
            s1 += "\n"
            stringRepr += s1
        return stringRepr

    def __eq__(self, otherMatrix):
        if otherMatrix==None:
            return False

        otherMatrixVals = otherMatrix.get_vals()

        for i in range(len(self.matrixValues)):
                if self.matrixValues[i] != otherMatrixVals[i]:
                    return False
        return True