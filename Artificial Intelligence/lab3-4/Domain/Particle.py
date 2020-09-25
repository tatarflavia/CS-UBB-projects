from random import randint, random
from copy import deepcopy

class Particle(object):
    """ The class that implements a particle aka a matrix of permutations"""

    def __init__(self,matrixValues,n):
        """ constructor

        input--
          l: the number of components
          n:the max val
          1:the min val
        """
        self.n=n
        self.factor=(n*(n-1))*2
        self._matrixValues = matrixValues
        self.evaluate()
        self.velocity=[]
        for i in range(self.n*2):
            local_vel=[]
            for i in range(self.n):
                local_vel.append(0)
            self.velocity.append(local_vel)
        #self.velocity = [0 for i in range(self.n*2)] #tot state si asta:for every permutation in the matrix:an int

        # the memory of that particle
        self._bestPozition = self._matrixValues.copy()
        self._bestFitness = self._fitness

    def fitnessFunction(self,state):
        #ret a number: the bigger, the closer you are to the solution
        #aka it checks the permutations in the state and gives a result based on how close to the sol is this state
        state_vals=[]
        for i in range(len(state)):
            local=[]
            for k in range(self.n):
                local.append(int(state[i][k]))
            state_vals.append(local)


        #check if it is in range:
        ok=0
        for i in range(len(state)):
            for k in range(self.n):
                if state[i][k] not in range(1,self.n+1):
                    ok=1
                    break
        if ok==1:
            return 0

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
        return self.factor-version3-version2-version1

    # def fit(self, pozition):
    #     """
    #     Determine the fitness of a particle. Lower is better.(min problem)
    #     For this problem we have the Schaffer's function
    #
    #     input --
    #         pozition: the pozition of the particle we wish to evaluate
    #     """
    #     n = len(pozition)
    #     f = 0
    #     for i in range(n - 1):
    #         y = pow(pozition[i], 2) + pow(pozition[i + 1], 2)
    #         f = f + pow(y, 0.25) * (pow(sin(50 * pow(y, 0.1)), 2) + 1)
    #     return f

    def get_velocity(self):
        return self.velocity

    def set_velocity(self,newVel):
        self.velocity=newVel

    def evaluate(self):
        """ evaluates the particle """
        self._fitness = self.fitnessFunction(self._matrixValues)

    @property
    def matrixValues(self):
        """ getter for values """
        return self._matrixValues

    @property
    def fitness(self):
        """ getter for fitness """
        return self._fitness

    @property
    def bestPozition(self):
        """ getter for best pozition """
        return self._bestPozition

    @property
    def bestFitness(self):
        """getter for best fitness """
        return self._bestFitness

    def set_matrixValues(self, newPozition):
        self._matrixValues = newPozition.copy()
        # automatic evaluation of particle's fitness
        self.evaluate()
        # automatic update of particle's memory
        if (self._fitness > self._bestFitness):
            self._bestPozition = self._matrixValues
            self._bestFitness = self._fitness

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

