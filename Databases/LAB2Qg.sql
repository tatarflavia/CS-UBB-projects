--g. 2 queries with a subquery in the FROM clause;        

--1.Find the employees that have done at least once a poor job(a service of 6 or less).
SELECT DISTINCT E.Employee_id
FROM Employees E INNER JOIN (
							SELECT CS.* 
							FROM ClientServices CS
							WHERE CS.Happiness_level<=6
								)m
							ON E.Employee_id=m.Employee_id

--2.Find the clients that have borrowed a movie for more than 4 days.
SELECT C.CName
FROM Clients C INNER JOIN (
						SELECT *
						FROM Rentals R
						WHERE R.[Duration_of_borrowing(in days)]>4
							)n
						ON C.Client_id=N.Client_id