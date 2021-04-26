--e. 2 queries using the IN operator to introduce a subquery in the WHERE clause; in at least one query, 
--the subquery should include a subquery in its own WHERE clause;

--1.Find all the titles of the movies in which Penelope Cruz plays.
SELECT M.MTitle
FROM Movies M
WHERE M.Movie_id IN (
	SELECT R.Movie_id  
	FROM Roles R,Actors A
	WHERE R.Actor_id=A.Actor_id AND A.AName='Penélope Cruz'
)

--2.Find how many movies in which is spoken english were borroweed by adults in total.
SELECT COUNT(*)
FROM Movies M
WHERE M.Lang_id IN 
	(SELECT L.Lang_id
	FROM Languages L
	WHERE L.LName='English'
	)		AND M.Movie_id IN (
							SELECT R.Movie_id
							FROM Rentals R
							WHERE R.Client_id IN (
													SELECT C.Client_id
													FROM Clients C
													WHERE C.ClientCat_id IN (
																					SELECT CC.ClientCat_id
																					FROM ClientCategories CC
																					WHERE CC.ClientCatName='Adult' OR CC.ClientCatName='Old'
																					)
														)
							)