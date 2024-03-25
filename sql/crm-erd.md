

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


__________________________________________________________________________________
References
‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾
video lectures: [4] Select Mastery Level 1, [5] Select Mastery Level 2,[6] Select Mastery Level 3
script code to populate table & data: https://github.com/bbrumm/databasestar/blob/main/courses/course_select_mastery_1/select_mastery_1_postgresql.sql

script code for each step: same as github above