USE Ejemplos
GO

CREATE TABLE DatosRestrictivos (
	ID SMALLINT IDENTITY(1,2) NOT NULL,
	Nombre NCHAR(15) UNIQUE NOT NULL,
	NumPelos INT NULL,
	TipoRopa CHAR NULL,
	NumSuerte TINYINT NULL,
	Minutos TINYINT NULL,
	CONSTRAINT PKDatosRestrictivos PRIMARY KEY (ID),
	CONSTRAINT CKNoEmpiezaPor CHECK (Nombre NOT LIKE 'N%' AND Nombre NOT LIKE 'X%'),
	CONSTRAINT CKNumeroEnRango CHECK (NumPelos BETWEEN 0 AND 150000),
	CONSTRAINT CKTiposRopa CHECK (TipoRopa LIKE '[CFEPBN]'),
	CONSTRAINT CKDivisiblePor3EnRango CHECK (NumSuerte BETWEEN 10 AND 40 AND NumSuerte % 3 = 0),
	CONSTRAINT CKMinutos CHECK (Minutos BETWEEN 20 AND 85 OR Minutos BETWEEN 120 AND 185)
)
GO

CREATE TABLE DatosRelacionados (
	NombreRelacionado NCHAR(15) NOT NULL,
	PalabraTabu VARCHAR(20) NULL,
	CONSTRAINT FKDatosRestrictivosRelacionados FOREIGN KEY (NombreRestrictivo) REFERENCES DatosRestrictivos (Nombre)
		ON DELETE NO ACTION ON UPDATE NO ACTION,
	CONSTRAINT CKPalabrasTabu CHECK (PalabraTabu NOT IN ('MENA','Gutrtel','ERE','Procés','sobresueldo') AND PalabraTabu NOT LIKE '%eo')
	--Seguir con esta tabla
)