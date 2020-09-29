--transaction1: update+delay+rollback
BEGIN TRAN
UPDATE Actors SET Country='America' WHERE Actor_id=1
WAITFOR DELAY '00:00:10'
ROLLBACK TRAN