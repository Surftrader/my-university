package com.poseal.university.servlet.lesson;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseal.university.dao.LessonDao;
import com.poseal.university.dao.impl.LessonDaoImpl;
import com.poseal.university.model.Lesson;

@WebServlet("/lesson")
public class LessonServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private LessonDao lessonDao;

    @Override
    public void init() {
        lessonDao = new LessonDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Lesson lesson = lessonDao.findOne(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("lesson", lesson);
        req.getRequestDispatcher("/models/lesson.jsp").forward(req, resp);
    }
}
