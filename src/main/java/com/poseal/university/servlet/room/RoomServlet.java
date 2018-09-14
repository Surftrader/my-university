package com.poseal.university.servlet.room;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseal.university.dao.DepartmentDao;
import com.poseal.university.dao.RoomDao;
import com.poseal.university.dao.impl.DepartmentDaoImpl;
import com.poseal.university.dao.impl.RoomDaoImpl;
import com.poseal.university.model.Department;
import com.poseal.university.model.Room;

@WebServlet("/room")
public class RoomServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private RoomDao roomDao;
    private DepartmentDao departmentDao;

    @Override
    public void init() {
        roomDao = new RoomDaoImpl();
        departmentDao = new DepartmentDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Room room = roomDao.findOne(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("room", room);

        Department department = departmentDao.findOne(room.getDepartmentId());
        req.setAttribute("department", department);

        req.getRequestDispatcher("/models/room.jsp").forward(req, resp);
    }
}
