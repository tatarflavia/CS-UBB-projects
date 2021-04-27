
from business.controller import *
from persistence.repo import RepoClient,RepoRental,RepoMovie

class test(unittest.TestCase):

    def setUp(self):
        unittest.TestCase.setUp(self)

    def tearDown(self):
        unittest.TestCase.tearDown(self)

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
        assert r1.GetClientID()==13
        assert r1.GetMovieID()==14
        assert r1.GetRentD()=='14-02-2018'
        assert r1.GetDueD()=='14-03-2018'
        assert r1.GetReturnedD()=='-'
        r1.updateReturnedD('14-04-2018')
        self.assertEqual(r1.GetReturnedD(),'14-04-2018')




    def test_repoRental(self):
        repo_list=RepoRental()
        assert repo_list.__len__()==0
        repo_list.add_rental({110,'A walk to remember','fantastic','romantic tragedy'})
        assert repo_list.GetAll()==[{110,'A walk to remember','fantastic','romantic tragedy'}]
        repo_list.add_rental({111, 'Captain America', 'marvelous', 'superhero'})
        repo_list.remove_rental({111, 'Captain America', 'marvelous', 'superhero'})
        assert repo_list.GetAll()==[{110,'A walk to remember','fantastic','romantic tragedy'}]
        assert repo_list.search_rental({110,'A walk to remember','fantastic','romantic tragedy'})=={110,'A walk to remember','fantastic','romantic tragedy'}
        repo_list.update_rental({110,'A walk to remember','fantastic','romantic tragedy'},{111, 'Captain America', 'marvelous', 'superhero'})
        assert repo_list.GetAll()==[{111, 'Captain America', 'marvelous', 'superhero'}]
        repo_list.removeAll()
        assert repo_list.GetAll()==[]

        repo_list1=RepoRental()
        rentalID=3
        idmovie=110
        idclient=91
        rented_date="2015-01-04"
        due_date="2015-02-03"
        returned_date="-"
        rent1 = Rental(rentalID, idmovie, idclient, rented_date, due_date, returned_date)
        rentalID = 4
        idmovie = 111
        idclient = 92
        rented_date = '2015-01-05'
        due_date = '2015-02-04'
        returned_date = "-"
        rent2 = Rental(rentalID, idmovie, idclient, rented_date, due_date, returned_date)
        repo_list1.add_rental(rent1)
        repo_list1.add_rental(rent2)
        assert repo_list.searchByMovieId(111)==True



    def test_RepoClient(self):
        repo_list=RepoClient()
        assert repo_list.__len__()==0
        repo_list.add_client({110,'A walk to remember','fantastic','romantic tragedy'})
        assert repo_list.GetAll()==[{110,'A walk to remember','fantastic','romantic tragedy'}]
        repo_list.add_client({111, 'Captain America', 'marvelous', 'superhero'})
        repo_list.remove_client({111, 'Captain America', 'marvelous', 'superhero'})
        assert repo_list.GetAll()==[{110,'A walk to remember','fantastic','romantic tragedy'}]
        assert repo_list.search_client({110,'A walk to remember','fantastic','romantic tragedy'})=={110,'A walk to remember','fantastic','romantic tragedy'}
        repo_list.update_client({110,'A walk to remember','fantastic','romantic tragedy'},{111, 'Captain America', 'marvelous', 'superhero'})
        assert repo_list.GetAll()==[{111, 'Captain America', 'marvelous', 'superhero'}]
        repo_list.removeAll()
        assert repo_list.GetAll()==[]

    def test_repoMovie(self):
        repo_list=RepoMovie()
        assert repo_list.__len__()==0
        repo_list.add_movie({110,'A walk to remember','fantastic','romantic tragedy'})
        assert repo_list.GetAll()==[{110,'A walk to remember','fantastic','romantic tragedy'}]
        repo_list.add_movie({111, 'Captain America', 'marvelous', 'superhero'})
        repo_list.remove_movie({111, 'Captain America', 'marvelous', 'superhero'})
        assert repo_list.GetAll()==[{110,'A walk to remember','fantastic','romantic tragedy'}]
        assert repo_list.search_movie({110,'A walk to remember','fantastic','romantic tragedy'})=={110,'A walk to remember','fantastic','romantic tragedy'}
        repo_list.update_movie({110,'A walk to remember','fantastic','romantic tragedy'},{111, 'Captain America', 'marvelous', 'superhero'})
        assert repo_list.GetAll()==[{111, 'Captain America', 'marvelous', 'superhero'}]
        repo_list.removeAll()
        assert repo_list.GetAll()==[]

    def test_movie_controller(self):
        pass

