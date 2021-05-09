/*
-0-
La convocatoria de elecciones en Madrid ha causado tal conmoción entre los 
directivos de LeoMetro que han decidido conceder una amnistía a todos los 
pasajeros que tengan un saldo negativo en sus tarjetas.
Crea un procedimiento que racargue la cantidad necesaria para dejar a 0 el 
saldo de las tarjetas que tengan un saldo negativo y hayan sido recargadas 
al menos una vez en los últimos dos meses.
*/

CREATE OR ALTER PROCEDURE ProporcionarAmnistia AS
BEGIN
	DECLARE @ARecargar TABLE
	(
	id UNIQUEIDENTIFIER
	,idTarjeta int
	,recarga smallmoney
	,momento smalldatetime
	,saldoresultante smallmoney
	)

	INSERT INTO @ARecargar
	SELECT 
		NEWID(), T.ID, ABS(T.Saldo), CURRENT_TIMESTAMP, 0
	FROM LM_Tarjetas AS T
	INNER JOIN LM_Recargas AS R
	ON R.ID_Tarjeta = T.ID
	WHERE Saldo < 0
		AND DATEDIFF(MONTH,CURRENT_TIMESTAMP,R.Momento_Recarga) < 2

	UPDATE LM_Tarjetas
	SET Saldo = 0
	WHERE ID IN (SELECT idTarjeta FROM @ARecargar)

	INSERT INTO LM_Recargas
	SELECT * FROM @ARecargar
END
GO

BEGIN TRANSACTION
BEGIN TRY
	EXECUTE ProporcionarAmnistia
	COMMIT TRANSACTION
END TRY
BEGIN CATCH
	ROLLBACK
END CATCH

SELECT * FROM LM_Tarjetas
GO
/*
-1-
Crea un procedimiento RecargarTarjeta que reciba como parámetros el ID de una tarjeta y un importe 
y actualice el saldo de la tarjeta sumándole dicho importe, además de grabar la correspondiente recarga
*/

CREATE OR ALTER PROCEDURE RecargarTarjeta @IdTarjeta int, @Importe smallmoney
AS
BEGIN
	BEGIN TRANSACTION
	BEGIN TRY
		UPDATE LM_Tarjetas
		SET Saldo += @Importe
		WHERE ID = @IdTarjeta

		DECLARE @NuevoSaldo smallmoney
		SELECT @NuevoSaldo = Saldo
		FROM LM_Tarjetas
		WHERE ID = @IdTarjeta

		INSERT INTO LM_Recargas
		VALUES (NEWID(), @IdTarjeta, @Importe, CURRENT_TIMESTAMP, @NuevoSaldo)

		COMMIT TRANSACTION
	END TRY
	BEGIN CATCH
		ROLLBACK
	END CATCH 
END
GO

EXEC RecargarTarjeta 1, 99
GO

/*
-2-
Crea un procedimiento almacenado llamado PasajeroSale que reciba como parámetros el ID de una tarjeta, 
el ID de una estación y una fecha/hora (opcional). 

El procedimiento se llamará cuando un pasajero 
pasa su tarjeta por uno de los tornos de salida del metro. 
Su misión es grabar la salida en la tabla LM_Viajes. 
Para ello deberá localizar la entrada que corresponda, que será la última entrada 
correspondiente al mismo pasajero y hará un update de las columnas que correspondan. 

Si no existe la entrada, grabaremos una nueva fila en LM_Viajes dejando a NULL la estación y el momento de entrada.
Si se omite el parámetro de la fecha/hora, se tomará la actual.
También debe actualizarse el saldo de la tarjeta, descontando el importe del viaje grabado.
*/
CREATE OR ALTER PROCEDURE AplicarCoste @IDTarjeta int, @Coste smallmoney
AS
BEGIN
	UPDATE LM_Tarjetas
		SET Saldo -= @Coste
		WHERE ID = @IdTarjeta
END
GO

CREATE OR ALTER PROCEDURE PasajeroSale 
	@IDTarjeta int, @IDEstacionSalida smallint
AS
BEGIN
BEGIN TRANSACTION
	
BEGIN TRY
	
	IF EXISTS	(
				SELECT IDEstacionEntrada FROM LM_Viajes 
				WHERE IDTarjeta = @IDTarjeta
				AND DATEDIFF(HH,MomentoEntrada,CURRENT_TIMESTAMP) <= 24
				)
	BEGIN
		UPDATE LM_Viajes
		SET IDEstacionSalida = @IDEstacionSalida,
			MomentoSalida = CURRENT_TIMESTAMP
		WHERE IDTarjeta = @IDTarjeta AND MomentoEntrada = (SELECT MAX(MomentoEntrada) FROM LM_Viajes)
	END--IF
	ELSE
	BEGIN
		INSERT INTO LM_Viajes (IDTarjeta, IDEstacionEntrada, IDEstacionSalida, MomentoEntrada, MomentoSalida, Importe_Viaje)
		VALUES (
		@IDTarjeta
		,NULL
		,@IDEstacionSalida
		,NULL
		,CURRENT_TIMESTAMP
		,(SELECT Precio_Zona FROM LM_Zona_Precios WHERE Zona = (SELECT Zona_Estacion FROM LM_Estaciones 
																WHERE ID = @IDEstacionSalida))
		)
	END--ELSE

	DECLARE @Coste smallmoney
		SELECT @Coste =  Importe_Viaje FROM LM_Viajes 
		WHERE MomentoEntrada = (SELECT MAX(MomentoEntrada) FROM LM_Viajes)
		AND IDTarjeta = @IDTarjeta

	EXEC AplicarCoste @IDTarjeta, @Coste

END TRY
BEGIN CATCH
	ROLLBACK
END CATCH
END
GO

EXEC PasajeroSale 1, 1
GO

/*
-3-
A veces, un pasajero reclama que le hemos cobrado un viaje de forma indebida. 
Escribe un procedimiento que reciba como parámetro el ID de un pasajero y 
la fecha y hora de la entrada en el metro y anule ese viaje, actualizando además 
el saldo de la tarjeta que utilizó.
*/

CREATE OR ALTER PROCEDURE AnularViaje @IDPasajero int, @MomentoEntrada smalldatetime
AS
BEGIN
	BEGIN TRANSACTION
	BEGIN TRY
		DECLARE @IDViaje int
			,@ImporteADevolver smallmoney
			,@IDTarjeta int
		
		-- Buscamos el viaje que tenemos que anular
		SELECT @IDViaje = V.ID FROM LM_Viajes AS V
			INNER JOIN LM_Tarjetas AS T
			ON T.ID = V.IDTarjeta
			WHERE T.IDPasajero = @IDPasajero
			AND MomentoEntrada = @MomentoEntrada

		-- Anulamos el coste
			-- Primero vemos cuanto cuesta
		SELECT @ImporteADevolver = Importe_Viaje
		FROM LM_Viajes
		WHERE ID = @IDViaje

		UPDATE LM_Viajes
		SET Importe_Viaje = 0
		WHERE ID = @IDViaje
		
		-- Actualizamos saldo
		SELECT @IDTarjeta = IDTarjeta FROM LM_Viajes
		WHERE ID = @IDViaje

		UPDATE LM_Tarjetas
		SET Saldo -= @ImporteADevolver
		WHERE ID = @IDTarjeta
	
	COMMIT TRANSACTION
	END TRY
	BEGIN CATCH
		ROLLBACK
	END CATCH
END
GO

SELECT * FROM LM_Viajes
EXEC AnularViaje 3,'24-02-2017 16:50:00'
GO

/*
-4-
La empresa de Metro realiza una campaña de 
promoción para pasajeros fieles.

Crea un procedimiento almacenado que recargue 
saldo a los pasajeros que cumplan determinados requisitos.
Se recargarán N1 euros a los pasajeros que hayan consumido 
más de 30 euros en el mes anterior (considerar mes completo, del día 1 al fin) 
y N2 euros a los que hayan utilizado más de 10 veces alguna estación de las zonas 3 o 4.

Los valores de N1 y N2 se pasarán como parámetros. Si se omiten, se tomará el valor 5.

Ambos premios son excluyentes. Si algún pasajero cumple ambas condiciones se le aplicará 
la que suponga mayor bonificación de las dos.
*/

CREATE OR ALTER PROCEDURE AplicarPromocion @premioMesAnterior smallmoney, @premioZonas3y4 smallmoney
AS
BEGIN
	DECLARE @PremioMayor bit
	SET @PremioMayor = 
		CASE WHEN @premioMesAnterior >= @premioZonas3y4 THEN 0
			ELSE 1
		END -- CASE

	DECLARE @Ganadores TABLE (
		IDTarjeta int PRIMARY KEY
		,PremioMesAnterior bit
		,PremioZonas3y4 bit
		,YaPremiado bit
	)

	INSERT INTO @Ganadores
	SELECT IDTarjeta
		,CASE
			WHEN SUM(Importe_Viaje) > 30 THEN 1
			ELSE 0
		END
		,CASE 
			WHEN COUNT(CASE WHEN EE.Zona_Estacion = 3
									OR EE.Zona_Estacion = 4
									OR ES.Zona_Estacion = 3
									OR ES.Zona_Estacion = 4
							THEN 1
							ELSE NULL
						END) > 10 THEN 1
			ELSE 0
		END
		, 0 -- Ninguno está premiado todavía

	FROM LM_Viajes AS V
	INNER JOIN LM_Estaciones AS EE
	ON EE.ID = V.IDEstacionEntrada
	INNER JOIN LM_Estaciones AS ES
	ON ES.ID = V.IDEstacionSalida

	WHERE MONTH(MomentoEntrada) = MONTH(CURRENT_TIMESTAMP) - 1
	GROUP BY V.IDTarjeta
	--End del select monstruoso

	--Hacemos update del saldo
	UPDATE LM_Tarjetas
	SET Saldo +=
	CASE WHEN G.PremioMesAnterior = 1 OR G.PremioZonas3y4 = 1 
		THEN CASE
			WHEN @PremioMayor = 0
				THEN COALESCE(NULLIF(G.PremioMesAnterior,0),NULLIF(G.PremioZonas3y4,0))
			WHEN @PremioMayor = 1
				THEN COALESCE(NULLIF(G.PremioZonas3y4,0),NULLIF(G.PremioMesAnterior,0))
		END
		ELSE 0
	END
	FROM @Ganadores AS G
	WHERE IDTarjeta = G.IDTarjeta
END
GO

EXEC AplicarPromocion 10, 10