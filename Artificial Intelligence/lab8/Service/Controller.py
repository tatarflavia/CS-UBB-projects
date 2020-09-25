import numpy as np
import matplotlib.pyplot as mpl
from Logic.ArtificialNeuralNetwork import NeuralNetwork


class Controller(object):
    def __init__(self,repo):
        self.repo=repo

    def doTheIterationsFunction(self,nrOfTrials,learningRate,):
        x = np.array(self.repo.getXValues()) #x values set as a matrix
        y = np.array(self.repo.getYValues()) #y values set as a matrix
        neuralNetwork = NeuralNetwork(x, y, 7)

        #starts the number of trials and training the neural network
        iterations = []
        for i in range(nrOfTrials):
            neuralNetwork.feedforward()
            neuralNetwork.backpropagation(learningRate)
            iterations.append(i)

        #plot of the whole training process
        mpl.plot(iterations, neuralNetwork.loss, label='loss value vs iteration')
        mpl.xlabel('Iterations')
        mpl.ylabel('loss function')
        mpl.legend()
        mpl.show()

        arrayOfResults=[]
        arrayOfResults.append(neuralNetwork.guessedOutput)
        arrayOfResults.append(neuralNetwork.error)
        return arrayOfResults
