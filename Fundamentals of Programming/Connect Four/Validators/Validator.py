'''
Created on 3 ian. 2019

@author: Armin
'''

from Errors.Errors import ValidError
class ValidBall(object):
    #this class represents the validation for a ball object
    def __init__(self):
        pass
    def validate_ball(self,ball):
        #function that validates a ball, raises an error if the ball is not right
        #input:ball
        #preconditions:ball object is a ball
        #output:error or none
        #postconditions:error if a param is not right in the ball object
        errors=""
        if ball.get_line()<0 or ball.get_line()>5:
            errors+="Invalid line number!"
        if ball.get_col()<0 or ball.get_col()>6:
            errors+="Invalid column number!"
        if len(ball.get_sign())>1:
            errors+="Invalid sign!"
        if errors!="":
            raise ValidError(errors)