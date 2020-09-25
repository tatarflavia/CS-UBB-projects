import string
from random import random, choice

from Model.chromosome import Chromosome, mutation, crossover, outputClassesList


class Population:
    # we have a population of chromosomes
    def __init__(self, trainingData, output, populationSize=100):
        self.trainingData=trainingData
        self.output=output
        self.population=[Chromosome() for i in range(populationSize)]
        self.populationSize=populationSize
        for chromosome in self.population:
            chromosome.growExpression()

    def train(self)->float:
        #create new chromosomes and then choose best ones to make the gp steps with
        newChromosomes=[]
        for i in range(self.populationSize):
            if random()<0.5:
                newChromosomes.append(mutation(choice(self.population)))
            else:
                newChromosomes.append(crossover(choice(self.population),choice(self.population)))
        self.population+=newChromosomes

        for chromosome in self.population:
            chromosome.computeFitness(self.trainingData,self.output)

        #after creating new population we calculate the new error and return it
        fitnessEvaluation=[(chromosome,chromosome.fitness) for chromosome in self.population]
        fitnessEvaluation=sorted(fitnessEvaluation,key=lambda x:x[1])
        self.population=[x[0]for x in fitnessEvaluation[:self.populationSize]]
        #returns the error for this population
        return fitnessEvaluation[0][1]/len(self.trainingData)

    def predict(self,inputData)->string:
        #you get some input sensors and it returns the direction the robot has to take
        #it returns the evaluated expression from the tree of the best chromosome
        fitnessEvaluation=[(chromosome,chromosome.fitness)for chromosome in self.population]
        fitnessEvaluation=sorted(fitnessEvaluation,key=lambda x:x[1])
        bestChromosome=fitnessEvaluation[0][0]
        output=int(round(bestChromosome.evaluateExpression(0,inputData)[0]))
        if output>3:
            return 'No class chosen'
        return outputClassesList[output]
