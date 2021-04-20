USE Northwind
GO

--2 Vender (hoy) 3 de Pavlova, 10 de Inlagd Sil y 25 de Filo Mix a un cliente.
--Distribuidor Speedy Express y vendedor Laura Callahan.

INSERT INTO [dbo].[Orders]
           ([CustomerID]
           ,[EmployeeID]
           ,[OrderDate]
           ,[RequiredDate]
           ,[ShippedDate]
           ,[ShipVia]
           ,[Freight]
           ,[ShipName]
           ,[ShipAddress]
           ,[ShipCity]
           ,[ShipRegion]
           ,[ShipPostalCode]
           ,[ShipCountry])
     VALUES
           (
		   'ALFKI'
           ,(SELECT EmployeeID FROM Employees WHERE FirstName = 'Laura' AND LastName = 'Callahan')
           ,CURRENT_TIMESTAMP
           ,DATEADD(MONTH, 1, CURRENT_TIMESTAMP)
           ,NULL
           ,1
		   ,38
           ,'EVERGREEN'
           ,(SELECT Address FROM Customers WHERE CustomerID = 'ALFKI')
           ,(SELECT City FROM Customers WHERE CustomerID = 'ALFKI')
           ,(SELECT Region FROM Customers WHERE CustomerID = 'ALFKI')
           ,(SELECT PostalCode FROM Customers WHERE CustomerID = 'ALFKI')
           ,(SELECT Country FROM Customers WHERE CustomerID = 'ALFKI')
		   )
GO

DECLARE @OrderID int
SELECT @OrderID = @@IDENTITY

INSERT INTO [dbo].[Order Details]
           ([OrderID]
           ,[ProductID]
           ,[UnitPrice]
           ,[Quantity]
           ,[Discount])
     VALUES
           (@OrderID
           ,(SELECT ProductID FROM Products WHERE ProductName = 'Pavlova')
           ,(SELECT UnitPrice FROM Products WHERE ProductName = 'Pavlova')
           ,3
           ,0)
		   ,
           (@OrderID
           ,(SELECT ProductID FROM Products WHERE ProductName = 'Inlagd Sil')
           ,(SELECT UnitPrice FROM Products WHERE ProductName = 'Inlagd Sil')
           ,10
           ,0)
		   ,
           (@OrderID
           ,(SELECT ProductID FROM Products WHERE ProductName = 'Filo Mix')
           ,(SELECT UnitPrice FROM Products WHERE ProductName = 'Filo Mix')
           ,25
           ,0)
GO

/*
Ante la bajada de ventas producida por la crisis, hemos de adaptar nuestros precios según las siguientes reglas:
	Los productos de la categoría de bebidas (Beverages) que cuesten más de $10 reducen su precio en un dólar.
	Los productos de la categoría Lácteos que cuesten más de $5 reducen su precio en un 10%.
	Los productos de los que se hayan vendido menos de 200 unidades en el último año, reducen su precio en un 5%
*/
SELECT * FROM Categories
SELECT * FROM Products

UPDATE Products
SET UnitPrice = UnitPrice - 1
WHERE CategoryID = 1 AND UnitPrice > 10

UPDATE Products
SET UnitPrice = UnitPrice - (UnitPrice / 10)
WHERE CategoryID = 4 AND UnitPrice > 5

UPDATE Products
SET UnitPrice = UnitPrice / 20
WHERE ProductID IN (SELECT OD.ProductID AS Ventas
					FROM [Order Details] AS OD
					INNER JOIN Orders AS O
					ON O.OrderID = OD.OrderID
					GROUP BY ProductID, O.OrderDate
					HAVING COUNT(ProductID)	< 200 
						AND YEAR(O.OrderDate) = MAX(YEAR(O.ORDERDATE))
					)
GO

/*
Inserta un nuevo vendedor llamado Michael Trump. Asígnale los territorios de Louisville, Phoenix, Santa Cruz y Atlanta.
*/

SELECT * FROM Employees

INSERT INTO [dbo].[Employees]
           ([LastName]
           ,[FirstName]
           ,[Title]
           ,[TitleOfCourtesy]
           ,[BirthDate]
           ,[HireDate]
           ,[Address]
           ,[City]
           ,[Region]
           ,[PostalCode]
           ,[Country]
           ,[HomePhone]
           ,[Extension]
           ,[Photo]
           ,[Notes]
           ,[ReportsTo]
           ,[PhotoPath])
     VALUES
           ('Trump'
           ,'Michael'
           ,'Sales representative'
           ,'Capo'
           ,'1948-12-08 00:00:00.000'
           ,'1992-05-01 00:00:00.000'
           ,'Avda del Oceano 111 Izq'
           ,'Punta Umbria'
           ,'Andalusia'
           ,'21010'
           ,'Spain'
           ,'(206) 555-9857'
           ,'5467'
           ,null
           ,'Education includes a BA in psychology from Colorado State University in 1970.  She also completed "The Art of the Cold Call."  Nancy is a member of Toastmasters International.'
           ,2
           ,'http://accweb/emmployees/davolio.bmp')


DECLARE @ID int
SET @ID = @@IDENTITY

SELECT * FROM Territories

INSERT INTO [dbo].[EmployeeTerritories]
           ([EmployeeID]
           ,[TerritoryID])
     VALUES
           (@ID
			,(SELECT TerritoryID FROM Territories WHERE TerritoryDescription = 'Louisville'))
		   ,(@ID
			,(SELECT TerritoryID FROM Territories WHERE TerritoryDescription = 'Phoenix'))
		   ,(@ID
			,(SELECT TerritoryID FROM Territories WHERE TerritoryDescription = 'Santa Cruz'))
		   ,(@ID
			,(SELECT TerritoryID FROM Territories WHERE TerritoryDescription = 'Atlanta'))


/*
Haz que las ventas del año 97 de Robert King que haya hecho a clientes de los estados de California
y Texas se le asignen al nuevo empleado.
*/
SELECT Region FROM Customers WHERE Country = 'USA'
SELECT * FROM Orders

UPDATE Orders
SET EmployeeID = @ID
FROM Orders AS O
INNER JOIN Employees AS E
ON E.EmployeeID = O.EmployeeID
INNER JOIN Customers AS C
ON C.CustomerID = O.CustomerID
WHERE 
	YEAR(OrderDate) = 1997 
	AND E.FirstName = 'ROBERT' AND E.LastName = 'KING'
	AND (C.Region = 'CA' OR C.Region = 'TX')
GO

/*
Todos los que han comprado "Outback Lager" han comprado cinco años 
después la misma cantidad de Chang al mismo vendedor
*/
DECLARE @output TABLE (idanterior int, idnueva int)

INSERT INTO Orders (CustomerID, EmployeeID, OrderDate,
	RequiredDate, ShipVia, ShipName, ShipAddress, ShipCity, ShipRegion,
	ShipPostalCode, ShipCountry)

OUTPUT inserted.OrderID INTO @output

SELECT 
	O.CustomerID, 
	O.EmployeeID, 
	DATEADD(YEAR, 5, O.OrderDate),
	DATEADD(MONTH, 1, DATEADD(YEAR, 5, O.OrderDate)), 
	O.ShipVia, 
	O.ShipName, 
	O.ShipAddress, 
	O.ShipCity, 
	O.ShipRegion,
	O.ShipPostalCode, 
	O.ShipCountry
FROM Orders AS O
INNER JOIN [Order Details] AS OD
ON OD.OrderID = O.OrderID
INNER JOIN Products AS P
ON P.ProductID = OD.ProductID
WHERE P.ProductName = 'OUTBACK LAGER'


INSERT INTO [dbo].[Order Details]
           ([OrderID]
           ,[ProductID]
           ,[UnitPrice]
           ,[Quantity]
           ,[Discount])
SELECT ID, 1, 0.85, P.UnitPrice
FROM @output


