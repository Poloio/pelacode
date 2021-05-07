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