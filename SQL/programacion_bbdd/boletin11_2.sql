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