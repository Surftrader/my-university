package com.poseal.university.servlet.group;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseal.university.dao.FacultyDao;
import com.poseal.university.dao.GroupDao;
import com.poseal.university.dao.StudentDao;
import com.poseal.university.dao.impl.FacultyDaoImpl;
import com.poseal.university.dao.impl.GroupDaoImpl;
import com.poseal.university.dao.impl.StudentDaoImpl;
import com.poseal.university.model.Faculty;
import com.poseal.university.model.Group;
import com.poseal.university.model.Student;

@WebServlet("/group")
public class GroupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private GroupDao groupDao;
    private StudentDao studentDao;
    private FacultyDao facultyDao;

    @Override
    public void init() {
        groupDao = new GroupDaoImpl();
        studentDao = new StudentDaoImpl();
        facultyDao = new FacultyDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Group group = groupDao.findOne(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("group", group);

        List<Student> listStudents = studentDao.findByGroup(group);
        req.setAttribute("listStudents", listStudents);

        Faculty faculty = facultyDao.findOne(group.getFacultyId());
        req.setAttribute("faculty", faculty);

        req.getRequestDispatcher("/models/group.jsp").forward(req, resp);
    }
}
