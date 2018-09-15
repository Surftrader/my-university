package com.poseal.university.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.poseal.university.model.Group;
import com.poseal.university.model.Lesson;
import com.poseal.university.model.Teacher;

public interface LessonDao extends CrudDao<Lesson, Integer> {

    List<Lesson> findSchedule(Group group, LocalDateTime start, LocalDateTime end);

    List<Lesson> findSchedule(Teacher teacher, LocalDateTime start, LocalDateTime end);

}
