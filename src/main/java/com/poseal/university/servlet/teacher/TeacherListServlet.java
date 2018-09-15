package com.poseal.university.servlet.teacher;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseal.university.dao.TeacherDao;
import com.poseal.university.dao.impl.TeacherDaoHibernate;
import com.poseal.university.model.Teacher;

@WebServlet("/teachers")
public class TeacherListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private TeacherDao teacherDao;

    @Override
    public void init() {
        teacherDao = new TeacherDaoHibernate();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Teacher> listTeachers = teacherDao.findAll();

        req.setAttribute("listTeachers", listTeachers);
        req.getRequestDispatcher("/listTeachers.jsp").forward(req, resp);
    }
}
