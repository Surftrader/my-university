package com.poseal.university.servlet.student;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseal.university.exception.ServiceException;
import com.poseal.university.service.dto.GroupDto;
import com.poseal.university.service.dto.StudentDto;
import com.poseal.university.service.group.GroupService;
import com.poseal.university.service.student.StudentService;

@WebServlet("/students")
public class StudentListServlet extends HttpServlet {
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

        String param = req.getParameter("id");
        List<StudentDto> listStudents = null;

        if (param == null) {
            listStudents = studentService.findAll();
        } else {
            GroupDto group = groupService.findOne(Integer.parseInt(param));
            listStudents = studentService.findAll(group);
        }
        req.setAttribute("listStudents", listStudents);
        req.getRequestDispatcher("/listStudents.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String studentName = req.getParameter("studentName");
        String studentSurname = req.getParameter("studentSurname");
        String groupId = req.getParameter("group");

        StudentDto student = new StudentDto();
        student.setName(studentName);
        student.setSurname(studentSurname);

        try {
            GroupDto group = groupService.findOne(Integer.parseInt(groupId));
            student.setGroupId(group.getId());
            student = studentService.saveStudent(student);
        } catch (ServiceException | IllegalArgumentException ex) {
            String errorMessage = "Invalid data!";
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/errorpages/errorPage.jsp").forward(req, resp);
        }

        GroupDto group = groupService.findOne(student.getGroupId());
        List<GroupDto> listGroups = groupService.findAll();

        req.setAttribute("listGroups", listGroups);
        req.setAttribute("student", student);
        req.setAttribute("group", group);

        resp.sendRedirect("student?id=" + student.getId());
    }
}
