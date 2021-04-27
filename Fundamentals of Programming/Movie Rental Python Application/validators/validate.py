from errors.errors import *
import datetime

#vezi de specificatii si teste

class ValidMovie(object):
    #class for validation of a movie
    def __init__(self):
        pass
    def ValidateMovie(self,movie):
        '''
        Function that gets a movie and validates it.
        input:movie
        precond:movie is a movie
        '''
        errors=""
        if movie.GetMovieID()<0:
            errors+="Invalid movie id!"
        if len(errors)>0:
            raise ValidError(errors)

class ValidClient(object):
    #class for validating a client
    def __init__(self):
        pass
    def validateClient(self,client):
        '''
        Function that gets a client and validates it.
        input:client
        precond:client is a client
        '''
        errors=""
        if client.GetClientID()<0:
            errors+="Invalid client id!"
        if len(errors)>0:
            raise ValidError(errors)

class ValidRental(object):
    #class for validating a rental
    def __init__(self):
        pass
    def validateRental(self,rental):
        '''
        Function that gets a rental and validates it.
        input:rental
        precond:rental is a rental
        '''
        errors=""
        if rental.GetClientID()<0:
            errors+="Invalid client id! "
        if rental.GetMovieID()<0:
            errors+="Invalid movie id! "
        if rental.GetRentalID()<0:
            errors+="Invalid rental id! "
        try:
            datetime.datetime.strptime(str(rental.GetRentD()), '%Y-%m-%d')
        except ValueError:
            errors += "Invalid rented date! "
        try:
            datetime.datetime.strptime(str(rental.GetDueD()), '%Y-%m-%d')
        except ValueError:
            errors += "Invalid rented date! "
        if len(errors)>0:
            raise ValidError(errors)