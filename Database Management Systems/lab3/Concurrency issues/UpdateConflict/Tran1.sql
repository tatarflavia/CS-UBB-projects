--update Conflict: tran1 changes a value and commits it ,  in the meantime trans2 changes it too AND WANTS ALSO TO COMMIT 
--normally, the value read should be the value after the change in tran1
--isolation level=SNAPSHOT specifies that data read within a transaction will never reflect changes made by other simultanous transactions.
--the tran uses the data row versions that exist when the transaction begins




alter database [Movie Rental Database] SET ALLOW_SNAPSHOT_ISOLATION ON USE [Movie Rental Database] 
GO
WAITFOR DELAY '00:00:10'
BEGIN TRAN
UPDATE CurrentVersionOfDatabase set DB_Current_version=4 where DB_ID=0
WAITFOR DELAY '00:00:10'
COMMIT TRAN

--update CurrentVersionOfDatabase set DB_Current_version=1 where DB_ID=0