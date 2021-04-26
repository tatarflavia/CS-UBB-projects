--CreateATable PROC
CREATE PROC Procedure1
AS
CREATE TABLE Movie_Showtime(
	[Day] TINYINT,
	[Month] TINYINT,
	[Movie_id] TINYINT NOT NULL,
	[Cinema_room_id] TINYINT NOT NULL,
	[3D_Screening] VARCHAR(3),
	[Date] DATE,
	CONSTRAINT PK_MovieShowtime
	PRIMARY KEY (Movie_id,Cinema_room_id)
)
GO

--DropATable proc
CREATE PROC Procedure1UNDO
AS
DROP TABLE Movie_Showtime;
GO

-- AddAForeignKey PROC
CREATE PROC Procedure2
AS
BEGIN
ALTER TABLE Movie_Showtime
ADD CONSTRAINT FK_MovieID
FOREIGN KEY (Movie_id) REFERENCES [Movies](Movie_id);
ALTER TABLE Movie_Showtime
ADD CONSTRAINT FK_CinemaRoomID
FOREIGN KEY (Cinema_room_id) REFERENCES [Cinema_room](Cinema_room_id);
END
GO

--dropAForeignKey PROC
CREATE PROC Procedure2UNDO
AS
BEGIN
ALTER TABLE Movie_Showtime
DROP CONSTRAINT FK_MovieID;
ALTER TABLE Movie_Showtime
DROP CONSTRAINT FK_CinemaRoomID;
END
GO

--AddAPrimaryKey proc
CREATE PROC Procedure3
AS
BEGIN
ALTER TABLE Movie_Showtime
DROP CONSTRAINT PK_MovieShowtime;
ALTER TABLE Movie_Showtime
ADD CONSTRAINT PK_Showtime
PRIMARY KEY (Movie_id,Cinema_room_id)
END
GO

--dropAPrimaryKey proc
CREATE PROC Procedure3UNDO
AS
BEGIN
ALTER TABLE Movie_Showtime
DROP CONSTRAINT PK_Showtime;
END
GO
--AddACandidateKey proc
CREATE PROC Procedure4
AS
BEGIN
ALTER TABLE Movie_Showtime
ADD RoomName VARCHAR(40) NOT NULL;
END
GO
--RemoveACandidateKey proc
CREATE PROC Procedure4UNDO
AS
BEGIN
ALTER TABLE Movie_Showtime
DROP COLUMN RoomName;
END
GO


--AddADefaultConstraint proc
CREATE PROC Procedure5
AS
BEGIN
ALTER TABLE Movie_Showtime
ADD CONSTRAINT DEF_3D
DEFAULT 'No' FOR [3D_Screening] ;
END
GO
--DropADefaultConstraint proc
CREATE PROC Procedure5UNDO
AS
BEGIN
ALTER TABLE Movie_Showtime
DROP CONSTRAINT DEF_3D;
END
GO

--AddAColumn proc
CREATE PROC Procedure6
AS
BEGIN
ALTER TABLE Movie_Showtime
ADD [Year] INT;
END
GO
--RemoveAColumn proc
CREATE PROC Procedure6UNDO
AS
BEGIN
ALTER TABLE Movie_Showtime
DROP COLUMN [Year];
END
GO

--ModifyTheTypeOfAColumn proc
CREATE PROC Procedure7
AS
BEGIN
	ALTER TABLE Movie_Showtime
	ALTER COLUMN [Date] VARCHAR(30);
END
GO

CREATE PROC Procedure7UNDO
AS
BEGIN
	ALTER TABLE Movie_Showtime
	ALTER COLUMN [Date] DATE;
END
GO

--CREATE TABLE ProceduresActions(
--we hold the id s of the versions of the database with the coressponding procedure
	--[DB_Proc_ID] TINYINT PRIMARY KEY,
	--[Proc_Name] VARCHAR(40),
	--[Progress_action] VARCHAR(40),
	--[Reverse_action] VARCHAR(40),
--)

CREATE TABLE CurrentVersionOfDatabase(
[DB_ID] TINYINT PRIMARY KEY,
[DB_Current_version] TINYINT
)
INSERT INTO CurrentVersionOfDatabase([DB_ID],[DB_Current_version])
VALUES(0,0);


USE [Movie Rental Database]
GO
CREATE PROC ChangeVersion @NewVersion INT
AS
BEGIN
	DECLARE @CurrentVersion INT
	DECLARE @NameProc VARCHAR(30)
	SELECT @CurrentVersion=C.DB_Current_version FROM CurrentVersionOfDatabase C WHERE C.[DB_ID]=0
	IF @NewVersion<0  OR @NewVersion>7
		BEGIN
		PRINT 'The new version must be from 0 to 7!'
		END
	
	ELSE
		
		BEGIN
		
		IF @CurrentVersion<@NewVersion
			BEGIN
			--SET @CurrentVersion=@CurrentVersion+1
			WHILE @CurrentVersion<>@NewVersion
				BEGIN

				SET @CurrentVersion=@CurrentVersion+1
				SET @NameProc='Procedure'+CONVERT(varchar(15),@CurrentVersion)
				EXEC @NameProc
				PRINT 'The procedure: '+@NameProc+' has been executed.'
				
				END
			END
		
		ELSE
			BEGIN

			WHILE @CurrentVersion<>@NewVersion
				BEGIN

				SET @NameProc='Procedure'+CONVERT(varchar(15),@CurrentVersion)+'UNDO'
				EXEC @NameProc
				PRINT 'The procedure: '+@NameProc+' has been executed.'
				SET @CurrentVersion=@CurrentVersion-1

				END

			END
		
		UPDATE CurrentVersionOfDatabase
		SET DB_Current_version=@CurrentVersion WHERE [DB_ID]=0

		END

END
GO

EXEC ChangeVersion 2;

UPDATE CurrentVersionOfDatabase
SET DB_Current_version=0 WHERE [DB_ID]=0





















