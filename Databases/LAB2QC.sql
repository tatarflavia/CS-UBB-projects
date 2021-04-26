--c. 2 queries with the difference operation; use EXCEPT and NOT IN;

--1.Find the title of the movies that haven't been rented at all yet. (USE EXCEPT)
SELECT M1.MTitle
FROM Movies M1
WHERE M1.Movie_id IN (
	SELECT M.Movie_id
	FROM Movies M
	EXCEPT
	SELECT R.Movie_id
	FROM Rentals R
)

--2.Find the name of the emloyees that haven't started work yet(don't have any services). (USE NOT IN)
SELECT DISTINCT E.EName
FROM Employees E
WHERE E.Employee_id NOT IN
	(
	SELECT CS.Employee_id
	FROM ClientServices CS
	)
