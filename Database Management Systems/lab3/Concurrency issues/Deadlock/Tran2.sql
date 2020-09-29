--transaction 2 : update MovieCat + delay + update actors

BEGIN TRAN
update MovieCategories set MCatName='Cat Deadlock tran2' where MCat_id=8
--tran2 has exclusively lock on Categories

WAITFOR DELAY '00:00:10'

UPDATE Actors set AName='Actor Deadlock tran2' where Actor_id=15
--tran2 will be blocked because tran1 has already blocked Actors=> both are blocked

commit tran

--TRAN2 will be the DEADLOCK VICTIM and terminates with an error => tran1 is saved

