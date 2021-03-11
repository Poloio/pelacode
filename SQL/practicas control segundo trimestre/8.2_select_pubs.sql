-- 1. Numero de libros que tratan de cada tema
SELECT type, COUNT(title_id) AS [Number of titles] FROM titles
GROUP BY type

-- 2. Número de autores diferentes en cada ciudad y estado
SELECT state, city, COUNT(au_id) FROM authors
GROUP BY state, city
ORDER BY state

-- 3. Nombre, apellidos, nivel y antigüedad en la empresa de los empleados con un nivel entre 100 y 150.
SELECT fname, lname, job_lvl FROM employee
WHERE job_lvl BETWEEN 100 AND 150
ORDER BY lname

-- 4. Número de editoriales en cada país. Incluye el país.
SELECT country, COUNT(pub_id) AS [Number of publishers] FROM publishers
GROUP BY country
ORDER BY country
 
-- 5. Número de unidades vendidas de cada libro en cada año (title_id, unidades y año).
SELECT title_id, DATEPART(year, ord_date) AS Year, SUM(qty) FROM sales
GROUP BY title_id, DATEPART(year, ord_date)
ORDER BY title_id

-- 6. Número de autores que han escrito cada libro (title_id y numero de autores).
SELECT title_id, COUNT(AU_ID) [Authors] FROM titleauthor
GROUP BY title_id

-- 7. ID, Titulo, tipo y precio de los libros cuyo adelanto inicial (advance) tenga un valor superior a $7.000, ordenado por tipo y título
SELECT title_id, type, price, advance FROM titles
WHERE advance > 7000
ORDER BY type, title