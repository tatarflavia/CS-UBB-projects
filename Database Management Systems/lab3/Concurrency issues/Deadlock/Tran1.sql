--transaction 1 : update actors + delay + update MovieCat

BEGIN TRAN
UPDATE Actors set AName='Actor Deadlock tran1' where Actor_id=15
--tran1 has exclusively lock on table Actors

WAITFOR DELAY '00:00:10'

update MovieCategories set MCatName='Cat Deadlock tran1' where MCat_id=8
--tran1 will be blocked because tran2 has already blocked Categories

commit tran




select * from Actors
select * from MovieCategories



--insert into Actors(AName) values ('Actor Deadlock')
--insert into MovieCategories(MCatName) values ('Cat Deadlock')


--update Actors set AName='Actor Deadlock' where Actor_id=15
--update MovieCategories set MCatName='Cat Deadlock' where MCat_id=8