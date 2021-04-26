USE [Movie Rental Database]


INSERT INTO ClientCategories(ClientCatName,ShortDesc)
values
('Student','enrolled in a faculty or school'),
('Pupil','younger than 7 years old'),
('Adult','employed and not enrolled in school anymore'),
('Old','has a pension')
;

INSERT INTO Clients(CName,CAge,CPhone,CGender,ClientCat_id)
values
('Popescu Ion',30,0731890087,'M',3),
('Avram Maria',17,0768905689,'F',1),
('Matei',12,0767778899,'M',2),
('Laura',14,0767578999,'F',2),
('Pop Maria',60,0734567899,'F',4)
;

--violates constraint 
INSERT INTO Clients(CName,CPhone,CGender,ClientCat_id)
values
('Alin Vasile',0258820456,'N',4);


INSERT INTO Shippers([Shipper_id],SName)
values
(1,'Toronto Film Prod'),
(2,'Los Angeles Studios'),
(3,'Supreme Studios'),
(4,'Finer Pictures');

INSERT INTO Shippers([Shipper_id], SName)
values
(5,'Prime Studios');

--violates pk constraint
INSERT INTO Shippers([Shipper_id],SName)
values
(1,'Funnky Studios');

INSERT INTO Stocks([Number_of_samples_for_movie],[Shipper_id])
values
(10,1),
(15,2),
(15,3),
(15,4),
(0,4),
(0,4),
(0,4);

INSERT INTO Stocks([Number_of_samples_for_movie],[Shipper_id])
values
(3,2);

INSERT INTO Languages(LName)
values
('English'),
('Spanish'),
('Romanian'),
('German'),
('French');

INSERT INTO MovieCategories(MCatName)
values
('Romance'),
('Fantasy'),
('Science Fiction'),
('Action'),
('Drama'),
('Musical'),
('Animation');

INSERT INTO Actors(AName,Gender,[Played_in_Hollywood(Broadway)])
values
('Jamie Campbell Bower','M','Yes'),
('Lily Collins','F','Yes'),
('Dane DeHaan','M','Yes'),
('Tom Hiddleston','M','Yes'),
('Chris Hemsworth','M','Yes'),
('Chris Evans','M','Yes'),
('Tom Holland','M','Yes');

INSERT INTO Actors(AName,Gender,[Played_in_Hollywood(Broadway)])
values
('Antonio Banderas','M','No'),
('Penélope Cruz','F','No');

INSERT INTO Speaking([Lang_id],[Actor_id],[Rate_of_knowing])
values
(1,1,100),
(1,2,100),
(1,3,100),
(1,4,100),
(1,5,100),
(1,6,100),
(1,7,100),
(2,8,100),
(2,9,100);

INSERT INTO Speaking([Lang_id],[Actor_id],[Rate_of_knowing])
values
(1,9,100);

INSERT INTO Movies(MTitle,Rating,[Length_minutes],[MCat_id],[Release_year],[Lang_id],[Stock_id])
values
('The Mortal Instruments: City of Bones','B++',130,1,2013,1,1),
('Thor','B++',115,4,2011,1,1),
('Thor:The Dark World','B++',115,4,2013,1,3),
('Thor: Ragnarok','A--',130,4,2017,1,3),
('Spider-Man: Homecoming','A--',133,4,2017,1,3),
('Spider-Man: Far from Home','A--',129,4,2019,1,2),
('The Avengers','A--',143,4,2012,1,2),
('Avengers: Infinity War','A--',149,4,2018,1,2),
('Avengers: Endgame','A++',181,4,2019,1,4),
('Pirates of the Caribbean: On Stranger Tides','A--',136,2,2011,1,4),
('Volver','A--',121,1,2006,2,4),
('The Mask of Zorro','A++',136,4,1998,2,4)
;

INSERT INTO Roles([Actor_id],[Movie_id],[Minutes_of_screentime])
values
(1,2,70),
(2,2,80),
(5,3,90),
(4,3,61),
(5,4,80),
(4,4,62),
(6,4,2),
(4,5,70),
(5,5,70),
(7,6,100),
(7,7,120),
(4,8,20),
(5,8,61),
(6,8,70),
(4,9,20),
(5,9,61),
(6,9,70),
(7,9,40),
(5,10,61),
(6,10,70),
(7,10,40),
(8,11,80),
(8,12,80),
(9,13,80)
;

INSERT INTO Managers(MName,MAge,MPhone)
values
('Pop Andrei',30,0731890087),
('Aneta Maria',18,0768905689),
('Matei Curca',19,0767778899),
('Laura Anut',22,0767578999)
;


INSERT INTO Employees(EName,EAge,EPhone,Salary,[Manager_id])
values
('Popa Mara',30,0731890087,3000,1),
('Ilinca Maria',18,0768905689,1200,1),
('Matei Ion',19,0767778899,1200,2),
('Laura Lavinia',20,0767578999,1200,2),
('Neluta Maria',23,0734567899,5000,3),
('Anita Marcu',45,0734567899,5000,3)
;

INSERT INTO Rentals([Client_id],[Movie_id],[Day],[Month],[Duration_of_borrowing(in days)])
values
(13,2,1,12,3),
(14,3,1,12,4),
(15,6,1,12,2),
(13,8,2,12,5),
(17,7,2,12,1),
(15,10,2,12,1)
;

INSERT INTO ClientServices([Client_id],[Employee_id],[Day],[Month],[Sum_of_pay],[Happiness_level])
values
(13,1,1,12,30,5),
(14,1,1,12,40,7),
(15,6,1,12,20,8),
(13,2,2,12,50,9),
(17,2,2,12,10,9),
(15,5,2,12,10,6)
;