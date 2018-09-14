package com.poseal.university.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.poseal.university.dao.PostgreConnectionUtils;
import com.poseal.university.dao.ScheduleDao;
import com.poseal.university.exception.DAOException;
import com.poseal.university.model.Schedule;

public class ScheduleDaoImpl implements ScheduleDao {

    private static final Logger log = LogManager.getLogger(ScheduleDaoImpl.class);

    @Override
    public List<Schedule> findAll() {
        log.trace("Entered findAll() method");
        List<Schedule> schedules = new ArrayList<>();
        final String sql = "SELECT * FROM schedule";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            log.trace("Created prepared statement and result set");
            while (resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(resultSet.getInt(1));
                log.trace("Set id of schedule = {} from database", schedule.getId());
                schedule.setFacultyId(resultSet.getInt(2));
                log.trace("Set faculty of schedule = {} from database", schedule.getFacultyId());
                schedules.add(schedule);
                log.trace("Add to list schedule: " + schedule);
            }
        } catch (SQLException e) {
            log.error("Error while finding of list of schedules!", e);
            throw new DAOException("Error while finding of list of schedules!", e);
        }
        log.info("List of " + schedules.size() + " schedules was found");
        log.trace("Exited findAll() method");
        return schedules;
    }

    @Override
    public Schedule findOne(Integer id) {
        log.trace("Enered findOne() method with argument id = {}", id);
        Schedule schedule = null;
        final String sql = "SELECT * FROM schedule WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement");
            statement.setInt(1, id);
            log.trace("Set param id={} into statment for query", id);
            try (ResultSet resultSet = statement.executeQuery()) {
                log.trace("Created result set");
                if (resultSet.next()) {
                    schedule = new Schedule();
                    schedule.setId(resultSet.getInt(1));
                    log.trace("Set id of schedule = {} from database", schedule.getId());
                    schedule.setFacultyId(resultSet.getInt(2));
                    log.trace("Set faculty of schedule = {} from database", schedule.getFacultyId());
                }
            }
        } catch (SQLException e) {
            log.error("Error while finding of schedule!", e);
            throw new DAOException("Error while finding of schedule!", e);
        }
        log.info("Schedule " + schedule + " was found");
        log.trace("Exited findOne() method");
        return schedule;
    }

    @Override
    public Schedule create(Schedule schedule) {
        log.trace("Enered create() method with argument schedule = {}", schedule);
        final String sql = "INSERT INTO schedule (faculty_id) VALUES (?)";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            log.trace("Created prepared statement");
            statement.setInt(1, schedule.getFacultyId());
            log.trace("Set faculty of schedule = {}", schedule.getFacultyId());
            statement.executeUpdate();
            log.debug("Inserted schedule={}", schedule);
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                log.trace("Created result set");
                if (resultSet.next()) {
                    log.debug("Return generated key from database");
                    schedule.setId(resultSet.getInt(1));
                    log.trace("Set generated key={} from database", schedule.getId());
                }
            }
        } catch (SQLException e) {
            log.error("Error while creating of schedule!", e);
            throw new DAOException("Error while creating of schedule!", e);
        }
        log.info("Schedule: " + schedule + " was created");
        log.trace("Exited create() method");
        return schedule;
    }

    @Override
    public void remove(Integer id) {
        log.trace("Enered remove() with argument id = {}", id);
        final String sql = "DELETE FROM schedule WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement");
            statement.setInt(1, id);
            log.trace("Set param id={} into statment for query", id);
            statement.executeUpdate();
            log.debug("Removed schedule with id={}", id);
        } catch (SQLException e) {
            log.error("Error while removing of schedule!", e);
            throw new DAOException("Error while removing of schedule!", e);
        }
        log.info("Schedule with id=" + id + " was removed");
        log.trace("Exited remove() method");
    }

    @Override
    public void update(Schedule schedule) {
        log.trace("Enered update() method with argument schedule = {}", schedule);
        final String sql = "UPDATE schedule SET faculty_id=? WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement");
            statement.setInt(1, schedule.getFacultyId());
            log.trace("Set faculty of schedule = {}", schedule.getFacultyId());
            statement.setInt(2, schedule.getId());
            log.trace("Set id of schedule = {}", schedule.getId());
            statement.executeUpdate();
            log.debug("Updated schedule={}", schedule);
        } catch (SQLException e) {
            log.error("Error while updating of schedule!", e);
            throw new DAOException("Error while updating of schedule!", e);
        }
        log.info("Schedule after update: " + schedule);
        log.trace("Exited update() method");
    }
}
