--a. 2 queries with the union operation; use UNION [ALL] and OR;

--1.Find the ages of all the employees(be them managers or not). (USE UNION)
SELECT E.EAge*2 AS [Age(multiplied)], E.EName
FROM Employees E
UNION 
SELECT M.MAge*2 AS [Age(multiplied)], M.MName
FROM Managers M

--2.Find the names of the clients that rented something  plus the employee that helped them on the day of 1st of december or the 2nd of dec(plus the pension). (USE OR)
SELECT E.EName, C.CName, E.Employee_id, C.Client_id,(E.EAge-18)*E.Salary AS Pension
FROM Employees E, Clients C, ClientServices CG
WHERE E.Employee_id=CG.Employee_id AND C.Client_id=CG.Client_id AND (CG.[Day]=1 AND CG.[Month]=12 OR CG.[Day]=2 AND CG.[Month]=12)





