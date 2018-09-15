package com.poseal.university.dao;

import java.util.List;

import com.poseal.university.model.Department;
import com.poseal.university.model.Subject;
import com.poseal.university.model.Teacher;

public interface TeacherDao extends CrudDao<Teacher, Integer> {

    List<Teacher> findAll(Department department);

    Teacher findOne(Subject subject);

}
