package com.poseal.university.service.student;

import java.util.List;

import com.poseal.university.dao.StudentDao;
import com.poseal.university.dao.impl.StudentDaoHibernate;
import com.poseal.university.exception.DataNotFoundException;
import com.poseal.university.exception.ServiceException;
import com.poseal.university.model.Group;
import com.poseal.university.model.Student;
import com.poseal.university.service.dto.GroupDto;
import com.poseal.university.service.dto.StudentDto;
import com.poseal.university.service.mappers.GroupMapper;
import com.poseal.university.service.mappers.Mapper;
import com.poseal.university.service.mappers.StudentMapper;

public class StudentService {

    private StudentDao studentDao;
    private Mapper<StudentDto, Student> studentMapper;
    private Mapper<GroupDto, Group> groupMapper;

    public StudentService() {
        studentDao = new StudentDaoHibernate();
        studentMapper = new StudentMapper();
        groupMapper = new GroupMapper();
    }

    public StudentDto findOne(Integer studentId) {
        Student student = studentDao.findOne(studentId);
        StudentDto studentDto = studentMapper.transformToDto(student);
        if (studentId == null) {
            throw new DataNotFoundException("Student with id = " + studentId + " not found!");
        }
        return studentDto;
    }

    public void remove(Integer studentId) {
        studentDao.remove(studentId);
    }

    public List<StudentDto> findAll() {
        List<Student> students = studentDao.findAll();
        return studentMapper.transformToListDto(students);
    }

    public List<StudentDto> findAll(GroupDto groupDto) {
        Group group = groupMapper.transformToModel(groupDto);
        List<Student> students = studentDao.findAll(group);
        return studentMapper.transformToListDto(students);
    }

    public StudentDto saveStudent(StudentDto studentDto) {
        Student student = studentMapper.transformToModel(studentDto);
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
        return studentMapper.transformToDto(student);
    }
}
