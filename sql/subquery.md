Draw the ERD diagram of library management system, that manages author, book, customer and cutomer_order.
Wrtie postgresql code to create corresponding table and relationship.
Insert data into them.

What is subquery?
    A subquery is a SELECT query contained inside another query. It is used when the result of one query is needed to be used in another query.
    
    example1 extract last order date from all order, and then get last order details.

    example2: select all books for which page count is greator than averag_page_count of all books in library.

Where it can be used?
    Clauses: WHERE,FROM,SELECT,HAVING
    Statement: INSERT,UPDATE,DELETE,SELECT

Subquery Examples & Usages:
    single row subquery -> subquery returns single row.
    multiple row subquery -> more than one row is returned by subquery.
    ex: select all books having languages as eng,eng-GB,en-CA,en-US

    subquery returning multiple columns.
    ex: Show all orders that were sent to the same city and country that the customer of an ID of 26 has lived in.





__________________________________________________________________________________
ANSWERS
‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾
=> ERD: /res/library-erd.png
=> books having page count greator than average page count
SELECT 
    book_id,title,isbn13,language_id,num_pages,publication_date,publisher_id
FROM book
WHERE num_pages > (
    SELECT AVG(num_pages)
    FROM book
);

=> Books having specific languages
```sql
SELECT
book_id,
title
FROM book
WHERE language_id IN (
  SELECT language_id
  FROM book_language
  WHERE language_code IN ('eng', 'en-GB', 'en-CA', 'en-US')
);
```

=> subquery returing multiple column
```sql
SELECT
    co.order_id,
    co.order_date,
    co.customer_id,
    a.address_id,
    a.street_number,
    a.street_name,
    a.city,
    c.country_name
FROM cust_order co
INNER JOIN address a ON co.dest_address_id = a.address_id
INNER JOIN country c ON a.country_id = c.country_id
WHERE (a.city, c.country_name) IN ( --multi column subquery
	SELECT
	a.city,
	co.country_name
	FROM address a
	INNER JOIN customer_address ca ON a.address_id = ca.address_id
	INNER JOIN country co ON a.country_id = co.country_id
	WHERE ca.customer_id = 26
);
```

__________________________________________________________________________________
References
‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾
video-sql: https://github.com/bbrumm/databasestar/tree/main/courses/course_master_subqueries