USE Colegas
GO

INSERT INTO People (ID, Nombre, Apellidos, FechaNac)
VALUES
	(1, 'Eduardo', 'Mingo', '1990-07-14'),
	(2, 'Margarita', 'Padera', '1992-11-11'),
	(4, 'Eloisa', 'Lamandra', '2000-03-02'),
	(5, 'Jordi', 'Videndo', '1989-05-28'),
	(6, 'Alfonso', 'Sito', '1978-10-10')
GO

INSERT INTO Carros (ID, Marca, Modelo, Anho, Color, IDPropietario) 
VALUES 
	(1, 'Seat', 'Ibiza', 2014, 'Blanco', null),
	(2, 'Ford', 'Focus', 2016, 'Rojo', 1),
	(3, 'Toyota', 'Corolla', 2017, 'Blanco', 4),
	(5, 'Renault', 'Megane', 2015, 'Azul', 2),
	(8 ,'Mitshubishi', 'Colt', 2011, 'Rojo', 6)
GO

INSERT INTO Libros (ID, Titulo, Autors)
VALUES
	(2, 'El corazón de las Tinieblas', 'Joseph Conrad'),
	(4, 'Cien años de soledad', 'Gabriel García Márquez'),
	(8, 'Harry Potter y la cámara de los secretos', 'J. K. Rowling'),
	(16, 'Evangelio del Flying Spaghetti Monster', 'Bobby Henderson')
GO

INSERT INTO Lecturas (IDLibro, IDLector, AnhoLectura)
VALUES
	(4, 1, 2020),
	(2, 4, 2021)
GO

UPDATE Carros SET IDPropietario = 6 WHERE IDPropietario = 2
GO

SELECT Nombre, Apellidos FROM People
WHERE DATEDIFF(YEAR, FechaNac, CAST(CURRENT_TIMESTAMP AS DATE)) >= 30
GO

SELECT Marca, Anho, Modelo FROM Carros
WHERE Color != 'Blanco' AND Color != 'Verde'
GO

INSERT INTO Libros (ID, Titulo, Autors)
VALUES (69, 'Vidas Santas', 'Abate Bringas')
GO

UPDATE Lecturas SET IDLibro = 69 WHERE IDLibro = 16
GO

UPDATE Carros SET IDPropietario = 5 
WHERE ID = 1
GO

SELECT IDLector FROM Lecturas
WHERE IDLibro % 2 = 0
GO

