--1.modify in 'yes' the 'can_rent_adult_movies_attribute' for clients over 18 
UPDATE Clients
SET [Can_rent_adult_movies] = 'Yes'
WHERE CAge >= 18;

--2.for the rest of them put 'no'
UPDATE Clients
SET [Can_rent_adult_movies] = 'No'
WHERE CAge	BETWEEN 0 AND 17;

--3.importancy for a role will be set to 'yes' if minutes of screentime>60  or if the actor playing is a hollywood star
UPDATE Roles
SET [Importancy] = 'Yes'
FROM Roles R, Actors A
WHERE R.Actor_id=A.Actor_id AND (R.Minutes_of_screentime>60 OR A.[Played_in_Hollywood(Broadway)]='Yes');

--4.exactly the opposite happens when the actor is not a hollywood star and their role < 60 mins
UPDATE Roles
SET [Importancy] = 'Yes'
FROM Roles R, Actors A
WHERE R.Actor_id=A.Actor_id AND (R.Minutes_of_screentime<60 AND A.[Played_in_Hollywood(Broadway)]='No');

--5.names of clients that have no surname will be completed with TBA
UPDATE Clients
SET [CName] = 'Matei Alin'
WHERE Client_id=13;

UPDATE Clients
SET [CName] = 'Matei Alina'
WHERE Client_id=14;

UPDATE Clients
SET [CName] = 'Matei'
WHERE Client_id=15;

UPDATE Clients
SET [CName] = 'Alina'
WHERE Client_id=16;

UPDATE Clients
SET [CName] = 'Andrei Maria'
WHERE Client_id=17;

UPDATE Clients
SET [CName] = CName+' TBA'
WHERE CName NOT LIKE '% %';

--6.update role description to be + hollywood star if that actor is in fact a hollywood star
UPDATE Roles
SET [Role_Description] = ' The actor is a Hollywood Star.'
FROM Roles R
WHERE R.Actor_id IN (
	SELECT A.Actor_id
	FROM Actors A
	WHERE A.[Played_in_Hollywood(Broadway)]='Yes'
);


UPDATE Stocks
SET [Number_of_samples_for_movie]=11
WHERE Stock_id=2

UPDATE Stocks
SET [Number_of_samples_for_movie]=12
WHERE Stock_id=3

UPDATE Stocks
SET [Number_of_samples_for_movie]=13
WHERE Stock_id=4


UPDATE Clients
SET CPhone=0731198675
WHERE Client_id=13

UPDATE Clients
SET CPhone=0734467453
WHERE Client_id=14

UPDATE Clients
SET CPhone=0748390044
WHERE Client_id=15

UPDATE Clients
SET CPhone=0734009921
WHERE Client_id=16

UPDATE Clients
SET CPhone=0744498275
WHERE Client_id=17
