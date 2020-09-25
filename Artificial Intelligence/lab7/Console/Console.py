

class Console(object):
    def __init__(self,ctrl):
        self.ctrl=ctrl


    def menu(self):
        print("The menu of options is:")
        print("1.Gradient Descent-solving a regression problem from a given database.")
        print("0.Exit the application.")


    def runApp(self):
        while True:
            self.menu()
            try:
                choice = int(input("Please choose an option from the menu:"))
                if choice == 1:
                    self.gradientDescent()
                    print()
                    print()
                elif choice == 0:
                    break
                else:
                    print("Unrecognized.")
            except ValueError:
                print("Try a number!")


    def gradientDescent(self):
        array=self.ctrl.callMainForGradientDescentRegressionProblem()
        best_error=array[0]
        coeffs=array[1]
        print("Best Error is:"+str(best_error))
        print("The function found is:  "+str(round(coeffs[0],2))+"*x^5 + "+str(round(coeffs[1],2))+"*x^4 + "+str(round(coeffs[2],2))+"*x^3 + "+str(round(coeffs[3],2))+"*x^2 + "+str(round(coeffs[4],2))+"*x + "+str(round(coeffs[5],2)))





