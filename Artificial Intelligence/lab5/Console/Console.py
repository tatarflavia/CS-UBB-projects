from Service.Controller import *
import matplotlib.pyplot as plt

class Console(object):
    def __init__(self):
        pass

    def menu(self):
        print("The menu of options is:")
        print("1.Ant Colony optimization Algorithm for euler square problem.")
        print("2.Validation tests.")
        print("0.Exit the application.")

    def ACO(self):
        n=int(input("Give the matrix dimensions please:"))
        populationSize=int(input("Give the population size please:"))
        trials=int(input("Give the number of trials please:"))
        numberOfAnts=int(input("Give the number of ants please:"))
        alpha=float(input("Give the alpha please:"))
        beta=float(input("Give the beta please:"))
        q0=float(input("Give the q0 please:"))
        rho=float(input("Give the density value please:"))
        ctrl = Controller(n,populationSize,trials,numberOfAnts,alpha, beta, q0, rho)
        ctrl.mainForACO()

    def validate(self):
        n = int(input("Give the matrix dimensions please:"))
        numberOfAnts = int(input("Give the number of ants please:"))
        alpha = float(input("Give the alpha please:"))
        beta = float(input("Give the beta please:"))
        q0 = float(input("Give the q0 please:"))
        rho = float(input("Give the density value please:"))
        self.validationTest(n,numberOfAnts,alpha, beta, q0, rho)

    def runApp(self):
        while True:
            self.menu()
            try:
                choice=int(input("Please choose an option for searching:"))
                if choice==1:
                    self.ACO()
                elif choice==2:
                    self.validate()
                elif choice==0:
                    break
                else :
                    print("Unrecognized.")
            except ValueError:
                print("Try a number!")

    def validationTest(self,n,numberOfAnts,alpha, beta, q0, rho):
        std=[]
        mean=[] #avg
        #we apply 30 runs:
        for i in range(30):
            #keep the fitness of every state in every pop in a run
            aco = Controller(n, 40, 1000, numberOfAnts,alpha, beta, q0, rho)
            std1,mean1=aco.validateTest()
            std.append(std1)
            mean.append(mean1)
            print(i)
        std,=plt.plot(std,color='m',label='Standard Deviation')
        mean,=plt.plot(mean,color='y',label='Average')
        plt.legend(handles=[std,mean])
        plt.show()



