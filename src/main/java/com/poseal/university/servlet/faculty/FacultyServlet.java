package com.poseal.university.servlet.faculty;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseal.university.dao.DepartmentDao;
import com.poseal.university.dao.FacultyDao;
import com.poseal.university.dao.GroupDao;
import com.poseal.university.dao.impl.DepartmentDaoHibernate;
import com.poseal.university.dao.impl.FacultyDaoHibernate;
import com.poseal.university.dao.impl.GroupDaoHibernate;
import com.poseal.university.model.Department;
import com.poseal.university.model.Faculty;
import com.poseal.university.model.Group;

@WebServlet("/faculty")
public class FacultyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private FacultyDao facultyDao;
    private GroupDao groupDao;
    private DepartmentDao departmentDao;

    @Override
    public void init() {
        facultyDao = new FacultyDaoHibernate();
        groupDao = new GroupDaoHibernate();
        departmentDao = new DepartmentDaoHibernate();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Faculty faculty = facultyDao.findOne(id);
        req.setAttribute("faculty", faculty);

        List<Group> listGroups = groupDao.findAll(faculty);
        req.setAttribute("listGroups", listGroups);

        List<Department> listDepartment = departmentDao.findAll(faculty);
        req.setAttribute("listDepartment", listDepartment);

        req.getRequestDispatcher("/models/faculty.jsp").forward(req, resp);
    }
}
