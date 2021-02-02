USE pubs
GO

-- CONSULTAS --

SELECT title, price, notes FROM titles
WHERE type = 'mod_cook' OR type = 'trad_cook'
GO

SELECT job_id, job_desc, max_lvl, min_lvl FROM jobs
WHERE 110 BETWEEN min_lvl AND max_lvl

SELECT title, title_id, type FROM titles
WHERE notes LIKE '% and %'

SELECT pub_name, city FROM publishers
WHERE country = 'USA' AND (state != 'CA' AND state != 'TX')

SELECT title, price, title_id FROM titles
WHERE (type = 'business' OR type = 'psychology') AND price BETWEEN 10 AND 20

SELECT au_fname, au_lname, address FROM authors
WHERE city != 'California' AND city != 'Oregon'

SELECT au_fname, au_lname, address FROM authors
WHERE au_lname LIKE '[DGS]%'

SELECT emp_id, fname, lname, job_lvl FROM employee
WHERE job_lvl < 100 ORDER BY lname

-- MODIFICACIONES --

INSERT INTO authors (au_id, au_lname, au_fname, phone, address, city, zip, contract)
VALUES
('666-69-4200',--Cuidao con el check
'Aragon',
'Carlos',
666690298,
'c Castillo de Constantina 1 4ªA',
'Sevilla',
41013,
0)
GO

INSERT INTO publishers (pub_id, pub_name, city, country)
VALUES
	(9969,
	'Ramona Publishers',
	'Huelva',
	'Spain')
GO

INSERT INTO titles (title_id, title, type, pub_id, price, advance, royalty, ytd_sales, notes, pubdate)
VALUES
	('BU1999',
	'Tretas de un usurero honesto',
	'business',
	9969,
	999,
	5000,
	10,
	6978645,
	'A Spanish guide to invest in stocks and get rich',
	GETDATE())
	,
	('MC1888',
	'Salchipapa',
	'mod_cook',
	9969,
	8000,
	160000,
	60,
	9999999,
	'Learn to cook old and cringe Spanish food memes',
	GETDATE())
GO

INSERT INTO titleauthor (au_id, title_id)
VALUES
	('666-69-4200',
	'BU1999')
	,
	('666-69-4200',
	'MC1888')
GO

ALTER TABLE jobs 
	DROP CONSTRAINT CK__jobs__min_lvl__3F466844
GO
ALTER TABLE jobs
	ADD CONSTRAINT CK__jobs__min_lvl__3F466844 CHECK
		(min_lvl >= 90)
GO