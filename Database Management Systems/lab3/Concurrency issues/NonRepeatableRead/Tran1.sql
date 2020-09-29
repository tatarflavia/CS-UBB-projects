--TRANSACTION 1: insert + delay + update

INSERT INTO Actors(AName,Gender,[Played_in_Hollywood(Broadway)]) VALUES ('Rose Reynolds','F','No');
BEGIN TRAN
WAITFOR DELAY '00:00:05'
UPDATE Actors SET Country='America' where AName='Rose Reynolds'
COMMIT TRAN


--delete from Actors where AName='Rose Reynolds'