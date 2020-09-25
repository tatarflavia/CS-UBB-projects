from Controller.Controller import Controller
from Repository.Repository import Repository
from Console.Console import Console
if __name__ == '__main__':

    repository = Repository()
    ctrl = Controller(repository)
    console=Console(ctrl)

    print("Run samples for the fuzzy system:")
    console.fuzzySystemControl(0, -30)
    #console.fuzzySystemControl(10, 30)
    #console.fuzzySystemControl(60, 30)
    #console.fuzzySystemControl(75, 20)
    print()
    print()
    print()

    console.runApp()