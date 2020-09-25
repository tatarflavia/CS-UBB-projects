from random import *
import numpy as np
from Domain.State import State

class ant(object):
    def __init__(self, n,dimIndivid,population):
        # constructor pentru clasa ant
        self.n = n #sqrt from size of population because we needed a matrix for the whole pop of individs
        self.dimIndividual = dimIndivid #how big is a matrix for an individ
        self.size = n * n #the size of the table in which this ant is searching aka the whole population
        self.path = [randint(0, self.size - 1)] #choose random an individ to where the path will lead in the start
        self.pop=population #reference to the population of individs






    def nextValidMovesForTheAnt(self):
        # returns a list of unused positions from the big table in its path:if not in path=> append pos to res
        new = []
        for i in range(self.size):
            if (i not in self.path):
                new.append(i)
        return new.copy()

    def distanceMoveScenario(self, a):
        #calculates the distance where it wants to go to depending on the possible moves after a is put in the path
        #it 'adds' a in path and sees whats the distance to the finish point, only a scenario
        scenarioAnt = ant(self.n,self.dimIndividual,self.pop)
        scenarioAnt.path = self.path.copy()
        scenarioAnt.path.append(a)
        return (self.size - len(scenarioAnt.nextValidMovesForTheAnt()))

    def addMove(self, trace, alpha, beta):
        #we search for the best position to add in the path if possible
        pozitionArray = [0 for i in range(self.size)]

        #next we take valid next moves and if 0 is the number of them => return
        nextValidStepsForTheAnt = self.nextValidMovesForTheAnt().copy()
        if (len(nextValidStepsForTheAnt) == 0):
            return False

        #next we add scenarios for every valid position: to see the distance to the finish would be if 'adding' that to path
        for pos in nextValidStepsForTheAnt:
            pozitionArray[pos] = self.distanceMoveScenario(pos)

        #next we calculate the formula trace^alpha * empirical(vizibility)^beta
        pozitionArray = [(pozitionArray[i] ** beta) * (trace[self.path[-1]][i] ** alpha) for i in range(len(pozitionArray))]

        #next we take best next pos from the position array and put it into the path to make a move
        pozitionArray = [[i, pozitionArray[i]] for i in range(len(pozitionArray))]
        pozitionArray = max(pozitionArray, key=lambda a: a[1]) #take the best
        self.path.append(pozitionArray[0])


    def fitness(self):
        #it takes the maximum fitness of an individ from its path
        maxOfFit=self.pop[self.path[0]//self.n][int(self.path[0]%self.n)].fitnessFunction()
        for pat in self.path:
            fit=self.pop[pat//self.n][int(pat%self.n)].fitnessFunction()
            if fit>maxOfFit:
                maxOfFit=fit
        return maxOfFit






































    def transformMatrixToPairs(self, matrix):
        matrixVals = matrix.get_vals()
        n = len(matrixVals) // 2
        newOne = []
        for i in range(n):
            l = []
            j = n + i  # it takes the second member of the pair
            for k in range(n):
                l.append([matrixVals[i][k], matrixVals[j][k]])
            newOne.append(l)
        return newOne

    def transformMatrixToPermutations(self, matrixVals):
        newOne = []
        for i in range(self.n):
            perm1 = []
            for j in range(self.n):
                perm1.append(matrixVals[i][j][0])
            newOne.append(tuple(perm1))
        for i in range(self.n):
            perm1 = []
            for j in range(self.n):
                perm1.append(matrixVals[i][j][1])
            newOne.append(tuple(perm1))
        return newOne