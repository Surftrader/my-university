package com.poseal.university.servlet.student;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseal.university.exception.ServiceException;
import com.poseal.university.model.Group;
import com.poseal.university.model.Student;
import com.poseal.university.service.group.GroupService;
import com.poseal.university.service.student.StudentService;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private StudentService studentService;
    private GroupService groupService;

    @Override
    public void init() {
        studentService = new StudentService();
        groupService = new GroupService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = studentService.findOne(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("student", student);

        Group group = groupService.findOne(student.getGroupId());
        req.setAttribute("group", group);

        List<Group> listGroups = groupService.findAll();
        req.setAttribute("listGroups", listGroups);

        req.getRequestDispatcher("/models/student.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String studentId = req.getParameter("studentId");
        String studentName = req.getParameter("studentName");
        String studentSurname = req.getParameter("studentSurname");
        String groupId = req.getParameter("group");

        Group group = null;
        Student student = null;
        try {
            group = groupService.findOne(Integer.parseInt(groupId));

            student = new Student();
            student.setId(Integer.parseInt(studentId));
            student.setName(studentName);
            student.setSurname(studentSurname);
            student.setGroupId(group.getId());

            student = studentService.saveStudent(student);

        } catch (ServiceException | IllegalArgumentException ex) {

            String errorMessage = "Invalid data!";
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/errorpages/errorPage.jsp").forward(req, resp);
        }
        List<Group> listGroups = groupService.findAll();
        req.setAttribute("listGroups", listGroups);
        req.setAttribute("student", student);
        req.setAttribute("group", group);

        resp.sendRedirect("student?id=" + student.getId());
    }
}
