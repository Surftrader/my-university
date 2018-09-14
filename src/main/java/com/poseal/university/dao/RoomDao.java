package com.poseal.university.dao;

import java.util.List;

import com.poseal.university.model.Department;
import com.poseal.university.model.Room;

public interface RoomDao extends CrudDao<Room, Integer>{

    List<Room> findByDepartment(Department department);

}
