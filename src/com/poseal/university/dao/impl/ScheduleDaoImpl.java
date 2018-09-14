package com.poseal.university.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.poseal.university.dao.PostgreConnectionUtils;
import com.poseal.university.dao.ScheduleDao;
import com.poseal.university.model.Schedule;

public class ScheduleDaoImpl implements ScheduleDao {

    @Override
    public List<Schedule> findAll() {
        List<Schedule> schedules = new ArrayList<>();
        final String sql = "SELECT * FROM schedule";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(resultSet.getInt(1));
                schedule.setFacultyId(resultSet.getInt(2));
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }

    @Override
    public Schedule findOne(Integer id) {
        Schedule schedule = null;
        final String sql = "SELECT * FROM schedule WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    schedule = new Schedule();
                    schedule.setId(resultSet.getInt(1));
                    schedule.setFacultyId(resultSet.getInt(2));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedule;
    }

    @Override
    public Schedule create(Schedule schedule) {
        final String sql = "INSERT INTO schedule (faculty_id) VALUES (?)";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, schedule.getFacultyId());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    schedule.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedule;
    }

    @Override
    public void remove(Integer id) {
        final String sql = "DELETE FROM schedule WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Schedule schedule) {
        final String sql = "UPDATE schedule SET faculty_id=? WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, schedule.getFacultyId());
            statement.setInt(2, schedule.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
