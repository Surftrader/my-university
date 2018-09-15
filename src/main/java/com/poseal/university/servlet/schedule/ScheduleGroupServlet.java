package com.poseal.university.servlet.schedule;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.poseal.university.constants.AppConstants;
import com.poseal.university.dao.GroupDao;
import com.poseal.university.dao.impl.GroupDaoImpl;
import com.poseal.university.exception.DatePeriodException;
import com.poseal.university.model.Group;
import com.poseal.university.model.Lesson;
import com.poseal.university.service.schedule.ScheduleService;

@WebServlet("/groupschedule")
public class ScheduleGroupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger log = LogManager.getLogger(ScheduleGroupServlet.class);

    private ScheduleService service;
    private GroupDao groupDao;

    @Override
    public void init() {
        service = new ScheduleService();
        groupDao = new GroupDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String groupId = req.getParameter("groupId");
        String dateStart = req.getParameter("dayStart");
        String dateEnd = req.getParameter("dayEnd");

        LocalDateTime before = null;
        LocalDateTime after = null;

        try {
            before = LocalDate.parse(dateStart, AppConstants.DATE_FORMATTER).atTime(00, 00);
        } catch (DateTimeParseException ex) {
            log.trace("Date = {} is not correct!", dateStart);
        }
        try {
            after = LocalDate.parse(dateEnd, AppConstants.DATE_FORMATTER).atTime(00, 00);
        } catch (DateTimeParseException ex) {
            log.trace("Date = {} is not correct!", dateEnd);
        }

        Integer id = Integer.parseInt(groupId);
        Group group = groupDao.findOne(id);
        List<Lesson> listLessons = null;

        try {
            listLessons = service.findSchedule(group, before, after);
        } catch (DatePeriodException ex) {
            String errorMessage = "Invalid date range!";
            log.trace("Invalid date range!", ex);
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/errorpages/errorPage.jsp").forward(req, resp);
        }
        req.setAttribute("group", group);
        req.setAttribute("listLessons", listLessons);
        req.getRequestDispatcher("/models/scheduleGroup.jsp").forward(req, resp);
    }
}
