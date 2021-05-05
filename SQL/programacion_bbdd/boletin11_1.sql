/*
-1-
Deseamos incluir un producto en la tabla Products llamado "Cruzcampo lata” pero no estamos seguros si se ha insertado o no.
El precio son 4,40, el proveedor es el 16, la categoría 1 y la cantidad por unidad es "Pack 6 latas” "Discontinued” toma el 
valor 0 y el resto de columnas se dejarán a NULL.
*/
GO
BEGIN
	IF NOT EXISTS (SELECT * FROM Products
					WHERE ProductName = 'CRUZCAMPO LATA')
	BEGIN
		INSERT INTO [dbo].[Products]
				   ([ProductName]
				   ,[SupplierID]
				   ,[CategoryID]
				   ,[QuantityPerUnit]
				   ,[UnitPrice]
				   ,[UnitsInStock]
				   ,[UnitsOnOrder]
				   ,[ReorderLevel]
				   ,[Discontinued])
			 VALUES
				   ('Cruzcampo lata'
				   ,null
				   , 1
				   ,'Pack de 6 latas'
				   ,4.40
				   ,null
				   ,null
				   ,null
				   ,0)
	END
END
GO

/*
-2-
Comprueba si existe una tabla llamada ProductSales. Esta tabla ha de tener de cada producto el ID, 
el Nombre, el Precio unitario, el número total de unidades vendidas y el total de dinero facturado 
con ese producto. Si no existe, créala
*/

IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES
				WHERE TABLE_NAME = 'ProductSales')
BEGIN
	CREATE TABLE ProductSales (
		ProductId int not null,
		ProductName nvarchar(40) not null,
		UnitPrice money not null,
		UnitsSold int not null,
		TotalEarnings money not null
	)
END
GO

SELECT * FROM ProductSales
/*
-3-
Comprueba si existe una tabla llamada ShipShip. Esta tabla ha de tener de cada Transportista el ID, 
el Nombre de la compañía, el número total de envíos que ha efectuado y el número de países 
diferentes a los que ha llevado cosas. Si no existe, créala
*/

IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES
				WHERE TABLE_NAME = 'ShipShip')
BEGIN
	CREATE TABLE ShipShip (
		ShipperID int not null,
		CompanyName nvarchar(40) not null,
		TotalShipments int not null,

	)
END