--d. 4 queries with INNER JOIN, LEFT JOIN, RIGHT JOIN, and FULL JOIN; one query will join at least 3 tables, 
--while another one will join at least two many-to-many relationships;


--1.Find the shippers of the movies including the shippers that have no movies or stocks or the movies that have no shipper selecting the movie title, stock id and shipper id and name.
SELECT ST.Shipper_id, SH.Shipper_id, SH.SName, M.MTitle, M.Movie_id
FROM Stocks ST
FULL JOIN Movies M ON M.Stock_id=ST.Stock_id
FULL JOIN Shippers SH ON SH.Shipper_id=ST.Shipper_id
ORDER BY M.Movie_id


--2.Find the stocks of all the movies including the stocks were misplaced and have no movie linked.

SELECT M.MTitle, M.Movie_id,S.Stock_id,S.Number_of_samples_for_movie
FROM Movies M
RIGHT JOIN Stocks S ON M.Stock_id=S.Stock_id
ORDER BY M.Movie_id

--3.Find the titles of the movies from every movie rental and the price that is to be calculated(number of days of borrowing * 10 lei) including the movies that have no rental yet.
SELECT M.Movie_id, M.MTitle, R.[Duration_of_borrowing(in days)]*10 AS Price, R.[Duration_of_borrowing(in days)], R.[Day], R.[Month]
FROM Movies M
LEFT JOIN Rentals R ON R.Movie_id=M.Movie_id

--4.Find for every movie borrowed who was the employee that gave it to which client including the movie title, the employee name and id, the client name and id, and the date of the borrowing. (at least 2 many to many relationships).
SELECT E.Employee_id, E.EName, C.Client_id, C.CName, M.MTitle, M.Movie_id, R.[Day], R.[Month]
FROM ClientServices CS
INNER JOIN Employees E ON E.Employee_id=CS.Employee_id
INNER JOIN Clients C ON C.Client_id=CS.Client_id
INNER JOIN Rentals R ON R.Client_id= C.Client_id
INNER JOIN Movies M ON M.Movie_id=R.Movie_id
ORDER BY R.[Day], R.[Month]
