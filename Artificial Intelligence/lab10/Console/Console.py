from Controller.Controller import Controller

class Console(object):
    def __init__(self,ctrl):
        self.ctrl=ctrl




    def menu(self):
        print("The menu of options is:")
        print("1.Fuzzy System Control-adjust the operating time of a sprinkler.")
        print("0.Exit the application.")




    def runApp(self):
        while True:
            self.menu()
            try:
                choice = int(input("Please choose an option from the menu:"))
                if choice == 1:
                    self.fuzzySystemControl()
                    print()
                    print()
                elif choice == 0:
                    break
                else:
                    print("Unrecognized.")
            except ValueError:
                print("Try a number!")




    def fuzzySystemControl(self,inpHum=None,inpTem=None):
        if inpHum!=None:
            humidity=inpHum
        else:
            humidity = float(input("Please give a number for the humidity of the soil:"))
        if inpTem != None:
            temperature=inpTem
        else:
            temperature = float(input("Please give a number for the temperature of the air:"))

        result=self.ctrl.startComputation({'humidity': humidity, 'temperature': temperature})
        file1 = open('E:\\School\\inteligenta artificiala an 2 sem 2\\lab10\\tema10\\output.txt', 'a')
        file1.write("For humidity: " + str(humidity) + " and temperature: " + str(temperature) + " => "+"\n")
        file1.write("this is the OPERATING TIME for the sprinkler: "+str(result)+"\n"+"\n")
        print("For humidity: " + str(humidity) + " and temperature: " + str(temperature) + " => ")
        print()
        print("this is the OPERATING TIME for the sprinkler: "+str(result))





