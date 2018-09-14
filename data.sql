INSERT INTO faculty(name)
    VALUES('Faculty of History');

INSERT INTO faculty(name) 
    VALUES('Faculty of Economics');
    
INSERT INTO faculty(name) 
    VALUES('Faculty of Mathematics');

INSERT INTO department(name, faculty_id)
    VALUES('D-1', 1);

INSERT INTO department(name, faculty_id)
    VALUES('D-2', 1);
	
INSERT INTO department(name, faculty_id)
    VALUES('D-3', 2);
	
INSERT INTO department(name, faculty_id)
    VALUES('D-4', 2);

INSERT INTO u_group(name, faculty_id)
    VALUES('Gr-1', 1);
	
INSERT INTO u_group(name, faculty_id)
    VALUES('Gr-2', 1);
	
INSERT INTO u_group(name, faculty_id)
    VALUES('Gr-3', 2);
	
INSERT INTO u_group(name, faculty_id)
    VALUES('Gr-4', 2);

INSERT INTO subject(name) 
    VALUES('History');

INSERT INTO subject(name) 
    VALUES('Economics');

INSERT INTO subject(name)
    VALUES('Mathematics');

INSERT INTO teacher(id, name, surname, subject_id, department_id) 
    VALUES(1,'Иван', 'Иванович', 1, 2);
	
INSERT INTO teacher(id, name, surname, subject_id, department_id) 
    VALUES(2,'Петр', 'Петров', 2, 1);
	
INSERT INTO teacher(id, name, surname, subject_id, department_id) 
    VALUES(3,'Мария', 'Ивановна', 3, 2);

INSERT INTO room(number, capacity, department_id)
    VALUES(1, 100, 1);
	
INSERT INTO room(number, capacity, department_id)
    VALUES(2, 30, 2);
	
INSERT INTO room(number, capacity, department_id)
    VALUES(3, 30, 2);
	
INSERT INTO student(id, name, surname, group_id) 
    VALUES(
    4,'Ivan', 'Ivanov', 1),
    (5,'Petr', 'Petrov', 1),
    (6,'Sergey', 'Sergeev', 1),
    (7,'Sidor', 'Sidorov', 1),
    (8,'Aleksandr', 'Aleksandrov', 2),
    (9,'Ivan', 'Sergeev', 2),
    (10,'Oleg', 'Mashin', 2),
    (11,'Konstantin', 'Grach', 2),
    (12,'Olga', 'Varenie', 3),
    (13,'Alena', 'Kukuruza', 3),
    (14,'Dmitriy', 'Petrov', 3),
    (15,'Viktor', 'Sokolov', 3),
    (16,'Misha', 'Prygunov', 4),
    (17,'Aleksey', 'Alekseyev', 4),
    (18,'Karen', 'Oganesyan', 4),
    (19,'Ahmat', 'Ivanov', 4),
    (20,'Anghela', 'Miroshnik', 4),
    (21,'Bogdan', 'Prokofyev', 4),
    (22,'Bogdana', 'Prokofyeva', 4
    );

INSERT INTO schedule(faculty_id) 
    VALUES(1);
	
INSERT INTO schedule(faculty_id) 
    VALUES(2);
	
INSERT INTO lesson(time_start, time_end, group_id, subject_id, teacher_id, room_id, schedule_id) 
    VALUES(
    '2018-10-25 08:00:00', '2018-10-25 09:30:00', 1, 1, 1, 1, 1
    );

INSERT INTO lesson(time_start, time_end, group_id, subject_id, teacher_id, room_id, schedule_id) 
    VALUES(
    '2018-10-25 08:00:00', '2018-10-25 09:30:00', 2, 2, 2, 2, 2
    );

INSERT INTO lesson(time_start, time_end, group_id, subject_id, teacher_id, room_id, schedule_id) 
    VALUES(
    '2018-10-25 09:50:00', '2018-10-25 11:20:00', 1, 2, 2, 2, 2
    );

INSERT INTO lesson(time_start, time_end, group_id, subject_id, teacher_id, room_id, schedule_id) 
    VALUES(
    '2018-11-25 09:50:00', '2018-11-25 11:20:00', 1, 2, 2, 2, 2
    );
