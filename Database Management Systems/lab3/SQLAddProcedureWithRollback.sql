CREATE FUNCTION uf_ValidateActivityName (@aName varchar(200)) RETURNS INT AS
BEGIN
DECLARE @return INT
SET @return = 0
IF(@aName IS NOT NULL AND @aName LIKE '_%')
	SET @return = 1
RETURN @return
END

go
CREATE FUNCTION uf_ValidateRoomType (@rType varchar(200)) RETURNS INT AS
BEGIN
DECLARE @return INT
SET @return = 0
IF(@rType IS NOT NULL AND @rType LIKE '_%')
	SET @return = 1
RETURN @return
END

go
CREATE FUNCTION uf_ValidateRoomNumber (@rNumber int) RETURNS INT AS
BEGIN
DECLARE @return INT
SET @return = 0
IF(@rNumber > 0 AND @rNumber < 201)
	SET @return = 1
RETURN @return
END

go
CREATE FUNCTION uf_ValidateDateTime (@dateAndTime varchar(200)) RETURNS INT AS
BEGIN
DECLARE @return INT
SET @return = 0
IF(ISDATE(@dateAndTime)=1)
	SET @return = 1
RETURN @return
END

go
CREATE or alter PROCEDURE AddProgrammsWith1Transaction @aName varchar(200),@rType varchar(200),@rNumber int,@dateAndTime varchar(200) AS
BEGIN

set nocount on;

BEGIN TRAN
	BEGIN TRY
		IF (dbo.uf_ValidateActivityName(@aName) = 0)
			BEGIN
				RAISERROR('Activity Name must not be empty',14,1)
			END

		IF dbo.uf_ValidateRoomType(@rType) = 0
			BEGIN
				RAISERROR('Room Type must not be empty',14,1)
			END

		IF dbo.uf_ValidateRoomNumber(@rNumber) = 0
			BEGIN
				RAISERROR('Room Number must be between 1 and 200',14,1)
			END
	
		IF dbo.uf_ValidateDateTime(@dateAndTime) = 0
			BEGIN
				RAISERROR('Date and time must be in the DATETIME format',14,1)
			END
	
		DECLARE @progDateTime datetime
		SET @progDateTime=(SELECT CONVERT(datetime, @dateAndTime))
	
		INSERT INTO Activities(AName) values(@aName)
		print 'Insert activity complete'
		INSERT INTO Rooms(RType,RNumber) values(@rType,@rNumber)
		print 'Insert room complete'

		DECLARE @activityId tinyint
		DECLARE @roomId tinyint
		SET @activityId=(select IDENT_CURRENT('Activities'))
		SET @roomId=(select IDENT_CURRENT('Rooms'))

		INSERT INTO Programm(ActivityId,RoomId,DateAndTime) VALUES (@activityId,@roomId,@progDateTime)
		print 'Insert programm complete'

		COMMIT TRAN
		SELECT 'Transaction committed'
	END TRY

	BEGIN CATCH
	ROLLBACK TRAN
	SELECT 'Transaction rollbacked'
	END CATCH
END


--scenarios::

--with success(commit):
SELECT * FROM Activities
SELECT * FROM Rooms
SELECT * FROM Programm

EXEC AddProgrammsWith1Transaction 'Old Star Wars Premiere','Cinema Room',20,'2019-06-10 17:00:00.000'

SELECT * FROM Activities
SELECT * FROM Rooms
SELECT * FROM Programm


--with error(rollback)
SELECT * FROM Activities
SELECT * FROM Rooms
SELECT * FROM Programm

EXEC AddProgrammsWith1Transaction '','',-2,'2019-06-11 20:00:00.000'

SELECT * FROM Activities
SELECT * FROM Rooms
SELECT * FROM Programm
