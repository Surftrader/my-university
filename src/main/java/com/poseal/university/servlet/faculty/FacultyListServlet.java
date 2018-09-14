package com.poseal.university.servlet.faculty;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseal.university.dao.FacultyDao;
import com.poseal.university.dao.impl.FacultyDaoImpl;
import com.poseal.university.model.Faculty;

@WebServlet("/faculties")
public class FacultyListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private FacultyDao facultyDao;

    @Override
    public void init() {
        facultyDao = new FacultyDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Faculty> listFaculties = facultyDao.findAll();

        req.setAttribute("listFaculties", listFaculties);
        req.getRequestDispatcher("/listFaculties.jsp").forward(req, resp);
    }
}
