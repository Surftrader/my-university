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
import com.poseal.university.dao.impl.DepartmentDaoHibernate;
import com.poseal.university.dao.impl.SubjectDaoHibernate;
import com.poseal.university.dao.impl.TeacherDaoHibernate;
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
        teacherDao = new TeacherDaoHibernate();
        subjectDao = new SubjectDaoHibernate();
        departmentDao = new DepartmentDaoHibernate();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Teacher teacher = teacherDao.findOne(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("teacher", teacher);

        Subject subject = teacher.getSubject();
        req.setAttribute("subject", subject);

        Department department = teacher.getDepartment();
        req.setAttribute("department", department);

        req.getRequestDispatcher("/models/teacher.jsp").forward(req, resp);
    }
}
