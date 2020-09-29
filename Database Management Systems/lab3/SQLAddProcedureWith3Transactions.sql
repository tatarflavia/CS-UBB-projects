CREATE or alter PROCEDURE AddActivities @aName varchar(200) AS
BEGIN

set nocount on;

BEGIN TRAN
	BEGIN TRY
		IF (dbo.uf_ValidateActivityName(@aName) = 0)
			BEGIN
				RAISERROR('Activity Name must not be empty',14,1)
			END
	
		INSERT INTO Activities(AName) values(@aName)
		print 'Insert activity complete'
		COMMIT TRAN
		SELECT 'Transaction(for activities table) committed'
		return 1
	END TRY

	BEGIN CATCH
	ROLLBACK TRAN
	SELECT 'Transaction(for activities table) rollbacked'
	return 0
	END CATCH
END


go
CREATE or alter PROCEDURE AddRooms @rType varchar(200),@rNumber int AS
BEGIN

set nocount on;

BEGIN TRAN
	BEGIN TRY
		IF dbo.uf_ValidateRoomType(@rType) = 0
			BEGIN
				RAISERROR('Room Type must not be empty',14,1)
			END

		IF dbo.uf_ValidateRoomNumber(@rNumber) = 0
			BEGIN
				RAISERROR('Room Number must be between 1 and 200',14,1)
			END
	
		INSERT INTO Rooms(RType,RNumber) values(@rType,@rNumber)
		print 'Insert room complete'
		COMMIT TRAN
		SELECT 'Transaction(for rooms table) committed'
		return 1
	END TRY

	BEGIN CATCH
	ROLLBACK TRAN
	SELECT 'Transaction(for rooms table) rollbacked'
	return 0
	END CATCH
END


go
CREATE or alter PROCEDURE AddProgramms @dateAndTime varchar(200) AS
BEGIN

set nocount on;

BEGIN TRAN
	BEGIN TRY

		IF dbo.uf_ValidateDateTime(@dateAndTime) = 0
			BEGIN
				RAISERROR('Date and time must be in the DATETIME format',14,1)
			END
	
		DECLARE @progDateTime datetime
		SET @progDateTime=(SELECT CONVERT(datetime, @dateAndTime))

		DECLARE @activityId tinyint
		DECLARE @roomId tinyint
		SET @activityId=(select IDENT_CURRENT('Activities'))
		SET @roomId=(select IDENT_CURRENT('Rooms'))

		INSERT INTO Programm(ActivityId,RoomId,DateAndTime) VALUES (@activityId,@roomId,@progDateTime)
		print 'Insert programm complete'

		COMMIT TRAN
		SELECT 'Transaction(for programm table) committed'
		return 1
	END TRY

	BEGIN CATCH
	ROLLBACK TRAN
	SELECT 'Transaction(for programm table) rollbacked'
	END CATCH
END


go
CREATE or alter PROCEDURE AddProgrammsWith3Transactions @aName varchar(200),@rType varchar(200),@rNumber int,@dateAndTime varchar(200) AS
BEGIN

set nocount on;

DECLARE @addActivityProcResult int
DECLARE @addRoomProcResult int
DECLARE @addProgrammProcResult int

exec @addActivityProcResult= dbo.AddActivities @aName
IF @addActivityProcResult = 1
	BEGIN
	
	exec @addRoomProcResult= dbo.AddRooms @rType,@rNumber

	IF @addRoomProcResult = 1
		BEGIN
		exec @addProgrammProcResult= dbo.AddProgramms @dateAndTime
		END

	END

END





--scenarios::

--with success(commit):
SELECT * FROM Activities
SELECT * FROM Rooms
SELECT * FROM Programm

EXEC AddProgrammsWith3Transactions 'Star Trek Premiere','Cinema Room',30,'2019-06-20 19:00:00.000'

SELECT * FROM Activities
SELECT * FROM Rooms
SELECT * FROM Programm


--with error(rollback)
SELECT * FROM Activities
SELECT * FROM Rooms
SELECT * FROM Programm

EXEC AddProgrammsWith3Transactions 'TVD Cast Memories','',-2,'2019-06-11 20:00:00.000'

SELECT * FROM Activities
SELECT * FROM Rooms
SELECT * FROM Programm

















