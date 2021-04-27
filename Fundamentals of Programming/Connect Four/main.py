from Business.Services import ServBall
from Persistence.Repos import RepoBall
from Validators.Validator import ValidBall
from Persistence.Repos import RepoPlayer
from Business.Services import ServPlayer
from Ui.ConsoleComp import ConsoleComp
from Ui.ConsolePlayers import ConsolePlayers
from Tests.Tests import test

t=test()

#prepare for starting the application,getting the layered architecture ready
repoPlayer=RepoPlayer()
servPlayer=ServPlayer(repoPlayer)
repoBall=RepoBall()
validBall=ValidBall()
servBall=ServBall(validBall,repoBall,repoPlayer)

class ApGenerator:
    #this class represents the first loop of the programm that ends after the user chooses to play with someone or the computer
    
    def appstart(self):
        print("This is the game Connect Four! Would you like to play with the computer or another player?")
        while True:
                answer=input("Your answer(yes or no):")
                if answer=='yes':
                    ui=ConsoleComp(servBall,servPlayer)
                    return ui
                elif answer=='no':
                    ui=ConsolePlayers(servBall,servPlayer)
                    return ui
                else:
                    print("Invalid answer!Please write 'yes' or 'no'!")

#start of the application                    
ui=ApGenerator()
ui.appstart().start()


        


