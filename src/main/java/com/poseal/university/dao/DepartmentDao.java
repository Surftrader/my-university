package com.poseal.university.dao;

import java.util.List;

import com.poseal.university.model.Department;
import com.poseal.university.model.Faculty;

public interface DepartmentDao extends CrudDao<Department, Integer> {

    List<Department> findByFaculty(Faculty faculty);

}
