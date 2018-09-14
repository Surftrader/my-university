package com.poseal.university.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.poseal.university.dao.LessonDao;
import com.poseal.university.dao.PostgreConnectionUtils;
import com.poseal.university.model.Lesson;

public class LessonDaoImpl implements LessonDao {

    @Override
    public List<Lesson> findAll() {
        List<Lesson> lessons = new ArrayList<>();
        final String sql = "SELECT * FROM lesson";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(resultSet.getInt(1));
                lesson.setTimeStart(resultSet.getObject(2, LocalDateTime.class));
                lesson.setTimeEnd(resultSet.getObject(3, LocalDateTime.class));
                lesson.setGroup(new GroupDaoImpl().findOne(resultSet.getInt(4)));
                lesson.setSubject(new SubjectDaoImpl().findOne(resultSet.getInt(5)));
                lesson.setTeacher(new TeacherDaoImpl().findOne(resultSet.getInt(6)));
                lesson.setRoom(new RoomDaoImpl().findOne(resultSet.getInt(7)));
                lesson.setSchedule(new ScheduleDaoImpl().findOne(resultSet.getInt(8)));

                lessons.add(lesson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lessons;
    }

    @Override
    public Lesson findOne(Integer id) {
        Lesson lesson = null;
        final String sql = "SELECT * FROM lesson WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    lesson = new Lesson();
                    lesson.setId(resultSet.getInt(1));
                    lesson.setTimeStart(resultSet.getObject(2, LocalDateTime.class));
                    lesson.setTimeEnd(resultSet.getObject(3, LocalDateTime.class));
                    lesson.setGroup(new GroupDaoImpl().findOne(resultSet.getInt(4)));
                    lesson.setSubject(new SubjectDaoImpl().findOne(resultSet.getInt(5)));
                    lesson.setTeacher(new TeacherDaoImpl().findOne(resultSet.getInt(6)));
                    lesson.setRoom(new RoomDaoImpl().findOne(resultSet.getInt(7)));
                    lesson.setSchedule(new ScheduleDaoImpl().findOne(resultSet.getInt(8)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesson;
    }

    @Override
    public Lesson create(Lesson lesson) {
        final String sql = "INSERT INTO lesson (time_start, time_end, group_id, subject_id, teacher_id, room_id, schedule_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setTimestamp(1, Timestamp.valueOf(lesson.getTimeStart()));
            statement.setTimestamp(2, Timestamp.valueOf(lesson.getTimeEnd()));
            statement.setInt(3, lesson.getGroup().getId());
            statement.setInt(4, lesson.getSubject().getId());
            statement.setInt(5, lesson.getTeacher().getId());
            statement.setInt(6, lesson.getRoom().getId());
            statement.setInt(7, lesson.getSchedule().getId());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    lesson.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesson;
    }

    @Override
    public void remove(Integer id) {
        final String sql = "DELETE FROM lesson WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Lesson lesson) {
        final String sql = "UPDATE lesson SET time_start=?, time_end=? , group_id=?, subject_id=?, teacher_id=?, room_id=?, schedule_id=? WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setTimestamp(1, Timestamp.valueOf(lesson.getTimeStart()));
            statement.setTimestamp(2, Timestamp.valueOf(lesson.getTimeEnd()));
            statement.setInt(3, lesson.getGroup().getId());
            statement.setInt(4, lesson.getSubject().getId());
            statement.setInt(5, lesson.getTeacher().getId());
            statement.setInt(6, lesson.getRoom().getId());
            statement.setInt(7, lesson.getSchedule().getId());
            statement.setInt(8, lesson.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
