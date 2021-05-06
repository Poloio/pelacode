/*
Si la columna TipoActualiza contiene una "I" hay que insertar una nueva fila en titles con 
todos los datos leídos de esa fila de ActualizaTitles.

Si TipoActualiza contiene una "D" hay que eliminar la fila cuyo código (title_id) se incluye.

Si TipoActualiza es "U" hay que actualizar la fila identificada por title_id con las columnas que no sean Null. Las que sean Null se dejan igual.
*/

DECLARE @VCB int, @Final int
SELECT @VCB = MIN(NumActualiza) FROM ActualizaTitles
SELECT @Final = MAX(NumActualiza) FROM ActualizaTitles

WHILE @VCB <= @Final
BEGIN
	DECLARE @Tipo char(1)
	SELECT @Tipo = TipoActualiza FROM ActualizaTitles 
		WHERE NumActualiza = @VCB

	IF @Tipo = 'I'
	BEGIN
		INSERT INTO titles
		SELECT title_id, title, type, pub_id, price, advance, royalty, ytd_sales, notes, pubdate
		FROM ActualizaTitles WHERE NumActualiza = @VCB
	END

	IF @Tipo = 'D'
	BEGIN
		DELETE FROM titles 
		WHERE title_id = (SELECT title_id FROM ActualizaTitles 
							WHERE NumActualiza = @VCB)
	END

	IF @Tipo = 'U'
	BEGIN
		UPDATE titles SET 
		title_id = COALESCE(A.title_id, 
							(SELECT title_id FROM titles
							WHERE title_id = A.title_id))
		,title = COALESCE(A.title, 
							(SELECT title FROM titles
							WHERE title_id = A.title_id))
		,type = COALESCE(A.type, 
							(SELECT type FROM titles
							WHERE title_id = A.title_id))
		,pub_id = COALESCE(A.pub_id, 
							(SELECT pub_id FROM titles
							WHERE title_id = A.title_id))
		,price = COALESCE(A.price, 
							(SELECT price FROM titles
							WHERE title_id = A.title_id))
		,advance = COALESCE(A.advance, 
							(SELECT advance FROM titles
							WHERE title_id = A.title_id))
		,royalty = COALESCE(A.royalty, 
							(SELECT royalty FROM titles
							WHERE title_id = A.title_id))
		,ytd_sales = COALESCE(A.ytd_sales, 
							(SELECT ytd_sales FROM titles
							WHERE title_id = A.title_id))
		,notes = COALESCE(A.notes, 
							(SELECT notes FROM titles
							WHERE title_id = A.title_id))
		,pubdate = COALESCE(A.pubdate, 
							(SELECT pubdate FROM titles
							WHERE title_id = A.title_id))
		FROM ActualizaTitles AS A
		WHERE NumActualiza = 5
	END
	
	SELECT @VCB = MIN(NumActualiza) FROM ActualizaTitles
		WHERE NumActualiza > @VCB
END
