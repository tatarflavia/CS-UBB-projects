import numpy as np

#artificial neural network with one hidden layer and linear activation function

class NeuralNetwork(object):

    def __init__(self, x, y, hidden):
        self.input = x  #input matrix for the nn
        self.weightsInputHidden = np.random.rand(self.input.shape[1], hidden) #matrix(how many attributes on a row,how man hidden nodes)
                                                                              #  of weights between input and hidden layer
        self.weightsHiddenOutput = np.random.rand(hidden, 1) #matrix(how many hidden nodes,1) of weights
                                                                 #between hidden and output layer (1 because there is one output on a row in the db)
        self.y = y  #real desired output matrix from the db
        self.guessedOutput = np.zeros(self.y.shape)  #output guess matrix of the nn
        self.error = 100000 #best found error in the nn
        self.loss=[] #array that keeps all found errors



    #formulas:
        #outputHidd=f(W from input-hidden  * I)
        #guessedOut=f(W from hidden-output * outputHid)
    def feedforward(self):
        #function that computes the output for all rows of inputs by computing for each row an output
        #it goes through both weights between layers using them to process an output for each layer
        self.outputFromHiddenLayer = self.activationLinearFunction(np.dot(self.input,self.weightsInputHidden))
        self.guessedOutput = self.activationLinearFunction(np.dot(self.outputFromHiddenLayer, self.weightsHiddenOutput))





    #formulas:
        #delta W ho=l_rate* (outputHidd transposed * (real_out - guessed))
        #delta W ih=l_rate* (input transposed * errorsFromHiddenLayer))
                                                       #errorsFromHiddenLayer=(real out- guessed) * W ho
    def backpropagation(self, learningRate):
        #basically we calculate the delta for each weights matrices corresponing to the output error
        #then we change the weights matrices and change the best found error
        # application of the chain rule to find derivative of the
        # loss function with respect to weights2 and weights1
        d_weightsHiddenOutput = np.dot(self.outputFromHiddenLayer.T, (2 * (self.y - self.guessedOutput) *self.linearDerivative()))

        d_weightsInputHidden = np.dot(self.input.T,
                            (np.dot(2 * (self.y -self.guessedOutput) * self.linearDerivative(),self.weightsHiddenOutput.T) *self.linearDerivative()))

        # update the weights with the derivative (slope) of the loss function
        self.weightsInputHidden += learningRate * d_weightsInputHidden
        self.weightsHiddenOutput += learningRate * d_weightsHiddenOutput

        #update the error if necessary
        if self.error > sum((self.y - self.guessedOutput)**2)/497:
            self.error = sum((self.y - self.guessedOutput)**2)/497
        self.loss.append(sum((self.y - self.guessedOutput)**2)/497)

    # the activation function:
    def activationLinearFunction(self,x):
        return x


    # the derivate of the activation function
    def linearDerivative(self):
        return 1