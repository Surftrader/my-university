package com.poseal.university.dao;

import java.util.List;

import com.poseal.university.model.Faculty;
import com.poseal.university.model.Group;

public interface GroupDao extends CrudDao<Group, Integer> {

    List<Group> findByFaculty(Faculty faculty);

}
