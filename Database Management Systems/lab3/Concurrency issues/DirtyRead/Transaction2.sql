--transaction2: select+delay+select(dirty read: isoletion level: read Uncommitted)
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED 
BEGIN TRAN
SELECT * FROM Actors
WAITFOR DELAY '00:00:15'
SELECT * FROM Actors
COMMIT TRAN