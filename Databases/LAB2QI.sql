--i. 4 queries using ANY and ALL to introduce a subquery in the WHERE clause; 2 of them should be rewritten with aggregation operators, 
--while the other 2 should also be expressed with [NOT] IN.

--1.Find who IS the oldest employee in the store.
SELECT E.EName,E.Employee_id,E.EAge, E.EPhone
FROM Employees E
WHERE E.EAge+1 > ALL (SELECT E1.EAge FROM Employees E1)

--Rewrite with MAX
SELECT E.EName,E.Employee_id,E.EAge, E.EPhone
FROM Employees E
WHERE E.EAge+1 > (SELECT MAX(E1.EAge) FROM Employees E1)

--2.Find the movie with the biggest stock inventary in the store.
SELECT M.MTitle, M.Stock_id, S.Number_of_samples_for_movie
FROM Movies M, Stocks S
WHERE M.Stock_id=S.Stock_id AND S.Number_of_samples_for_movie+1 > ALL (SELECT S1.Number_of_samples_for_movie FROM Stocks S1)

--Rewrite with MAX
SELECT M.MTitle, M.Stock_id, S.Number_of_samples_for_movie
FROM Movies M, Stocks S
WHERE M.Stock_id=S.Stock_id AND S.Number_of_samples_for_movie+1 > ALL (SELECT MAX(S1.Number_of_samples_for_movie) FROM Stocks S1)

--3.The director of the store has proposed a meeting with all the employees that were never clients of the store they know so well already to ask them why not buy or rent a movie and 
--offering them some discounts in order to convince them to try at least once being clients.
SELECT E.EPhone, E.EName, E.Employee_id
FROM Employees E
WHERE E.EPhone !=ANY (SELECT C.CPhone FROM Clients C)

--Rewrite
SELECT E.EPhone, E.EName, E.Employee_id
FROM Employees E
WHERE E.EPhone NOT IN (SELECT C.CPhone FROM Clients C)

--4.The director has proposed that some vacant places in the staff should be filled with pasionated people from the very clients that buy from the store. He needs to call the clients that 
--aren't already employees to ask them if they would like to come to an interview.
SELECT C.CName, C.CPhone, C.Client_id
FROM Clients C
WHERE C.CPhone != ANY (SELECT E.EPhone FROM Employees E)

--Rewrite
SELECT C.CName, C.CPhone, C.Client_id
FROM Clients C
WHERE C.CPhone NOT IN (SELECT E.EPhone FROM Employees E)