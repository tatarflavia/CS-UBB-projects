from Errors.Errors import ValidError,RepoError,GameError
from builtins import str


class ConsolePlayers(object):
    def __init__(self,servBall,servPlayer):
        self.__servBall = servBall
        self.__servPlayer = servPlayer
        
        
    def ui_add_player(self,parts):
        #adds a player
        if len(parts)!=2:
            raise ValueError("Invalid number of params for adding your names(don't forget the comma)!")
        self.__servPlayer.add_player(parts[0].strip(),'x')
        self.__servPlayer.add_player(parts[1].strip(),'o')
        
        
    def menu(self):
        #menu of commands
        print("You chose to play with a friend.You can choose the command below or you can continue the game:")
        print("-'exit' for giving up")
    
        
    def ui_add_move(self, number):
        #adds a move/ball for the first player
        if number<0 or number>6:
            raise ValueError("You are out of the board!")
        self.__servBall.add_playerMove(number)
            
    
    
    def ui_add_movePlayer2(self, number):
        #adds a move/ball for the second player
        if number<0 or number>6:
            raise ValueError("You are out of the board!")
        self.__servBall.add_playerMove2(number)
    
    
    def startgame(self):
        #the loop for the game itself, adds only moves and is over when someone wins
        b=self.__servBall.get_board()
        print("You can only give the column number(number from 0 to 6) or press twice 'exit' if you want to end the game\n")
        print("This is the table for the game:")
        n=1 #the number of times the row has been given,to see whose turn is it
        while self.__servBall.wins()==0 and self.__servBall.tie()==False:
            print(b)
            command=input("Please give a number:")
            if command=='exit':
                return
            else:
                try:
                    number=int(command) #number is the nb of the column
                    if n%2!=0:
                        self.ui_add_move(number)
                    else:
                        self.ui_add_movePlayer2(number)
                    n+=1
                except ValueError as ve:
                    print(ve)
                    print("Invalid input!")
                except RepoError as re:
                    print(re)
                except ValidError as va:
                    print(va)
                except GameError as ga:
                    print(ga)
                #print("Choose another entry!")
        
        #game over instance
        print(b)
        print("Game over!")
        if self.__servBall.tie()==True:
            print("It's a tie!")
            return
        else:
            l=self.__servBall.get_winning_player(self.__servBall.wins())
            for i in l:
                print(str(i))
            return
    
    def start(self):
        #start of the application, adds the players and makes the connection to the table and the game loop
        self.menu()
        while True:
            command=input("Please give your name and your friend's name(<name>,<name>):")
            if command=='exit':
                return
            else:
                try:
                    parts=command.strip()
                    parts=command.split(",")
                    self.ui_add_player(parts)
                    print("Thank you "+str(parts[0].strip())+" and "+str(parts[1].strip())+"!The game will start now!")
                    print(str(parts[0].strip())+" has the symbol 'x' and "+str(parts[1].strip())+" has 'o'.Good luck!")
                    print("The player "+parts[0].strip()+" starts.")
                    self.startgame()
                except ValueError as ve:
                    print(ve)
                except RepoError as re:
                    print(re)
                except ValidError as va:
                    print(va)
                except GameError as ga:
                    print(ga)
                #print("Invalid command!")
            #if the game is over, the second loop with the game is over and the programm comes back to this loop, so we have to make sure the programm is really over
            if self.__servBall.wins()!=0 or self.__servBall.tie()==True:
                print("Congratulations!")
                return
    
            