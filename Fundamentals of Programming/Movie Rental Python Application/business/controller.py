from domain.classes import *
from validators.validate import *
import datetime



#vezi de specificatii si teste
#
# class RepoUndo(object):
#     def __init__(self):
#         self.__operations=[]
#
#     def __len__(self):
#         return len(self.__operations)
#
#     def add_op(self,element):
#         self.__operations.append(element)
#     def get_operation(self,index):
#         for i in range(0,len(self.__operations)):
#             if i==index:
#                 return self.__operations[i]


#
# class Undo(object):
#     def __init__(self):
#         self.__index=0
#
#         self.__operations = []
#
#     def add_op(self,op):
#         self.__index=int(self.__index)+1
#         self.__operations.append(op)
#


    # def get_operation(self,index):
    #     print(str(index))
    #     print("lungimea aiococ")
    #     print(len(self.__operations))
    #     for i in range(0,len(self.__operations)):
    #         if i==index:
    #             print("se baga aici?")
    #             print(str(self.__operations))
    #             return self.__operations[i]
    # def Undo(self):
    #     # if self.__index==0:
    #     #     return None
    #     # else:
    #
    #     #op=self.get_operation(len(self.__operations))
    #     self.__index = self.__index - 1
    #
    #     if op.GetOp=='addMovie':
    #         ServMovie.remove_movie(op.GetEl1.GetMovieID())
    #     elif op.GetOp=='removeMovie':
    #         ServMovie.add_movie(op.GetEl1.GetMovieID,op.GetEl1.GetTitle,op.GetEl1.GetDesc,op.GetEl1.GetGenre)
    #     elif op.GetOp=='updateMovie':
    #         ServMovie.update_movie(op.GetEl2,op.GetEl1.GetMovieID,op.GetEl1.GetTitle,op.GetEl1.GetDesc,op.GetEl1.GetGenre)
    #     elif op.GetOp=='addClient':
    #         ServClient.remove_client(op.GetEl1.GetClientID)
    #     elif op.GetOp=='removeClient':
    #         ServClient.add_client(op.GetEl1.GetClientID,op.GetEl1.GetName)
    #     elif op.GetOp=='updateClient':
    #         ServClient.update_client(op.GetEl2.GetClientID,op.GetEl1.GetClientID,op.GetEl1.GetName)
    #
    #


class ServMovie(object):
    def __init__(self,validMovie,repoMovie):
        self.__validMovie=validMovie
        self.__repoMovie=repoMovie

        #self.__undo = Undo()
    def add_movie(self, movieID,movie_title,movie_desc,movie_gen):
        movie=Movie(movieID,movie_title,movie_desc,movie_gen)
        self.__validMovie.ValidateMovie(movie)
        self.__repoMovie.add_movie(movie)


        #op=Undo_operation("addMovie",movie,0)

        #self.__undo.add_op(op)


    def remove_movie(self,movieID):
        movie=self.__repoMovie.searchById(movieID)
        self.__repoMovie.remove_movie(movie)

        #op = Undo_operation("removeMovie", movie,0)
        #self.__undo.add_op(op)
    def update_movie(self, oldmovieID,newmovieID,newtitle,newdesc,newgen):

        oldmovie=self.__repoMovie.searchById(oldmovieID)
        newmovie=Movie(newmovieID,newtitle,newdesc,newgen)
        self.__repoMovie.update_movie(oldmovie,newmovie)

        #op = Undo_operation("updateMovie", oldmovie, newmovie)
        #self.__undo.add_op(op)

    def search_movie_id(self,movieID):
        movie = self.__repoMovie.searchById(movieID)
        return movie
    def search_movie(self,title):
        list=[]
        title=title.lower()
        for elem in self.__repoMovie.GetAll():
            if title in elem.GetTitle().lower() or title in elem.GetDesc().lower() or title in elem.GetGenre():
                list.append(elem)
        return list
    def getAll(self):
        return self.__repoMovie.GetAll()



class ServClient(object):
    def __init__(self,validClient,repoClient):
        self.__validClient=validClient
        self.__repoClient=repoClient
        #self.__undo = Undo()
    def add_client(self,clientID,client_name):
        client=Client(clientID,client_name)
        self.__validClient.validateClient(client)
        self.__repoClient.add_client(client)
        #op = Undo_operation("addClient", client, 0)
        #self.__undo.add_op(op)
    def getAll(self):
        return self.__repoClient.GetAll()
    def remove_client(self,clientID):
        client=self.__repoClient.searchById(clientID)
        self.__repoClient.remove_client(client)
        #op = Undo_operation("removeClient", client, 0)
        #self.__undo.add_op(op)
    def update_client(self, oldclientID,newclientID,newname):
        oldclient=self.__repoClient.searchById(oldclientID)
        newclient=Client(newclientID,newname)
        self.__repoClient.update_client(oldclient,newclient)
        #op = Undo_operation("updateClient", oldclient, newclient)
        #self.__undo.add_op(op)

    def search_client_id(self,clientID):
        client = self.__repoClient.searchById(clientID)
        return client
    def search_client(self,name):
        list=[]
        name=name.lower()
        for elem in self.__repoClient.GetAll():
            if name in elem.GetName().lower():
                list.append(elem)
        return list

class ServRental(object):
    def __init__(self,validRental,repoRental,repoMovie,repoClient):
        self.__validRental=validRental
        self.__repoRental=repoRental
        self.__repoMovie=repoMovie
        self.__repoClient=repoClient


    def add_rent(self,idclient,idmovie):
        client=self.__repoClient.searchById(idclient)
        movie=self.__repoMovie.searchById(idmovie)

        if self.__repoRental.searchByMovieId(idmovie)==True and self.__repoRental.searchByClientId(idclient)==True:
            rentalID=len(self.__repoRental.GetAll())+1
            idclient=int(idclient)
            idmovie=int(idmovie)

            start_date=datetime.date(2015,1,1)
            rented_date = start_date+datetime.timedelta(rentalID)
            due_date=rented_date + datetime.timedelta(30)
            returned_date='-'
            rent=Rental(rentalID,idmovie,idclient,rented_date,due_date,returned_date)

            self.__validRental.validateRental(rent)
            self.__repoRental.add_rental(rent)



    def return_movie(self,idclient,idmovie):
        client = self.__repoClient.searchById(idclient)
        movie = self.__repoMovie.searchById(idmovie)

        if self.__repoRental.searchByClientMovieId(idclient,idmovie)==True:
            CurrentDate = datetime.datetime.now().date()
            rentalId=self.__repoRental.getRentalId(idclient,idmovie)
            oldrental=self.__repoRental.searchById(rentalId)
            newrental=Rental(rentalId,idmovie,idclient,oldrental.GetRentD(),oldrental.GetDueD(),CurrentDate)
            self.__repoRental.update_rental(oldrental,newrental)

    def get_late_rentals(self):
        lateRentals=[] #list for the statistics
        keylist=[] #list that will hold dictionaries like {movie id, number of delay days}
        rentals=self.__repoRental.GetAll()
        for r in rentals:
            current_time=datetime.datetime.now().date()
            if (r.GetDueD()) == str:
                duedate = datetime.datetime.strptime(r.GetDueD(), '%Y-%m-%d').date()
            else:
                duedate=r.GetDueD()
            if r.GetReturnedD()=='-' and current_time>duedate:
                days_delay=current_time-duedate
                days=int(days_delay.days)
                movie=self.__repoMovie.searchById(r.GetMovieID())
                moviename=movie.GetTitle()
                entity=lateRentalsDTO(moviename,days)
                keylist.append(entity)
        keylist.sort(key=lambda x:x.getDays(),reverse=True)
        return keylist

    def get_all_rentals(self):
        allRent=[] #list for the statistics
        keylist=[] #list that will hold dictionaries like {moviename}
        rentals=self.__repoRental.GetAll()
        for r in rentals:
            if r.GetReturnedD()=='-':
                movie=self.__repoMovie.searchById(r.GetMovieID())
                name=movie.GetTitle()
                entity=allMovieDTO(name)
                keylist.append(entity)
        keylist.sort(key=lambda  x:x.getName(),reverse=False)
        return keylist

    def most_rented_movies(self):
        keylist=[] #list that will hold dictionaries like {movieid,moviename,nr of times,nr of days}
        rentals=self.__repoRental.GetAll()
        movies=self.__repoMovie.GetAll()
        for m in movies:
            entity=mostRentDTO(m.GetMovieID(),m.GetTitle(),0,0)
            keylist.append(entity)
        for r in rentals:
            movieId=r.GetMovieID()
            for k in keylist:
                if k.getMovieID()==movieId:
                    times=k.getNrTimes()
                    times+=1
                    k.setNrTimes(times)
                    days=k.getNrDays()
                    if r.GetReturnedD() != '-':
                        if type(r.GetRentD()) == str:
                            rentdate = datetime.datetime.strptime(r.GetRentD(), '%Y-%m-%d').date()
                        else:
                            rentdate = r.GetRentD()
                        if type(r.GetReturnedD()) == str:
                            retdate = datetime.datetime.strptime(r.GetReturnedD(), '%Y-%m-%d').date()
                        else:
                            retdate = r.GetReturnedD()
                        days+=int((retdate-rentdate).days)
                    k.setNrDays(days)
        keylist.sort(key=lambda x:x.getNrTimes(),reverse=True)
        keylist.sort(key=lambda x:x.getNrDays(),reverse=True)
        return keylist







    def getAll(self):
        return self.__repoRental.GetAll()






