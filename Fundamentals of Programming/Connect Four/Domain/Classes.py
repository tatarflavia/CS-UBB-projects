'''
Created on 3 ian. 2019

@author: Armin
'''
from Errors.Errors import GameError
from texttable import Texttable



class Player(object):
    #this class represents player objects
    def __init__(self,Id,name,sign):
        #params of the player
        #input:id,name,sign
        #preconditions:id- positive int, name string, sign - a letter
        #output:player object
        #postconditions:player object is a player
        self.__Id = Id
        self.__name = name
        self.__sign = sign

    def get_name(self):
        #function that gets the name of the player
        #input:player
        #preconditions:player object is a player
        #output:name
        #postconditions:name is a string
        return self.__name


    def get_sign(self):
        #function that gest the sign of the player
        #input:player
        #preconditions:player object is a player
        #output:sign
        #postconditions:sign is a string
        return self.__sign
    
    def __eq__(self, other):
        #function that returns true or false,true if self==other
        #input:self,other
        #preconditions:both are players
        #output:true or false
        #postconditions:true if other==self, false otherwise
        if type(other)==str:
            return self.__sign==other
        return self.__sign==other.__sign
    def __str__(self):
        #str function for the player
        return "The player "+self.__name+" with the sign "+self.__sign+" wins!"
    
class Ball(object):
    #this class represents ball objects(a ball is a thing that is put into a square in the table)
    def __init__(self,ID,line,col,sign):
        #params: id,line in the table,column number in the table, sign
        #input:id,lin,col,sign
        #preconditions:id- positive int, lin int, col int, sign - a letter
        #output:ball object
        #postconditions:ball object is a ball
        self.__ID = ID
        self.__line = line
        self.__col = col
        self.__sign = sign

    def get_line(self):
        #function that gets the line of the ball
        #input:ball
        #preconditions:ball object is a ball
        #output:line
        #postconditions:line is an int
        return self.__line


    def get_col(self):
        #function that gets the column of the ball
        #input:ball
        #preconditions:ball object is a ball
        #output:col
        #postconditions:col is a int, column number of the ball
        return self.__col


    def get_sign(self):
        #function that gets the sign of the ball
        #input:ball
        #preconditions:ball object is a ball
        #output:sign
        #postconditions:sign is a string
        return self.__sign

    def __eq__(self, other):
        #function that returns true or false,true if self==other
        #input:self,other
        #preconditions:both are ball objects
        #output:true or false
        #postconditions:true if other==self, false otherwise
        return self.__ID==other.__ID
    
class Board(object):
    #this class is the representation of the 7X6 board
    def __init__(self):
       
        #the data from the table in a list of lists manner
        self._data=[[0]*7,[0]*7,[0]*7,[0]*7,[0]*7,[0]*7]
        
    def move(self,col,sign):
        #changes the data for the line and column with the sign everytime a ball is put there
        if self._data[0][col]!=0:
            raise GameError("That column is full!")
        i=self.get_line(col)
        d={'x':1,'o':2}
        self._data[i][col]=d[sign]
        
    def get_line(self,col):
        #function that gets the line of the of the last empty square from the column col
        if self._data[0][col]!=0:
            raise GameError("That column is full!")
        i=5 #i is the line number that finds the square thatis empty
        while self._data[i][col]!=0:
            i-=1
        return i   
    
    def comp_move(self):
        #function that returns line and column numbers in a list where the computer will make the most appropriate move
        l=[] #the list with the results
                         
                     
        #first we check the 3 combinations of x's and we block them
        for i in range(0,4):
            for j in range(0,5):
                if self._data[i][j]==1==self._data[i][j+1]==self._data[i][j+2]: #for lines
                    if j>=1 and self._data[i][j-1]==0  :
                        l.extend([i,j-1])
                        return l
                    if  j<4 and self._data[i][j+3]==0:
                        l.extend([i,j+3])
                        return l
                if self._data[i][j]==1==self._data[i+1][j]==self._data[i+2][j]: #for columns
                    if  i>=1 and self._data[i-1][j]==0 :
                        l.extend([i-1,j])
                        return l
                    if i<3 and self._data[i+3][j]==0:
                        l.extend([i+3,j])
                        return l
                if self._data[i][j]==1==self._data[i+1][j+1]==self._data[i+2][j+2]: #for \ diag
                    if i>=1 and j>=1 and self._data[i-1][j-1]==0   :
                        l.extend([i-1,j-1])
                        return l
                    if i<3 and j<4 and self._data[i+3][j+3]==0  :
                        l.extend([i+3,j+3])
                        return l
        for i in range(4,6): #for lower rows
            for j in range(0,5):
                if self._data[i][j]==1==self._data[i][j+1]==self._data[i][j+2]: #for lines
                    if  j>=1 and self._data[i][j-1]==0 : 
                        l.extend([i,j-1])
                        return l
                    if j<4 and self._data[i][j+3]==0:
                        l.extend([i,j+3])
                        return l
        
        #for last columns:
        for i in range(0,4):
            for j in range(5,7):
                if self._data[i][j]==1==self._data[i+1][j]==self._data[i+2][j]: #for columns
                        if i>=1 and self._data[i-1][j]==0:
                            l.extend([i-1,j])
                            return l
                        if i<3 and self._data[i+3][j]==0:
                            l.extend([i+3,j])
                            return l
        #for / diag
        for i in range(0,4):
            for j in range(6,1,-1):
                if self._data[i][j]==1==self._data[i+1][j-1]==self._data[i+2][j-2]:
                    if i>=1 and j<6 and self._data[i-1][j+1]==0:
                        l.extend([i-1,j+1])
                        return l
                    if i<3 and j>=3 and self._data[i+3][j-3]==0:
                        l.extend([i+3,j-3])
                        return l
        #------------------------------------------------------------------------------ 
        #for the 2 combinations of 'x' we check and we block them
        for i in range(0,5):
            for j in range(0,6):
                if self._data[i][j]==1==self._data[i][j+1]: #for lines
                    if j>=1 and self._data[i][j-1]==0   :
                        l.extend([i,j-1])
                        return l
                    if j<5 and self._data[i][j+2]==0  :
                        l.extend([i,j+2])
                        return l
                if self._data[i][j]==1==self._data[i+1][j]: #for columns
                    if i>=1 and self._data[i-1][j]==0   :
                        l.extend([i-1,j])
                        return l
                    if i<4 and self._data[i+2][j]==0:
                        l.extend([i+2,j])
                        return l
                if self._data[i][j]==1==self._data[i+1][j+1]: #for \ diag
                    if i>=1 and j>=1  and self._data[i-1][j-1]==0  :
                        l.extend([i-1,j-1])
                        return l
                    if  i<4 and j<5 and self._data[i+2][j+2]==0 :
                        l.extend([i+2,j+2])
                        return l
        #for the last row
        for j in range(0,6):
            if self._data[5][j]==1==self._data[5][j+1]:
                if j>=1  and self._data[5][j-1]==0:
                    l.extend([5,j-1])
                    return l
                if  j<5 and self._data[5][j+2]==0:
                    l.extend([5,j+2])
                    return l
        #for last column
        for i in range(0,5):
            if self._data[i][6]==1==self._data[i+1][6]: #for columns
                    if i>=1 and self._data[i-1][6]==0  :
                        l.extend([i-1,6])
                        return l
                    if  i<4 and self._data[i+2][6]==0:
                        l.extend([i+2,6])
                        return l
        #for the / diag
        for i in range(0,5):
            for j in range(6,0,-1):
                if self._data[i][j]==1==self._data[i+1][j-1]:
                    if i>=1 and j<6 and self._data[i-1][j+1]==0:
                        l.extend([i-1,j+1])
                        return l
                    if  i<4 and j>=2 and self._data[i+2][j-2]==0:
                        l.extend([i+2,j-2])
                        return l
                    
        #------------------------------------------------------------------------------ 
        #try to make computer moves considering the board and what the computer already had there(make combinations)
        #make combinations of 2 or 3 or 4 by putting in the front and then in the back of the former combination
        for i in range(0,3):
            for j in range(0,4):
                #for lines
                if self._data[i][j]==0 and self._data[i][j+1]==2 or self._data[i][j+2]==2 and self._data[i][j+1]==2 and self._data[i][j]==0 or self._data[i][j]==0 and self._data[i][j+1]==2 and self._data[i][j+2]==2 and self._data[i][j+3]==2:
                    l.extend([i,j])  #put in the front
                    return l
                if self._data[i][j]==2 and self._data[i][j+1]==0 :
                    l.extend([i,j+1])
                    return l
                if self._data[i][j+2]==0 and self._data[i][j+1]==2 and self._data[i][j]==2:
                    l.extend([i,j+2])
                    return l
                if self._data[i][j+1]==2 and self._data[i][j+2]==2 and self._data[i][j+3]==0 and self._data[i][j]==2:
                    l.extend([i,j+3])
                    return l
                #for columns
                if self._data[i][j]==0 and self._data[i+1][j]==2 or self._data[i][j]==0 and self._data[i+1][j]==2 and self._data[i+2][j]==2 or self._data[i][j]==0 and self._data[i+1][j]==2 and self._data[i+2][j]==2 and self._data[i+3][j]==2:
                    l.extend([i,j])
                    return l
                if self._data[i][j]==2 and self._data[i+1][j]==0:
                    l.extend([i+1,j])
                    return l
                if self._data[i][j]==2 and self._data[i+1][j]==2 and self._data[i+2][j]==0:
                    l.extend([i+2,j])
                    return l
                if self._data[i][j]==2 and self._data[i+1][j]==2 and self._data[i+2][j]==2 and self._data[i+3][j]==0:
                    l.extend([i+3,j])
                    return l
                #for \ diag
                if self._data[i][j]==0 and self._data[i+1][j+1]==2 or self._data[i][j]==0 and self._data[i+1][j+1]==2 and self._data[i+2][j+2]==2 or self._data[i][j]==0 and self._data[i+1][j+1]==2 and self._data[i+2][j+2]==2 and self._data[i+3][j+3]==2:
                    l.extend([i,j])
                    return l
                if self._data[i][j]==2 and self._data[i+1][j+1]==0:
                    l.extend([i+1,j+1])
                    return l
                if self._data[i][j]==2 and self._data[i+1][j+1]==2 and self._data[i+2][j+2]==0:
                    l.extend([i+2,j+2])
                    return l
                if self._data[i][j]==2 and self._data[i+1][j+1]==2 and self._data[i+2][j+2]==2 and self._data[i+3][j+3]==0:
                    l.extend([i+3,j+3])
                    return l
        #for bottom rows
        for i in range(3,6):
            for j in range(0,4):
                if self._data[i][j]==0 and self._data[i][j+1]==2 or self._data[i][j+2]==2 and self._data[i][j+1]==2 and self._data[i][j]==0 or self._data[i][j]==0 and self._data[i][j+1]==2 and self._data[i][j+2]==2 and self._data[i][j+3]==2:
                    l.extend([i,j])
                    return l
                if self._data[i][j]==2 and self._data[i][j+1]==0:
                    l.extend([i,j+1])
                    return l
                if self._data[i][j+2]==0 and self._data[i][j+1]==2 and self._data[i][j]==2:
                    l.extend([i,j+2])
                    return l
                if self._data[i][j]==2 and self._data[i][j+1]==2 and self._data[i][j+2]==2 and self._data[i][j+3]==0:
                    l.extend([i,j+3])
                    return l
        #for last coloums
        for i in range(0,3):
            for j in range(4,7):
                if self._data[i][j]==0 and self._data[i+1][j]==2 or self._data[i][j]==0 and self._data[i+1][j]==2 and self._data[i+2][j]==2 or self._data[i][j]==0 and self._data[i+1][j]==2 and self._data[i+2][j]==2 and self._data[i+3][j]==2:
                    l.extend([i,j])
                    return l
                if self._data[i][j]==2 and self._data[i+1][j]==0:
                    l.extend([i+1,j])
                    return l
                if self._data[i][j]==2 and self._data[i+1][j]==2 and self._data[i+2][j]==0:
                    l.extend([i+2,j])
                    return l
                if self._data[i][j]==2 and self._data[i+1][j]==2 and self._data[i+2][j]==2 and self._data[i+3][j]==0:
                    l.extend([i+3,j])
                    return l
        #for / diag
        for i in range(0,3):
            for j in range(6,2,-1):
                if self._data[i][j]==0 and self._data[i+1][j-1]==2 or self._data[i][j]==0 and self._data[i+1][j-1]==2 and self._data[i+2][j-2]==2 or self._data[i][j]==0 and self._data[i+1][j-1]==2 and self._data[i+2][j-2]==2 and self._data[i+3][j-3]==2:
                    l.extend([i,j])
                    return l
                if self._data[i][j]==2 and self._data[i+1][j-1]==0:
                    l.extend([i+1,j-1])
                    return l
                if self._data[i][j]==2 and self._data[i+1][j-1]==2 and self._data[i+2][j-2]==0:
                    l.extend([i+2,j-2])
                    return l
                if self._data[i][j]==2 and self._data[i+1][j-1]==2 and self._data[i+2][j-2]==2 and self._data[i+3][j-3]==0:
                    l.extend([i+3,j-3])
                    return l
                
        #in last instance, we put the move in the center column
        for i in range(5,-1,-1):
            if self._data[i][3]==0:
                l.extend([i,3]) 
                return l 
            
    def wins(self):
        # function that ret true if there was a winning situation and false otherwise
        #for lines and columns:
        k=0
        for i in range(0,3):
            for j in range(0,4):
                if self._data[i][j]!=0 and self._data[i][j]==self._data[i][j+1]==self._data[i][j+2]==self._data[i][j+3]: #for line
                    k= self._data[i][j]
                if self._data[i][j]!=0 and self._data[i][j]==self._data[i+1][j]==self._data[i+2][j]==self._data[i+3][j]: #for column
                    k= self._data[i][j]
                if self._data[i][j]!=0 and self._data[i][j]==self._data[i+1][j+1]==self._data[i+2][j+2]==self._data[i+3][j+3]: #for \ diag
                    k= self._data[i][j]   
        #for the / diag:
        for i in range(0,3):
            for j in range(6,2,-1):
                if self._data[i][j]!=0 and self._data[i][j]==self._data[i+1][j-1]==self._data[i+2][j-2]==self._data[i+3][j-3]:
                    k= self._data[i][j]
        #for lower rows
        for i in range(3,6):
            for j in range(0,4):
                if self._data[i][j]!=0 and self._data[i][j]==self._data[i][j+1]==self._data[i][j+2]==self._data[i][j+3]: #for line
                    k= self._data[i][j]
        #for last coloums:
        for i in range(0,3):
            for j in range(4,7):
                if self._data[i][j]!=0 and self._data[i][j]==self._data[i+1][j]==self._data[i+2][j]==self._data[i+3][j]: #for column
                    k= self._data[i][j]
                
        if k!=0:
            if k==1:
                return 'x'
            else:
                return 'o'
        return 0
        
    def __str__(self):
        #str function
        t=Texttable()
        d={0:" ",1:'x',2:'o'}
        for i in range(0,6):
            lista = self._data[i][:]
            for j in range(0,7):
                lista[j] = d[lista[j]]
            t.add_row(lista)
        return t.draw()
    

    