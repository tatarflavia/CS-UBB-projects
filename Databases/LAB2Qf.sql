--f. 2 queries using the EXISTS operator to introduce a subquery in the WHERE clause;

--1.Find all the movies in the store that have Chris Hemsworth in the cast.
SELECT M.MTitle
FROM Movies M
WHERE EXISTS (
			SELECT *
			FROM Actors A, Roles R
			WHERE A.Actor_id=R.Actor_id AND A.AName='Chris Hemsworth' AND R.Movie_id=M.Movie_id
				)

--2.Find the names of 2 most the devoted clients(have placed at least 2 commands).
SELECT TOP 2 C.CName, C.Client_id
FROM Clients C 
WHERE EXISTS(
				SELECT *
				FROM Rentals R
				WHERE R.Client_id=C.Client_id
				GROUP BY R.Client_id
				HAVING COUNT(*)>=2
					)
ORDER BY C.CName