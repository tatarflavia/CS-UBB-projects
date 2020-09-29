--transaction 1 : update actors + delay + update MovieCat with DEADLOCK PRIORITY=NORMAL => it will be chosen as the deadlock victim

BEGIN TRAN
UPDATE Actors set AName='Actor Deadlock tran1' where Actor_id=15
--tran1 has exclusively lock on table Actors

WAITFOR DELAY '00:00:10'

update MovieCategories set MCatName='Cat Deadlock tran1' where MCat_id=8
--tran1 will be the deadlock victim because it has the lowest priority

commit tran




