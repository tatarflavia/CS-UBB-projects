from math import sin, cos
from random import random, randint

# define max depth of a tree
max_depth = 5
# define nb of ultrasonic sensors
problemTerminalNumber = 24
# we define the constants that will be used in the tree:
constantTerminals = [0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1]

# we define the functions that will be used in the tree:
functions = ['+', '-', '*', 'sin', 'cos']

# we define the way of interpretations for the functions
functionImplementations = {
    '+': lambda child1, child2: child1+child2,
    '-': lambda child1, child2: child1-child2,
    '*': lambda child1, child2: child1*child2,
    'sin': lambda child1, child2: sin(child1),
    'cos': lambda child1, child2: cos(child1)
}

# define number of functions from above
functionNumber = 5

# define the output classes(directions) and how to interpret them into a dict
outputClasses = {
    'Slight-Left-Turn': 0,
    'Move-Forward': 1,
    'Slight-Right-Turn': 2,
    'Sharp-Right-Turn': 3
}
# list of output(directions)
outputClassesList = ['Slight-Left-Turn', 'Move-Forward', 'Slight-Right-Turn', 'Sharp-Right-Turn']


class Chromosome:
    # this class holds a chromosome representation aka a tree inside

    def __init__(self, deph=max_depth):
        # depth and representation of the tree
        self.depth = deph
        self.representation = [0 for i in range(2**(self.depth+1)-1)]
        self.fitness = 0
        self.size = 0

    def growExpression(self, position=0, depth=0):
        #it grows the expression from the tree
        if((position==0) or (depth<self.depth)) and random()<0.5:
            self.representation[position] = -randint(1,functionNumber)
            firstChildEnd = self.growExpression(position+1,depth+1)
            secondChildEnd = self.growExpression(firstChildEnd,depth+1)
            return secondChildEnd
        else:
            self.representation[position] = randint(1,problemTerminalNumber+len(constantTerminals))
            self.size = position+1
            return position+1

    def evaluateExpression(self,position,trainData):
        #it returns the output (0-3) of the evaluated tree expression
        node = self.representation[position]
        if node>0: #a terminal
            if node<=problemTerminalNumber:
                return trainData[node-1],position
            else:
                return constantTerminals[node-problemTerminalNumber-1],position
        elif node<0: #a function
            function = functions[-node-1]
            firstChild = self.evaluateExpression(position+1,trainData)
            secondChild = self.evaluateExpression(firstChild[1]+1,trainData)
            return functionImplementations[function](firstChild[0],secondChild[0]),secondChild[1]

    def computeFitness(self,trainData,output):
        #we want to see the error as to not have a wrong chromosome
        error=0.0
        for i in range(len(trainData)):
            currentError = abs(outputClasses[output[i]]-self.evaluateExpression(0,trainData[i])[0])
            error+=currentError
        self.fitness=error

    def traverse(self,position):
        # function for traversing the tree inside the chromosome
        if self.representation[position]>0: #terminal
            return position+1
        else:
            return self.traverse(self.traverse(position+1))

def crossover(parent1,parent2):
    #it returns a child after crossing the parents
    #it crosses them by swappng 2 branches from the 2 trees from the parents and then returning the child
    child=Chromosome()
    while True:
        startParent1=randint(0,parent1.size-1)
        endParent1=parent1.traverse(startParent1)
        startParent2=randint(0,parent2.size-1)
        endParent2=parent2.traverse(startParent2)
        if len(child.representation)>endParent1+(endParent2-startParent2-1)+(parent1.size-endParent1-1):
            break
    i=-1
    for i in range(startParent1):
        child.representation[i]=parent1.representation[i]
    for j in range(startParent2,endParent2):
        i=i+1
        child.representation[i]=parent2.representation[j]
    for j in range(endParent1,parent1.size):
        i=i+1
        child.representation[i]=parent1.representation[j]
    child.size=i+1
    return  child

def mutation(chromosome):
    #it adds a mutation and then returns the new chromosome
    result=Chromosome()
    position=randint(0,chromosome.size-1)
    result.representation=chromosome.representation[:]
    result.size=chromosome.size
    if result.representation[position]>0: #terminal
        result.representation[position]=randint(1,problemTerminalNumber+len(constantTerminals))
    else: #function
        result.representation[position]=-randint(1,functionNumber)
    return result
