USE Northwind
GO

--2 Vender (hoy) 3 de Pavlova, 10 de Inlagd Sil y 25 de Filo Mix a un cliente.
--Distribuidor Speedy Express y vendedor Laura Callahan.

USE [Northwind]
GO

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
           ('ALFKI'
           ,(SELECT EmployeeID FROM Employees WHERE FirstName = 'Laura' AND LastName = 'Callahan')
           ,CURRENT_TIMESTAMP
           ,DATEADD(MONTH, 1, CURRENT_TIMESTAMP)
           ,NULL
           ,1
           ,30,38
           ,'EVERGREEN'
           ,'Obere Str. 57'
           ,<ShipCity, nvarchar(15),>
           ,<ShipRegion, nvarchar(15),>
           ,<ShipPostalCode, nvarchar(10),>
           ,<ShipCountry, nvarchar(15),>)
GO
select * from Orders
select * from Customers where CustomerID = 'ALFKI'

select

