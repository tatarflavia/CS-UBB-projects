--transaction2 SOLUTION: select+delay+select(isolation level:read commited)
--solution
SET TRANSACTION ISOLATION LEVEL READ COMMITTED 
BEGIN TRAN
SELECT * FROM Customers
WAITFOR DELAY '00:00:15'
SELECT * FROM Customers
COMMIT TRAN