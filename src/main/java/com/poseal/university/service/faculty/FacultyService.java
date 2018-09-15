package com.poseal.university.service.faculty;

import com.poseal.university.dao.FacultyDao;
import com.poseal.university.dao.impl.FacultyDaoHibernate;
import com.poseal.university.model.Faculty;

public class FacultyService {

    private FacultyDao facultyDao;

    public FacultyService() {
        facultyDao = new FacultyDaoHibernate();
    }

    public Faculty findOne(Integer facultyId) {

        Faculty faculty = null;

        if (facultyId == null || String.valueOf(facultyId).isEmpty()) {
            throw new IllegalArgumentException("Faculty ID mustn't be NULL or EMPTY!");
        }
        faculty = facultyDao.findOne(facultyId);

        return faculty;
    }
}
