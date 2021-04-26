if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestRunTables_Tables]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)

ALTER TABLE [TestRunTables] DROP CONSTRAINT FK_TestRunTables_Tables

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestTables_Tables]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)

ALTER TABLE [TestTables] DROP CONSTRAINT FK_TestTables_Tables

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestRunTables_TestRuns]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)

ALTER TABLE [TestRunTables] DROP CONSTRAINT FK_TestRunTables_TestRuns

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestRunViews_TestRuns]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)

ALTER TABLE [TestRunViews] DROP CONSTRAINT FK_TestRunViews_TestRuns

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestTables_Tests]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)

ALTER TABLE [TestTables] DROP CONSTRAINT FK_TestTables_Tests

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestViews_Tests]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)

ALTER TABLE [TestViews] DROP CONSTRAINT FK_TestViews_Tests

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestRunViews_Views]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)

ALTER TABLE [TestRunViews] DROP CONSTRAINT FK_TestRunViews_Views

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestViews_Views]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)

ALTER TABLE [TestViews] DROP CONSTRAINT FK_TestViews_Views

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[Tables]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)

drop table [Tables]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[TestRunTables]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)

drop table [TestRunTables]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[TestRunViews]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)

drop table [TestRunViews]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[TestRuns]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)

drop table [TestRuns]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[TestTables]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)

drop table [TestTables]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[TestViews]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)

drop table [TestViews]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[Tests]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)

drop table [Tests]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[Views]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)

drop table [Views]

GO



CREATE TABLE [Tables] (

	[TableID] [int] IDENTITY (1, 1) NOT NULL ,

	[Name] [nvarchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL 

) ON [PRIMARY]

GO



CREATE TABLE [TestRunTables] (

	[TestRunID] [int] NOT NULL ,

	[TableID] [int] NOT NULL ,

	[StartAt] [datetime] NOT NULL ,

	[EndAt] [datetime] NOT NULL 

) ON [PRIMARY]

GO



CREATE TABLE [TestRunViews] (

	[TestRunID] [int] NOT NULL ,

	[ViewID] [int] NOT NULL ,

	[StartAt] [datetime] NOT NULL ,

	[EndAt] [datetime] NOT NULL 

) ON [PRIMARY]

GO



CREATE TABLE [TestRuns] (

	[TestRunID] [int] IDENTITY (1, 1) NOT NULL ,

	[Description] [nvarchar] (2000) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,

	[StartAt] [datetime] NULL ,

	[EndAt] [datetime] NULL 

) ON [PRIMARY]

GO



CREATE TABLE [TestTables] (

	[TestID] [int] NOT NULL ,

	[TableID] [int] NOT NULL ,

	[NoOfRows] [int] NOT NULL ,

	[Position] [int] NOT NULL 

) ON [PRIMARY]

GO



CREATE TABLE [TestViews] (

	[TestID] [int] NOT NULL ,

	[ViewID] [int] NOT NULL 

) ON [PRIMARY]

GO



CREATE TABLE [Tests] (

	[TestID] [int] IDENTITY (1, 1) NOT NULL ,

	[Name] [nvarchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL 

) ON [PRIMARY]

GO



CREATE TABLE [Views] (

	[ViewID] [int] IDENTITY (1, 1) NOT NULL ,

	[Name] [nvarchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL 

) ON [PRIMARY]

GO



ALTER TABLE [Tables] WITH NOCHECK ADD 

	CONSTRAINT [PK_Tables] PRIMARY KEY  CLUSTERED 

	(

		[TableID]

	)  ON [PRIMARY] 

GO



ALTER TABLE [TestRunTables] WITH NOCHECK ADD 

	CONSTRAINT [PK_TestRunTables] PRIMARY KEY  CLUSTERED 

	(

		[TestRunID],

		[TableID]

	)  ON [PRIMARY] 

GO



ALTER TABLE [TestRunViews] WITH NOCHECK ADD 

	CONSTRAINT [PK_TestRunViews] PRIMARY KEY  CLUSTERED 

	(

		[TestRunID],

		[ViewID]

	)  ON [PRIMARY] 

GO



ALTER TABLE [TestRuns] WITH NOCHECK ADD 

	CONSTRAINT [PK_TestRuns] PRIMARY KEY  CLUSTERED 

	(

		[TestRunID]

	)  ON [PRIMARY] 

GO



ALTER TABLE [TestTables] WITH NOCHECK ADD 

	CONSTRAINT [PK_TestTables] PRIMARY KEY  CLUSTERED 

	(

		[TestID],

		[TableID]

	)  ON [PRIMARY] 

GO



ALTER TABLE [TestViews] WITH NOCHECK ADD 

	CONSTRAINT [PK_TestViews] PRIMARY KEY  CLUSTERED 

	(

		[TestID],

		[ViewID]

	)  ON [PRIMARY] 

GO



ALTER TABLE [Tests] WITH NOCHECK ADD 

	CONSTRAINT [PK_Tests] PRIMARY KEY  CLUSTERED 

	(

		[TestID]

	)  ON [PRIMARY] 

GO



ALTER TABLE [Views] WITH NOCHECK ADD 

	CONSTRAINT [PK_Views] PRIMARY KEY  CLUSTERED 

	(

		[ViewID]

	)  ON [PRIMARY] 

GO



ALTER TABLE [TestRunTables] ADD 

	CONSTRAINT [FK_TestRunTables_Tables] FOREIGN KEY 

	(

		[TableID]

	) REFERENCES [Tables] (

		[TableID]

	) ON DELETE CASCADE  ON UPDATE CASCADE ,

	CONSTRAINT [FK_TestRunTables_TestRuns] FOREIGN KEY 

	(

		[TestRunID]

	) REFERENCES [TestRuns] (

		[TestRunID]

	) ON DELETE CASCADE  ON UPDATE CASCADE 

GO



ALTER TABLE [TestRunViews] ADD 

	CONSTRAINT [FK_TestRunViews_TestRuns] FOREIGN KEY 

	(

		[TestRunID]

	) REFERENCES [TestRuns] (

		[TestRunID]

	) ON DELETE CASCADE  ON UPDATE CASCADE ,

	CONSTRAINT [FK_TestRunViews_Views] FOREIGN KEY 

	(

		[ViewID]

	) REFERENCES [Views] (

		[ViewID]

	) ON DELETE CASCADE  ON UPDATE CASCADE 

GO



ALTER TABLE [TestTables] ADD 

	CONSTRAINT [FK_TestTables_Tables] FOREIGN KEY 

	(

		[TableID]

	) REFERENCES [Tables] (

		[TableID]

	) ON DELETE CASCADE  ON UPDATE CASCADE ,

	CONSTRAINT [FK_TestTables_Tests] FOREIGN KEY 

	(

		[TestID]

	) REFERENCES [Tests] (

		[TestID]

	) ON DELETE CASCADE  ON UPDATE CASCADE 

GO



ALTER TABLE [TestViews] ADD 

	CONSTRAINT [FK_TestViews_Tests] FOREIGN KEY 

	(

		[TestID]

	) REFERENCES [Tests] (

		[TestID]

	),

	CONSTRAINT [FK_TestViews_Views] FOREIGN KEY 

	(

		[ViewID]

	) REFERENCES [Views] (

		[ViewID]

	)

GO
--table with a single column primary key and no foreign keys; cu view 1 
CREATE TABLE schools
(
	schoolId int NOT NULL,
	nrOfKids int,
	CONSTRAINT pk_schools PRIMARY KEY (schoolId)
);

-- table with a multicolumn primary key,
CREATE TABLE activities
(
	week_day int NOT NULL,
	hourOfActivity int NOT NULL,
	activityName varchar(50),
	CONSTRAINT pk_activities PRIMARY KEY (week_day,hourOfActivity)
);

--table with a single column primary key and at least one foreign key;
CREATE TABLE kidsProgramme
(
	kidsProgrammeId int NOT NULL,
	schoolId int,
	week_day int NOT NULL,
	hourOfActivity int NOT NULL,
	duration int,
	CONSTRAINT pk_kidsProgramme PRIMARY KEY (kidsProgrammeId),
	CONSTRAINT fk_activities FOREIGN KEY (week_day,hourOfActivity) REFERENCES activities(week_day,hourOfActivity),
	CONSTRAINT fk_schools FOREIGN KEY (schoolId) REFERENCES schools(schoolId)
);

CREATE TABLE MAXForIDS(ID int primary key identity(1,1),[Value] int)

--VIEWS:
--a view with a SELECT statement operating on one table;(shows all activities)
GO
CREATE VIEW view_1
AS
SELECT * FROM schools

GO
--a view with a SELECT statement operating on at least 2 tables;(shows all activities from the kids programm table)
CREATE VIEW view_2
AS
SELECT kp.week_day,kp.hourOfActivity FROM kidsProgramme kp
INNER JOIN activities a
ON kp.hourOfActivity = a.hourOfActivity AND a.week_day = kp.week_day

GO

--a view with a SELECT statement that has a GROUP BY clause and operates on at least 2 tables(shows all schools and activities from the kids programm table while counting how many entries with the same duration)
CREATE VIEW view_3
AS
SELECT COUNT(kp.duration) as "nr", kp.duration FROM kidsProgramme kp
INNER JOIN schools s ON kp.schoolId = s.schoolId
INNER JOIN activities a ON kp.hourOfActivity = a.hourOfActivity AND a.week_day = kp.week_day
GROUP BY kp.duration

GO

INSERT INTO Tables
values
('schools'),
('activities'),
('kidsProgramme');

INSERT INTO Views
values
('view_1'),
('view_2'),
('view_3');

INSERT INTO Tests
values
('TestForSchoolAndView1'),
('TestForActivityAndView2'),
('TestForProgrammAndView3');

SELECT * FROM Tables
SELECT * FROM Views
SELECT * FROM Tests
SELECT * FROM TestTables
SELECT * FROM TestViews

INSERT INTO TestTables
values
(1,1,5000,5),
(2,2,3000,8),
(3,3,6000,3);

INSERT INTO TestViews
values
(1,1),
(2,2),
(3,3);

--PROCEDURA 1 pt tabelul school si view 1 
USE [MoviesLab4]
GO
alter PROC test_1
AS
BEGIN
	--take params
	DECLARE @NrOfRowsToInsert INT --cat inseram
	DECLARE @PositionTillErasing INT --pana unde stergem
	DECLARE @PositionFromErasing INT--de unde stergem
	DECLARE @CurrentID VARCHAR(30)
	SELECT @NrOfRowsToInsert=T.NoOfRows FROM TestTables T WHERE T.TestID=1

	--see from where we insert 
	DECLARE @OK INT=(SELECT COUNT(*) FROM schools)
	IF @OK=0
		BEGIN
		SET @CurrentID=0
		END
	ELSE
		BEGIN
		SELECT @CurrentID=MAX(s.schoolId) FROM schools s
		SET @CurrentID=@CurrentID+1
		END
	
	--start inserting
	declare @StartDateForTable DateTime = getDate();
	WHILE @NrOfRowsToInsert>0
		BEGIN
		INSERT INTO schools VALUES (@CurrentID,200);
		SET @CurrentID=@CurrentID+1 
		SET @NrOfRowsToInsert=@NrOfRowsToInsert-1
		END
	

	--start with evaluating view 1
	declare @StartDateForView DateTime = getDate();
	SELECT * FROM dbo.view_1
	declare @EndDateForView DateTime = getDate();

	--start erasing from table till we reach position desired
	delete from kidsProgramme;
	SELECT @PositionFromErasing=MAX(s.schoolId) FROM schools s
	SET @PositionTillErasing =(SELECT t.Position FROM TestTables t where t.TestID=1)
	WHILE @PositionFromErasing>@PositionTillErasing
		BEGIN
		DELETE FROM schools WHERE schoolId=@PositionFromErasing
		SET @PositionFromErasing=@PositionFromErasing-1
		END
	declare @EndDateforTable DateTime = getDate();


	INSERT INTO TestRuns VALUES ('test1ForSchoolsView1',@StartDateForTable,@EndDateforTable);
	declare @IDForRun int =(SELECT MAX(TestRunID) FROM TestRuns)

	INSERT INTO TestRunTables VALUES (@IDForRun,1,@StartDateForTable,@EndDateforTable);
	INSERT INTO TestRunViews VALUES (@IDForRun,1,@StartDateForView,@EndDateForView);

END
GO
EXEC test_1;



SELECT * FROM TestRunTables;
SELECT * FROM TestRuns;
SELECT * FROM TestRunViews;
SELECT * FROM schools;



--PROCEDURA 2 pt tabelul ACTIVITY SI VIEW 2 PT ACTIVITY SI KIDS PROGRAMM 
GO
alter PROC test_activity_insert @CurrentID INT
AS
BEGIN
	--take params
	DECLARE @NrOfRowsToInsert INT --cat inseram
	SELECT @NrOfRowsToInsert=T.NoOfRows FROM TestTables T WHERE T.TestID=2
	
	--start inserting
	declare @StartDateForTable DateTime = getDate();
	WHILE @NrOfRowsToInsert>0
		BEGIN
		INSERT INTO activities VALUES (@CurrentID,@CurrentID,'movie');
		SET @CurrentID=@CurrentID+1 
		SET @NrOfRowsToInsert=@NrOfRowsToInsert-1
		END
END
GO

USE [MoviesLab4]
GO
ALTER PROC test_2
AS
BEGIN
	--take params
	DECLARE @NrOfRowsToInsert INT --cat inseram
	DECLARE @PositionTillErasing INT --pana unde stergem
	DECLARE @PositionFromErasing INT--de unde stergem
	DECLARE @CurrentID INT
	SELECT @NrOfRowsToInsert=T.NoOfRows FROM TestTables T WHERE T.TestID=2

	--see from where we insert : getting the current id for all 3 tables and getting the max one
	DELETE FROM MAXForIDS;

	DECLARE @OK INT=(SELECT COUNT(*) FROM activities)
	IF @OK<>0
		BEGIN
		SET @OK=(select MAX(a.week_day) FROM activities a)
		END
	INSERT INTO MAXForIDS VALUES (@OK);
	SET @OK=(SELECT COUNT(*) FROM schools)
	IF @OK<>0
		BEGIN
		SET @OK=(select MAX(s.schoolId) FROM schools s)
		END
	INSERT INTO MAXForIDS VALUES (@OK);
	SET @OK=(SELECT COUNT(*) FROM kidsProgramme)
	IF @OK<>0
		BEGIN
		SET @OK=(select MAX(k.kidsProgrammeId) FROM kidsProgramme k)
		END
	INSERT INTO MAXForIDS VALUES (@OK);
	
	--get max for the current id
	SELECT @CurrentID =MAX(m.[Value]) FROM MAXForIDS m
	SET @CurrentID =@CurrentID +1
	
	--start inserting
	declare @StartDateForTable DateTime = getDate();
	EXEC test_activity_insert @CurrentID;
	declare @EndDateforTable DateTime = getDate();
	WHILE @NrOfRowsToInsert>0
		BEGIN
		INSERT INTO schools VALUES (@CurrentID,200);
		INSERT INTO kidsProgramme VALUES (@CurrentID,@CurrentID,@CurrentID,@CurrentID,3);
		SET @CurrentID=@CurrentID+1 
		SET @NrOfRowsToInsert=@NrOfRowsToInsert-1
		END
	

	--start with evaluating view 2
	declare @StartDateForView DateTime = getDate();
	SELECT * FROM dbo.view_2
	declare @EndDateForView DateTime = getDate();

	--start erasing from table till we reach position desired
	SELECT @PositionFromErasing=MAX(a.week_day) FROM activities a
	set @PositionTillErasing=(select t.Position FROM TestTables t where t.TestID=2)
	WHILE (select count(*) from activities)>@PositionTillErasing
		BEGIN
		DELETE FROM kidsProgramme WHERE kidsProgrammeId=@PositionFromErasing
		DELETE FROM schools WHERE schoolId=@PositionFromErasing
		DELETE FROM activities WHERE week_day=@PositionFromErasing
		SET @PositionFromErasing=@PositionFromErasing-1
		END
	


	INSERT INTO TestRuns VALUES ('test2ForActivitiesView2',@StartDateForTable,@EndDateforTable);
	declare @IDForRun int =(SELECT MAX(TestRunID) FROM TestRuns)

	INSERT INTO TestRunTables VALUES (@IDForRun,2,@StartDateForTable,@EndDateforTable);
	INSERT INTO TestRunViews VALUES (@IDForRun,2,@StartDateForView,@EndDateForView);

END
GO
EXEC test_2;



SELECT * FROM TestRunTables;
SELECT * FROM TestRuns;
SELECT * FROM TestRunViews;
SELECT * FROM activities;




--PROCEDURA 3 pt tabelul  SI VIEW 3 PT kids programm
USE [MoviesLab4]
GO
ALTER PROC test_3
AS
BEGIN
	--take params
	DECLARE @NrOfRowsToInsert INT --cat inseram
	DECLARE @PositionTillErasing INT --pana unde stergem
	DECLARE @PositionFromErasing INT--de unde stergem
	DECLARE @CurrentID INT
	SELECT @NrOfRowsToInsert=T.NoOfRows FROM TestTables T WHERE T.TestID=3

	--see from where we insert : getting the current id for all 3 tables and getting the max one
	DELETE FROM MAXForIDS;

	DECLARE @OK INT=(SELECT COUNT(*) FROM activities)
	IF @OK<>0
		BEGIN
		SET @OK=(select MAX(a.week_day) FROM activities a)
		END
	INSERT INTO MAXForIDS VALUES (@OK);
	SET @OK=(SELECT COUNT(*) FROM schools)
	IF @OK<>0
		BEGIN
		SET @OK=(select MAX(s.schoolId) FROM schools s)
		END
	INSERT INTO MAXForIDS VALUES (@OK);
	SET @OK=(SELECT COUNT(*) FROM kidsProgramme)
	IF @OK<>0
		BEGIN
		SET @OK=(select MAX(k.kidsProgrammeId) FROM kidsProgramme k)
		END
	INSERT INTO MAXForIDS VALUES (@OK);
	
	--get max for the current id
	SELECT @CurrentID =MAX(m.[Value]) FROM MAXForIDS m
	SET @CurrentID =@CurrentID +1
	
	--start inserting
	declare @StartDateForTable DateTime = getDate();
	WHILE @NrOfRowsToInsert>0
		BEGIN
		INSERT INTO activities VALUES (@CurrentID,@CurrentID,'movie');
		INSERT INTO schools VALUES (@CurrentID,200);
		INSERT INTO kidsProgramme VALUES (@CurrentID,@CurrentID,@CurrentID,@CurrentID,3);
		SET @CurrentID=@CurrentID+1 
		SET @NrOfRowsToInsert=@NrOfRowsToInsert-1
		END
	

	--start with evaluating view 3
	declare @StartDateForView DateTime = getDate();
	SELECT * FROM dbo.view_3
	declare @EndDateForView DateTime = getDate();

	--start erasing from table till we reach position desired
	SELECT @PositionFromErasing=MAX(k.kidsProgrammeId) FROM kidsProgramme k
	SELECT @PositionTillErasing=t.Position FROM TestTables t where t.TestID=3
	WHILE (select count(*) from kidsProgramme)>@PositionTillErasing
		BEGIN
		DELETE FROM kidsProgramme WHERE kidsProgrammeId=@PositionFromErasing
		DELETE FROM schools WHERE schoolId=@PositionFromErasing
		DELETE FROM activities WHERE week_day=@PositionFromErasing
		SET @PositionFromErasing=@PositionFromErasing-1
		END
	declare @EndDateforTable DateTime = getDate();


	INSERT INTO TestRuns VALUES ('test3ForProgrammView3',@StartDateForTable,@EndDateforTable);
	declare @IDForRun int =(SELECT MAX(TestRunID) FROM TestRuns)

	INSERT INTO TestRunTables VALUES (@IDForRun,3,@StartDateForTable,@EndDateforTable);
	INSERT INTO TestRunViews VALUES (@IDForRun,3,@StartDateForView,@EndDateForView);

END
GO
EXEC test_3;



SELECT * FROM TestRunTables;
SELECT * FROM TestRuns;
SELECT * FROM TestRunViews;
SELECT * FROM kidsProgramme;

delete from kidsProgramme;
delete from schools;
delete from activities;




