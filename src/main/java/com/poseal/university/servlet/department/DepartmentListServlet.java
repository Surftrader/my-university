package com.poseal.university.servlet.department;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseal.university.dao.DepartmentDao;
import com.poseal.university.dao.impl.DepartmentDaoHibernate;
import com.poseal.university.model.Department;

@WebServlet("/departments")
public class DepartmentListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private DepartmentDao departmentDao;

    @Override
    public void init() {
        departmentDao = new DepartmentDaoHibernate();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Department> listDepartments = departmentDao.findAll();

        req.setAttribute("listDepartments", listDepartments);
        req.getRequestDispatcher("/listDepartments.jsp").forward(req, resp);
    }
}
