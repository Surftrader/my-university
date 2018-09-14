package com.poseal.university.dao;

import java.util.List;

import com.poseal.university.model.Group;
import com.poseal.university.model.Student;

public interface StudentDao extends CrudDao<Student, Integer> {

    List<Student> findByGroup(Group group);

}
