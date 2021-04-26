--1.erase the first 5 clients
DELETE FROM Clients 
WHERE (Client_id>=7 AND Client_id<=11);


--2.The law says that people under 21 years old can't be managers, so we'll have to remove them from the manager list.
DELETE FROM Managers
WHERE MAge<21 AND MName IS NOT NULL AND MPhone IS NOT NULL;


--3.erase the stocks that have no more movies in them
DELETE FROM Stocks
WHERE Number_of_samples_for_movie=0
