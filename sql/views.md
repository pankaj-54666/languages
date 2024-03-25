## What is a view.
    A view is an object on database that lets user see certain data. View is like a saved SELECT query.
    And acts as a table.

## why use views?
    Make view accessbile touser and not the entire table.
    Hide columns from users(eg. employee salary)
    Complicaed query? -> save it as a view
    use columns aliases for simplicity and clarity

    Easier to deploy changes. 
    Adjust table without impacting application

## Create a view.
```sql
CREATE VIEW games_summary AS
SELECT id, games_year, season AS games_season
FROM games;
```

## Create view with multiple table.
```sql
CREATE VIEW person_with_region AS
SELECT
p.id AS person_id,
p.full_name,
p.gender,
r.noc,
r.region_name
FROM person p
INNER JOIN person_region pr ON p.id = pr.person_id
INNER JOIN noc_region r ON pr.region_id = r.id;
```

## Inserting into view. (rest of value will be set to null)
```sql
CREATE VIEW person_summary AS
SELECT id, full_name, gender
FROM person;

INSERT INTO person_summary (id, full_name, gender)
VALUES (200000, 'John Test', 'M');
```

## Update into the view (more important)
```sql
UPDATE person_summary
SET full_name = 'Sarah Test',
gender = 'F'
WHERE id = 200000;
```

# None Updatable view
Only views that follows these creteria are updatable,rest all are nonupdatable.
    - modification must refernece columns from only one base table.
    - columns being updated must directly reference data. (and not to aggregation,computatio or set operations) 
    - No group by, distinct or having clause
    - No TOP with WITH CHECK OPTION

## rename and drop a view
```sql
EXEC sp_rename 'person_with_region', 'person_regions';
DROP VIEW person_regions;
```

## get info about a view
```sql
SELECT *
FROM sys.views;

SELECT *
FROM sys.sql_modules;

SELECT
v.object_id,
v.name,
v.create_date,
v.modify_date,
v.with_check_option,
m.uses_ansi_nulls,
m.uses_quoted_identifier,
m.is_schema_bound,
m.definition
FROM sys.views v
INNER JOIN sys.sql_modules m ON v.object_id = m.object_id;
```

## Indexed view
As we have know that view are only saved select query. 
But some view can be made to act like a table and can be indexed.
You can check the video if you need more info.


## References
https://github.com/bbrumm/databasestar/tree/main/courses/course_sqlserver_views
video: [16] SQL Advance - Views in SQL