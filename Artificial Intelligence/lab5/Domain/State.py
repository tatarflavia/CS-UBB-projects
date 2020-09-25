class State(object):
    # holds a matrix configuration
    def __init__(self, matrixValues,n):
        self.matrixValues = matrixValues  # a matrix of nXn: a list of permutations
        self.n=n
        self.mutationFactor = (n * (n - 1)) * n
    def get_vals(self):
        return self.matrixValues

    def set_vals(self,v):
        self.matrixValues=v

    def fitnessFunction(self):

        #ret a number: the bigger, the closer you are to the solution
        #aka it checks the permutations in the state and gives a result based on how close to the sol is this state
        state_vals=self.matrixValues
        version1=0 #it would be perfect to remain 0 for it to be a sol

        #we check the lines first
        for i in range(self.n):
            l=[]
            for j in range(self.n):
                l.append(state_vals[i][j]) #put the permutations from lines in l
            #get how many perms are the same: if 0 => all unique => very close to sol
            version1+=len(l)-len(set(l)) #set holds only unique perm from l

        #we check the columns second
        version2=0
        for i in range(self.n):
            l=[]
            for j in range(self.n,self.n*2):
                l.append(state_vals[j][i]) #perms from columns in l
            version2+=len(l)-len(set(l))

        #lastly we check that all pairs of permutations are different from one another no matter the position
        version3=0
        l=[]
        for i in range(self.n):
            j=self.n+i #this is how we get the second part of the pair (i,j) from state
            for k in range(self.n):
                l.append((state_vals[i][k],state_vals[j][k])) #here we add pairs of permutations from the state
        version3+=len(l)-len(set(l))

        #now return a number

        if self.mutationFactor-version3-version2-version1==0 or self.mutationFactor-version3-version2-version1==1:
            return 10
        return self.mutationFactor-version3-version2-version1

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