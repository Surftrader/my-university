package com.poseal.university.servlet.subject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseal.university.dao.SubjectDao;
import com.poseal.university.dao.TeacherDao;
import com.poseal.university.dao.impl.SubjectDaoImpl;
import com.poseal.university.dao.impl.TeacherDaoImpl;
import com.poseal.university.model.Subject;
import com.poseal.university.model.Teacher;

@WebServlet("/subject")
public class SubjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private SubjectDao subjectDao;
    private TeacherDao teacherDao;

    @Override
    public void init() {
        subjectDao = new SubjectDaoImpl();
        teacherDao = new TeacherDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Subject subject = subjectDao.findOne(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("subject", subject);

        Teacher teacher = teacherDao.findBySubject(subject);

        req.setAttribute("teacher", teacher);
        req.getRequestDispatcher("/models/subject.jsp").forward(req, resp);
    }
}
