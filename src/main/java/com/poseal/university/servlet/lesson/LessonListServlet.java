package com.poseal.university.servlet.lesson;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseal.university.dao.LessonDao;
import com.poseal.university.dao.impl.LessonDaoHibernate;
import com.poseal.university.model.Lesson;

@WebServlet("/lessons")
public class LessonListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private LessonDao lessonDao;

    @Override
    public void init() {
        lessonDao = new LessonDaoHibernate();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Lesson> listLessons = lessonDao.findAll();

        req.setAttribute("listLessons", listLessons);
        req.getRequestDispatcher("/listLessons.jsp").forward(req, resp);
    }
}
