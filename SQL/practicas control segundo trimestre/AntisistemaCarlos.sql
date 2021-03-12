-- Oh, capitán, mi capitán

-- Cuántos actos cada organización o grupo (aunque sean junto a otros) y personas detenidas en ellas

SELECT DISTINCT G.ID, G.Nombre, COUNT(GP.IDActo) AS [Actos participados], ISNULL(D.TotalDetenidos, 0) AS TotalDetenidos 
FROM Grupos AS G
INNER JOIN GruposProtestas AS GP
ON GP.IDGrupo = G.ID
LEFT JOIN (
	SELECT D.IDActo, GP.IDGrupo, COUNT(D.IDActivista) AS TotalDetenidos FROM Detenciones AS D
	INNER JOIN GruposProtestas AS GP
	ON GP.IDActo = D.IDActo
	INNER JOIN Activistas AS A
	ON A.Organizacion = GP.IDGrupo
	GROUP BY D.IDActo, GP.IDGrupo
) AS D
ON D.IDActo = GP.IDActo AND D.IDGrupo = G.ID
GROUP BY G.ID, G.Nombre, D.TotalDetenidos
GO

-- me he rallado mucho porque creía que lo estaba haciendo mal porque salía en blanco y era porque todos
-- los activistas estaban con su grupo a null

-- Actos en los que NO se han producido incidentes sin materiales de "Arrojadizas" ni "Armas blancas"
SELECT * FROM Categorias
GO

SELECT DISTINCT MI.IDActo, AP.Titulo FROM MaterialesIncidentes AS MI -- Comenzando en MI ya sabemos que en todos los actos resultados tienen incidentes
INNER JOIN Materiales AS M
ON MI.IDMaterial = M.ID
INNER JOIN Categorias AS C
ON C.ID = M.Categoria
INNER JOIN ActosProtesta AS AP
ON AP.ID = MI.IDActo
WHERE C.Nombre NOT IN ('ARROJADIZAS', 'ARMAS BLANCAS')
GO

-- El acto en el que más detenciones se han producido en cada ciudad y cuántos eran menores de edad
-- Vemos a cuantos han detenido en cada acto y ciudad
CREATE VIEW DetencionesActoCiudad AS
SELECT AP.Ciudad, AP.ID, COUNT(D.ID) AS Detenciones FROM ActosProtesta AS AP
INNER JOIN Detenciones AS D
ON AP.ID = D.IDActo
GROUP BY AP.Ciudad, AP.ID
GO

SELECT DAC.Ciudad, AP.Titulo, MD.MenoresDetenidos FROM DetencionesActoCiudad AS DAC

INNER JOIN ( -- Maximo de detenidos en un acto en cada ciudad
	SELECT Ciudad, MAX(Detenciones) AS MaxDetenciones FROM DetencionesActoCiudad
	GROUP BY Ciudad
) AS mxDC ON DAC.Ciudad = mxDC.Ciudad AND mxDC.MaxDetenciones = DAC.Detenciones

INNER JOIN ( -- Menores detenidos por cada acto
	SELECT D.IDActo, COUNT(A.ID) AS MenoresDetenidos FROM Detenciones AS D
	INNER JOIN Activistas AS A
	ON A.ID = D.IDActivista
	WHERE DATEPART(YEAR, CURRENT_TIMESTAMP) - DATEPART(YEAR, FechaNac) < 18-- Manera incorrecta, no me acuerdo de la precisa y no me puedo parar
	GROUP BY D.IDActo
) AS MD ON IDActo = DAC.ID

INNER JOIN ActosProtesta AS AP-- Hacemos join para saber el nombre del acto
ON DAC.ID = AP.ID

-- Saber la hora más peligrosa en cada ciudad
-- No me acuerdo de nada sobre obtener las horas por intervalos :/
-- Lo haría obteniendo el número de detenciones en cada intervalo de hora en cada ciudad,
-- haciendo una tabla que contenga el numero de detenciones por intervalo, obteniendo esta de
-- la hora de detencion en incidentes.
--
-- Después haría una selección que me diga cuál ha sido el número más alto en cada ciudad sobre
-- la tabla anterior, basicamente haciendo una superventa juntando en el ON 2.MaxDetenciones = 1.Detenciones AND 1.Ciudad = 2.Ciudad

-- Me sobra tiempo asi que lo intento
-- Creo que con datepart pilla la hora en el intervalo que se pide

GO
CREATE VIEW vDetencionesCiudadHora AS
SELECT AP.Ciudad, DATEPART(HOUR,D.Hora) AS Hora, COUNT(D.ID) AS Detenciones FROM Incidentes AS I
INNER JOIN Detenciones AS D
ON D.OrdIncidente = I.Ord
INNER JOIN ActosProtesta AS AP
ON AP.ID = I.IDActo
GROUP BY AP.Ciudad, DATEPART(HOUR,D.Hora)
GO

SELECT MXDC.Ciudad, DC.Hora AS [Hora mas peligrosa] FROM vDetencionesCiudadHora AS DC
INNER JOIN (
	SELECT Ciudad, MAX(Detenciones) MaxDetenciones FROM vDetencionesCiudadHora
	GROUP BY Ciudad
) AS MXDC ON MXDC.MaxDetenciones = DC.Detenciones AND MXDC.Ciudad = DC.Ciudad


-- Actualizar club de fans de el fari para nombrar socios a todos los activistas que hayan participado
-- en actos convocados por ellos
SELECT * FROM Activistas
-- Primero obtenemos los activistas que han participado en actos convocados por este grupo
GO
CREATE VIEW ParticipanActosDelFari AS
SELECT ACP.IDActivista AS IDProtesta FROM ActosProtesta AS AP
INNER JOIN ActivistasProtestas AS ACP
ON ACP.IDActo = AP.ID
INNER JOIN GruposProtestas AS GP
ON GP.IDActo = AP.ID
INNER JOIN Grupos AS G
ON G.ID = GP.IDGrupo
WHERE G.Nombre = 'CLUB DE FANS DE EL FARI'
-- Y hacemos el update requiriendo que sus IDs esténe en la vista en la cláusula where
GO
UPDATE Activistas
SET Organizacion = (SELECT ID FROM Grupos WHERE Nombre = 'CLUB DE FANS DE EL FARI')
WHERE ID IN (SELECT * FROM ParticipanActosDelFari)
	
	