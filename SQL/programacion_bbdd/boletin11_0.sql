USE LeoMetro
GO
/*
-1-
Crea una función inline que nos devuelva el número de estaciones 
que ha recorrido cada tren en un determinado periodo de tiempo. 
El principio y el fin de ese periodo se pasarán como parámetros
*/

CREATE OR ALTER FUNCTION estacionesRecorridas(@IDTren int, @Principio smalldatetime, @Fin smalldatetime)
RETURNS int AS
BEGIN
	DECLARE @Estaciones int
	SELECT @Estaciones = ISNULL(COUNT(estacion), 0) FROM LM_Recorridos
	WHERE
		Momento BETWEEN @Principio AND @Fin
		AND Tren = @IDTren
	GROUP BY Tren

	RETURN @Estaciones
END
GO

SELECT dbo.estacionesRecorridas(110,'24-02-2017','28-02-2017') as estaciones
GO

/*
-2-
Crea una función inline que nos devuelva el número de veces que cada usuario 
ha entrado en el metro en un periodo de tiempo. El principio y el fin de ese periodo 
se pasarán como parámetros
*/

CREATE OR ALTER FUNCTION subidasUsuarios(@Principio smalldatetime, @Fin smalldatetime)
RETURNS TABLE AS
RETURN	
	SELECT P.ID, P.Nombre, P.Apellidos, COUNT(V.ID) AS Subidas FROM LM_Viajes AS V
	INNER JOIN LM_Tarjetas AS T
	ON V.IDTarjeta = T.ID
	INNER JOIN LM_Pasajeros AS P
	ON P.ID = T.IDPasajero
	
	WHERE MomentoEntrada BETWEEN @Principio AND @Fin
	GROUP BY P.ID, P.Nombre, P.Apellidos
GO

SELECT * FROM subidasUsuarios('24-02-2017','28-02-2017') ORDER BY ID
SELECT * FROM LM_Viajes
GO

/*
-3-
Crea una función inline a la que pasemos la matrícula de un tren y una fecha de inicio 
y fin y nos devuelva una tabla con el número de veces que ese tren ha estado en cada estación, 
además del ID, nombre y dirección de la estación
*/

CREATE OR ALTER FUNCTION estacionesRecorridasPorTren(@MatriculaTren char(7), @FInicio smalldatetime, @FFin smalldatetime)
RETURNS TABLE AS
RETURN
	SELECT E.ID, E.Denominacion, E.Direccion, ISNULL(COUNT(R.Momento),0) AS EstacionesRecorridas  FROM LM_Trenes AS T
	INNER JOIN LM_Recorridos AS R
	ON R.Tren = T.ID
	RIGHT JOIN LM_Estaciones AS E
	ON E.ID = R.estacion
	WHERE T.Matricula = @MatriculaTren
		AND R.Momento BETWEEN @FInicio AND @FFin
	GROUP BY E.ID, E.Denominacion, E.Direccion
GO

SELECT * FROM LM_Trenes
SELECT * FROM estacionesRecorridasPorTren('5607GLZ','24-02-2017','28-02-2017')
GO
/*
-4-
Crea una función inline que nos diga el número de personas que han pasado por una estacion en un periodo de tiempo. 
Se considera que alguien ha pasado por una estación si ha entrado o salido del metro por ella. El principio y el fin 
de ese periodo se pasarán como parámetros
*/

SELECT * FROM LM_Viajes
GO

CREATE OR ALTER FUNCTION entradasSalidasEstacion(@Estacion int, @FInicio smalldatetime, @FFin smalldatetime)
RETURNS int AS
BEGIN
	DECLARE @Personas int
	SELECT @Personas = COUNT(P.ID) FROM LM_Viajes AS V
	INNER JOIN LM_Tarjetas AS T
	ON T.ID = V.IDTarjeta
	INNER JOIN LM_Pasajeros AS P
	ON P.ID = T.IDPasajero

	WHERE (V.MomentoEntrada BETWEEN @FInicio AND @FFin
		OR V.MomentoSalida BETWEEN @FInicio AND @FFin)
		AND 
		(V.IDEstacionEntrada = @Estacion
		OR V.IDEstacionSalida = @Estacion)

	RETURN @Personas
END
GO

SELECT dbo.entradasSalidasEstacion(3,'24-02-2017','28-02-2017') as personas
GO

/*
-5-
Crea una función inline que nos devuelva los kilómetros
que ha recorrido cada tren en un periodo de tiempo. El principio 
y el fin de ese periodo se pasarán como parámetros
*/
SELECT * FROM LM_Trenes
SELECT * FROM LM_Itinerarios
GO

CREATE OR ALTER FUNCTION dbo.kmRecorridos(@IDTren int, @FInicio smalldatetime, @FFin smalldatetime)
RETURNS INT AS
BEGIN
	DECLARE @kmRecorridos int
	SELECT @kmRecorridos = SUM(distancia) FROM LM_Itinerarios AS I
	INNER JOIN LM_Lineas AS L
	ON L.ID = I.Linea
	INNER JOIN LM_Recorridos AS R
	ON R.Linea = L.ID AND (R.estacion = I.estacionIni OR R.estacion = I.estacionFin)
	WHERE R.Tren = @IDTren
	AND R.Momento BETWEEN @FInicio AND @FFin

	RETURN @kmRecorridos
END
GO

SELECT dbo.kmRecorridos(104,'24-02-2017','28-02-2017') AS KILOMETROS
GO
/*
-7-
Crea una función inline que nos devuelva el tiempo total que cada usuario ha pasado en el metro en un periodo de tiempo. 
El principio y el fin de ese periodo se pasarán como parámetros. Se devolverá ID, nombre y apellidos del pasajero. 
El tiempo se expresará en horas y minutos.
*/
CREATE OR ALTER FUNCTION dbo.tiempoTotalEnMetro(@IdUsuario int, @FInicio smalldatetime, @FFin smalldatetime)
RETURNS TABLE AS
RETURN
	SELECT P.ID, P.Nombre, P.Apellidos FROM LM_Pasajeros AS P
	INNER JOIN LM_Tarjetas AS T
	ON T.IDPasajero = P.ID
	INNER JOIN LM_Viajes AS V
	ON V.IDTarjeta = 
