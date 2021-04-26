
CREATE TABLE Actors
(
[Actor_id] INT PRIMARY KEY IDENTITY(1,1),
[IMDB_Score] INT UNIQUE NOT NULL,
AName VARCHAR(20) NOT NULL,
Country VARCHAR(20),
Gender CHAR(1) CHECK(Gender='F' or Gender='M'),
[Played_in_Hollywood(Broadway)] VARCHAR(3) CHECK([Played_in_Hollywood(Broadway)]='Yes' or [Played_in_Hollywood(Broadway)]='No')
)

CREATE TABLE Movies
(
[Movie_id] INT PRIMARY KEY IDENTITY(1,1),
MTitle VARCHAR(20) NOT NULL,
[Description] VARCHAR(100),
Rating CHAR(3) NOT NULL,
Length_minutes INT,
)

CREATE TABLE [Roles]
(
Role_id INT PRIMARY KEY IDENTITY(1,1),
[Actor_id] INT,
CONSTRAINT FK_Actor_Role FOREIGN KEY (Actor_id)
    REFERENCES Actors(Actor_id),
Movie_id INT ,
CONSTRAINT FK_Movie_Role FOREIGN KEY (Movie_id)
    REFERENCES Movies(Movie_id),
--CONSTRAINT pk_roles PRIMARY KEY (Actor_id,Movie_id),
[Role_Description] VARCHAR(100),
Minutes_of_screentime INT,
Importancy CHAR(3),
)
ALTER TABLE Movies
ALTER COLUMN MTitle VARCHAR(100);

INSERT INTO Actors([IMDB_Score],AName,Gender,[Played_in_Hollywood(Broadway)])
values
(50,'Jamie Campbell Bower','M','Yes'),
(60,'Lily Collins','F','Yes'),
(30,'Dane DeHaan','M','Yes'),
(5,'Tom Hiddleston','M','Yes'),
(6,'Chris Hemsworth','M','Yes'),
(7,'Chris Evans','M','Yes'),
(4,'Tom Holland','M','Yes'),
(90,'Antonio Banderas','M','No'),
(80,'Penélope Cruz','F','No');

INSERT INTO Movies(MTitle,Rating,[Length_minutes])
values
('The Mortal Instruments: City of Bones','B++',130),
('Thor','B++',116),
('Thor:The Dark World','B++',115),
('Thor: Ragnarok','A--',131),
('Spider-Man: Homecoming','A--',133),
('Spider-Man: Far from Home','A--',129),
('The Avengers','A--',143),
('Avengers: Infinity War','A--',149),
('Avengers: Endgame','A++',181),
('Pirates of the Caribbean: On Stranger Tides','A--',136),
('Volver','A--',121),
('The Mask of Zorro','A++',137)
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

SELECT * FROM Movies
SELECT * FROM Actors
SELECT * FROM Roles

--a. Write queries on Ta such that their execution plans contain the following operators

--clustered index scan; see all the actors that have a score bigger than 50(less talented)
SELECT A.Actor_id,A.AName
FROM Actors A 
WHERE A.IMDB_Score>50

SELECT M.Movie_id,M.MTitle
FROM Movies M
WHERE M.Length_minutes>100

--clustered index seek; see the actor that has the id=3
SELECT A.Actor_id,A.AName
FROM Actors A 
WHERE A.Actor_id=3

SELECT M.Movie_id,M.MTitle
FROM Movies M
WHERE M.Movie_id=4

--nonclustered index scan;
SELECT A.IMDB_Score
FROM Actors A


--nonclustered index seek;
SELECT A.Actor_id
FROM Actors A 
WHERE A.IMDB_Score=50

--key lookup.;
SELECT *
FROM Actors A 
WHERE A.IMDB_Score=50

--b. Write a query on table Tb with a WHERE clause of the form WHERE b2 = value and analyze its execution plan. 
--Create a nonclustered index that can speed up the query. Recheck the query’s execution plan (operators, SELECT’s estimated subtree cost).
SELECT M.Movie_id
FROM Movies M
WHERE M.Length_minutes=130

CREATE NONCLUSTERED INDEX IDX_NCL_MLenMin ON Movies(Length_minutes)
DROP INDEX IDX_NCL_MLenMin ON Movies;

--c. Create a view that joins at least 2 tables. Check whether existing indexes are helpful; if not, reassess existing indexes / examine the cardinality of the tables.
--see in which movies played every actor
GO
CREATE or alter VIEW view_index
AS
SELECT A.Actor_id,A.AName
FROM Actors A
INNER JOIN Roles R ON A.Actor_id=R.Actor_id
GO

--cl ind scan 19% on Roles
--cl ind scan 40% on Movies
--cl ind seek 40% on Actors
--total:0,0173478
SELECT * FROM view_index


SELECT * FROM view_index
WITH(INDEX(IDX_NCL_MLenMin))

--CREATE NONCLUSTERED INDEX IDX_NCL_ActorID ON Roles(Actor_id)
--DROP INDEX IDX_NCL_ActorID ON Roles;

--CREATE NONCLUSTERED INDEX IDX_NCL_MovieID ON Roles(Movie_id)
--DROP INDEX IDX_NCL_MovieID ON Roles;

CREATE UNIQUE NONCLUSTERED INDEX [IDX_NCL_ActorID] ON Roles(Actor_id,Movie_id)
DROP index [IDX_NCL_ActorID] on Roles;
go


SELECT * FROM view_index
WITH(INDEX(IDX_NCL_ActorID))



