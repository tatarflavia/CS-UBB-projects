from Controller.controller import Population
from Repository.fileOperations import *

epsilon = 0.6

if __name__ == '__main__':
    # first train the data until the error is less than epsilon
    (trainingData, output) = readTrainingData()
    population = Population(trainingData,output)

    i = 1
    while True:
        error = population.train()
        #print("Trial number: "+str(i))
        i += 1
        if error < epsilon:
            break

    # then test the trained robot and print for every row of sensors the guessed output to be compared to the real one
    testData = readTestData()
    for i in range(len(testData)):
        output = population.predict(testData[i])
        print(output)