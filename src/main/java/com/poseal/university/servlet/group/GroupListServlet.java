package com.poseal.university.servlet.group;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseal.university.dao.GroupDao;
import com.poseal.university.dao.impl.GroupDaoImpl;
import com.poseal.university.model.Group;

@WebServlet("/groups")
public class GroupListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private GroupDao groupDao;

    @Override
    public void init() {
        groupDao = new GroupDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Group> listGroups = groupDao.findAll();

        req.setAttribute("listGroups", listGroups);
        req.getRequestDispatcher("/listGroups.jsp").forward(req, resp);
    }
}
