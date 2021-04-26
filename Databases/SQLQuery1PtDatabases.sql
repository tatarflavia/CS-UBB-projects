CREATE TABLE Shippers
(
[Shipper_id] TINYINT PRIMARY KEY ,
SName VARCHAR(20) NOT NULL
)

CREATE TABLE Stocks
(
[Stock_id] TINYINT PRIMARY KEY IDENTITY(1,1),
[Number_of_samples_for_movie] INT NOT NULL,
[Shipper_id] TINYINT REFERENCES [Shippers](Shipper_id)
	ON DELETE SET NULL
	ON UPDATE CASCADE
)

CREATE TABLE Languages
(
[Lang_id] TINYINT PRIMARY KEY IDENTITY(1,1),
LName VARCHAR(20) NOT NULL,
[Countries_that_speak] VARCHAR(100),
)

CREATE TABLE MovieCategories
(
[MCat_id] TINYINT PRIMARY KEY IDENTITY(1,1),
MCatName VARCHAR(20) NOT NULL,
ShortDesc VARCHAR(100)
)

CREATE TABLE Actors
(
[Actor_id] TINYINT PRIMARY KEY IDENTITY(1,1),
AName VARCHAR(20) NOT NULL,
Country VARCHAR(20),
Gender CHAR(1) CHECK(Gender='F' or Gender='M'),
[Played_in_Hollywood(Broadway)] VARCHAR(3) CHECK([Played_in_Hollywood(Broadway)]='Yes' or [Played_in_Hollywood(Broadway)]='No')
)

CREATE TABLE Speaking
(
Lang_id TINYINT REFERENCES [Languages](Lang_id)
	ON UPDATE CASCADE,
Actor_id TINYINT REFERENCES [Actors](Actor_id)
	ON UPDATE CASCADE,
Rate_of_knowing DECIMAL(5,2) CHECK(Rate_of_knowing>=0 and Rate_of_knowing<=100),
CONSTRAINT PK_Speaking PRIMARY KEY (Lang_id,Actor_id)
)


CREATE TABLE Movies
(
[Movie_id] TINYINT PRIMARY KEY IDENTITY(1,1),
MTitle VARCHAR(20) NOT NULL,
[Description] VARCHAR(100),
Rating CHAR(3) NOT NULL,
Length_minutes INT,
MCat_id TINYINT REFERENCES [MovieCategories](MCat_id)
	ON DELETE SET NULL
	ON UPDATE CASCADE,
Release_year INT NOT NULL,
Lang_id TINYINT REFERENCES [Languages](Lang_id)
	ON DELETE SET NULL
	ON UPDATE CASCADE,
Stock_id TINYINT REFERENCES [Stocks](Stock_id)
	ON DELETE SET NULL
	ON UPDATE CASCADE,
)

CREATE TABLE [Roles]
(
[Actor_id] TINYINT REFERENCES [Actors](Actor_id)
	ON UPDATE CASCADE,
Movie_id TINYINT REFERENCES [Movies](Movie_id)
	ON UPDATE CASCADE,
[Role_Description] VARCHAR(100),
Minutes_of_screentime INT,
Importancy CHAR(3),
CONSTRAINT PK_Roles PRIMARY KEY (Actor_id,Movie_id)
)


CREATE TABLE Managers
(
[Manager_id] TINYINT PRIMARY KEY IDENTITY(1,1),
MName VARCHAR(20) NOT NULL,
[MAge] TINYINT,
MPhone INT NOT NULL,
MEmail VARCHAR(20)
)

CREATE TABLE Employees
(
[Employee_id] TINYINT PRIMARY KEY IDENTITY(1,1),
EName VARCHAR(20) NOT NULL,
[EAge] TINYINT ,
[EAdress] VARCHAR(40) ,
EPhone INT NOT NULL,
EEmail VARCHAR(20) ,
Salary INT,
Manager_id TINYINT REFERENCES [Managers](Manager_id)
	ON DELETE SET NULL
	ON UPDATE CASCADE
)


CREATE TABLE ClientCategories
(
[ClientCat_id] TINYINT PRIMARY KEY IDENTITY(1,1),
ClientCatName VARCHAR(20) NOT NULL,
ShortDesc VARCHAR(100)
)

CREATE TABLE Clients
(
[Client_id] TINYINT PRIMARY KEY IDENTITY(1,1),
CName VARCHAR(20) NOT NULL,
CAge TINYINT,
CPhone INT NOT NULL,
CEmail VARCHAR(40) ,
CCity VARCHAR(40) ,
CStreet VARCHAR(40) ,
CStreetNumber TINYINT ,
CGender CHAR(1) CHECK(CGender='F' or CGender='M'),
ClientCat_id TINYINT REFERENCES [ClientCategories](ClientCat_id)
	ON DELETE SET NULL
	ON UPDATE CASCADE
)

CREATE TABLE ClientServices
(
Client_id TINYINT REFERENCES [Clients](Client_id)
	ON UPDATE CASCADE,
Employee_id TINYINT REFERENCES [Employees](Employee_id)
	ON UPDATE CASCADE,
[Date] DATE,
[Day] INT not null,
[Month] INT not null,
Sum_of_pay TINYINT NOT NULL,
Happiness_level TINYINT CHECK(Happiness_level>=1 and Happiness_level<=10),
CONSTRAINT PK_ClientServices PRIMARY KEY (Client_id,Employee_id)
)

CREATE TABLE Rentals
(
Client_id TINYINT REFERENCES [Clients](Client_id)
	ON UPDATE CASCADE,
Movie_id TINYINT REFERENCES [Movies](Movie_id)
	ON UPDATE CASCADE,
[Date] DATE,
[Day] INT not null,
[Month] INT not null,
[Duration_of_borrowing(in days)] INT NOT NULL,
CONSTRAINT PK_Rentals PRIMARY KEY (Client_id,Movie_id)
)

ALTER TABLE Clients
ADD [Can_rent_adult_movies] varchar(3);

ALTER TABLE Movies
ALTER COLUMN MTitle VARCHAR(50);


CREATE TABLE Cinema_room
(
Cinema_room_id TINYINT PRIMARY KEY IDENTITY(1,1),
[Nb_of_seats] INT,
[4DX_Screen] varchar(3)

)



CREATE TABLE AdultActivities
(
ActivityId TINYINT PRIMARY KEY IDENTITY(1,1),
[HourOfActivity] INT CHECK (HourOfActivity>=0), CHECK (HourOfActivity<=24),
[WeekDay] INT CHECK (WeekDay>=1), CHECK (WeekDay<=7),
[Name] varchar(100)
)

CREATE TABLE AdultClubs
(
ClubId TINYINT PRIMARY KEY IDENTITY(1,1),
ClubName varchar(100),
ClubType varchar(100),
[AgeRange] varchar(100),
[ParticipantsNumber] int CHECK (ParticipantsNumber>=1),
ActivityId TINYINT REFERENCES [AdultActivities](ActivityId)
	ON UPDATE CASCADE,
)




CREATE TABLE Activities
(
ActivityId TINYINT PRIMARY KEY IDENTITY(1,1),
[AName] varchar(200)
)



CREATE TABLE Rooms
(
RoomId TINYINT PRIMARY KEY IDENTITY(1,1),
[RType] varchar(200),
RNumber int
)


CREATE TABLE Programm
(
ProgrammId TINYINT PRIMARY KEY IDENTITY(1,1),
ActivityId TINYINT REFERENCES [Activities](ActivityId),
RoomId TINYINT REFERENCES [Rooms](RoomId),
[DateAndTime] DATETIME
)


