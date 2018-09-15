package com.poseal.university.servlet.department;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseal.university.dao.DepartmentDao;
import com.poseal.university.dao.FacultyDao;
import com.poseal.university.dao.RoomDao;
import com.poseal.university.dao.TeacherDao;
import com.poseal.university.dao.impl.DepartmentDaoHibernate;
import com.poseal.university.dao.impl.FacultyDaoHibernate;
import com.poseal.university.dao.impl.RoomDaoHibernate;
import com.poseal.university.dao.impl.TeacherDaoHibernate;
import com.poseal.university.model.Department;
import com.poseal.university.model.Faculty;
import com.poseal.university.model.Room;
import com.poseal.university.model.Teacher;

@WebServlet("/department")
public class DepartmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private DepartmentDao departmentDao;
    private FacultyDao facultyDao;
    private TeacherDao teacherDao;
    private RoomDao roomDao;

    @Override
    public void init() {
        departmentDao = new DepartmentDaoHibernate();
        facultyDao = new FacultyDaoHibernate();
        teacherDao = new TeacherDaoHibernate();
        roomDao = new RoomDaoHibernate();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department department = departmentDao.findOne(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("department", department);

        Faculty faculty = facultyDao.findOne(department.getFaculty().getId());
        req.setAttribute("faculty", faculty);

        List<Teacher> listTeachers = teacherDao.findAll(department);
        req.setAttribute("listTeachers", listTeachers);

        List<Room> listRooms = roomDao.findAll(department);
        req.setAttribute("listRooms", listRooms);
        req.getRequestDispatcher("/models/department.jsp").forward(req, resp);
    }
}
