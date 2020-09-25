

class Console(object):
    def __init__(self,ctrl):
        self.ctrl=ctrl


    def menu(self):
        print("The menu of options is:")
        print("1.Solve a simple regression problem- using an ANN with a hidden layer and linear activation function.")
        print("0.Exit the application.")


    def runApp(self):
        while True:
            self.menu()
            try:
                choice = int(input("Please choose an option from the menu:"))
                if choice == 1:
                    self.neuralNetwork()
                    print()
                    print()
                elif choice == 0:
                    break
                else:
                    print("Unrecognized.")
            except ValueError:
                print("Try a number!")


    def neuralNetwork(self):
        nrOfTrials=int(input("Please give the nr of trials:"))
        learningRate=float(input("Please give the learning rate:")) #0.00000001
        array=self.ctrl.doTheIterationsFunction(nrOfTrials,learningRate)
        best_error=array[1]
        guessedOutputs=array[0]
        print("The outputs found by the neural network are:  " + str(guessedOutputs) )
        print("Best found error is:"+str(best_error))





