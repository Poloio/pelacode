/*
Queremos hacer una función inline a la que pasemos el número de un Gran Premio (numOrden) y 
nos devuelva una tabla con la clasificación tras disputarse dicho gran premio (y todos los anteriores, claro). 
La tabla devuelta tendrá las siguientes columnas: Dorsal del Piloto, Nombre, Numero de carreras finalizadas, 
número de carreras ganadas, Número de podiums (posiciones del 1 al 3), puntos.
*/

CREATE OR ALTER FUNCTION ClasificacionPremio (@NumOrden tinyint)
RETURNS TABLE AS
RETURN
	SELECT P.Dorsal, P.Nombre, COUNT(NULLIF(D.Finalizado,0)) AS CarrerasFinalizadas,
	COUNT(CASE WHEN D.Posición = 1 THEN 1 ELSE NULL END) AS Ganados,
	COUNT(CASE WHEN D.Posición >= 1 AND D.Posición <= 3 THEN 1 ELSE NULL END) AS Podios,
	SUM(CASE
			WHEN Posición = 1 THEN 20
			WHEN Posición = 2 THEN 15
			WHEN Posición = 3 THEN 11
			WHEN Posición = 4 THEN 8
			WHEN Posición = 5 THEN 6
			WHEN Posición = 6 THEN 4
			WHEN Posición = 7 THEN 3
			ELSE CASE WHEN Posición = NULL THEN 0 ELSE 1 END
		END) AS Puntos

	FROM Pilotos AS P
	INNER JOIN Disputas AS D
	ON D.Dorsal = P.Dorsal
	WHERE D.NumOrden <= @NumOrden
	GROUP BY P.Dorsal, P.Nombre
GO

SELECT * FROM ClasificacionPremio(99) ORDER BY Puntos DESC
