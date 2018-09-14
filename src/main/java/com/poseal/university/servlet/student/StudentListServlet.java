package com.poseal.university.servlet.student;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/students")
public class StudentListServlet extends HttpServlet {
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

        String param = req.getParameter("id");
        List<Student> listStudents = null;

        if (param == null) {
            listStudents = studentDao.findAll();
        } else {
            Group group = groupDao.findOne(Integer.parseInt(param));
            listStudents = studentDao.findByGroup(group);
        }
        req.setAttribute("listStudents", listStudents);
        req.getRequestDispatcher("/listStudents.jsp").forward(req, resp);
    }
}
