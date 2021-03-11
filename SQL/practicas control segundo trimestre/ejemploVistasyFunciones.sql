USE NorthWind

-- Unidades vendidas de cada producto en 1996
SELECT OD.ProductID, P.ProductName, SUM(OD.Quantity) AS VentasAnuales FROM Products AS P
	INNER JOIN [Order Details] AS OD ON P.ProductID = OD.ProductID
	INNER JOIN Orders AS O ON OD.OrderID = O.OrderID
	Where Year (O.OrderDate) = 1996 
	Group By OD.ProductID, P.ProductName


-- Las del 97. Incluimos los productos con ventas cero
SELECT P.ProductID, P.ProductName, SUM(ISNULL(OD.Quantity,0)) AS VentasAnuales FROM Products AS P
	LEFT JOIN [Order Details] AS OD ON P.ProductID = OD.ProductID
	LEFT JOIN Orders AS O ON OD.OrderID = O.OrderID
	Where Year (O.OrderDate) = 1997 OR O.OrderDate IS NULL
	Group By P.ProductID, P.ProductName

-- Queremos saber la diferencia entre ambos a�os

SELECT V97.ProductName, ISNULL(V96.VentasAnuales, 0) as Ventas96, V97.VentasAnuales As Ventas97, V97.VentasAnuales - IsNull(V96.VentasAnuales,0) AS Incremento,  
	CAST (((V97.VentasAnuales - ISNULL(V96.VentasAnuales, 0))*100)/V96.VentasAnuales AS Decimal(5,1)) AS Porcentaje FROM 
(SELECT OD.ProductID, P.ProductName, SUM(OD.Quantity) AS VentasAnuales FROM Products AS P
	INNER JOIN [Order Details] AS OD ON P.ProductID = OD.ProductID
	INNER JOIN Orders AS O ON OD.OrderID = O.OrderID
	Where Year (O.OrderDate) = 1996 
	Group By OD.ProductID, P.ProductName) AS V96
RIGHT JOIN 
(SELECT P.ProductID, P.ProductName, SUM(ISNULL(OD.Quantity,0)) AS VentasAnuales FROM Products AS P
	LEFT JOIN [Order Details] AS OD ON P.ProductID = OD.ProductID
	LEFT JOIN Orders AS O ON OD.OrderID = O.OrderID
	Where Year (O.OrderDate) = 1997 OR O.OrderDate IS NULL
	Group By P.ProductID, P.ProductName) AS V97
ON V96.ProductID = V97.ProductID

-- Ahora convertimos las subconsultas en vistas
GO
CREATE VIEW V96 AS
SELECT OD.ProductID, P.ProductName, SUM(OD.Quantity) AS VentasAnuales FROM Products AS P
	INNER JOIN [Order Details] AS OD ON P.ProductID = OD.ProductID
	INNER JOIN Orders AS O ON OD.OrderID = O.OrderID
	Where Year (O.OrderDate) = 1996 
	Group By OD.ProductID, P.ProductName

GO
CREATE VIEW V97 AS
SELECT P.ProductID, P.ProductName, SUM(ISNULL(OD.Quantity,0)) AS VentasAnuales FROM Products AS P
	LEFT JOIN [Order Details] AS OD ON P.ProductID = OD.ProductID
	LEFT JOIN Orders AS O ON OD.OrderID = O.OrderID
	Where Year (O.OrderDate) = 1997 OR O.OrderDate IS NULL
	Group By P.ProductID, P.ProductName
GO

-- Y reescribimos la consulta usando las vistas
SELECT V97.ProductName, ISNULL(V96.VentasAnuales, 0) as Ventas96, V97.VentasAnuales As Ventas97, V97.VentasAnuales - IsNull(V96.VentasAnuales,0) AS Incremento,  
	CAST (((V97.VentasAnuales - V96.VentasAnuales)*100)/V96.VentasAnuales AS Decimal(5,1)) AS Porcentaje 
	FROM V96 RIGHT JOIN V97 ON V96.ProductID = V97.ProductID

-- Versi�n con funcion inline
GO
-- Ventas de un a�o concreto
CREATE Function FNVentasAnuales (@Anno AS SmallInt) RETURNS TABLE AS
RETURN
(SELECT P.ProductID, P.ProductName, SUM(ISNULL(OD.Quantity,0)) AS VentasAnuales FROM Products AS P
	LEFT JOIN [Order Details] AS OD ON P.ProductID = OD.ProductID
	LEFT JOIN Orders AS O ON OD.OrderID = O.OrderID
	Where Year (O.OrderDate) = @Anno OR O.OrderDate IS NULL
	Group By P.ProductID, P.ProductName)
GO
--Reescribimos de nuevo la consulta 
SELECT V97.ProductName, ISNULL(V96.VentasAnuales, 0) as Ventas96, V97.VentasAnuales As Ventas97, V97.VentasAnuales - IsNull(V96.VentasAnuales,0) AS Incremento,  
	CAST (((V97.VentasAnuales - V96.VentasAnuales)*100)/Nullif (V96.VentasAnuales,0) AS Decimal(5,1)) AS Porcentaje 
	FROM FNVentasAnuales(1996) AS V96 RIGHT JOIN FNVentasAnuales(1997) AS V97 ON V96.ProductID = V97.ProductID

SELECT * FROM FNVentasAnuales(1996)
GO
-- Ahora hacemos con dos a�os consecutivos

SELECT OD.ProductID, P.ProductName, SUM(OD.Quantity) AS VentasAnuales FROM Products AS P
	INNER JOIN [Order Details] AS OD ON P.ProductID = OD.ProductID
	INNER JOIN Orders AS O ON OD.OrderID = O.OrderID
	Group By OD.ProductID, P.ProductName, Year (O.OrderDate)


SELECT VPrevio.Annio AS [A�o base], VPosterior.ProductName, ISNULL(VPrevio.VentasAnuales, 0) as VentasAnterior, VPosterior.VentasAnuales As VentasPosterior, VPosterior.VentasAnuales - IsNull(VPrevio.VentasAnuales,0) AS Incremento,  
	CAST (((VPosterior.VentasAnuales - ISNULL(VPrevio.VentasAnuales, 0))*100)/VPrevio.VentasAnuales AS Decimal(5,1)) AS Porcentaje FROM 
(SELECT OD.ProductID, P.ProductName, SUM(OD.Quantity) AS VentasAnuales, Year (O.OrderDate) AS Annio FROM Products AS P
	INNER JOIN [Order Details] AS OD ON P.ProductID = OD.ProductID
	INNER JOIN Orders AS O ON OD.OrderID = O.OrderID
	Group By OD.ProductID, P.ProductName, Year (O.OrderDate)) AS VPrevio
RIGHT JOIN 
(SELECT OD.ProductID, P.ProductName, SUM(ISNULL(OD.Quantity,0)) AS VentasAnuales, Year (O.OrderDate) AS Annio FROM Products AS P
	INNER JOIN [Order Details] AS OD ON P.ProductID = OD.ProductID
	INNER JOIN Orders AS O ON OD.OrderID = O.OrderID
	Group By OD.ProductID, P.ProductName, Year (O.OrderDate)) AS VPosterior
ON VPrevio.ProductID = VPosterior.ProductID AND VPrevio.Annio = VPosterior.Annio - 1

GO
CREATE OR ALTER View VentasAnuales AS
SELECT OD.ProductID, P.ProductName, SUM(ISNULL(OD.Quantity,0)) AS VentasAnuales, Year (O.OrderDate) AS Annio FROM Products AS P
	INNER JOIN [Order Details] AS OD ON P.ProductID = OD.ProductID
	INNER JOIN Orders AS O ON OD.OrderID = O.OrderID
	Group By OD.ProductID, P.ProductName, Year (O.OrderDate)