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
import com.poseal.university.dao.TeacherDao;
import com.poseal.university.dao.impl.TeacherDaoImpl;
import com.poseal.university.exception.DatePeriodException;
import com.poseal.university.model.Lesson;
import com.poseal.university.model.Teacher;
import com.poseal.university.service.schedule.ScheduleService;

@WebServlet("/teacherschedule")
public class ScheduleTeacherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger log = LogManager.getLogger(ScheduleTeacherServlet.class);

    private ScheduleService service;
    private TeacherDao teacherDao;

    @Override
    public void init() {
        service = new ScheduleService();
        teacherDao = new TeacherDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teacherId = req.getParameter("teacherId");
        String dateStart = req.getParameter("dayStart");
        String dateEnd = req.getParameter("dayEnd");

        LocalDateTime before = null;
        LocalDateTime after = null;

        try {
            before = LocalDate.parse(dateStart, AppConstants.DATE_FORMATTER).atTime(00, 00);
        } catch (DateTimeParseException ex) {
            log.trace("Date = {} is incorrect!", dateStart);
        }
        try {
            after = LocalDate.parse(dateEnd, AppConstants.DATE_FORMATTER).atTime(00, 00);
        } catch (DateTimeParseException ex) {
            log.trace("Date = {} is incorrect!", dateEnd);
        }

        Integer id = Integer.parseInt(teacherId);
        Teacher teacher = teacherDao.findOne(id);
        List<Lesson> listLessons = null;

        try {
            listLessons = service.findSchedule(teacher, before, after);
        } catch (DatePeriodException ex) {
            log.trace("Invalid date range!", ex);
            req.getRequestDispatcher("/errorpages/dateErrorPage.jsp").forward(req, resp);
        }
        req.setAttribute("teacher", teacher);
        req.setAttribute("listLessons", listLessons);
        req.getRequestDispatcher("/models/scheduleTeacher.jsp").forward(req, resp);
    }
}
