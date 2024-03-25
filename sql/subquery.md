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

    subquery in FROM clause.
    ex: show data for all customers orders info per day. Columns(order_date,books_orderes,total_shipping_cost,total_book_price,total_order_amount)

    nester subquery example.
    ex: find price of all books ordered at maximum price.

    correlated subquery. (previous all query was indepedent, but a correlated query are depent upon outer query)
    The nested subquery used column in outer query, then the overall query becomes correlated.(i.e one cannot function without the other)
    ex: list of books that are longer or having higher number of pages than the average number of pages for that book publisher.

    subquery in SELECT clause

    subquery in HAVING clause

    subquery in insert statement.
    ex: get value from other table and insert into current table.

    subquery in update statment. (more common than using in  insert statement)
    during update you can use subquery in either SET or WHERE clause.

    subquery in delete statement.







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

=> customer orders info per day data
```sql
SELECT
DATE(order_date) AS order_date,
COUNT(book_id) AS books_ordered,
SUM(shipping_cost) AS total_shipping_cost,
SUM(price) AS total_book_price,
SUM(shipping_cost) + SUM(price) AS total_order_amount
FROM (
	SELECT
	co.order_id,
	co.order_date,
	sm.method_name,
	sm.cost AS shipping_cost,
	ol.book_id,
	ol.price
	FROM cust_order co
	INNER JOIN shipping_method sm ON co.shipping_method_id = sm.method_id
	INNER JOIN order_line ol ON co.order_id = ol.order_id
) AS sub
GROUP BY DATE(order_date)
ORDER BY DATE(order_date) ASC;
```

=> all books orderes at maximum price
```sql
SELECT --query3: all books info ordered at maximum price
title, 
isbn13,
publication_date
FROM book
WHERE book_id IN ( --query2: all book_id ordered at maximum price
	SELECT book_id
	FROM order_line
	WHERE price = ( --query1: maximum book price
		SELECT MAX(price)
		FROM order_line
	)
);
```

=> correlated subquery
```sql
SELECT
book_id,
title,
num_pages,
publisher_id
FROM book b
WHERE num_pages > (
	SELECT
	AVG(num_pages)
	FROM book b2
    WHERE b2.publisher_id = b.publisher_id
);
-- using join instead of correlated query
SELECT
b.book_id,
b.title,
b.num_pages,
b.publisher_id
FROM book b
INNER JOIN (
	SELECT
	publisher_id,
	AVG(num_pages) AS avg_num_pages
	FROM book
	GROUP BY publisher_id
) pub
ON b.publisher_id = pub.publisher_id
WHERE b.num_pages > pub.avg_num_pages;
```

=> subquery in select clause
```sql
SELECT
book_id,
title,
num_pages,
publisher_id,
(
	SELECT AVG(b2.num_pages)
	FROM book b2
    WHERE b2.publisher_id = b1.publisher_id
) AS avg_for_publisher
FROM book b1
WHERE num_pages < (
	SELECT AVG(b2.num_pages)
	FROM book b2
    WHERE b2.publisher_id = b1.publisher_id
);

-- alternative syntax (avoiding duplicate code)
SELECT
book_id,
title,
num_pages,
publisher_id,
avg_for_publisher
FROM (
	SELECT
	book_id,
	title,
	num_pages,
	publisher_id,
	(
		SELECT AVG(b2.num_pages)
		FROM book b2
		WHERE b2.publisher_id = b1.publisher_id
	) AS avg_for_publisher
	FROM book b1
) AS sub
WHERE num_pages < avg_for_publisher;
```

=> subquery in having clause
```sql
SELECT
DATE(order_date) AS order_date_only,
COUNT(*)
FROM cust_order
GROUP BY DATE(order_date)
HAVING COUNT(*) >= (
	SELECT COUNT(*)
	FROM cust_order
	WHERE DATE(order_date) = '2020-01-01'
)
```

=> subquery in insert statement
```sql
INSERT INTO order_summary (order_date, order_count)
VALUES ('2020-01-01', (
  SELECT COUNT(*)
  FROM cust_order
  WHERE DATE(order_date) = '2020-01-01'
  )
);
```

=> subquery in update statement
```sql
-- subquery in where during update
-- add comment for book published after first order
UPDATE book
SET comments = 'Published after first order'
WHERE publication_date > (
	SELECT MIN(order_date)
    FROM cust_order
);

-- subquery in set during update
-- set comment to say number of books ordered
UPDATE book b
SET b.comments = (
	SELECT COUNT(*)
    FROM order_line o
    WHERE o.book_id = b.book_id
);
```

=> subquery in delete statement
```sql
DELETE FROM all_books
WHERE book_id NOT IN (
	SELECT book_id
    FROM order_line
);
```

__________________________________________________________________________________
References
‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾
video-sql: https://github.com/bbrumm/databasestar/tree/main/courses/course_master_subqueries