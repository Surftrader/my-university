package com.poseal.university.service.mappers;

import java.util.ArrayList;
import java.util.List;

import com.poseal.university.model.Group;
import com.poseal.university.model.Student;
import com.poseal.university.service.dto.GroupDto;
import com.poseal.university.service.dto.StudentDto;
import com.poseal.university.service.group.GroupService;

public class StudentMapper implements Mapper<StudentDto, Student> {

    private Mapper<GroupDto, Group> groupMapper;

    public StudentMapper() {
        groupMapper = new GroupMapper();
    }

    @Override
    public Student transformToModel(StudentDto studentDto) {
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setName(studentDto.getName());
        student.setSurname(studentDto.getSurname());

        GroupService groupService = new GroupService();
        GroupDto groupDto = groupService.findOne(studentDto.getGroupId());
        Group group = groupMapper.transformToModel(groupDto);
        student.setGroup(group);

        return student;
    }

    @Override
    public StudentDto transformToDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setSurname(student.getSurname());
        studentDto.setGroupId(student.getGroup().getId());

        return studentDto;
    }

    @Override
    public List<StudentDto> transformToListDto(List<Student> students) {
        List<StudentDto> studentDto = new ArrayList<>();
        for (Student student : students) {
            studentDto.add(transformToDto(student));
        }
        return studentDto;
    }

    @Override
    public List<Student> transformToListModel(List<StudentDto> studentsDto) {
        List<Student> students = new ArrayList<>();
        for (StudentDto studentDto : studentsDto) {
            students.add(transformToModel(studentDto));
        }
        return students;
    }
}
