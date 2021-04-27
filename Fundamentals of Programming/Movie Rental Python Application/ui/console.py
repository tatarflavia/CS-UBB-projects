
from ui.validation import valid
#from business.controller import Undo
from errors.errors import RepoError,ValidError

class console(object):
    def __init__(self,servMovie,servClient,servRental):
        self.__servMovie=servMovie
        self.__servClient=servClient
        self.__servRental=servRental
        #self.__undo=Undo()


    def ui_add_movie(self,command):
        if len(command) != 6:
            print("Invalid number of params!")
            return
        else:
            movieID = int(command[2])
            if isinstance(movieID, int):
                movie_title=command[3]
                movie_desc=command[4]
                movie_gen=command[5]
                self.__servMovie.add_movie(movieID,movie_title,movie_desc,movie_gen)
            else:
                raise ValueError("The movie id is not a number!")
    def ui_add_client(self,command):
        if len(command) != 4:
            print("Invalid number of params!")
            return
        else:
            clientID = int(command[2])
            if isinstance(clientID, int):
                client_name=command[3]
                self.__servClient.add_client(clientID,client_name)
            else:
                raise ValueError("The client id is not a number!")
    def ui_remove_movie(self,command):
        if len(command) != 3:
            print("Invalid number of params!")
            return
        else:
            movieID=int(command[2])
            if isinstance(movieID, int):
                self.__servMovie.remove_movie(movieID)
            else:
                raise ValueError("The movie id is not a number!")
    def ui_remove_client(self,command):
        if len(command) != 3:
            print("Invalid number of params!")
            return
        else:
            clientID=int(command[2])
            if isinstance(clientID, int):
                self.__servClient.remove_client(clientID)
            else:
                raise ValueError("The movie id is not a number!")

    def ui_update_movie(self, command):
        if len(command) != 8:
            print("Invalid number of params!")
            return
        else:

            oldmovieID=int(command[2])
            newmovieID=int(command[4])
            newtitle=command[5]
            newdesc=command[6]
            newgen=command[7]
            self.__servMovie.update_movie(oldmovieID,newmovieID,newtitle,newdesc,newgen)


    def ui_update_client(self, command):
        if len(command) != 6:
            print("Invalid number of params!")
            return
        else:
            oldclientID=int(command[2])
            newclientID=int(command[4])
            newname=command[5]
            self.__servClient.update_client(oldclientID,newclientID,newname)



    def ui_search_movie(self,command):
        if len(command)!=2:
            raise ValueError("Not enough params for searching movies!")
        else:
            title=command[1]
            if title.isdigit():
                movieID = int(command[1])
                movie = self.__servMovie.search_movie_id(movieID)
                print("The movie by id " + str(movieID) + " is:" + str(movie))
            else:
                list=self.__servMovie.search_movie(title)
                for i in list:
                    print(i)

    def ui_search_client(self,command):
        if len(command)!=2:
            raise ValueError("Not enough params for searching clients!")
        else:
            name=command[1]
            if name.isdigit():
                clientID = int(command[1])
                client = self.__servClient.search_client_id(clientID)
                print("The client by id " + str(clientID) + " is:" + str(client))
            else:
                list=self.__servClient.search_client(name)
                for i in list:
                    print(i)

    def ui_rent(self,command):
        if len(command)!=3:
            raise ValueError("Not enough info for renting!")
        else:
            idclient=int(command[1])
            idmovie=int(command[2])
            self.__servRental.add_rent(idclient,idmovie)


    def ui_return(self,command):
        if len(command)!=3:
            raise ValueError("Not enough info for returning!")
        else:
            idclient=int(command[1])
            idmovie=int(command[2])
            self.__servRental.return_movie(idclient,idmovie)



    # def ui_undo(self,command):
    #     if len(command)!=1:
    #         raise ValueError("Not enough params!")
    #     else:
    #
    #         self.__undo.Undo()

    def ui_late_rentals(self,command):
        if len(command)!=2:
            raise ValueError("Not enough params for late rentals statistics!")
        else:
            lateRent=self.__servRental.get_late_rentals()
            for i in lateRent:
                print(str(i))

    def ui_all_rentals(self,command):
        if len(command)!=2:
            raise ValueError("Not enough params for all rentals statistics!")
        else:
            allRent=self.__servRental.get_all_rentals()
            for i in allRent:
                print(i)

    def ui_most_rented_movies(self,command):
        if len(command)!=2:
            raise ValueError("Not enough params for most rented movies statistics!")
        else:
            mostRent=self.__servRental.most_rented_movies()
            for i in mostRent:
                print(i)


    def ui_list_movie(self):
        movie=self.__servMovie.getAll()
        for i in movie:
            print(i)
    def ui_list_client(self):
        client=self.__servClient.getAll()
        for i in client:
            print(i)

    def ui_list_rental(self):
        rental = self.__servRental.getAll()
        for i in rental:
            print(i)


    def menu(self):
        print("List of commands:")
        print("-add movie <movieId>, <title>, <description>, <genre>")
        print("-add client  <clientId>, <name>")
        print("-update movie <movieId>  with <newID> <newtitle> <newdesc> <newgenre>")
        print("-update client <clientId> with <newid> <newname>")
        print("-list movie")
        print("-list client")
        print("-list rental")
        print("-remove movie <movieId>")
        print("-remove client <clientId>")
        print("-searchMovie <input>")
        print("-searchClient <input>")
        print("-rent <idclient> <idmovie>")
        print("-return <idclient> <idmovie>")
        print("-statistics (lateRentals)/(allRentals),(mostRentedMovies)")

    def run(self):
        self.menu()
        while True:

            x=input("Please give a command from the menu above:")
            command=x.split(" ")
            v=valid()
            if command[0]=='exit':
                return
            else:
                try:
                    if command[0]=='add':
                        if v.validAdd(command)==True:
                            if command[1]=='movie':
                                self.ui_add_movie(command)
                            elif command[1]=='client':
                                self.ui_add_client(command)
                            else:
                                print("Invalid command! Please try again!")
                        else:
                            print("Invalid typo! Please try again!")
                    elif command[0]=='list':
                        if v.validPrint(command)==True:

                            if command[1]=='movie':
                                self.ui_list_movie()
                            elif command[1]=='client':
                                self.ui_list_client()
                            elif command[1]=='rental':
                                self.ui_list_rental()
                            else:
                                print("Invalid command! Please try again!")
                        else:
                            print("Invalid typo! Please try again!")

                    elif command[0]=='remove':
                        if v.validRemove(command)==True:
                            if command[1]=='movie':
                                self.ui_remove_movie(command)
                            elif command[1]=='client':
                                self.ui_remove_client(command)
                            else:
                                print("Invalid command! Please try again!")
                        else:
                            print("Invalid typo! Please try again!")
                    elif command[0]=='update':
                        if v.validUpdate(command)==True:
                            if command[1]=='movie':

                                self.ui_update_movie(command)
                            elif command[1] == 'client':

                                self.ui_update_client(command)
                            else:
                                print("Invalid command! Please try again!")
                        else:
                            print("Invalid typo! Please try again!")

                    elif command[0]=='searchMovie':
                        self.ui_search_movie(command)
                    elif command[0]=='searchClient':
                        self.ui_search_client(command)
                    elif command[0]=='rent':
                        self.ui_rent(command)
                    elif command[0]=='return':
                        self.ui_return(command)
                    # elif command[0]=='undo':
                    #     self.ui_undo(command)
                    elif command[0]=='statistics':
                        if command[1]=='lateRentals':
                            self.ui_late_rentals(command)
                        elif command[1]=='allRentals':
                            self.ui_all_rentals(command)
                        elif command[1]=='mostRentedMovies':
                            self.ui_most_rented_movies(command)
                        else:
                            print("Invalid typo!Try again for statistics!")
                    else:
                        print("Invalid command! Please try again!")

                except ValueError as ve:
                    print(ve)
                except RepoError as re:
                    print(re)
                except ValidError as va:
                    print(va)

