--transaction 2 : update MovieCat + delay + update actors with deadlock priority High  => it will be the winner
SET DEADLOCK_PRIORITY HIGH
BEGIN TRAN
update MovieCategories set MCatName='Cat Deadlock tran2' where MCat_id=8
--tran2 has exclusively lock on Categories

WAITFOR DELAY '00:00:10'

UPDATE Actors set AName='Actor Deadlock tran2' where Actor_id=15

commit tran

--highest priority => 
--TRAN1 will be the DEADLOCK VICTIM and terminates with an error => tran2 is saved

select * from Actors
select * from MovieCategories

