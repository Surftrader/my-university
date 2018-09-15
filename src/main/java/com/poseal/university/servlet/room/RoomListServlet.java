package com.poseal.university.servlet.room;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseal.university.dao.RoomDao;
import com.poseal.university.dao.impl.RoomDaoHibernate;
import com.poseal.university.model.Room;

@WebServlet("/rooms")
public class RoomListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private RoomDao roomDao;

    @Override
    public void init() {
        roomDao = new RoomDaoHibernate();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Room> listRooms = roomDao.findAll();

        req.setAttribute("listRooms", listRooms);
        req.getRequestDispatcher("/listRooms.jsp").forward(req, resp);
    }
}
