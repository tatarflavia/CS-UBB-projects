--b. 2 queries with the intersection operation; use INTERSECT and IN;

--1.Find the id's of the employees that actually did any work on the 1st of dec. (USE INTERSECT)
SELECT E.Employee_id AS ID
FROM Employees E
INTERSECT
SELECT CS.Employee_id AS ID
FROM ClientServices CS
WHERE CS.[Day]=1 AND CS.[Month]=12
ORDER BY ID


--2.Find the name of the clients that were served by 'Popa Mara'. (USE IN)
SELECT C.CName as ClientName
FROM Clients C, ClientServices CS
WHERE CS.Client_id=C.Client_id AND CS.Employee_id IN
	(SELECT E.Employee_id
	FROM Employees E
	WHERE E.EName='Popa Mar'
	)
ORDER BY ClientName