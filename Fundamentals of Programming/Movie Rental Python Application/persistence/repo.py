from errors.errors import RepoError
import datetime
from domain.classes import *
import pickle


#vezi de teste si specificatii


class RepoUndo(object):
    def __init__(self):
        self.__operations=[]

    def __len__(self):
        return len(self.__operations)

    def add_op(self,element):
        self.__operations.append(element)
    def get_operation(self,index):
        for i in range(0,len(self.__operations)):
            if i==index:
                return self.__operations[i]

class RepoMovie(object):
    '''
    This class represents a movie repository(crud operations made on a class of movies)
    '''
    def __init__(self):
        '''
        initialisation for the list of movies with nothing at first
        '''
        self._movies=[]

    def __len__(self):
        '''
        returns the length of the list
        '''
        return len(self._movies)

    def add_movie(self, element):
        '''
        Function that adds the element to the list self.__movies
        input:element,self.__movies
        output:self.movies will be the new list with the added element
        '''

        if element in self._movies:
            raise RepoError("The movie already exists!")
        else:
            self._movies.append(element)

    def search_movie(self,element):
        '''
        Function that searches for the element in the self.__movies
        '''
        if element not in self._movies:
            raise RepoError("The movie is not in the list!")
        else:
            for i in range(0, len(self._movies)):
                if self._movies[i]==element:
                    return self._movies[i]

    def searchById(self, idMovie):
        for movie in self._movies:
            if movie == idMovie:
                return movie
        raise RepoError("Inexisting movie!")

    def remove_movie(self,element):
        '''
        Function that removes the element from the list self.__list
        '''
        if element not in self._movies:
            raise RepoError("The movie is not in the list!")
        else:
            for i in self._movies:
                if i==element:
                    self._movies.remove(i)


    def update_movie(self,oldthing,element):
        '''
        Function that changes the oldthing with element in the self.__movies
        '''
        if oldthing not in self._movies:
            raise RepoError("The movie is not in the list!")
        else:
            for i in range(0, len(self._movies)):
                if self._movies[i]==oldthing:
                    self._movies[i]=element

    def GetAll(self):
        '''
        Gets all the elements from the list self.__movies
        '''
        return self._movies[:]
    def removeAll(self):
        '''
        removes all elements from the list self.__movies
        '''
        self._movies.clear()

    def toStr(self):
        for i in range(0, len(self._movies)):
            return str(i)

class RepoMovieText(RepoMovie):
    def __init__(self,filename):
        self.__filename=filename
        RepoMovie.__init__(self)
        self.__ReadAll()
    def add_movie(self, element):
        RepoMovie.add_movie(self,element)
        self.__saveToFile()
    def remove_movie(self,element):
        RepoMovie.remove_movie(self,element)
        self.__saveToFile()
    def update_movie(self,oldthing,element):
        RepoMovie.update_movie(self,oldthing,element)
        self.__saveToFile()
    def __ReadAll(self):
        with open(self.__filename,'r') as f:
            list=f.readlines()
            for i in list:
                i=i.strip()
                if i!="":
                    i=i.split(",")
                    id=int(i[0].strip())
                    name=i[1].strip()
                    desc=i[2].strip()
                    gen=i[3].strip()
                    movie=Movie(id,name,desc,gen)
                    self._movies.append(movie)
    def __saveToFile(self):
        with open(self.__filename,"w") as f:
            for i in self._movies:
                f.write(str(i.GetMovieID())+","+str(i.GetTitle())+","+str(i.GetDesc())+","+str(i.GetGenre())+"\n")

class RepoMoviePickle(RepoMovie):
    def __init__(self,filename):
        self.__filename=filename
        RepoMovie.__init__(self)
        self.__ReadAll()
    def add_movie(self, element):
        RepoMovie.add_movie(self,element)
        self.__saveToBinary()
    def remove_movie(self,element):
        RepoMovie.add_movie(self,element)
        self.__saveToBinary()
    def update_movie(self,oldthing,element):
        RepoMovie.update_movie(self,oldthing,element)
        self.__saveToBinary()
    def __ReadAll(self):
        try:
            self._movies=pickle.load(open(self.__filename,"rb"))
        except EOFError:
            self._movies=[]
    def __saveToBinary(self):
        pickle.dump(self._movies,open(self.__filename,"wb"))




class RepoClient(object):
    '''
    This class represents a client repository(crud operations made on a class of clients)
    '''
    def __init__(self):
        '''
        initialisation for the list of clients with nothing at first
        '''
        self._clients=[]

    def __len__(self):
        '''
        returns the length of the list
        '''
        return len(self._clients)

    def add_client(self, element):
        '''
        Function that adds the element to the list self.__clients
        input:element,self.__clients
        output:self.clients will be the new list with the added element
        '''

        if element in self._clients:
            raise RepoError("The client already exists!")
        else:
            self._clients.append(element)

    def search_client(self,element):
        '''
        Function that searches for the element in the self.__clients
        '''
        if element not in self._clients:
            raise RepoError("The client is not in the list!")
        else:
            for i in range(0, len(self._clients)):
                if self._clients[i]==element:
                    return self._clients[i]

    def searchById(self, idClient):
        for client in self._clients:
            if client == idClient:
                return client
        raise RepoError("Inexisting client!")

    def remove_client(self,element):
        '''
        Function that removes the element from the list self.__clients
        '''
        if element not in self._clients:
            raise RepoError("The client is not in the list!")
        else:
            for i in self._clients:
                if i==element:
                    self._clients.remove(i)

    def update_client(self,oldthing,element):
        '''
        Function that changes the oldthing with element in the self.__clients
        '''
        if oldthing not in self._clients:
            raise RepoError("The client is not in the list!")
        else:
            for i in range(0, len(self._clients)):
                if self._clients[i]==oldthing:
                    self._clients[i]=element



    def GetAll(self):
        '''
        Gets all the elements from the list self.__clients
        '''
        return self._clients[:]
    def removeAll(self):
        '''
        removes all elements from the list self.__clients
        '''
        self._clients.clear()

    def toStr(self):
        for i in range(0, len(self._clients)):
            return str(i)

class RepoClientText(RepoClient):
    def __init__(self,filename):
        self.__filename=filename
        RepoClient.__init__(self)
        self.__ReadAll()

    def add_client(self, element):
        RepoClient.add_client(self,element)
        self.__saveToFile()

    def remove_client(self,element):
        RepoClient.remove_client(self,element)
        self.__saveToFile()
    def update_client(self,oldthing,element):
        RepoClient.update_client(self,oldthing,element)
        self.__saveToFile()

    def __ReadAll(self):
        with open(self.__filename,"r")as f:
            list=f.readlines()
            for i in list:
                i=i.strip()
                if i!="":
                    i=i.split(",")
                    id=int(i[0].strip())
                    name=i[1].strip()
                    client=Client(id,name)
                    self._clients.append(client)
    def __saveToFile(self):
        with open(self.__filename,"w") as f:
            for i in self._clients:
                f.write(str(i.GetClientID())+","+str(i.GetName())+"\n")

class RepoClientPickle(RepoClient):
    def __init__(self,filename):
        self.__filename=filename
        RepoClient.__init__(self)
        self.__ReadAll()

    def add_client(self, element):
        RepoClient.add_client(self,element)
        self.__saveToBinary()

    def remove_client(self,element):
        RepoClient.remove_client(self,element)
        self.__saveToBinary()
    def update_client(self,oldthing,element):
        RepoClient.update_client(self,oldthing,element)
        self.__saveToBinary()


    def __ReadAll(self):
        try:
            self._clients=pickle.load(open(self.__filename,"rb"))
        except EOFError:
            self._clients=[]
    def __saveToBinary(self):
        pickle.dump(self._clients,open(self.__filename,"wb"))



class RepoRental(object):
    '''
    This class represents a rental repository(crud operations made on a class of rentals)
    '''
    def __init__(self):
        '''
        initialisation for the list of rentals with nothing at first
        '''
        self._rentals=[]

    def __len__(self):
        '''
        returns the length of the list
        '''
        return len(self._rentals)

    def add_rental(self, element):
        '''
        Function that adds the element to the list self.__rentals
        input:element,self.__rentals
        output:self.rentals will be the new list with the added element
        '''

        if element in self._rentals:
            raise RepoError("The rental already exists!")
        else:
            self._rentals.append(element)

    def search_rental(self,element):
        '''
        Function that searches for the element in the self.__rentals
        '''
        if element not in self._rentals:
            raise RepoError("The rental is not in the list!")
        else:
            for i in range(0, len(self._rentals)):
                if self._rentals[i]==element:
                    return self._rentals[i]

    def searchById(self, idRental):
        for rental in self._rentals:
            if rental == idRental:
                return rental
        raise RepoError("Inexisting rental!")

    def searchByMovieId(self, idmovie):
        for rental in self._rentals:
            if rental.GetMovieID() == idmovie:
                if rental.GetReturnedD()=='-':
                    raise RepoError("Unavailable movie!")
        return True

    def searchByClientId(self, idclient):
        for rental in self._rentals:
            if rental.GetClientID() == idclient:
                if rental.GetReturnedD()=='-':
                    CurrentDate = datetime.datetime.now().date()
                    ExpectedDate =rental.GetDueD()
                    if type(ExpectedDate)==str:
                        ExpectedDate=datetime.datetime.strptime(ExpectedDate, '%Y-%m-%d').date()

                    if CurrentDate > ExpectedDate:
                        raise RepoError("This client can't rent anymore books because the due date has passed without returning another book!")
        return True

    def searchByClientMovieId(self, idclient,idmovie):
        for rental in self._rentals:
            if rental.GetClientID() == idclient:
                if rental.GetMovieID() == idmovie:
                    return True
        raise RepoError("This client has not rented that movie! Invalid combination!")

    def getRentalId(self, idclient, idmovie):
        for rental in self._rentals:
            if rental.GetClientID() == idclient:
                if rental.GetMovieID() == idmovie:
                    return rental.GetRentalID()
        raise RepoError("Invalid combination!")

    def remove_rental(self,element):
        '''
        Function that removes the element from the list self.__rentals
        '''
        if element not in self._rentals:
            raise RepoError("The rental is not in the list!")
        else:
            for i in self._rentals:
                if i==element:
                    self._rentals.remove(i)


    def update_rental(self,oldthing,element):
        '''
        Function that changes the oldthing with element in the self.__rentals
        '''
        if oldthing not in self._rentals:
            raise RepoError("The rental is not in the list!")
        else:
            for i in range(0, len(self._rentals)):
                if self._rentals[i]==oldthing:
                    self._rentals[i]=element



    def GetAll(self):
        '''
        Gets all the elements from the list self.__rentals
        '''
        return self._rentals[:]
    def removeAll(self):
        '''
        removes all elements from the list self.__rentals
        '''
        self._rentals.clear()

    def toStr(self):
        for i in range(0, len(self._rentals)):
            return str(i)

class RepoRentalText(RepoRental):

    def __init__(self,filname):
        self.__filename=filname
        RepoRental.__init__(self)
        self.__ReadAll()
    def add_rental(self, element):
        RepoRental.add_rental(self,element)
        self.__saveToFile()
    def update_rental(self,oldthing,element):
        RepoRental.update_rental(self,oldthing,element)
        self.__saveToFile()
    def __ReadAll(self):
        with open(self.__filename,"r") as f:
            list=f.readlines()
            for i in list:
                i=i.strip()
                if i!="":
                    i=i.split(",")
                    idclient=int(i[2].strip())
                    idmovie=int(i[1].strip())
                    idrental=int(i[0].strip())
                    rented_date = i[3].strip()
                    due_date = i[4].strip()
                    returned_date = i[5].strip()
                    rent = Rental(idrental, idmovie, idclient, rented_date, due_date, returned_date)
                    self._rentals.append(rent)
    def __saveToFile(self):
        with open(self.__filename,"w") as f:
            for i in self._rentals:
                f.write(str(i.GetRentalID())+","+str(i.GetMovieID())+","+str(i.GetClientID())+","+str(i.GetRentD())+","+str(i.GetDueD())+","+str(i.GetReturnedD())+"\n")

class RepoRentalPickle(RepoRental):
    def __init__(self,filename):
        self.__filename=filename
        RepoRental.__init__(self)
        self.__ReadAll()
    def add_rental(self, element):
        RepoRental.add_rental(self,element)
        self.__saveToBinary()
    def update_rental(self,oldthing,element):
        RepoRental.update_rental(self,oldthing,element)
        self.__saveToBinary()

    def __ReadAll(self):
        try:
            self._rentals=pickle.load(open(self.__filename,"rb"))
        except EOFError:
            self._rentals=[]
    def __saveToBinary(self):
        pickle.dump(self._rentals,open(self.__filename,"wb"))



