

Can you draw the ERD diagram for a Customer Relationship Management System having tables order, person and company?
Write Postgresql code to generate the tables & relationship. (use answer of above as basis of ERD)
Write MySql code to generate tables & relationship.


Write query for each of the following requirements:
(a) Write query to show the number of orders and total order value by month for 2020.
    Hints: start small
        show all order by month -> show number of orders -> group the result by month-> show order value 

(b) Show the top customers(ie. customers having highest order value)
    Hint: show order details -> group by person -> limit customers

(c) Show details of most recently placed order. Columns(orderid,order_date,order_value,customer,company)
    Hint:   show order -> show order with customer and company info -> write compisite query with first select MAX(order_date) and use that in WHERE to get order info of that date
    -> use a subquery to remove the one manual step

(d) Generate report that show the top5 companies that have orderes from us. Columns(company_name,number_of_order,total_order_value(USD),total_order_value(INR),average_order_value)
    Hint: select company by order value -> group by companies -> use operator to show INR total_order_value column -> use currenty_exchange_table/lookup table -> select top 5 -> simplify query with subquery

(e) Show use of HAVING clause
    use to filter on columns with aggregate functions.
    ex: HAVING count(*) >1

(f) 22/gravity-erd 
    create table + populate data + answers: https://github.com/bbrumm/databasestar/tree/main/sample_databases/sample_db_gravity
    (i) show a list of best selling books of all time,and show the title, author,ISBN,publisher, and the number of sales for each book for res/erd-gravity.png
    Hint:
        get number of sales for each book -> get book info also by joining the query with book table -> show the publisher info by joining with publiser table -> add row limiting and pagination -> show authore by joining with appropriate table -> contact the author list using string_agg -> 

    (ii) Write a query to get the books the have made the most revenu.
    (iii) Find the best selleing books for particular calender year.

(g) 23/gravity-erd
    (i) Show the number of books sales and revenue,grouped by month and year, for all orders.
    Hint: show all order -> count orders by date -> count book sales by date -> count sales by year -> show an overall total with ROLLUP -> count and sum sales by year -> count and sum sales by year and month -> exclude cancelled and refunded order

(h) 24/gravity-erd
    (i) Show the city,country,number of orders sent to that city, and the numner of customers in that city.

    (ii) After click to one countyr on ui, show customoer details, order_details and the shipping methode used.

    (iii) In above query, can you show the total order value for each order? (i. the sum of each of the products that have been ordered)

    (iv) Is it possible to see the number of orders in the same city?? 
        Hint: use OVER window function
    



__________________________________________________________________________________
ANSWERS
‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾
=> Draw ERD -> view /res/crm-erd.md

=> create tabel, postgresql-code
```sql

```

=> create-table, sql-code
```sql
CREATE TABLE company (
    company_id INT,
    company_name VARCHAR(200),
    city VARCHAR(200),
    num_employees INT,
    CONSTRAINT pk_company PRIMARY KEY (company_id)
);

sd
CREATE TABLE person (
    person_id INT,
    company_id INT,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    CONSTRAINT pk_person PRIMARY KEY (person_id),
    CONSTRAINT fk_person_cid FOREIGN KEY (company_id) REFERENCES
    company(company_id)
);
```
=> Insert Data: https://github.com/bbrumm/databasestar/blob/main/courses/course_select_mastery_1/select_mastery_1_postgresql.sql



=> Queries:
(a)
```sql

-- postgresql
SELECT
    TO_CHAR(order_date, 'MON') AS month_name,
    COUNT(*) AS num_orders,
    SUM(order_value) AS total_order_value
FROM sales_order
WHERE order_date < '20210101'
AND order_date >= '20200101'
GROUP BY TO_CHAR(order_date, 'MON') --TO_CHAR (date_value, format)
ORDER BY TO_CHAR(order_date, 'M') ASC

-- mysql
SELECT
    DATE_FORMAT(order_date, 'MON') AS month_name,
    COUNT(*) AS num_orders,
    SUM(order_value) AS total_order_value
FROM sales_order
WHERE order_date < '20210101'
AND order_date >= '20200101'
GROUP BY DATE_FORMAT(order_date, 'MON') -- get month of date,DATE_FORMAT (date_value, format)
ORDER BY DATE_FORMAT(order_date, 'M') ASC
```

(b)
```sql
SELECT
    p.person_id,
    p.first_name,
    p.last_name,
    c.company_name,
    SUM(s.order_value) AS order_total
FROM sales_order s
INNER JOIN person p ON s.person_id = p.person_id
LEFT JOIN company c ON p.company_id = c.company_id
WHERE active_status != 'Inactive'
AND active_status IS NOT NULL
GROUP BY p.person_id, p.first_name, p.last_name, c.company_name
HAVING SUM(s.order_value) > 100
ORDER BY SUM(s.order_value) DESC;
```



(c)


```sql

-- use subquery to get the latest order
SELECT
    s.id,
    s.person_id,
    s.order_date,
    s.order_value,
    p.first_name,
    p.last_name,
    c.company_name
FROM sales_order s
INNER JOIN person p ON s.person_id = p.person_id
LEFT JOIN company c ON p.company_id = c.company_id
WHERE s.order_date = ( --subquery
        SELECT MAX(order_date)
        FROM sales_order
    );


-- create a view in database for simplying the application logic + call this new view in application logic as it would be a valid table
CREATE VIEW latest_order_details AS
SELECT
    s.id,
    s.person_id,
    s.order_date,
    s.order_value,
    p.first_name,
    p.last_name,
    c.company_name
FROM sales_order s
INNER JOIN person p ON s.person_id = p.person_id
LEFT JOIN company c ON p.company_id = c.company_id
WHERE s.order_date = (
    SELECT MAX(order_date)
    FROM sales_order
);


```

(d)
```sql
SELECT
    company_name,
    total_order_value,
    num_orders,
    average_order_value
FROM ( --subquery
    SELECT TOP 5
        c.company_name,
        ROUND(SUM(s.order_value * cc.conversion_rate), 2) AS
        total_order_value,
        COUNT(*) AS num_orders,
        ROUND(AVG(s.order_value * cc.conversion_rate), 2) AS
        average_order_value
    FROM company c
    INNER JOIN person p ON c.company_id = p.company_id
    INNER JOIN sales_order s ON p.person_id = s.person_id
    INNER JOIN currency_convert cc ON cc.from_currency = 'USD'
    WHERE cc.to_currency = 'GBP'
    GROUP BY c.company_name
)
ORDER BY total_order_value DESC;
```
=> (f)(i) list of best selling books
```sql
SELECT
b.book_id,
b.title,
ba.authors,
b.isbn13,
p.publisher_name,
COUNT(*) AS num_sales
FROM order_line o
INNER JOIN book b ON o.book_id = b.book_id
INNER JOIN publisher p ON b.publisher_id = p.publisher_id
INNER JOIN ( --just to extract all authores in comma seperate string(this techinique is called inline view)
  SELECT
  ba.book_id,
  GROUP_CONCAT(a.author_name SEPARATOR ', ') AS authors
  FROM book_author ba
  INNER JOIN author a on ba.author_id = a.author_id
  GROUP BY ba.book_id
) ba ON b.book_id = ba.book_id
GROUP BY b.book_id, b.title, ba.authors, b.isbn13, p.publisher_name
ORDER BY COUNT(*) DESC
LIMIT 20;
```

(g) (i)
```sql
-- show all orders
SELECT order_id,order_date
FROM cust_order;

-- count orders by date
SELECT order_date,COUNT(*)
FROM cust_order
GROUP BY order_date;

-- count book sales by date
SELECT o.order_date,COUNT(*)
FROM cust_order o
INNER JOIN order_line l ON o.order_id = l.order_id
GROUP by o.order_date;

-- count sales by year
SELECT EXTRACT(YEAR FROM o.order_date) AS order_year
COUNT(*)
FROM cust_order o
INNER JOIN order_line l ON o.order_id = l.order_id
GROUP BY EXTRACT(YEAR FROM o.order_date);

-- Showing an overall total
SELECT EXTRACT(YEAR FROM o.order_date) AS order_year
COUNT(*)
FROM cust_order o
INNER JOIN order_line l ON o.order_id = l.order_id
GROUP BY ROLLUP(EXTRACT(YEAR FROM o.order_date)); --ROLLUP is the way data can be grouped, it adds a overall total at end of row

-- count and sum sales by year
SELECT
EXTRACT(YEAR FROM o.order_date) AS order_year
SUM(l.price) AS revenue,
COUNT(*) AS total_order
FROM cust_order o
INNER JOIN order_line l ON o.order_id = l.order_id
GROUP BY ROLLUP(EXTRACT(YEAR FROM o.order_date));

-- Count and sum sales by year and month
SELECT 
EXTRACT(YEAR FROM o.order_date) AS order_year
EXTRACT(MONT FROM o.order_date) AS order_month
SUM(l.price) AS revenue,
COUNT(*) AS total_orders
FROM cust_order o
INNER JOIN order_line l ON o.order_id = l.order_id
GROUP BY ROLLUP(
    EXTRACT(YEAR FROM o.order_date),
    EXTRACT(MONTH FROM o.order_date)
);

-- exclude orders that are canceleed and refuned
SELECT 
EXTRACT(YEAR FROM o.order_date) AS order_year
EXTRACT(MONT FROM o.order_date) AS order_month
SUM(l.price) AS revenue,
COUNT(*) AS total_orders
FROM cust_order o
INNER JOIN order_line l ON o.order_id = l.order_id
WHERE o.order_id NOT IN( --subquery to select cancelled & refunded order
    SELECT order_id 
    FROM order_history
    WHERE status_id IN(5,6)
)
GROUP BY ROLLUP(
    EXTRACT(YEAR FROM o.order_date),
    EXTRACT(MONTH FROM o.order_date)
);
```

(h) (i)
```sql
-- start small
-- show city and country
SELECT a.city,co.country_name
FROM address a
INNER JOIN country co ON a.country_id = co.country_id
ORDER BY co.country_name ASC,a.city ASC;

-- show order count 
SELECT o.order_id,COUNT(*)
FROM address a
INNER JOIN country co ON a.country_id = co.country_id
INNER JOIN cust_order o ON a.address_id = o.dest_address_id
GROUP BY o.order_id
ORDER BY COUNT(*) DESC;

-- show customer count + order_count for each city
SELECT
a.city,
co.country_name,
COUNT(DISTINCT o.order_id) AS num_orders,
COUNT(ca.customer_id) AS num_customers
FROM address a
INNER JOIN country co ON a.country_id = co.country_id
INNER JOIN cust_order o ON a.address_id = o.dest_address_id
INNER JOIN customer_address ca ON a.address_id = ca.address_id
GROUP BY a.city,co.country_name
ORDER BY co.country_name ASC,a.city ASC;

-- Show information for country that don't have any order also.
SELECT
a.city,
co.country_name,
COUNT(DISTINCT o.order_id) AS num_orders,
COUNT(ca.customer_id) AS num_customers
FROM address a
INNER JOIN country co ON a.country_id = co.country_id
LEFT JOIN cust_order o ON a.address_id = o.dest_address_id --use left join 
LEFT JOIN customer_address ca ON a.address_id = ca.address_id
GROUP BY a.city,co.country_name
ORDER BY co.country_name ASC,a.city ASC;
```

(h)(ii)
```sql
-- show order_details,customer_details and shipping details for each country
-- show order_detaisl and address_details
SELECT o.order_id,o.order_date,a.stree_number,a.steet_name,a.city
FROM cust_order o
INNER JOIN address a ON o.des_address_id = a.addredd_id
INNER JOIN country c ON a.country_id= c.country_id
WHERE c.country_name = 'INDIA'

-- add customer details
SELECT a.order_id,o.order_date
        cu.first_name,cu.last_name,cu.email, --added customer details
        a.street_number,a.street_name,a.city
FROM cust_order o
INNER JOIN address a ON o.dest_address_id = a.address_id
INNER JOIN country c ON a.country_id = c.country_id
INNER JOIN customer cu ON o.customer_id = cu.customer_id
WHERE c.country_name = 'India'

-- add shipping methode
SELECT a.order_id,o.order_date
        cu.first_name,cu.last_name,cu.email, 
        a.street_number,a.street_name,a.city
        sm.methode_name AS shipping_methode --added
FROM cust_order o
INNER JOIN address a ON o.dest_address_id = a.address_id
INNER JOIN country c ON a.country_id = c.country_id
INNER JOIN customer cu ON o.customer_id = cu.customer_id
INNER JOIN shipping_method sm ON o.shipping_methode_id = sm.methode_id --added
WHERE c.country_name = 'India'
ORDER BY a.city ASC,o.order_date ASC;
```

(h)(iii)
```sql
--add total order value for each order in queyrii
SELECT a.order_id,o.order_date
        cu.first_name,cu.last_name,cu.email, 
        a.street_number,a.street_name,a.city
        sm.methode_name AS shipping_methode 
        SUM(ol.price) AS order_value --added
FROM cust_order o
INNER JOIN address a ON o.dest_address_id = a.address_id
INNER JOIN country c ON a.country_id = c.country_id
INNER JOIN customer cu ON o.customer_id = cu.customer_id
INNER JOIN shipping_method sm ON o.shipping_methode_id = sm.methode_id
INNER JOIN order_line ol ON o.order_id = ol.order_id --added
WHERE c.country_name = 'India'
GROUP BY o.order_id,o.order_date,cu.first_name,cu.last_name,cu.email,a.street_numnber,a.street_name,sm.methode_name --added
ORDER BY a.city ASC,o.order_date ASC;
```

(h)(iv)
```sql
SELECT a.order_id,o.order_date
        cu.first_name,cu.last_name,cu.email, 
        a.street_number,a.street_name,a.city
        COUNT(*) OVER (PARTITION BY a.city) AS num_orders_city, -- added
        sm.methode_name AS shipping_methode 
        SUM(ol.price) AS order_value --added
FROM cust_order o
INNER JOIN address a ON o.dest_address_id = a.address_id
INNER JOIN country c ON a.country_id = c.country_id
INNER JOIN customer cu ON o.customer_id = cu.customer_id
INNER JOIN shipping_method sm ON o.shipping_methode_id = sm.methode_id
INNER JOIN order_line ol ON o.order_id = ol.order_id --added
WHERE c.country_name = 'India'
GROUP BY o.order_id,o.order_date,cu.first_name,cu.last_name,cu.email,a.street_numnber,a.street_name,sm.methode_name --added
ORDER BY a.city ASC,o.order_date ASC;
```


__________________________________________________________________________________
References
‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾
video lectures: [4] Select Mastery Level 1, [5] Select Mastery Level 2,[6] Select Mastery Level 3
script code to populate table & data: https://github.com/bbrumm/databasestar/blob/main/courses/course_select_mastery_1/select_mastery_1_postgresql.sql

script code for each step: same as github above