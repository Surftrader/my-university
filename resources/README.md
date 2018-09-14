# my-university

Person - абстрактный класс, родитель классов Student, Teacher.
Поля:
name: String - Имя, 
surname: String - Фамилия

Student - студент университета(University), состоит в группе (Group).

Teacher - преподаватель кафедры(Department), который читает предмет.
Поле:
subject: Subject - предмет.

Subject - предмет, который читает преподаватель.
Поле: 
name: String - название предмета.

Room - аудитория кафедры с номером и информацией о вместительности людей.
Поля: 
number: Integer - номер аудитории, 
capacity: Integer - вместительность людей.

Department - кафедра факультета, которая содержит штат преподавателей и аудитории.
Поля: 
name: String - название кафедры,
teachers: List - список преподаваелей,
rooms: List - список аудиторий

Group - группа факультета(Faculty), у которой есть список студентов. 
Поля: 
name: String- название группы,
students: List - список студентов.

Lesson - этот класс описывает занятие в определенный промежуток времени по предмету, который читает преподаватель в аудитории.
Поля: 
timeStart: LocalDateTime - начало занятия,
timeEnd: LocalDateTime - конец занятия, 
subject: Subject,
teacher: Teacher,
room: Room.

Schedule - расписание, которое содержит список занятий и список групп
Поля:
groups: List,
lessons: List

University - университет со списком факультетов.
Поля: 
name: String - название университета,
faculties: List - список факультетов

Faculty - факультет университета, который содержит информацию о кафедрах, группах и расписании.
Поля: 
name: String - название факультета,
schedule: Schedule - расписание,
groups: List - список групп,
departments: List - список кафедр.

Методы:
addLesson(lesson: Lesson) - добавляет занятие в расписание
showSchedulePerson(person: Person, time: LocalDateTime) - показывает расписание преподавателя или студента на заданный период
