package com.poseal.university.servlet.group;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseal.university.service.dto.GroupDto;
import com.poseal.university.service.group.GroupService;

@WebServlet("/groups")
public class GroupListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private GroupService groupService;

    @Override
    public void init() {
        groupService = new GroupService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<GroupDto> listGroups = groupService.findAll();

        req.setAttribute("listGroups", listGroups);
        req.getRequestDispatcher("/listGroups.jsp").forward(req, resp);
    }
}
