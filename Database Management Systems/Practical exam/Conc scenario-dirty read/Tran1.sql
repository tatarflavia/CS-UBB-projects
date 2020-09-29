--transaction1: update+delay+rollback in the isolation level read committed: the default one for sql server
--transaction2: select+delay+select in the isolation level read uncommitted and then for the solved one read committed
--Tran 1 updates a row with id 1, so it will acquire an Xlock on that row for writing it(because all levels aquire an Xlock for writing), then it waits: tran is still on going
--then tran 2 begins and as it is in the read uncommitted it will not need any lock for reading from the customers table, so no Slock required, so it can read
--this is when the dirty read scenario happens, because tran2 can read data changed by another ongoing tran (T1) which means dirty read of data
--then it reads again after tran 1 rollbacked and the row will not be Flavia, it will be Marian, as it was before tran1 changed data
--the solve is changing the isolation level to read commited because then Slock would be required for reading data, and as T1 has aquired an Xlock on this row, 
--Tran2 can't read the row until tran1 releases the lock (when the transaction finishes)

--tran1: changes row from Marian to Flavia
BEGIN TRAN
UPDATE Customers SET CName='Flavia' WHERE CustID=1
WAITFOR DELAY '00:00:10'
ROLLBACK TRAN


--UPDATE Customers SET CName='Marian' WHERE CustID=1