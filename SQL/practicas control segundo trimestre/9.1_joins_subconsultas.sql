
-- 1 Nombre de los proveedores y número de productos que nos vende cada uno
SELECT CompanyName, COUNT(P.ProductID) AS [Number of products] FROM Suppliers AS S
INNER JOIN Products AS P
ON P.SupplierID = S.SupplierID
GROUP BY CompanyName

-- 2 Nombre completo y telefono de los vendedores que trabajen en New York, Seattle, Vermont, Columbia, Los Angeles, Redmond o Atlanta.
SELECT FirstName, LastName, HomePhone FROM Employees
WHERE City IN ('NEW YORK', 'SEATTLE', 'VERMONT', 'COLUMBIA', 'LOS ANGELES', 'REDMOND', 'ATLANTA')

-- 3 Número de productos de cada categoría y nombre de la categoría.
SELECT C.CategoryName, COUNT(P.ProductID) AS [Number of products] FROM Categories AS C
INNER JOIN Products AS P
ON P.CategoryID = C.CategoryID
GROUP BY C.CategoryName

-- 4 Nombre de la compañía de todos los clientes que hayan comprado queso de cabrales o tofu.
SELECT ProductName FROM Products

SELECT DISTINCT C.CompanyName FROM Customers AS C
INNER JOIN Orders AS O
ON C.CustomerID = O.CustomerID
INNER JOIN [Order Details] AS OD
ON O.OrderID = OD.OrderID
INNER JOIN Products AS P
ON P.ProductID = OD.ProductID
WHERE P.ProductName IN ('QUESO DE CABRALES', 'TOFU')

-- 5 Empleados (ID, nombre, apellidos y teléfono) que han vendido algo a Bon app' o Meter Franken.
SELECT E.EmployeeID, E.FirstName, E.LastName, E.HomePhone FROM Employees AS E
INNER JOIN Orders AS O
ON O.EmployeeID = E.EmployeeID
INNER JOIN Customers AS C
ON C.CustomerID = O.CustomerID
WHERE C.CompanyName IN ('Bon app''', 'Meter Franken')

-- 6 Empleados (ID, nombre, apellidos, mes y día de su cumpleaños) que no han vendido nunca nada a ningún cliente de Portugal. *
SELECT EmployeeID, FirstName, LastName, DATEPART(MONTH, BirthDate) AS Bithmonth, DATEPART(DAY, BirthDate) AS Birthday
FROM Employees
WHERE EmployeeID NOT IN (
	SELECT E.EmployeeID FROM Employees AS E
	INNER JOIN Orders AS O
	ON O.EmployeeID = E.EmployeeID
	INNER JOIN Customers AS C
	ON C.CustomerID = O.CustomerID
	WHERE C.Country = 'PORTUGAL'
	)

-- 7 Total de ventas en US$ de productos de cada categoría (nombre de la categoría).
SELECT C.CategoryName, SUM(ISNULL(OD.Quantity*OD.UnitPrice, 0)) AS [Earnings US$] FROM Categories AS C
--ISNULL si no se ha vendido ningun producto de alguna categoria
LEFT JOIN Products AS P
ON P.CategoryID = C.CategoryID
LEFT JOIN [Order Details] AS OD
ON OD.ProductID = P .ProductID
GROUP BY C.CategoryName

-- 8 Total de ventas en US$ de cada empleado cada año (nombre, apellidos, dirección).
SELECT E.FirstName, E.LastName, E.Address, DATEPART(YEAR, O.OrderDate) AS OrderYear, SUM(OD.Quantity*OD.UnitPrice) AS YearlyEarnings FROM Employees AS E
INNER JOIN Orders AS O
ON O.EmployeeID = E.EmployeeID
INNER JOIN [Order Details] AS OD
ON OD.OrderID = O.OrderID
GROUP BY E.FirstName, E.LastName, E.Address, DATEPART(YEAR, O.OrderDate)
ORDER BY E.LastName, DATEPART(YEAR, O.OrderDate)

-- 9 Ventas de cada producto en el año 97. Nombre del producto y unidades.
SELECT P.ProductName, SUM(OD.Quantity) AS [Units sold in 1997] FROM Products AS P
INNER JOIN [Order Details] AS OD
ON P.ProductID = OD.ProductID
INNER JOIN Orders AS O
ON O.OrderID = OD.OrderID
WHERE DATEPART(YEAR, O.OrderDate) = 1997
GROUP BY P.ProductName
ORDER BY P.ProductName

-- 10 Cuál es el producto del que hemos vendido más unidades en cada país. *
-- Vemos cuanto se ha vendido cada producto en cada pais
GO
CREATE OR ALTER VIEW ProductosVendidosPorPais AS
SELECT OD.ProductID, O.ShipCountry, SUM(OD.Quantity) AS Quantity FROM [Order Details] AS OD
INNER JOIN Orders AS O
ON O.OrderID = OD.OrderID
GROUP BY OD.ProductID, O.ShipCountry
GO
-- Ahora calculamos el máximo de cada país y lo relacionamos con la anterior
SELECT PVC.ProductID, P.ProductName, PVC.ShipCountry FROM ProductosVendidosPorPais AS PVC
INNER JOIN (
	SELECT MAX(Quantity) AS MaxSales, ShipCountry FROM ProductosVendidosPorPais
	GROUP BY ShipCountry
) AS MAX
ON MAX.MaxSales = PVC.Quantity AND MAX.ShipCountry = PVC.ShipCountry
INNER JOIN Products AS P 
ON P.ProductID = PVC.ProductID
GO

-- 11 Empleados (nombre y apellidos) que trabajan a las órdenes de Andrew Fuller.
SELECT E.LastName, E.FirstName FROM Employees AS E
INNER JOIN Employees AS BO
ON E.ReportsTo = BO.EmployeeID
WHERE BO.FirstName = 'ANDREW' AND BO.LastName = 'FULLER'

-- 12 Número de subordinados que tiene cada empleado, incluyendo los que no tienen ninguno. Nombre, apellidos, ID.
SELECT BO.LastName, BO.FirstName, BO.EmployeeID, ISNULL(COUNT(SUB.EmployeeID), 0) AS Subordinates FROM Employees AS BO
LEFT JOIN Employees AS SUB
ON SUB.ReportsTo = BO.EmployeeID
GROUP BY BO.LastName, BO.FirstName, BO.EmployeeID







