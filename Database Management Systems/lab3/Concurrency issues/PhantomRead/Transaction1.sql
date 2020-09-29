--TRANSACTION 1 : delay+insert+commit

BEGIN TRAN
WAITFOR DELAY '00:00:04'
INSERT INTO Actors(AName,Gender) VALUES('Emma Watson','F')
COMMIT TRAN


--delete from Actors where AName='Emma Watson'
