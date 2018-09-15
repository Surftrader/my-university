package com.poseal.university.service.schedule;

import java.time.LocalDateTime;
import java.util.List;

import com.poseal.university.constants.AppConstants;
import com.poseal.university.dao.LessonDao;
import com.poseal.university.dao.impl.LessonDaoHibernate;
import com.poseal.university.exception.DatePeriodException;
import com.poseal.university.model.Group;
import com.poseal.university.model.Lesson;
import com.poseal.university.model.Teacher;

public class ScheduleService {

    private LessonDao lessonDao;

    public ScheduleService() {
        lessonDao = new LessonDaoHibernate();
    }

    public List<Lesson> findSchedule(Teacher teacher, LocalDateTime start, LocalDateTime end) {

        if (start == null) {
            start = AppConstants.TIME_MIN;
            if (end == null) {
                end = AppConstants.TIME_MAX;
            }
        } else if (end == null) {
            end = start.plusDays(1);
        }

        if (start.isAfter(end)) {
            throw new DatePeriodException("Start time is later than end time!");
        }

        return lessonDao.findSchedule(teacher, start, end);
    }

    public List<Lesson> findSchedule(Group group, LocalDateTime start, LocalDateTime end) {

        if (start == null) {
            start = AppConstants.TIME_MIN;
            if (end == null) {
                end = AppConstants.TIME_MAX;
            }
        } else if (end == null) {
            end = start.plusDays(1);
        }

        if (start.isAfter(end)) {
            throw new DatePeriodException("Start time is later than end time!");
        }

        return lessonDao.findSchedule(group, start, end);
    }

    public List<Lesson> findAll() {
        return lessonDao.findAll();
    }
}
