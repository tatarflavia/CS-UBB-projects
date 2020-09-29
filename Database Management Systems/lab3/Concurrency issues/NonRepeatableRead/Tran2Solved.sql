--transaction 2: select+ delay +select (isolation level : read repeatable read): READS SAME DATA: repeatable reads

SET TRANSACTION ISOLATION LEVEL REPEATABLE READ
BEGIN TRAN 
SELECT * FROM Actors
WAITFOR DELAY '00:00:05'
SELECT * FROM Actors
COMMIT TRAN