--transaction 2: select+ delay +select (isolation level : serializable): READS same DATA

SET TRANSACTION ISOLATION LEVEL SERIALIZABLE
BEGIN TRAN 
SELECT * FROM Actors
WAITFOR DELAY '00:00:05'
SELECT * FROM Actors
COMMIT TRAN