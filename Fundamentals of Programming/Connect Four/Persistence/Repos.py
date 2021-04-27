from Errors.Errors import RepoError


class RepoPlayer(object):
    #this class represents the repo for the players, has all the info about the list of players from appliation
    def __init__(self):
        self._players=[]
    def add_player(self,player):
        #this function adds a player to the repo or raises an error is the player is already in the repo
        #input:player
        #precond:player is a player
        #output:self._players 
        #postcond:self._players=self._players+player is the new list after adding the player
        if player in self._players:
            raise RepoError("Existing player!")
        else:
            self._players.append(player)
    def get_player1(self):
        #this function returns the first player object from the repo
        #input:self._players
        #precond:self._players is the list from the repo
        #output: the first element from the list
        #postcond:the first element is a player object
        return self._players[0]
    def get_player2(self):
        #this function returns the second player from the repo
        #input:self._players
        #precond:self._players is the list from the repo
        #output: the second element from the list
        #postcond:the second element is a player object
        return self._players[1]
    def get_all(self):
        #this function returns the whole list of players from the repo
        #output:copy self._players
        #postcond:self._players is the list from the repo
        return self._players[:]
    
    def get_player_by_sign(self,sign):
        #this function returns the player from the repo (that only has 2 objects in it) that has the sign sign from the params
        #input:self._players,sign
        #precond:self._players is the list from the repo,sign is string
        #output: player
        #postcond:player is a player object,element from the repo list
        for player in self._players:
            if player==sign:
                return player



class RepoBall(object):
    #this class represents 
    def __init__(self):
        self._moves=[]
    def add_ball(self,ball):
        #this function adds a ball to the repo or raises an error is the ball is already in the repo
        #input:ball
        #precond:ball is a ball object
        #output:self._moves
        #postcond:self._moves=self._moves+ball is the new list after adding the ball
        if ball in self._moves:
            raise RepoError("Existing ball!")
        else:
            self._moves.append(ball)    
    def get_all(self):
        #this function returns the whole list of elements from the repo
        #output:copy self._moves
        #postcond:self._moves is the list from the repo
        return self._moves[:]
    

            