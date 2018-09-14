package com.poseal.university.servlet.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseal.university.dao.GroupDao;
import com.poseal.university.dao.StudentDao;
import com.poseal.university.dao.impl.GroupDaoImpl;
import com.poseal.university.dao.impl.StudentDaoImpl;
import com.poseal.university.model.Group;
import com.poseal.university.model.Student;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private StudentDao studentDao;
    private GroupDao groupDao;

    @Override
    public void init() {
        studentDao = new StudentDaoImpl();
        groupDao = new GroupDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = studentDao.findOne(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("student", student);

        Group group = groupDao.findOne(student.getGroupId());
        req.setAttribute("group", group);

        req.getRequestDispatcher("/models/student.jsp").forward(req, resp);
    }
}
