from persistence.repo import *
from domain.classes import *
from validators.validate import *
from business.controller import *
from ui.console import console




#     # RepoUndo=RepoUndo()
#     # undo=Undo(RepoUndo)
#
#
#
#     #we put some elements in the client and the movie list
#     servMovie.add_movie(110,'A walk to remember','fantastic','romantic tragedy')
#     servMovie.add_movie(111, 'Captain America', 'marvelous', 'superhero')
#     servMovie.add_movie(112, 'Clockwork Orange', 'ultra violence', 'drama')
#     servMovie.add_movie(113,'Princess Switch','romance at its finest','romance')
#     servMovie.add_movie(114,'Captain America:Civil War','incredible','superhero marvel')
#     servMovie.add_movie(115,'City of bones','miraculous','fantasy')
#     servMovie.add_movie(117,'Avengers','fantastic','romantic tragedy')
#     servMovie.add_movie(116,'Satantango(HU)','too long','drama')
#     servMovie.add_movie(118,'A walk to remember','fantastic','romantic tragedy')
#     servMovie.add_movie(119,'A walk to remember','fantastic','romantic tragedy')
#
#     servClient.add_client(90,'John')
#     servClient.add_client(91,'Hugh')
#     servClient.add_client(92,'Melissa')
#     servClient.add_client(93,'Alex')
#     servClient.add_client(94,'Tony')
#     servClient.add_client(95,'Red')
#     servClient.add_client(96,'Aly')
#     servClient.add_client(97,'Max')
#     servClient.add_client(98,'Isac')
#     servClient.add_client(99,'Greg')
#
        # servRental.add_rent(96, 119)
        # servRental.add_rent(92, 117)
        # servRental.return_movie(96, 119)
        # servRental.add_rent(93, 112)
        # servRental.add_rent(94, 113)
        # servRental.return_movie(94, 113)
        # servRental.add_rent(97, 119)
        # servRental.add_rent(98, 116)
        # servRental.return_movie(97, 119)
#
#
#     #initialisation for the UI
#     UI=console(servMovie,servClient,servRental)
#
#     #we start the user
#     UI.run()

class Ap_start:
    def __init__(self,settingsFile):
        self.__settingsFile=settingsFile
        self.__settings=self.ReadSettingsFile()
    def ReadSettingsFile(self):
        settings={}
        with open(self.__settingsFile,"r") as f:
            lines=f.readlines()
            for i in lines:
                i=i.strip()
                if i!= "":
                    line=i.split("=")
                    settings[line[0].strip()]=line[1].strip()
        return settings
    def GenerateApp(self):
        if self.__settings["repository"]=="inmemory":
            repoMovie = RepoMovie()
            repoClient=RepoClient()
            repoRental=RepoRental()
        elif self.__settings["repository"]=="textfile":
            print("aici")
            repoMovie=RepoMovieText(self.__settings["movies"])
            repoClient = RepoClientText(self.__settings["clients"])
            repoRental=RepoRentalText(self.__settings["rentals"])
        elif self.__settings["repository"]=="binaryfile":
            repoMovie=RepoMoviePickle(self.__settings["movies"])
            repoClient=RepoClientPickle(self.__settings["clients"])
            repoRental=RepoRentalPickle(self.__settings["rentals"])



        validMovie = ValidMovie()
        servMovie=ServMovie(validMovie,repoMovie)
        validClient = ValidClient()
        servClient=ServClient(validClient,repoClient)
        validRental = ValidRental()
        servRental=ServRental(validRental,repoRental,repoMovie,repoClient)

        if self.__settings["repository"]=="inmemory"  :
            servMovie.add_movie(110, 'A walk to remember', 'fantastic', 'romantic tragedy')
            servMovie.add_movie(111, 'Captain America', 'marvelous', 'superhero')
            servMovie.add_movie(112, 'Clockwork Orange', 'ultra violence', 'drama')
            servMovie.add_movie(113, 'Princess Switch', 'romance at its finest', 'romance')
            servMovie.add_movie(114, 'Captain America:Civil War', 'incredible', 'superhero marvel')
            servMovie.add_movie(115, 'City of bones', 'miraculous', 'fantasy')
            servMovie.add_movie(117, 'Avengers', 'fantastic', 'romantic tragedy')
            servMovie.add_movie(116, 'Satantango(HU)', 'too long', 'drama')
            servMovie.add_movie(118, 'A walk to remember', 'fantastic', 'romantic tragedy')
            servMovie.add_movie(119, 'A walk to remember', 'fantastic', 'romantic tragedy')

            servClient.add_client(90, 'John')
            servClient.add_client(91, 'Hugh')
            servClient.add_client(92, 'Melissa')
            servClient.add_client(93, 'Alex')
            servClient.add_client(94, 'Tony')
            servClient.add_client(95, 'Red')
            servClient.add_client(96, 'Aly')
            servClient.add_client(97, 'Max')
            servClient.add_client(98, 'Isac')
            servClient.add_client(99, 'Greg')

            servRental.add_rent(96, 119)
            servRental.add_rent(92, 117)
            servRental.return_movie(96, 119)
            servRental.add_rent(93, 112)
            servRental.add_rent(94, 113)
            servRental.return_movie(94, 113)
            servRental.add_rent(97, 119)
            servRental.add_rent(98, 116)
            servRental.return_movie(97, 119)



        UI = console(servMovie, servClient, servRental)
        return UI

apGen=Ap_start("system.properties")
UI=apGen.GenerateApp()
UI.run()



