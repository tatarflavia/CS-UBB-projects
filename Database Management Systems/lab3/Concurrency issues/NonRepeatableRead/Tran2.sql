--transaction 2: select+ delay +select (isolation level : read commited): READS DIFF DATA: non repeatable reads

SET TRANSACTION ISOLATION LEVEL READ COMMITTED
BEGIN TRAN 
SELECT * FROM Actors
WAITFOR DELAY '00:00:05'
SELECT * FROM Actors
COMMIT TRAN