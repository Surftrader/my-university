CREATE TABLE faculty (
    id SERIAL PRIMARY KEY
    ,name VARCHAR(30) NOT NULL
    );

CREATE TABLE subject (
    id SERIAL PRIMARY KEY
    ,name VARCHAR(30) NOT NULL
    );

CREATE TABLE u_group (
    id SERIAL PRIMARY KEY
    ,name VARCHAR(30) NOT NULL
    ,faculty_id INT REFERENCES faculty (id)
    );

CREATE TABLE schedule (
    id SERIAL PRIMARY KEY
    ,faculty_id INT REFERENCES faculty (id)
    );

CREATE TABLE student (
    id SERIAL PRIMARY KEY    
    ,name VARCHAR(30) NOT NULL
    ,surname VARCHAR(30) NOT NULL
    ,group_id INT REFERENCES u_group (id)
    );

CREATE TABLE department (
    id SERIAL PRIMARY KEY    
    ,name VARCHAR(30) NOT NULL
    ,faculty_id INT REFERENCES faculty (id) 
    );

CREATE TABLE teacher (
    id SERIAL PRIMARY KEY    
    ,name VARCHAR(30) NOT NULL
    ,surname VARCHAR(30) NOT NULL
    ,subject_id INT REFERENCES subject (id)
    ,department_id INT REFERENCES department (id) 
    );

CREATE TABLE room (
    id SERIAL PRIMARY KEY    
    ,number INT NOT NULL
    ,capacity INT NOT NULL
    ,department_id INT REFERENCES department (id) 
    );

CREATE TABLE lesson (
    id SERIAL PRIMARY KEY
    ,time_start TIMESTAMP
    ,time_end TIMESTAMP
    ,group_id INT REFERENCES u_group (id)
    ,subject_id INT REFERENCES subject (id)
    ,teacher_id INT REFERENCES teacher (id)
    ,room_id INT REFERENCES room (id)
    ,schedule_id INT REFERENCES schedule (id)
    );
    
CREATE SEQUENCE JPA_SEQUENCE START WITH 50 INCREMENT BY 1;
