'''
Created on 6 ian. 2019

@author: Armin
'''
from Business.Services import ServBall,ServPlayer
from Persistence.Repos import RepoBall,RepoPlayer
from Validators.Validator import ValidBall
import unittest
from Domain.Classes import Ball,Board,Player
from Errors.Errors import ValidError, RepoError

class test(unittest.TestCase): 
    #unittest class for the functions and classes from the programm
    def setUp(self):
        unittest.TestCase.setUp(self)
    def tearDown(self):
        unittest.TestCase.tearDown(self)  
    def test_board(self):
        #test function for the board class from domain
        b=Board()
        b.move(3, 'x')
        b.move(3, 'o')
        b.move(4, 'x')
        b.move(4, 'o')
        b.move(2, 'x')
        assert b.wins()==0    
        b.move(4,'x')
        b.move(0, 'o')
        b.move(6, 'x')
        b.comp_move()
        assert b.wins()==0
        assert b.comp_move()==[5,1]
        assert b.get_line(6)==4
        assert b.get_line(4)==2
        
    def test_player(self):
        #test function for the player class from domain
        p=Player(1,'alina','x')
        b=Player(2,'alina2','y')
        assert p.get_name()=='alina'
        assert b.get_name()=='alina2'
        assert p.get_sign()=='x'
        assert b.get_sign()=='y'
        k=Player(3,'holland','x')
        assert k.__eq__(p)
        y=Player(4,'alex','y')
        assert b.__eq__(y)
        
    def test_ball(self):
        #test function for the ball class from domain
        b=Ball(1,0,0,'x')
        b2=Ball(1,1,1,'o')
        assert b.get_col()==0
        assert b.get_line()==0
        assert b2.get_col()==1
        assert b2.get_line()==1
        assert b.get_sign()=='x'
        assert b2.get_sign()=='o'
        assert b.__eq__(b2)
        b3=Ball(3,3,3,'y')
        assert b.__eq__(b3)==False
        
    def RepoBallTest(self):
        #test function for the RepoBall class from persistence
        b=Ball(1,0,0,'x')
        b2=Ball(2,1,1,'o')
        repo=RepoBall()
        repo.add_ball(b)
        assert repo.get_all()==[b]
        repo.add_ball(b2)
        assert repo.get_all()==[b,b2]
        
    def RepoPlayerTest(self):
        #test function for the RepoPlayer class from persistence
        p=Player(1,'alin','x')
        p2=Player(2,'alina','o')
        repo=RepoPlayer()
        assert repo==[]
        repo.add_player(p)
        self.assertEqual(repo,[p])
        assert repo.get_all()==[p]
        repo.add_player(p2)
        assert repo==[p,p2]
        self.assertEqual(repo, [p,p2])
    
    def ValidBallTest(self):
        #test function for the ValidBall class from validation
        b=Ball(1,6,7,'x')
        valid=ValidBall()
        try:
            valid.validate_ball(b)
            assert False
        except ValidError:
            pass
    
 
 
def ValidBallTest():
        #test function for the ValidBall class from validation
        b=Ball(1,6,7,'x')
        valid=ValidBall()
        try:
            valid.validate_ball(b)
            assert False
        except ValidError:
            pass   
        b1=Ball(2,3,1,'o') 
        assert valid.validate_ball(b1)==None
        b2=Ball(3,9,5,'x')
        try:
            valid.validate_ball(b2)
            assert False
        except ValidError:
            pass
        b3=Ball(4,1,1,'o')
        assert valid.validate_ball(b3)==None
ValidBallTest()


def RepoBallTest():
        #test function for the RepoBall class from persistence
        b=Ball(1,0,0,'x')
        b2=Ball(2,1,1,'o')
        repo=RepoBall()
        repo.add_ball(b)
        assert repo.get_all()==[b]
        repo.add_ball(b2)
        assert repo.get_all()==[b,b2]
        
def RepoPlayerTest():
        #test function for the RepoPlayer class from persistence
        p=Player(1,'alin','x')
        p2=Player(2,'alina','o')
        repo=RepoPlayer()
        assert repo.get_all()==[]
        repo.add_player(p)
        assert repo.get_all()==[p]
        repo.add_player(p2)
        assert repo.get_all()==[p,p2]
        assert repo.get_player1()==p
        assert repo.get_player2()==p2
        assert repo.get_player_by_sign('x')==p
        assert repo.get_player_by_sign('o')==p2
RepoBallTest()
RepoPlayerTest()

def ServPlayerTest():
    #test function for the ServPlayer class from business
    p2=Player(2,'alina','o')
    p3=Player(3,'giggi','i')
    repo=RepoPlayer()
    serv=ServPlayer(repo)
    serv.add_player('alin', 'x')
    p=Player(1,'alin','x')
    assert serv.get_all()==[p]
    serv.add_player('alina','o')
    serv.add_player('giggi','i')
    assert serv.get_all()==[p,p2,p3]
    try:
        serv.add_player('bai','x')
        assert False
    except RepoError:
        pass
    try:
        serv.add_player('ba','o')
        assert False
    except RepoError:
        pass
    assert serv.get_all()==[p,p2,p3]
    
ServPlayerTest()

def ServBallTest():
    #test function for the ServBall  class from business
    repoBall=RepoBall()
    repoPlayer=RepoPlayer()
    p=Player(1,'alina','x')
    p2=Player(2,'gigig','o')
    repoPlayer.add_player(p)
    repoPlayer.add_player(p2)
    b=Board()
    validB=ValidBall()
    servBall=ServBall(validB,repoBall,repoPlayer)
    servBall._board=b
    assert servBall.board==b
    assert servBall.wins()==0
    assert servBall.get_empties()==42
    assert servBall.tie()==False
    servBall.add_playerMove(0)
    servBall.add_playerMove2(1)
    assert servBall.get_winning_player('x')==[p]
    assert servBall.get_winning_player('o')==[p2]
    assert servBall.board.comp_move()==[5,2]
    servBall.generate_move()
    assert servBall.get_empties()==39
    assert servBall.tie()==0
    servBall.generate_move()
    servBall.generate_move()
    assert servBall.get_empties()==37
    assert servBall.get_winning_player('o')==[p2]
    assert servBall.tie()==False
    
ServBallTest()
    
