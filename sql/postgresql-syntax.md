/*
Table Manipulation: CREATE TABLE,ALTER TABLE,DROP TABLE,TRUNCATE TABLE
Data manipulation statement: SELECT,INSERT,UPDATE,DELETE
Clauses:    WHERE,ORDER BY,GROUP BY,HAVING,DISTINCT
Operators: math operator, LIKE,ILIKE,||, (https://www.databasestar.com/sql-operators/)
SET operators:
Case Statements: 
*/

# Table Manipulation
```sql
/* Create Table with implicity column constrain*/
CREATE TABLE website_user(
    id SERIAL NOT NULL PRIMARY KEY,
    first_name VARCHAR(250),
    email VARCHAR(250)
);

/* Create a table with explict column constrain */
CREATE TABLE website_user(
    id SERIAL NOT NULL,
    first_name VARCHAR(250),
    email VARCHAR(250),

    CONSTRAINT pk_user_id PRIMARY KEY(id)
);

-- SERIAL is used to auto increment that field. (so NO NEED to pass via INSERT statement)
-- If serial is not there but it is stil PRIMARY KEY | NOT NULL, then you need to pass that via INSERT statement
INSERT INTO website_user(first_name,email)
VALUES('sky','sky@gmai.com');

/* Create table with all data field and various constrain*/
DROP TABLE IF EXISTS workout_plan CASCADE;
DROP TABLE IF EXISTS website_user CASCADE ;

CREATE TABLE workout_plan(
	id SERIAL PRIMARY KEY,
	name VARCHAR(250)
);

DROP TYPE IF EXISTS gamer_level CASCADE;
CREATE TYPE gamer_level AS ENUM ('beginner', 'intermediate', 'advance');

CREATE TABLE website_user (
    id SERIAL PRIMARY KEY,
    age INT,
    first_name VARCHAR(255),
    details TEXT,
    user_weight NUMERIC(5, 2), --numberic having total of 5 digits , with max 2 digits after decimal
    user_ratio DOUBLE PRECISION, -- to store float, you can also use REAL that is having less percision
    dob DATE,
    last_login TIMESTAMP,
    is_admin BOOLEAN DEFAULT false,
    json_user_details JSON, --just to show json data type storage
	gamer_level gamer_level,
    gender CHAR, 
    language_code CHAR(2), --two character code en, hi, etc (it can also be VARCHAR(2))

    user_status VARCHAR(20),
    workout_plan_id INT, 

    unique_column VARCHAR(100) UNIQUE,
    not_null_column VARCHAR(100) NOT NULL,
    check_column INT CHECK (check_column >= 0), --inline constrain here
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- all explict constrain below this line
    -- CONSTRAIN pk_user_id PRIMARY KEY(id) --disabled here, as this constrain is specified with inline style
    CONSTRAINT fk_workout_plan_id FOREIGN KEY (workout_plan_id) REFERENCES workout_plan(id),
    CONSTRAINT chk_user_status CHECK (user_status IN ('active', 'inactive', 'deactivated')),
	CONSTRAINT chk_gender CHECK (gender IN ('M','F')),
    CONSTRAINT chk_age CHECK (age >= 18 AND age <=80)
    -- CONSTRAINT uc_ex7_regno UNIQUE (registration_category, registration_number) --use this to set uniqueu constrain
    -- TODO: check can be added with pattern matching with regex OR can use function also
);

/* Insert data into above table*/

INSERT INTO workout_plan(name) VALUES('sample name');

INSERT INTO website_user (age, first_name, details, user_weight, user_ratio, dob, last_login, is_admin, json_user_details, gamer_level, gender, user_status, workout_plan_id, unique_column, not_null_column, check_column)
VALUES
    (30, 'John', 'Some details about John', 75.5, 1.8, '1992-05-15', '2024-03-25 10:00:00', false, '{"key": "value"}', 'beginner', 'M', 'active', 1, 'unique_value', 'not_null_value', 5);
```


__________________________________________________________________________________
REFERENCES
‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾
databasestart: https://www.databasestar.com/postgresql/#Learning_the_Basics
udemy-postgresql course