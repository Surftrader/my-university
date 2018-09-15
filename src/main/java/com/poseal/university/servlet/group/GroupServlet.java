package com.poseal.university.servlet.group;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseal.university.dao.FacultyDao;
import com.poseal.university.dao.impl.FacultyDaoHibernate;
import com.poseal.university.model.Faculty;
import com.poseal.university.service.dto.GroupDto;
import com.poseal.university.service.dto.StudentDto;
import com.poseal.university.service.group.GroupService;
import com.poseal.university.service.student.StudentService;

@WebServlet("/group")
public class GroupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private GroupService groupService;
    private StudentService studentService;
    private FacultyDao facultyDao;

    @Override
    public void init() {
        groupService = new GroupService();
        studentService = new StudentService();
        facultyDao = new FacultyDaoHibernate();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GroupDto group = groupService.findOne(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("group", group);

        List<StudentDto> listStudents = studentService.findAll(group);
        req.setAttribute("listStudents", listStudents);

        Faculty faculty = facultyDao.findOne(group.getFacultyId());
        req.setAttribute("faculty", faculty);

        req.getRequestDispatcher("/models/group.jsp").forward(req, resp);
    }
}
