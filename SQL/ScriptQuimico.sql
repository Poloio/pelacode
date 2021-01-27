CREATE DATABASE LaboratorioQuimico
GO
-- USE MASTER DROP DATABASE LaboratorioQuimico

USE LaboratorioQuimico
GO

CREATE TABLE LQ_Elementos (
	simbolo NCHAR(2) NOT NULL,
	nombre VARCHAR(15) NOT NULL,
	CONSTRAINT PK_Elementos PRIMARY KEY (simbolo)
)
GO

CREATE TABLE LQ_TiposCompuesto (
	tipo SMALLINT IDENTITY NOT NULL,
	denominacion VARCHAR(30) UNIQUE NOT NULL,
	CONSTRAINT PK_TiposCompuesto PRIMARY KEY (tipo)
)
GO

CREATE TABLE LQ_Moleculas (
	nombreClasico VARCHAR(30) NOT NULL,
	nombreIUPAC VARCHAR(30) NOT NULL,
	color VARCHAR(20) NULL,
	densidad DECIMAL(5,3) NULL,
	puntoFusion REAL NOT NULL,
	puntoEbullicion REAL NOT NULL,
	CONSTRAINT CK_PFusionEsEntero CHECK (puntoFusion > 0),
	CONSTRAINT CK_PEbullicionEsEnteroYMayor CHECK (puntoEbullicion > 0 
													AND puntoEbullicion > puntoFusion)
)
GO

ALTER TABLE LQ_Elementos ADD
	numeroAtomico SMALLINT UNIQUE NOT NULL,
	masaAtomica DECIMAL(8,5) NULL,
	CONSTRAINT CK_RangoNumAtomico CHECK (numeroAtomico BETWEEN 1 AND 300)
GO

ALTER TABLE LQ_Moleculas ADD
	codigo INT IDENTITY NOT NULL,
	CONSTRAINT PK_Moleculas PRIMARY KEY (codigo)
GO

CREATE TABLE LQ_MoleculasElementos (
	simboloElemento NCHAR(2) NOT NULL,
	codigoMolecula INT NOT NULL,
	numero SMALLINT NOT NULL,
	CONSTRAINT CK_NumeroIndiceMayorQue0 CHECK (numero > 0),

	CONSTRAINT FK_MoleculasElementos_Elemento FOREIGN KEY (simboloElemento) REFERENCES LQ_Elementos (simbolo)
		ON DELETE NO ACTION ON UPDATE NO ACTION,
	CONSTRAINT FK_MoleculasELementos_Molecula FOREIGN KEY (codigoMolecula) REFERENCES LQ_Moleculas (codigo)
		ON DELETE NO ACTION ON UPDATE NO ACTION,
	CONSTRAINT PK_MoleculasElementos PRIMARY KEY (simboloElemento, codigoMolecula)
)
GO

ALTER TABLE LQ_Moleculas ADD
	tipo SMALLINT NULL,
	CONSTRAINT FK_Moleculas_TiposCompuesto FOREIGN KEY (tipo) REFERENCES LQ_TiposCompuesto (tipo)
GO

CREATE TABLE LQ_Colores (
	nombre VARCHAR(15) NOT NULL,
	CONSTRAINT PK_Colores PRIMARY KEY (nombre)
)
GO

ALTER TABLE LQ_Moleculas ADD
	color VARCHAR(15) NULL,
	CONSTRAINT FK_Moleculas_Colores FOREIGN KEY (color) REFERENCES LQ_Colores (nombre)
		ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE LQ_Elementos ADD
	tipo VARCHAR(12) NOT NULL,
	CONSTRAINT CK_TiposElemento CHECK (tipo IN ('Metal','No Metal','Gas Noble','Tierra Rara'))
GO

CREATE TABLE LQ_Reacciones (
	ID INT IDENTITY NOT NULL,
	ExoEndotermica BIT NOT NULL
	CONSTRAINT PK_Reacciones PRIMARY KEY (ID)
)

CREATE TABLE LQ_ReaccionesReactivos (
	IDReaccion INT NOT NULL,
	codigoMoleculaReactiva INT NOT NULL,
	cantidadRelativa DECIMAL(5,4) NOT NULL
	CONSTRAINT FKReaccionesReactivos_Reacciones FOREIGN KEY (IDReaccion) REFERENCES LQ_Reacciones (ID)
		ON DELETE NO ACTION ON UPDATE NO ACTION,
	CONSTRAINT FKReaccionesReactivos_Moleculas FOREIGN KEY (codigoMoleculaReactiva) REFERENCES LQ_Moleculas (codigo)
		ON DELETE NO ACTION ON UPDATE NO ACTION,
	CONSTRAINT PKReaccionesReactivos PRIMARY KEY (IDReaccion, codigoMoleculaReactiva)
)

CREATE TABLE LQ_ReaccionesProductos (
	IDReaccion INT NOT NULL,
	codigoMoleculaProducto INT NOT NULL,
	cantidadRelativa DECIMAL(5,4) NOT NULL
	CONSTRAINT FKReaccionesReactivos_Reacciones FOREIGN KEY (IDReaccion) REFERENCES LQ_Reacciones (ID)
		ON DELETE NO ACTION ON UPDATE NO ACTION,
	CONSTRAINT FKReaccionesReactivos_Moleculas FOREIGN KEY (codigoMoleculaProducto) REFERENCES LQ_Moleculas (codigo)
		ON DELETE NO ACTION ON UPDATE NO ACTION,
	CONSTRAINT PKReaccionesReactivos PRIMARY KEY (IDReaccion, codigoMoleculaProducto)
)