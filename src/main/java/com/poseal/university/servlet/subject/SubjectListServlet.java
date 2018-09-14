package com.poseal.university.servlet.subject;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseal.university.dao.SubjectDao;
import com.poseal.university.dao.impl.SubjectDaoImpl;
import com.poseal.university.model.Subject;

@WebServlet("/subjects")
public class SubjectListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private SubjectDao subjectDao;

    @Override
    public void init() {
        subjectDao = new SubjectDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Subject> listSubjects = subjectDao.findAll();

        req.setAttribute("listSubjects", listSubjects);
        req.getRequestDispatcher("/listSubjects.jsp").forward(req, resp);
    }

}
