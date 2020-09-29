use TransactionAtATMs
go


IF OBJECT_ID('Transactions', 'U') IS NOT NULL
	DROP TABLE [Transactions]

IF OBJECT_ID('Cards', 'U') IS NOT NULL
	DROP TABLE [Cards]

IF OBJECT_ID('ATMs', 'U') IS NOT NULL
	DROP TABLE [ATMs]

IF OBJECT_ID('Customers', 'U') IS NOT NULL
	DROP TABLE [Customers]

IF OBJECT_ID('Banks', 'U') IS NOT NULL
	DROP TABLE [Banks]
GO




CREATE TABLE Banks
	(
	BankID INT PRIMARY KEY IDENTITY(1,1),
	BName VARCHAR(100),
	SwiftCode INT,
	WebsiteURL VARCHAR(100),
	ShareholderCountry VARCHAR(100)
	)


CREATE TABLE Customers
	(
	CustID INT PRIMARY KEY IDENTITY(1,1),
	CName VARCHAR(100),
	CType VARCHAR(100)
	)


CREATE TABLE ATMs
	(
	AtmID INT PRIMARY KEY IDENTITY(1,1),
	AAdress VARCHAR(100),
	CashDeposits BIT,
	BankID INT REFERENCES Banks(BankID)
	)

	
CREATE TABLE Cards
	(
	CardID INT PRIMARY KEY IDENTITY(1,1),
	CardNumber INT,
	CExpiration DATE,
	CustID INT REFERENCES Customers(CustID),
	BankID INT REFERENCES Banks(BankID),
	)

CREATE TABLE Transactions
	(
	CardID INT REFERENCES Cards(CardID) not null,
	AtmID INT REFERENCES ATMs(AtmID) not null,
	MoneyAmount INT,
	TType varchar(100),
	TDate date,
	TTime time,
	PRIMARY KEY (CardID,AtmID)
	)
	

