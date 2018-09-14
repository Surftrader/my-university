CREATE DATABASE my_university_db;

CREATE ROLE 
    rector 
    PASSWORD 'rector';

GRANT ALL PRIVILEGES
    ON DATABASE my_university_db
    TO rector;
