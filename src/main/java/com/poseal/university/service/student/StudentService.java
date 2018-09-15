package com.poseal.university.service.student;

import java.util.List;

import com.poseal.university.dao.StudentDao;
import com.poseal.university.dao.impl.StudentDaoImpl;
import com.poseal.university.exception.ServiceException;
import com.poseal.university.model.Group;
import com.poseal.university.model.Student;

public class StudentService {

    private StudentDao studentDao;

    public StudentService() {
        studentDao = new StudentDaoImpl();
    }

    public Student findOne(Integer studentId) {
        return studentDao.findOne(studentId);
    }

    public void remove(Integer studentId) {
        studentDao.remove(studentId);
    }

    public List<Student> findAll() {
        return studentDao.findAll();
    }

    public List<Student> findAll(Group group) {
        return studentDao.findAll(group);
    }

    public Student saveStudent(Student student) {

        student.setName(student.getName().trim());
        student.setSurname(student.getSurname().trim());

        if (student.getName().isEmpty() | student.getSurname().isEmpty()) {

            throw new ServiceException("Invalid name or surname of student!");
        }

        if (student.getId() == null) {
            student = studentDao.create(student);
        } else {
            studentDao.update(student);
        }
        return student;
    }
}
