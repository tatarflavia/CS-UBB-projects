from Service.Controller import *

class Console(object):
    def __init__(self, service):
        self.service = service

    def menu(self):
        print("The menu of options is:")
        print("1.Depth First Search - uniformed search for latin square problem.")
        print("2.Greedy Best First Search- informed search for latin square problem.")
        print("0.Exit the application.")

    def runApp(self):
        while True:
            self.menu()
            try:
                choice=int(input("Please choose an option for searching:"))
                if choice==1:
                    solutionNode=self.service.depthFirstSearchUniformedSearch(self.service.getInitialState())
                    print("Solution node is:\n"+str(solutionNode))
                    print()
                elif choice==2:
                    solutionNode=self.service.greedyBestFirstSearchInformedSearch(self.service.getInitialState())
                    print("Solution node is:\n" + str(solutionNode))
                    print()
                elif choice==0:
                    break
                else :
                    print("Unrecognized.")
            except ValueError:
                print("Try a number!")

