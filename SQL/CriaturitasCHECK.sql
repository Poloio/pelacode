USE Ejemplos
GO
ALTER TABLE CriaturitasRaras ADD
	CONSTRAINT CKCriaturitasNumeroPie CHECK (NumeroPie BETWEEN 25 AND 60),
	CONSTRAINT CKCriaturitasNumeroChico CHECK (NumeroChico < 1000),
	CONSTRAINT CKCriaturitasNumeroGrande CHECK (NumeroGrande > NumeroChico*100),
	CONSTRAINT CKCriaturitasNumeroMediano CHECK (NumeroIntermedio % 2 = 0 AND NumeroIntermedio BETWEEN NumeroChico AND NumeroGrande),
	CONSTRAINT CKCriaturitasFechaNac CHECK (FechaNac < CAST(CURRENT_TIMESTAMP AS DATE)),
	CONSTRAINT CKCriaturitasNivelIngles CHECK (NivelIngles IN ('A0','A1','A2','B1','B2','C1','C2')),
	CONSTRAINT CKCriaturitasHistorieta CHECK (Historieta NOT LIKE '%oscuro%' AND Historieta NOT LIKE '%apocalipsis%'),
	CONSTRAINT CKCriaturitasTemperatura CHECK (Temperatura BETWEEN 32.5 AND 44)
GO
