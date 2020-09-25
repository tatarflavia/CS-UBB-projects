from Service.EvolutionaryAlgorithm import EvolutionaryAlgorithm
from Service.HillClimbingAlgorithm import HillClimbingAlgorithm
from Service.ParticleSwarmOptimisation import ParticleSwarmOptiAlgorithm
import matplotlib.pyplot as plt

class Controller(object):
    def __init__(self):
        pass

    def doEA(self, n,population,trials,mutation,crossover):
        ea=EvolutionaryAlgorithm(n,population,trials,mutation,crossover)
        return ea.mainForEvolutionaryAlg()

    def doHillAlg(self,n,nrOfTrials):
        hill=HillClimbingAlgorithm(n,nrOfTrials)
        res = hill.greedyHillClimbing()
        # while res==None:
        #     res = hill.greedyHillClimbing()
        return res

    def doPSO(self,n, noParticles, sizeOfNeighbourhood , w, c1, c2, nrOfTrials):
        pso=ParticleSwarmOptiAlgorithm(n, noParticles, sizeOfNeighbourhood , w, c1, c2, nrOfTrials)
        return pso.mainForPSO()

    def validationTest(self,n,mutation,crossover):
        std=[]
        mean=[] #avg
        #we apply 30 runs:
        for i in range(30):
            #keep the fitness of every state in every pop in a run
            ea = EvolutionaryAlgorithm(n, 40, 1000, mutation, crossover)
            std1,mean1=ea.validateTest()
            std.append(std1)
            mean.append(mean1)
            print(i)
        std,=plt.plot(std,color='m',label='Standard Deviation')
        mean,=plt.plot(mean,color='y',label='Average')
        plt.legend(handles=[std,mean])
        plt.show()

    def validationTestPSO(self,n,sizeOfNeighbourhood,w,c1,c2):
        std = []
        mean = []  # avg
        # we apply 30 runs:
        for i in range(15):
            # keep the fitness of every state in every pop in a run
            pso=ParticleSwarmOptiAlgorithm(n,40,sizeOfNeighbourhood,w,c1,c2,1000)
            std1, mean1 = pso.validateTest()
            std.append(std1)
            mean.append(mean1)
            print(i)
        std, = plt.plot(std, color='m', label='Standard Deviation')
        mean, = plt.plot(mean, color='y', label='Average')
        plt.legend(handles=[std, mean])
        plt.show()






