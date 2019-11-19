-- Base de datos para guardar datos sobre aldeas y las guerras blablabla
-- 15/11/2019
-- Creado por el gran Don Pelayo el Reconquistador

CREATE DATABASE Nervionia
GO

USE Nervionia
GO

CREATE TABLE NEREspecies (
	ID SmallInt Not NULL IDENTITY(1,1),
	NomCientifico VarChar(50) Not Null,
	NomComun VarChar(50) Not NULL,
	Alimentacion VarChar(50) Not NULL,
	Volador Bit Not NULL,
	CONSTRAINT PKNER_Especies PRIMARY KEY (ID)
)
GO

CREATE TABLE NERSeres (
	ID SmallInt Not NULL IDENTITY(1,1),
	IDEspecie SmallInt Not NULL,

	CONSTRAINT PK_Seres PRIMARY KEY (ID),
	CONSTRAINT FK_SeresEspecies FOREIGN KEY (IDEspecie) REFERENCES NERESpecies (ID)
		ON DELETE No Action ON UPDATE Cascade,
	-- IDTribu DONE
)

CREATE TABLE NERTribus (
	ID Smallint Not NULL IDENTITY(1,1),
	Nombre VarChar(50) Not NULL,
	Lema VarChar(140) Not NULL,
	
	CONSTRAINT PK_Tribus PRIMARY KEY (ID)
)
GO

ALTER TABLE NERSeres ADD
	IDTribu SmallInt Not NULL,

	CONSTRAINT FK_SeresTribus FOREIGN KEY (IDTribu) REFERENCES NERTribus (ID)
GO

CREATE TABLE NERReligiones (
	ID SmallInt Not NUll IDENTITY(1,1),
	Nombre VarChar(50) Not NULL,
	Tipo VarChar(70) Not NULL

	CONSTRAINT PK_Religiones PRIMARY KEY (ID)
)
GO

CREATE TABLE TRIBUS_RELIGIONES (
	IDTribu SmallInt Not NULL,
	IDReligion SmallInt Not NULL,

	CONSTRAINT FK_TRIBUS_RELIGIONESRTribus FOREIGN KEY (IDTribu) REFERENCES NERTribus (ID)
		ON DELETE No Action ON UPDATE Cascade,
	CONSTRAINT FK_TRIBUS_RELIGIONESReligiones FOREIGN KEY (IDReligion) REFERENCES NERReligiones (ID)
		ON DELETE No Action ON UPDATE Cascade,
	CONSTRAINT PK_TRIBUS_RELIGIONES PRIMARY KEY (IDTribu, IDReligion)
)

CREATE TABLE NERSacerdotes (
	ID SmallInt Not NULL IDENTITY(1,1),
	IDReligion SmallInt Not NULL,
	Nombre VarChar(70) Not Null,

	CONSTRAINT PK_Sacerdotes PRIMARY KEY (ID),
	CONSTRAINT FK_SacerdotesReligiones FOREIGN KEY (IDReligion) REFERENCES NERReligiones (ID)
		ON DELETE No Action ON UPDATE Cascade
)

CREATE TABLE SERES_ESCLAVOS (
	IDSer SmallInt Not NULL,
	Fecha Date, NULL,

	CONSTRAINT FK_SERES_ESCLAVOSSeres FOREIGN KEY (IDSer) REFERENCES NERSeres (ID)
		ON DELETE No Action ON UPDATE Cascade,
	CONSTRAINT PK_SERES_ESCLAVOS PRIMARY KEY (IDSer)
)

CREATE TABLE NERPoblados (
	ID SmallInt Not NULL IDENTITY(1,1),
	Nombre VarChar(50) Not NULL,
	NumHab int Not NULL,
	NumForti int NULL,
	Longitud Decimal Not NULL
	-- Sin Terminar
)
