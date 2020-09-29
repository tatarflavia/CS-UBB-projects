--transaction2: select+delay+select(dirty read: isoletion level: read Uncommitted)
--first wrong atempt
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED 
BEGIN TRAN
SELECT * FROM Customers
WAITFOR DELAY '00:00:15'
SELECT * FROM Customers
COMMIT TRAN