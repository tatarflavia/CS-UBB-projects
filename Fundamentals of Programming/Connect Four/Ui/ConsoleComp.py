from Errors.Errors import ValidError,RepoError,GameError



class ConsoleComp(object):
    def __init__(self,servBall,servPlayer):
        self.__servBall = servBall
        self.__servPlayer = servPlayer
        
        
    def ui_add_player(self,command):
        #adds a player
        parts=command.split(" ")
        if len(parts)!=1:
            raise ValueError("Invalid number of params for adding a name!")
        self.__servPlayer.add_player(command,'x')
        self.__servPlayer.add_player('computer','o')
        
        
    def menu(self):
        #menu of commands
        print("You chose to play with the computer.You can choose the command below or you can continue the game:")
        print("-'exit' for giving up")
    
        
    def ui_add_move(self, number):
        #adds a move/ball for the player, number is the nb of the column
        if number<0 or number>6:
            raise ValueError("You are out of the board!")
        self.__servBall.add_playerMove(number)
            
    
    
    def ui_add_Compmove(self):
        #adds a move for the computer
        self.__servBall.generate_move()
    
    
    def startgame(self):
        #the loop for the game itself, adds only moves and is over when someone wins
        b=self.__servBall.board
        print("You can only give the column number(number from 0 to 6) or press twice 'exit' if you want to end the game\n")
        print("This is the table for the game:")
        while self.__servBall.wins()==0 and self.__servBall.tie()==False:
            print(b)
            command=input("Please give a number:")
            if command=='exit':
                return
            else:
                try:
                    number=int(command)
                    self.ui_add_move(number)
                    if type(number)==int:
                        self.ui_add_Compmove()
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
        
        #game over
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
            command=input("Please give your name:")
            if command=='exit':
                return
            else:
                try:
                    inputs=command.strip().split(" ")[0]
                    self.ui_add_player(inputs)
                    print("Thank you "+str(inputs)+"!Your symbol is 'x'.The game will start now!")
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
