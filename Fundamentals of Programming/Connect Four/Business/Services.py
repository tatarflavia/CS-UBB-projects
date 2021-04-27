'''
Created on 3 ian. 2019

@author: Armin
'''
from Domain.Classes import Player,Ball,Board
class ServPlayer(object):
    #this class represents the service for the players
    def __init__(self,repoPlayer):
        #params
        self.__repoPlayer=repoPlayer
    def add_player(self,name,sign):
        #function that adds a player,creates the object and adds it to the repo
        player=Player(len(self.__repoPlayer.get_all())+1,name,sign)
        self.__repoPlayer.add_player(player)
    def get_all(self):
        #function that gets from the repo the whole list of players and returns it
        return self.__repoPlayer.get_all()
        

class ServBall(object):
    #this  class represents the service for the moves/ball objects
    def __init__(self,validBall,repoBall,repoPlayer):
        #params
        self.__validBall=validBall
        self.__repoBall=repoBall
        self.__repoPlayer=repoPlayer
        self._board=Board()
    
    @property
    def board(self):
        #getter for the board from the params
        return self._board
    
    def wins(self):
        #function that returns 0 or the winnig sign using the wins() function from the board class
        if len(self.__repoBall.get_all())<7:
            return 0
        if self._board.wins()!=0:
            return self._board.wins()
        return 0
    
    def get_empties(self):
        #function that gets the number of empty holes from the board
        return 42-len(self.__repoBall.get_all())
    
    def tie(self):
        #function that tells whether it's a tie or not using the tie() function from the board
        if self.get_empties()==0 and self.wins()==0:
            return True
        return False
    
    def get_winning_player(self,sign):
        #returns a list formed by only a player that is a winner,we get the sign from the wins function
        l=[]
        l.append(self.__repoPlayer.get_player_by_sign(sign))
        return l
        
        
    def add_playerMove(self,number):
        #function that adds a move for the player 1(used in both modes)
        ball=Ball(len(self.__repoBall.get_all())+1,self._board.get_line(number),number,self.__repoPlayer.get_player1().get_sign())
        self.__validBall.validate_ball(ball)
        self.__repoBall.add_ball(ball)
        self._board.move(ball.get_col(), ball.get_sign())
    
    def add_playerMove2(self,number):
        #function that adds a move for the player 2(used only in 2 players mode)
        ball=Ball(len(self.__repoBall.get_all())+1,self._board.get_line(number),number,self.__repoPlayer.get_player2().get_sign())
        self.__validBall.validate_ball(ball)
        self.__repoBall.add_ball(ball)
        self._board.move(ball.get_col(), ball.get_sign())
    
    def generate_move(self):
        #function that adds a move for the computer(used only in one player mode)
        Id=len(self.__repoBall.get_all())+1
        sign='o'
        lin=int(self._board.comp_move()[0])
        col=int(self._board.comp_move()[1])
        ball=Ball(Id,lin,col,sign)
        self.__validBall.validate_ball(ball)
        self.__repoBall.add_ball(ball)
        self._board.move(ball.get_col(), ball.get_sign())
        
        
        
        
