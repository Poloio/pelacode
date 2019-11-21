/*
Una empresa de recreativas quiere guardar en una base de datos información sobre sus jugadores y los juegos que quieren.
Los videojuegos tienen un nombre, un género, un estudio que lo publica y nos interesa clasificarlos por año de salida.
Los jugadores tienen un ID de cliente, nombre y pueden o no indicar si tienen algún género de videojuego favorito.
Además, nos interesa saber los juegos que se ha pasado cada jugador y sus puntuaciones máximas cuando lo hacen.
*/

CREATE DATABASE Recreativas
GO

USE Recreativas
GO

CREATE TABLE PELVideojuegos (
	ID smallInt Not NULL IDENTITY(1,1),
	Nombre varChar(70) Not NULL,
	Estudio varChar(50) Not NULL,
	AnioSalida smallInt Not NULL,

	CONSTRAINT PK_Videojuegos PRIMARY KEY (ID)
)
GO

CREATE TABLE PELJugadores (
	ID smallInt Not NULL IDENTITY(1,1),
	Nombre varChar(70) Not NULL,
		
	CONSTRAINT PK_Jugadores PRIMARY KEY (ID)
)
GO

CREATE TABLE PELGeneros (
	Nombre varChar(70) Not NULL UNIQUE,
	CONSTRAINT PK_Generos PRIMARY KEY (Nombre)
)
GO

CREATE TABLE PEL_VIDEOJUEGOS_JUGADORES (
	IDVideojuego smallInt Not NULL,
	IDJugador smallInt Not NULL,

	CONSTRAINT FK_VIDEOJUEGOS_JUGADORESVidejuegos FOREIGN KEY (IDVideojuego) REFERENCES PELVideojuegos (ID)	
		ON DELETE No Action ON UPDATE Cascade,
	CONSTRAINT FK_VIDEOJUEGOS_JUGADORESJugadores FOREIGN KEY (IDJugador) REFERENCES PELJugadores (ID)	
		ON DELETE No Action ON UPDATE Cascade,
	CONSTRAINT PK_VIDEOJUEGOS_JUGADORES PRIMARY KEY (IDVideojuego,IDJugador)
)
GO

CREATE TABLE PEL_JUGADORES_GENEROS (
	IDJugador smallInt Not NULL,
	Genero varChar(70) Not NULL,

	CONSTRAINT PEL_JUGADORES_GENEROSJugadores FOREIGN KEY (IDJugador) REFERENCES PELJugadores (ID)	
		ON DELETE No Action ON UPDATE Cascade,
	CONSTRAINT PEL_JUGADORES_GENEROSGeneros FOREIGN KEY (Genero) REFERENCES PELGeneros (Nombre)	
		ON DELETE No Action ON UPDATE Cascade,
	CONSTRAINT PK_JUGADORES_GENEROS PRIMARY KEY (IDJugador,Genero)
)
GO

CREATE TABLE PEL_VIDEOJUEGOS_GENEROS (
	IDVideojuego smallint Not NULL,
	Genero varChar(70) Not NULL,

	CONSTRAINT FK_VIDEOJUEGOS_GENEROSVideojuegos FOREIGN KEY (IDVideojuego) REFERENCES PELVideojuegos (ID)
		ON DELETE No Action ON UPDATE Cascade,
	CONSTRAINT FK_VIDEOJUEGOS_GENEROSGeneros FOREIGN KEY (Genero) REFERENCES PELGeneros (Nombre)
		ON DELETE No Action ON UPDATE Cascade,
	CONSTRAINT PK_VIDEOJUEGOS_GENEROS PRIMARY KEY (IDVideojuego,Genero)
)
GO