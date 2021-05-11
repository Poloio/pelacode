--OPCION B

--Ej 1 
GO
CREATE OR ALTER FUNCTION SerieFavoritaDe (@IDSubscriptor int)
/*
NOMBRE: SerieFavoritaDe FUNCIÓN
DESCRIPCIÓN: Devuelve el identificador de la serie más vista por los usuarios asignados a la
	subscripción pasada por parámetro
ENTRADAS: IDSubscriptor que	identifica esta subscripción.
SALIDAS: un smallint que identifica la serie más vista.
*/
RETURNS smallint AS
BEGIN
	DECLARE @SFavorita smallint
	
	DECLARE @TiempoPorSerie TABLE (
	Serie smallint PRIMARY KEY NOT NULL
	,Horas int NULL
	)
	--Una tabla temporal para hacerlo más claro
	INSERT INTO @TiempoPorSerie
	SELECT C.IDSerie, SUM(DATEDIFF(MINUTE,V.MinutoInicio,V.MinutoFin)) / 60 FROM HCapitulos AS C
	INNER JOIN HVisionados AS V
	ON V.IDContenido = C.ID
	INNER JOIN HPerfiles AS P
	ON V.IDPerfil = P.ID
	WHERE P.IDSuscripcion = @IDSubscriptor
	GROUP BY C.IDSerie

	RETURN (SELECT Serie FROM @TiempoPorSerie WHERE Horas = (SELECT MAX(Horas) FROM @TiempoPorSerie))
END
GO

SELECT * FROM HSuscripciones
SELECT * FROM HSeries WHERE ID = dbo.SerieFavoritaDe(1)

-- Ej 2
GO
CREATE OR ALTER FUNCTION ImporteGastadoEnExtras (@IDSubscripcion int, @FInicio smalldatetime, @FFin smalldatetime)
/*
NOMBRE: ImporteGastadoEnExtras FUNCIÓN
DESCRIPCIÓN: Devuelve la suma de dinero gastado por una subscripción
	en contenido extra dentro de un periodo pasado por parámetros.
ENTRADAS: IDSubscripcion, un entero que identifica la subscripción.
	FInicio, la fecha y hora de inicio del periodo.
	FFIn, la fecha y hora de fin de periodo.
SALIDAS: la cantidad en money de dinero gastado.
*/
RETURNS money AS
BEGIN
	DECLARE @TipoSub char(1)
	SELECT @TipoSub = tipo FROM HSuscripciones
	WHERE ID = @IDSubscripcion

	RETURN (
	SELECT
		SUM(CASE -- El precio depende de la subscripción de la cuenta
				WHEN @TipoSub = 'S' THEN precioS
				WHEN @TipoSub = 'E' OR @TipoSub = 'P' THEN precioE
			END)
	FROM HPeliculas
	WHERE ID IN (SELECT IDContenido FROM HVisionados AS V
					INNER JOIN HPerfiles AS P
					ON P.ID = V.IDPerfil
					WHERE P.IDSuscripcion = @IDSubscripcion
					AND V.FechaHora BETWEEN @FInicio AND @FFin
					)
	)
END
GO

SELECT dbo.ImporteGastadoEnExtras(1, '1-1-1900', CURRENT_TIMESTAMP)

-- Ej 3
GO
CREATE OR ALTER FUNCTION EstadisticasSerie (@IDSerie smallint)
/*
NOMBRE:	EstadisticasSerie FUNCION
DESCRIPCIÓN: Devuelve una tabla que contiene las estadísticas generales de cada temporada de una serie.
ENTRADAS: IDSerie que identifica la serie que se quiere.
SALIDAS: una tabla con su id, titulo, numero de temporada, visualizaciones e incremento respecto
	a la temporada anterior.
*/
RETURNS TABLE AS
RETURN
SELECT S.ID, S.titulo, C.numeroTemporada, dbo.VisualizacionesDeTemporada(S.ID,C.numeroTemporada) AS VecesVista
	,CASE
		WHEN C.numeroTemporada = 1 THEN NULL
		-- Calculamos el incremento usando la misma función pero restando 1 al num de temporada
		ELSE (dbo.VisualizacionesDeTemporada(S.ID,C.numeroTemporada) - 
			dbo.VisualizacionesDeTemporada(S.ID,C.numeroTemporada-1)) 
			/ dbo.VisualizacionesDeTemporada(S.ID,C.numeroTemporada-1) * 100
	END AS IncrementoPorcientoTempAnterior
FROM HCapitulos AS C
INNER JOIN HSeries AS S
ON S.ID = C.IDSerie
WHERE C.IDSerie = @IDSerie
GROUP BY S.ID, S.titulo, C.numeroTemporada
GO

SELECT * FROM EstadisticasSerie(1)
-- No me sale el incremento :(

GO
CREATE OR ALTER FUNCTION VisualizacionesDeTemporada (@IDSerie smallint, @NumTemporada smallint)
-- Devuelve la cantidad de visualizaciones de una temoporada de una serie en concreto
-- pasados por parámetros
RETURNS int AS
BEGIN
	RETURN (
		SELECT COUNT(V.ID) FROM HVisionados AS V
		INNER JOIN HCapitulos AS C
		ON C.ID = V.IDContenido
		INNER JOIN HSeries AS S
		ON S.ID = C.IDSerie
		WHERE C.IDSerie = @IDSerie
			AND C.numeroTemporada = @NumTemporada
	)
END
GO

--Ej 4
GO
CREATE OR ALTER PROCEDURE CrearRecibo @IDUsuario int, @Mes smallint, @Anio int
/*
NOMBRE: CrearRecibo PROCEDIMIENTO
ENTRADAS: IDUsuario, un entero que identifica al usuario
	Mes, un entero que representa el mes que se necesita.
	Anio, un entero que representa el año que se necesita. Si se deja vacío será el actual
POSTCONDICIÓN: Se crea un recibo con los datos correspondientes en la tabla HRecibos
*/
AS BEGIN
	BEGIN TRANSACTION
	BEGIN TRY
		-- He intentado que se pueda poner null en los parametros pero por algun motivo
		-- no funciona @Anio = CASE WHEN NULL THEN CURR_TMSTAMP ELSE @Anio END
		-- Obtenemos la cuota
		DECLARE @Cuota smallmoney
		SELECT @Cuota = TS.importeMensual
		FROM HSuscripciones AS S
		INNER JOIN HTiposSuscripcion AS TS
		ON TS.tipo = S.tipo
		WHERE S.ID = @IDUsuario

		--Obtenemos los gastos extras
		DECLARE @GastosExtra money
		SELECT @GastosExtra = dbo.ImporteGastadoEnExtras(@IDUsuario, DATEFROMPARTS(@Anio,@Mes,1), 
															DATEFROMPARTS(@Anio,@Mes,28))--No es lo correcto porque Febrero y todos
																						--los meses con 31 fallaría
		INSERT INTO HRecibos (IDSuscripcion, numeroRecibo, inicioPeriodo, finPeriodo,
								fecha, importeSuscripcion, importeExtras)
		SELECT @IDUsuario 
			,COUNT(numeroRecibo) + 1
			,DATEFROMPARTS(@Anio,@Mes,1)
			,DATEFROMPARTS(@Anio,@Mes,28)
			,CURRENT_TIMESTAMP
			,@Cuota
			,@GastosExtra
		FROM HRecibos
		WHERE IDSuscripcion = @IDUsuario
	COMMIT TRANSACTION
	END TRY
	BEGIN CATCH
		ROLLBACK
	END CATCH
END
GO

EXEC CrearRecibo 3, 2, 2020
SELECT * FROM HRecibos WHERE IDSuscripcion = 3