--transaction 2: select+ delay +select (isolation level : repeatable read): READS DIFF DATA: phantom reads

SET TRANSACTION ISOLATION LEVEL REPEATABLE READ
BEGIN TRAN 
SELECT * FROM Actors
WAITFOR DELAY '00:00:05'
SELECT * FROM Actors
COMMIT TRAN