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
