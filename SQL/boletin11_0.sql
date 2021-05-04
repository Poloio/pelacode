/*
Crea una función inline que nos devuelva el número de estaciones 
que ha recorrido cada tren en un determinado periodo de tiempo. 
El principio y el fin de ese periodo se pasarán como parámetros
*/

CREATE OR ALTER FUNCTION estacionesRecorridas(@IDTren int, @Principio smalldatetime, @Fin smalldatetime)
RETURNS int AS
BEGIN
	DECLARE @Estaciones int
	SELECT @Estaciones = COUNT(estacion) FROM LM_Recorridos
	WHERE
		Momento BETWEEN @Principio AND @Fin
		Tren = 
END
GO

SELECT * FROM LM_Recorridos