-- Base de datos de clientes de un cyber
-- 14/11/2019
-- Don Pelayo el Reconquistador

CREATE DataBase Urbanizacion
GO
USE Urbanizacion
GO

CREATE TABLE URViviendas (
	ID SmallInt Not Null Identity (100,1),
	Direccion VarChar(50) Not NULL,
	Superficie Decimal(5,1) NULL,
	IDPropietario SmallInt Not NULL,
	CONSTRAINT PKViviendas Primary Key (ID)
)
GO

CREATE TABLE URPersonas (
	ID SmallInt Not NULL Identity (1,1),
	Nombre VarChar(30) Not NULL,
	IDViviendaVive SmallInt Not NULL,
	IDPareja SmallInt NULL,
	CONSTRAINT PKPersonas Primary Key (ID),
	CONSTRAINT FKViviendaPersonaVive Foreign Key (IDViviendaVive) REFERENCES URPersonas (ID)
ON DELETE No Action ON UPDATE Cascade,
	CONSTRAINT FKPareja FOREIGN KEY (IDPareja) REFERENCES URPersonas (ID)
ON DELETE No Action ON UPDATE Cascade,
	CONSTRAINT UKMonogamia UNIQUE (IDPareja)
)
GO

ALTER TABLE URViviendas ADD CONSTRAINT FKViviendaPropietario FOREIGN KEY (IDPropietario) 
	REFERENCES URPersonas (ID) ON DELETE No Action ON UPDATE Cascade
GO

CREATE TABLE URRecibos (
	
)
GO