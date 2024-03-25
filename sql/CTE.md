
## What is CTE?
CTE stand for Common Table Expression.
Lets us define a query with name inside another query.
It helps us simplify queries.

## Query with CTE
```sql
WITH it_employees (emp_id, first_name, title) AS
(
	SELECT emp_id, first_name, title
	FROM employee
	WHERE dept_id = 3
)
SELECT first_name, title
FROM it_employees;
```

## Query with two CTEs
```sql
WITH requests_in_progress (request_id, request_name, created_date, request_status, assigned_to) AS (
	SELECT
	request_id,
	request_name,
	created_date,
	request_status,
	assigned_to
	FROM work_request
	WHERE request_status != 'Done'
),
it_employees (emp_id, first_name, title) AS (
	SELECT emp_id, first_name, title
	FROM employee
	WHERE dept_id = 3
)
SELECT
e.emp_id,
e.first_name,
e.title,
w.request_id,
w.request_name,
w.created_date,
w.request_status
FROM it_employees e
INNER JOIN requests_in_progress w ON w.assigned_to = e.emp_id;
```

## CTE use during view creation
```sql
CREATE VIEW it_requests_in_progress AS
WITH requests_in_progress (request_id, request_name, created_date, request_status, assigned_to) AS (
	SELECT
	request_id,
	request_name,
	created_date,
	request_status,
	assigned_to
	FROM work_request
	WHERE request_status != 'Done'
),
it_employees (emp_id, first_name, title) AS (
	SELECT emp_id, first_name, title
	FROM employee
	WHERE dept_id = 3
)
SELECT
e.emp_id,
e.first_name,
e.title,
w.request_id,
w.request_name,
w.created_date,
w.request_status
FROM it_employees e
INNER JOIN requests_in_progress w ON w.assigned_to = e.emp_id;
```

## Insert with CTE
```sql
WITH requests_in_progress (request_id, request_name, created_date, request_status, assigned_to) AS (
	SELECT
	request_id,
	request_name,
	created_date,
	request_status,
	assigned_to
	FROM work_request
	WHERE request_status != 'Done'
)
INSERT INTO emp_req_in_progress (emp_id, first_name, title, request_id, request_name, created_date, request_status)
SELECT
e.emp_id,
e.first_name,
e.title,
w.request_id,
w.request_name,
w.created_date,
w.request_status
FROM employee e
INNER JOIN requests_in_progress w ON w.assigned_to = e.emp_id;
```

## Update with CTE
```sql
SELECT *
FROM work_request;

--Update with CTE
WITH requests_in_progress (request_id, request_name, created_date, request_status, assigned_to) AS (
	SELECT
	request_id,
	request_name,
	created_date,
	request_status,
	assigned_to
	FROM work_request
	WHERE request_status != 'Done'
)
UPDATE requests_in_progress
SET assigned_to = 8
WHERE request_id = 7;
```

## Recuersive CTE,indenting CTE
see video if needed information about this.
(skipped)

## References
sql code: https://github.com/bbrumm/databasestar/tree/main/courses/course_sqlserver_cte
video: 