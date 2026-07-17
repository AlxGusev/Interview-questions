DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS departments;

CREATE TABLE departments (
                             id   SERIAL PRIMARY KEY,
                             name VARCHAR(100) NOT NULL
);

CREATE TABLE employees (
                           id            SERIAL PRIMARY KEY,
                           name          VARCHAR(100) NOT NULL,
                           department_id INT REFERENCES departments(id),
                           salary        NUMERIC(10, 2) NOT NULL,
                           manager_id    INT REFERENCES employees(id),
                           hire_date     DATE NOT NULL
);

CREATE TABLE orders (
                        id          SERIAL PRIMARY KEY,
                        customer_id INT NOT NULL,
                        employee_id INT REFERENCES employees(id),
                        order_date  DATE NOT NULL,
                        amount      NUMERIC(10, 2) NOT NULL
);


-- ============================================
-- DEPARTMENTS
-- ============================================
INSERT INTO departments (name)
VALUES ('Engineering'), ('Sales'), ('HR'), ('Marketing'), ('Finance'), ('Support');


-- ============================================
-- EMPLOYEES
-- ============================================
INSERT INTO employees (name, department_id, salary, manager_id, hire_date)
SELECT
    'Employee ' || i,
    (1 + floor(random() * 6))::INT,
    round((40000 + random() * 80000)::NUMERIC, 2),
    NULL,
    CURRENT_DATE - (floor(random() * 3650))::INT
FROM generate_series(1, 200) AS s(i);

UPDATE employees e
SET manager_id = sub.manager_id
FROM (
         SELECT
             e2.id,
             (SELECT id FROM employees WHERE id < e2.id ORDER BY random() LIMIT 1) AS manager_id
         FROM employees e2
         WHERE e2.id > 10
     ) sub
WHERE e.id = sub.id;

INSERT INTO employees (name, department_id, salary, manager_id, hire_date)
SELECT name, department_id, round((40000 + random() * 80000)::NUMERIC, 2), NULL, CURRENT_DATE
FROM employees
WHERE id IN (5, 12, 47);


-- ============================================
-- ORDERS
-- ============================================
INSERT INTO orders (customer_id, employee_id, order_date, amount)
SELECT
    (1 + floor(random() * 500))::INT,
    (SELECT id FROM employees ORDER BY random() LIMIT 1),
    CURRENT_DATE - (floor(random() * 730))::INT,
    round((10 + random() * 990)::NUMERIC, 2)
FROM generate_series(1, 2000) AS s(i);


DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS customer_orders;
-- ============================================
-- CUSTOMERS
-- ============================================
CREATE TABLE customers (
                           customer_id SERIAL,
                           email VARCHAR(100),
                           first_name VARCHAR(50),
                           last_name VARCHAR(50)
);

-- ============================================
-- CUSTOMER_ORDERS
-- ============================================
CREATE TABLE customer_orders (
                        order_id SERIAL,
                        customer_id INT,
                        order_date DATE NOT NULL,
                        amount NUMERIC(10, 2),
                        status VARCHAR(50)
);


-- ============================================
-- GENERATION CUSTOMERS
-- ============================================
INSERT INTO customers (first_name, last_name, email)
SELECT
    'Customer_' || g.i,
    'LastName_' || g.i,
    'customer_' || g.i || '@example.com'
FROM generate_series(1, 400001, 1) AS g(i);


-- ============================================
--  GENERATION CUSTOMER ORDERS
-- ============================================
INSERT INTO customer_orders (customer_id, order_date, amount, status)
SELECT
    (RANDOM() * 400000)::INT + 1,
    '2024-01-01'::DATE + (RANDOM() * 365)::INT,
    (RANDOM() * 500)::NUMERIC(10, 2),
    CASE WHEN RANDOM() > 0.5 THEN 'completed' ELSE 'pending' END
FROM generate_series(1, 40_000_000);