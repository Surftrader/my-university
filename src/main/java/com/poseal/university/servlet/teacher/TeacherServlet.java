package com.poseal.university.servlet.teacher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseal.university.dao.DepartmentDao;
import com.poseal.university.dao.SubjectDao;
import com.poseal.university.dao.TeacherDao;
import com.poseal.university.dao.impl.DepartmentDaoImpl;
import com.poseal.university.dao.impl.SubjectDaoImpl;
import com.poseal.university.dao.impl.TeacherDaoImpl;
import com.poseal.university.model.Department;
import com.poseal.university.model.Subject;
import com.poseal.university.model.Teacher;

@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private TeacherDao teacherDao;
    private SubjectDao subjectDao;
    private DepartmentDao departmentDao;

    @Override
    public void init() {
        teacherDao = new TeacherDaoImpl();
        subjectDao = new SubjectDaoImpl();
        departmentDao = new DepartmentDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Teacher teacher = teacherDao.findOne(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("teacher", teacher);

        Subject subject = subjectDao.findOne(teacher.getSubjectId());
        req.setAttribute("subject", subject);

        Department department = departmentDao.findOne(teacher.getDepartmentId());
        req.setAttribute("department", department);

        req.getRequestDispatcher("/models/teacher.jsp").forward(req, resp);
    }
}
