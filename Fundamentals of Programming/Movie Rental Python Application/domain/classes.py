#vezi de teste
import unittest


class Movie(object):
    '''
    This class represents movies.
    '''
    def __init__(self, movieID, title, desc, genre):
        '''
        Function that creates a movie.
        input:movieID,title,desc,genre
        peconditions:movieID-natural number and the others are strings
        '''
        self.__movieID = movieID
        self.__title = title
        self.__desc = desc
        self.__genre = genre

    def UpdateGenre(self, newGenre):
        '''Function that changes the genre of the movie into the param newGenre
        input:newGenre
        preconditions:newGenre-string
        '''
        self.__genre = newGenre
    def UpdateDesc(self, newDesc):
        '''Function that changes the description of the movie into the param newDesc
            input:newDesc
            preconditions:newDesc-string
            '''
        self.__desc = newDesc
    def UpdateTitle(self, newTitle):
        '''Function that changes the title of the movie into the param newTitle
            input:newTitle
            preconditions:newTitle-string
            '''
        self.__title = newTitle

    def GetMovieID(self):
        '''Function that gets the id of the movie
            input:
            preconditions:
            output:movieID
            postconditions:movieID-natural number
            '''
        return self.__movieID
    def GetTitle(self):
        '''Function that gets the title of the movie
            input:
            preconditions:
            output:title
            postconditions:title-string
            '''
        return self.__title
    def GetDesc(self):
        '''Function that gets the description of the movie
            input:
            preconditions:
            output:desc
            postconditions:desc-string
            '''
        return self.__desc
    def GetGenre(self):
        '''Function that gets the genre of the movie
            input:
            preconditions:
            output:genre
            postconditions:genre-string
            '''
        return self.__genre
    def __str__(self):
        return str(self.__movieID) + ',' + str(self.__title) + ',' + str(self.__desc) + ',' + str(self.__genre)

    def __eq__(self, value):
        if type(value)==int:
            return self.__movieID == value
        return self.__movieID == value.__movieID

class Client(object):
    '''
    This class represents clients.
    '''
    def __init__(self, clientID, name):
        '''
        Function that creates a client.
        input:clientID,name
        peconditions:clientID-natural number and name is a string
        '''
        self.__clientID = clientID
        self.__name = name
    def Updatename(self, newName):
        '''Function that changes the name of the client into the param newName
        input:newName
        preconditions:newName-string
        '''
        self.__name = newName
    def GetClientID(self):
        '''Function that gets the id of the client
            input:
            preconditions:
            output:clientID
            postconditions:clientID-natural number
        '''
        return self.__clientID
    def GetName(self):
        '''Function that gets the name of the client
            input:
            preconditions:
            output:title
            postconditions:name-string
        '''
        return self.__name

    def UpdateClient(self, newName):
        '''Function that changes the name of the client into the param newName
        input:newName
        preconditions:newName-string
        '''
        self.__name = newName
    def __str__(self):
        return str(self.__clientID) + ',' + str(self.__name)

    def __eq__(self, value):
        if type(value)==int:
            return self.__clientID == value
        return self.__clientID == value.__clientID



class Rental:
    '''
    This class represents movies.
    '''
    def __init__(self, rentalID, movieID, clientID, rented_date, due_date, returned_date):
        '''
        Function that creates a rent.
        input:rentalID, movieID, clientID, rented_date, due_date, returned_date
        peconditions:movieID,clientID,rentalID-natural numbers and the others are strings
        '''
        self.__rentalID = rentalID
        self.__movieID = movieID
        self.__clientID = clientID
        self.__rented_date = rented_date
        self.__due_date = due_date
        self.__returned_date = returned_date


    def GetRentalID(self):
        '''Function that gets the id of the rent
                    input:
                    preconditions:
                    output:rentalID
                    postconditions:rentalID-natural number
                    '''
        return self.__rentalID
    def GetMovieID(self):
        '''Function that gets the id of the movie from the rent
                    input:
                    preconditions:
                    output:movieID
                    postconditions:movieID-natural number
                    '''
        return self.__movieID
    def GetClientID(self):
        '''Function that gets the id of the client from the rent
                    input:
                    preconditions:
                    output:clientID
                    postconditions:clientID-natural number
                    '''
        return self.__clientID
    def GetRentD(self):
        '''Function that gets the rented date of the rent
                    input:
                    preconditions:
                    output:rented_date
                    postconditions:rented_date-date
                    '''
        return self.__rented_date
    def GetDueD(self):
        '''Function that gets the due date of the rent
            input:
            preconditions:
            output:due_date
            postconditions:due_date-date
        '''
        return self.__due_date
    def GetReturnedD(self):
        '''Function that gets the returned date of the rent
            input:
            preconditions:
            output:returned_date
            postconditions:returned_date-date
        '''
        return self.__returned_date
    def updateReturnedD(self,newD):
        '''Function that sets the returned date of the rent to newD
            input:newD
            preconditions:newD is a date
            output:new returned_date
            postconditions:returned_date-date
        '''
        self.__returned_date=newD

    def __str__(self):
        return str(self.__rentalID) + ',' + str(self.__movieID) + ',' + str(self.__clientID) + ',' + str(self.__rented_date)+','+str(self.__due_date)+','+str(self.__returned_date)

    def __eq__(self, value):
        if type(value)==int:
            return self.__rentalID == value
        return self.__rentalID== value.__rentalID


class Undo_operation(object):
    '''
    This class represents undo operations
    '''
    def __init__(self,op,el1,el2=0):
        '''
        Function that creates an operation.
        input:op,el
        preconditions:op is an operation and el is an element for that operation
        '''
        self.__op=op
        self.__el1=el1
        self.__el2=el2

    def GetOp(self):
        '''Function that gets the operation name from the operation
            input:
            preconditions:
            output:operation name
            postconditions:operation name is an operation
        '''
        return self.__op
    def GetEl1(self):
        '''Function that gets the element 1 from the operation
            input:
            preconditions:
            output:element
            postconditions:element 1 is the first element for that operation
        '''
        return self.__el1
    def GetEl2(self):
        '''Function that gets the element 2 from the operation
            input:
            preconditions:
            output:element
            postconditions:element 2 is the second element for that operation
        '''
        return self.__el2

    def __str__(self):
        return str(self.__op) + ' ' + str(self.__el1)+' '+str(self.__el2)

class lateRentalsDTO(object):
    def __init__(self,name,days):
        self.__name=name
        self.__days=days
    def getName(self):
        return self.__name
    def getDays(self):
        return self.__days
    def __str__(self):
        return "The movie "+str(self.__name)+" has "+str(self.__days)+" delay days."

class allMovieDTO(object):
    def __init__(self,name):
        self.__name=name
    def getName(self):
        return self.__name
    def __str__(self):
        return "The movie "+str(self.__name)+" is currently rented."


class mostRentDTO(object):
    def __init__(self,movieid,movieTitle,nrTimes,nrDays):
        self.__movieid=movieid
        self.__movieTitle=movieTitle
        self.__nrTimes=nrTimes
        self.__nrDays=nrDays
    def getMovieID(self):
        return self.__movieid
    def getMovieTitle(self):
        return self.__movieTitle
    def getNrTimes(self):
        return self.__nrTimes
    def setNrTimes(self,new):
        self.__nrTimes=new
    def getNrDays(self):
        return self.__nrDays
    def setNrDays(self,new):
        self.__nrDays=new
    def __str__(self):
        return "The movie "+str(self.__movieid)+", "+str(self.__movieTitle)+" has been rented "+str(self.__nrTimes)+" times and "+str(self.__nrDays)+" days."




class entities_test(unittest.TestCase):

    def test_movie(self):
        m1 = Movie(1, 'bla', 'bla', 'bla')
        m2 = Movie(2, 'bla', 'bla', 'bla')
        assert m1.GetMovieID() == 1
        assert m1.GetGenre() == 'bla'
        m2.UpdateDesc('hei')
        assert m2.GetDesc() == 'hei'
        assert m2.GetMovieID()==2
        assert m2.GetTitle()=='bla'
        m1.UpdateTitle('lala')
        assert m1.GetTitle()=='lala'


    def test_client(self):
        c1=Client(12,'John')
        c2=Client(45,'Ion')
        assert c1.GetClientID()==12
        assert c2.GetName()=='Ion'
        c1.Updatename('ba')
        c1.UpdateClient('bae')
        assert c1.GetName()=='bae'

    def test_rental(self):
        r1=Rental(12,14,13,'14-02-2018','14-03-2018','-')
        self.assertEqual(r1.GetRentalID(),12)
        assert r1.GetRentalID()==12
        assert r1.GetClientID()==14
        assert r1.GetMovieID()==13
        assert r1.GetRentD()=='14-02-2018'
        assert r1.GetDueD()=='14-03-2018'
        assert r1.GetReturnedD()=='-'
        r1.updateReturnedD('14-04-2018')
        self.assertEqual(r1.GetReturnedD(),'14-04-2018')





