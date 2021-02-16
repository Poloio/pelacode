--Nombre de la mascota, raza, especie y fecha de nacimiento de aquellos que hayan sufrido leucemia, moquillo o toxoplasmosis
SELECT M.Alias, M.Raza, M.FechaNacimiento  FROM
BI_Mascotas AS M
INNER JOIN BI_Mascotas_Enfermedades AS ME
ON M.Codigo = ME.Mascota
INNER JOIN BI_Enfermedades AS E
ON E.ID = ME.IDEnfermedad
WHERE E.Nombre IN ('leucemia','moquillo','toxoplamosis')

--Nombre, raza y especie de las mascotas que hayan ido a alguna visita en primavera (del 20 de marzo al 20 de Junio)
SELECT M.Alias, M.Raza, M.Especie FROM
BI_Mascotas AS M
INNER JOIN BI_Visitas AS V
ON M.Codigo = V.Mascota
WHERE FORMAT(V.Fecha, 'dd-mm') BETWEEN '20-03' AND '20-06'

--Nombre y teléfono de los propietarios de mascotas que hayan sufrido rabia, sarna, artritis o filariosis y hayan 
--tardado más de 10 días en curarse. Los que no tienen fecha de curación, considera la fecha actual para calcular
--la duración del tratamiento.
SELECT C.Nombre, C.Telefono FROM
BI_Clientes AS C
INNER JOIN BI_Mascotas AS M
ON C.Codigo = M.CodigoPropietario
INNER JOIN BI_Mascotas_Enfermedades AS ME
ON M.Codigo = ME.Mascota
INNER JOIN BI_Enfermedades AS E
--TERMINAR DESPUES



