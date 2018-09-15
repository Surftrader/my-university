package com.poseal.university.servlet.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseal.university.service.student.StudentService;

@WebServlet("/student/delete")
public class DeleteStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private StudentService studentService;

    @Override
    public void init() throws ServletException {
        studentService = new StudentService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String studentId = req.getParameter("studentId");

        studentService.remove(Integer.parseInt(studentId));

        req.getRequestDispatcher("/index.jsp").forward(req, resp);

    }
}
