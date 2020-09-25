import numpy as np
from Domain.State import State
from Domain.Ant import ant
from math import *

class Controller(object):
    def __init__(self , n,populationSize,trials,numberOfAnts,alpha, beta, q0, rho):
        self.population = [] #a list of states (table for the ants to move about)
        self.n = n  #dimensions for the matrix
        self.current_generation = 0
        self.trials=trials
        self.nrOfStates=int(sqrt(populationSize))
        self.numberOfAnts=numberOfAnts
        self.alpha=alpha # controls the trail importance (how many ants have visited that edge)
        self.beta=beta # controls the visibility importance (how close is the next city)
        self.q0=q0 #pheromone degration coeff
        self.rho=rho # evaporation coefficient of pheromone trail
        #basically for every individ in the pop there is a matrix which shows the pheromon trail between this individ and all the others to help in choosing a path
        self.traceForAnts = [[1 for i in range(self.nrOfStates*self.nrOfStates)] for j in range(self.nrOfStates*self.nrOfStates)]
        for i in range(self.nrOfStates):
            line=[]
            for j in range(self.nrOfStates):
                line.append(self.getInitialIndivid()) # a list of state objects
            self.population.append(line)



    def getInitialIndivid(self):
        #gets an individ aka a matrix (everytime a different one)
        state = []
        for i in range(2*self.n): #we will have 2*n permutations
            permutation = np.random.permutation(self.n)
            for j in range(len(permutation)):
                #we just add 1 to be like in the example
                permutation[j]+=1
            permutation=tuple(permutation)
            state.append(permutation)
        individ=State(state,self.n)
        return individ




    def iterationForACO(self):
        #make a different population of ants at each iteration
        antPopulation = [ant(self.nrOfStates, self.n, self.population) for i in range(self.numberOfAnts)]

        #next we make the ants move on the board: every ant makes (len(pop)) moves
        for i in range(self.nrOfStates * self.nrOfStates):
            for x in antPopulation:
                x.addMove(self.traceForAnts, self.alpha, self.beta)

        # update the trace with all pheromons from ants which keeps all the paths for every individ in the states population
        dTrace = [1.0 / antPopulation[i].fitness() for i in range(len(antPopulation))]
        for i in range(self.nrOfStates * self.nrOfStates):
            for j in range(self.nrOfStates * self.nrOfStates):
                self.traceForAnts[i][j] = (1 - self.rho) * self.traceForAnts[i][j] #formula
        for i in range(len(antPopulation)):
            for j in range(len(antPopulation[i].path) - 1):  #x and y are individe and we update their respective trace place
                x = antPopulation[i].path[j]
                y = antPopulation[i].path[j + 1]
                self.traceForAnts[x][y] = self.traceForAnts[x][y] + dTrace[i]

        # return best ant that attempts to reach the solution: max from all the ants fitness
        bestAnt = [[antPopulation[i].fitness(), i] for i in range(len(antPopulation))]
        bestAnt = max(bestAnt)
        # return best ant path in which our euler square sol may be
        return antPopulation[bestAnt[1]].path

    def mainForACO(self):
        #main function, which calls iteration a number of trials times
        #takes the longest path(which has all individs in the population) as the best solution, then looks in it for the solution to the euler square problem
        bestSol = []

        for i in range(self.trials):
            sol = self.iterationForACO().copy()
            if len(sol) > len(bestSol):
                bestSol = sol.copy()

        maxFit=0
        eulerSquare=[]
        #sol for euler square
        for solutie in bestSol:
            fit=self.population[solutie//self.nrOfStates][solutie%self.nrOfStates].fitnessFunction()
            if fit>maxFit:
                maxFit=fit
                eulerSquare=self.population[solutie//self.nrOfStates][solutie%self.nrOfStates]

        print("Solution for euler square is:\n"+str(eulerSquare))



    def validateTest(self):
        #1000 trials, for each path given as a intermidiate solution we do fitness for each individ
        # and then at the end findout the mean and std deviation from these results
        res=[]
        while self.current_generation!=self.trials: #we have 1000 trials => 1000 generations
            sol=self.iterationForACO().copy()
            #compute mean and deviation
            for state in sol:
                res.append(self.population[state//self.nrOfStates][state%self.nrOfStates].fitnessFunction())
            self.current_generation+=1
        return (np.std(res),np.mean(res))







