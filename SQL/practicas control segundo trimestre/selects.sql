USE Northwind
GO
-- 2. ID de producto y número de unidades vendidas de cada producto.
SELECT ProductID, SUM(Quantity) AS Cantidad FROM [Order Details]
GROUP BY ProductID
ORDER BY ProductID
GO

-- 3. ID del cliente y número de pedidos que nos ha hecho.
SELECT CustomerID, COUNT(OrderID) AS orderqt FROM Orders
GROUP BY CustomerID
GO

-- 4. ID del cliente, año y número de pedidos que nos ha hecho cada año.
SELECT CustomerID, DATEPART(year, OrderDate) AS Year, COUNT(OrderID) AS Number FROM Orders
GROUP BY CustomerID, DATEPART(year, OrderDate)
ORDER BY CustomerID

-- 5. ID del producto, precio unitario y total facturado de ese producto, ordenado por cantidad 
-- facturada de mayor a menor. Si hay varios precios unitarios para el mismo producto tomaremos el mayor.
SELECT ProductID, MAX(UnitPrice) AS MaxPrice, SUM(UnitPrice*Quantity) AS Total FROM [Order Details]
GROUP BY ProductID
ORDER BY SUM(UnitPrice*Quantity) DESC

-- 6. ID del proveedor e importe total del stock acumulado de productos correspondientes a ese proveedor.
SELECT SupplierID, SUM(UnitPrice * UnitsInStock) AS totalPrice FROM Products
GROUP BY SupplierID

-- 7. Número de pedidos registrados mes a mes de cada año.
SELECT DATEPART(YEAR, OrderDate) AS Año, DATEPART(MONTH, OrderDate) AS Mes, COUNT(OrderID) AS Numero FROM Orders
GROUP BY DATEPART(YEAR, OrderDate), DATEPART(MONTH, OrderDate)

-- 8. Año y tiempo medio transcurrido entre la fecha de cada pedido (OrderDate) y la fecha en la que lo hemos enviado (ShipDate), en días para cada año.
SELECT DATEPART(YEAR, OrderDate) AS Año, AVG(DATEDIFF(day, OrderDate, ShippedDate)) AS [Average Waiting Days] FROM Orders
GROUP BY DATEPART(YEAR, OrderDate)
ORDER BY DATEPART(YEAR, OrderDate) DESC
