--h. 4 queries with the GROUP BY clause, 3 of which also contain the HAVING clause; 2 of the latter will also have a subquery in the HAVING clause; 
--use the aggregation operators: COUNT, SUM, AVG, MIN, MAX;


--1.Find which is the youngest age and salary out of all employees.
SELECT TOP 1 MIN(E.EAge) AS MinAge, MIN(E.Salary) AS MinSalary
FROM Employees E
GROUP BY E.Salary,E.EAge

--2.Find all the employees that have the salary bigger than the average of all salaries in the store.
SELECT E.Employee_id,E.Salary
FROM Employees E
GROUP BY E.Employee_id,E.Salary
HAVING E.Salary>(
					SELECT AVG(E2.Salary)
					FROM Employees E2
						)


--3.Find the movies released in 2019 with the biggest quantity in the inventary.

SELECT MAX(S.Number_of_samples_for_movie) AS MaxNumberOfSamples,S.Stock_id
FROM Stocks S
GROUP BY S.Stock_id
HAVING S.Stock_id IN (
						SELECT M.Stock_id
						FROM Movies M
						WHERE M.Release_year = 2019
						)

						
--4.Find the movies with the maximum length of minutes (but being more than 130 minutes).
SELECT MAX(M.Length_minutes), M.Movie_id
FROM Movies M
GROUP BY M.Movie_id,M.Length_minutes
HAVING Length_minutes>130
