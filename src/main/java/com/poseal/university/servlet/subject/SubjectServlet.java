package com.poseal.university.servlet.subject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseal.university.dao.SubjectDao;
import com.poseal.university.dao.TeacherDao;
import com.poseal.university.dao.impl.SubjectDaoHibernate;
import com.poseal.university.dao.impl.TeacherDaoHibernate;
import com.poseal.university.model.Subject;
import com.poseal.university.model.Teacher;

@WebServlet("/subject")
public class SubjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private SubjectDao subjectDao;
    private TeacherDao teacherDao;

    @Override
    public void init() {
        subjectDao = new SubjectDaoHibernate();
        teacherDao = new TeacherDaoHibernate();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Subject subject = subjectDao.findOne(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("subject", subject);

        Teacher teacher = teacherDao.findOne(subject);

        req.setAttribute("teacher", teacher);
        req.getRequestDispatcher("/models/subject.jsp").forward(req, resp);
    }
}
