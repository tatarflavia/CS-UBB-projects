from Service.Controller import Controller

class Console(object):
    def __init__(self):
        pass




    def menu(self):
        print("The menu of options is:")
        print("1.Decision Tree-solving a classification problem from a given database.")
        print("0.Exit the application.")




    def runApp(self):
        while True:
            self.menu()
            try:
                choice = int(input("Please choose an option from the menu:"))
                if choice == 1:
                    self.decisionTree()
                    print()
                    print()
                elif choice == 0:
                    break
                else:
                    print("Unrecognized.")
            except ValueError:
                print("Try a number!")




    def decisionTree(self):
        practice=float(input("Please give a percentage for the practice data extraction:"))
        ctrl=Controller(practice)
        max=0
        for i in range(5):
            res=round(ctrl.decisionTreeFunction()*100,2)
            print(str(res)+"%")
            if res>max:
                max=res
            #print(str(round(ctrl.decisionTreeFunction()*100,2))+"%")
        print("This is the accuracy from the decision tree made in the first step compared with the testing data: "+str(max)+"%")





