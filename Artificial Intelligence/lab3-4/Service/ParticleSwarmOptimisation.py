import numpy as np
from Domain.Particle import Particle
from copy import deepcopy


class ParticleSwarmOptiAlgorithm(object):
    def __init__(self, n, noParticles, sizeOfNeighbourhood , w, c1, c2, nrOfTrials):
        self.inertialCoeffW=w
        self.cognitiveFactorC1=c1
        self.socialFactorC2=c2
        self.n=n
        self.nrOfStates=noParticles
        self.sizeOfNeighbourhood=sizeOfNeighbourhood
        self.population=[]
        self.trials=nrOfTrials
        for i in range(self.nrOfStates):
            self.population.append(self.getInitialIndivid()) # a list of state objects
        #we establish the particles' neighbors
        self.neighbours=self.selectNeighbors()

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
        individ=Particle(state,self.n)
        return individ

    def selectNeighbors(self):
        """  the selection of the neighbours for each particle
        input --
           pop: current population
           nSize: the number of neighbours of a particle
        output--
           ln: list of neighblours for each particle
        """
        if(self.sizeOfNeighbourhood==len(self.population)):
            neighbors = []
            for i in range(len(self.population)): #every particle in the swarm
                localNeighbor = []
                 #we want to create neighbours for the particle
                x=np.random.permutation(len(self.population)) #we add the whole pop as positions in the neighbour
                localNeighbor.append(x)
                neighbors.append(localNeighbor.deepcopy())
            return neighbors

        if (self.sizeOfNeighbourhood > len(self.population)):
            self.sizeOfNeighbourhood = len(self.population)

        # aici faci in loc de randint si while direct o permutare din pozitiile din pop
        # x=append(permutation(popSize))
        # vecinii se parcurg in ordinea scrisa si alegi cel mai bun fitness ca sa iti dea best neighbour
        # si se tin minte pozitiile din populatie din moment ce nu se schimba populatia pt memory purpose
        neighbors = []
        for i in range(len(self.population)):
            localNeighbor = []
            for j in range(self.sizeOfNeighbourhood):
                x = np.random.randint(0, len(self.population) - 1)
                while x in localNeighbor:
                    x = np.random.randint(0, len(self.population) - 1)
                localNeighbor.append(x)
            neighbors.append(localNeighbor.copy())
        return neighbors

    def iteration(self,w):
        """
        an iteration
        pop: the current state of the population
        for each particle we update the velocity and the position
        according to the particle's memory and the best neighbor's pozition
        """
        pop=self.population
        bestNeighbors = []
        # determine the best neighbor for each particle
        for i in range(len(pop)):
            bestNeighbors.append(self.neighbours[i][0])
            for j in range(1, len(self.neighbours[i])):
                if (pop[bestNeighbors[i]].fitness < pop[self.neighbours[i][j]].fitness):
                    bestNeighbors[i] = self.neighbours[i][j]

        # update the velocity for each particle
        #aici vine modificat sa mearga cu permutari efectiv le scazi pe elemente
        for i in range(len(pop)):
            for j in range(len(pop[0].get_velocity())):
                vel_vals=pop[i].get_velocity()
                newVelocity=[0]*self.n
                #now work on permutations elements
                for k in range(self.n):
                    newVelocity[k] = w * vel_vals[j][k]
                    newVelocity[k] = newVelocity[k] + self.cognitiveFactorC1 * np.random.random() * (pop[bestNeighbors[i]].matrixValues[j][k] - pop[i].matrixValues[j][k])
                    newVelocity[k] = newVelocity[k] + self.socialFactorC2 * np.random.random() * (pop[i].bestPozition[j][k] - pop[i].matrixValues[j][k])
                vel_vals[j]=newVelocity
                pop[i].set_velocity(vel_vals)

        #cod prof:\
        # for i in range(len(pop)):  # for each particle
        #     for j in range(len(pop[0].velocity)):  # for each velocity / permutation change velocity
        #         newVelocity = w * pop[i].velocity[j]
        #         newVelocity = newVelocity + c1 * random() * (pop[bestNeighbors[i]].pozition[j] - pop[i].pozition[j])
        #         newVelocity = newVelocity + c2 * random() * (pop[i].bestPozition[j] - pop[i].pozition[j])
        #         pop[i].velocity[j] = newVelocity


        # update the pozition for each particle
        # pos=matrice la noi si trebuie schimbat ,velocity la fel
        for i in range(len(pop)):
            newPozition = []
            for j in range(len(pop[0].get_velocity())):
                newPermutation = [0] * self.n
                for k in range(self.n):
                    newPermutation[k]=pop[i].matrixValues[j][k]+pop[i].get_velocity()[j][k]
                newPozition.append(tuple(newPermutation))
            newPart=Particle(newPozition,self.n)
            pop[i].set_matrixValues(newPozition)
        return pop

    def mainForPSO(self):
        for i in range(self.trials):
            self.population = self.iteration(self.inertialCoeffW / (i + 1))
        # print the best individual
        best = 0
        for i in range(1, len(self.population)):
            if (self.population[i].fitness > self.population[best].fitness):
                best = i
        print("solution:\n"+str(self.population[best]))
        return self.population[best]

        # fitnessOptim = P[best].fitness
        # individualOptim = P[best].pozition
        # print('Result: The detectet minimum point is (%3.2f %3.2f) \n with function\'s value %3.2f' % \
        #       (individualOptim[0], individualOptim[1], fitnessOptim))

    def validateTest(self):
        #1000 trials, for each population we do fitness and then at the end findout the mean and std deviation
        res=[]
        for i in range(self.trials):
         #we have 1000 trials => 1000 generations
            self.population=self.iteration(self.inertialCoeffW / (i + 1))
            #compute mean and deviation
            for state in self.population:
                res.append(state.fitness)

        return (np.std(res),np.mean(res))
