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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.poseal.university.dao.LessonDao;
import com.poseal.university.dao.PostgreConnectionUtils;
import com.poseal.university.exception.DAOException;
import com.poseal.university.model.Group;
import com.poseal.university.model.Lesson;
import com.poseal.university.model.Teacher;

public class LessonDaoImpl implements LessonDao {

    private static final Logger log = LogManager.getLogger(LessonDaoImpl.class);

    @Override
    public List<Lesson> findAll() {
        log.trace("Entered findAll() method");
        List<Lesson> lessons = new ArrayList<>();
        final String sql = "SELECT * FROM lesson";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            log.trace("Created prepared statement and result set");
            while (resultSet.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(resultSet.getInt(1));
                log.trace("Set id of lesson = {} from database", lesson.getId());
                lesson.setTimeStart(resultSet.getTimestamp(2).toLocalDateTime());
                log.trace("Set start time of lesson = {} from database", lesson.getTimeStart());
                lesson.setTimeEnd(resultSet.getTimestamp(3).toLocalDateTime());
                log.trace("Set end time of lesson = {} from database", lesson.getTimeEnd());
                lesson.setGroup(new GroupDaoImpl().findOne(resultSet.getInt(4)));
                log.trace("Set group of lesson by id={} from database", resultSet.getInt(4));
                lesson.setSubject(new SubjectDaoImpl().findOne(resultSet.getInt(5)));
                log.trace("Set subject of lesson by id={} from database", resultSet.getInt(5));
                lesson.setTeacher(new TeacherDaoImpl().findOne(resultSet.getInt(6)));
                log.trace("Set teacher of lesson by id={} from database", resultSet.getInt(6));
                lesson.setRoom(new RoomDaoImpl().findOne(resultSet.getInt(7)));
                log.trace("Set room of lesson by id={} from database", resultSet.getInt(7));
                lesson.setSchedule(new ScheduleDaoImpl().findOne(resultSet.getInt(8)));
                log.trace("Set schedule of lesson by id={} from database", resultSet.getInt(8));
                lessons.add(lesson);
                log.trace("Add to list lesson: " + lesson);
            }
        } catch (SQLException e) {
            log.error("Error while finding of list of lessons!", e);
            throw new DAOException("Error while finding of list of lessons!", e);
        }
        log.info("List of " + lessons.size() + " lessons was found");
        log.trace("Exited findAll() method");
        return lessons;
    }

    @Override
    public Lesson findOne(Integer id) {
        log.trace("Enered findOne() method with argument id = {}", id);
        Lesson lesson = null;
        final String sql = "SELECT * FROM lesson WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement");
            statement.setInt(1, id);
            log.trace("Set param id={} into statment for query", id);
            try (ResultSet resultSet = statement.executeQuery()) {
                log.trace("Created result set");
                if (resultSet.next()) {
                    lesson = new Lesson();
                    lesson.setId(resultSet.getInt(1));
                    log.trace("Set id of lesson = {} from database", lesson.getId());
                    lesson.setTimeStart(resultSet.getTimestamp(2).toLocalDateTime());
                    log.trace("Set start time of lesson = {} from database", lesson.getTimeStart());
                    lesson.setTimeEnd(resultSet.getTimestamp(3).toLocalDateTime());
                    log.trace("Set end time of lesson = {} from database", lesson.getTimeEnd());
                    lesson.setGroup(new GroupDaoImpl().findOne(resultSet.getInt(4)));
                    log.trace("Set group of lesson by id={} from database", resultSet.getInt(4));
                    lesson.setSubject(new SubjectDaoImpl().findOne(resultSet.getInt(5)));
                    log.trace("Set subject of lesson by id={} from database", resultSet.getInt(5));
                    lesson.setTeacher(new TeacherDaoImpl().findOne(resultSet.getInt(6)));
                    log.trace("Set teacher of lesson by id={} from database", resultSet.getInt(6));
                    lesson.setRoom(new RoomDaoImpl().findOne(resultSet.getInt(7)));
                    log.trace("Set room of lesson by id={} from database", resultSet.getInt(7));
                    lesson.setSchedule(new ScheduleDaoImpl().findOne(resultSet.getInt(8)));
                    log.trace("Set schedule of lesson by id={} from database", resultSet.getInt(8));
                }
            }
        } catch (SQLException e) {
            log.error("Error while finding of lesson!", e);
            throw new DAOException("Error while finding of lesson!", e);
        }
        log.info("Lesson " + lesson + " was found");
        log.trace("Exited findOne() method");
        return lesson;
    }

    @Override
    public Lesson create(Lesson lesson) {
        log.trace("Enered create() method with argument lesson = {}", lesson);
        final String sql = "INSERT INTO lesson (time_start, time_end, group_id, subject_id, teacher_id, room_id, schedule_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            log.trace("Created prepared statement");
            statement.setTimestamp(1, Timestamp.valueOf(lesson.getTimeStart()));
            log.trace("Set start time of lesson = {}", lesson.getTimeStart());
            statement.setTimestamp(2, Timestamp.valueOf(lesson.getTimeEnd()));
            log.trace("Set end time of lesson = {}", lesson.getTimeEnd());
            statement.setInt(3, lesson.getGroup().getId());
            log.trace("Set group of lesson = {}", lesson.getGroup().getId());
            statement.setInt(4, lesson.getSubject().getId());
            log.trace("Set subject of lesson = {}", lesson.getSubject().getId());
            statement.setInt(5, lesson.getTeacher().getId());
            log.trace("Set teacher of lesson = {}", lesson.getTeacher().getId());
            statement.setInt(6, lesson.getRoom().getId());
            log.trace("Set room of lesson = {}", lesson.getRoom().getId());
            statement.setInt(7, lesson.getSchedule().getId());
            log.trace("Set schedule of lesson = {}", lesson.getSchedule().getId());
            statement.executeUpdate();
            log.debug("Inserted lesson={}", lesson);
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                log.trace("Created result set");
                if (resultSet.next()) {
                    log.debug("Return generated key from database");
                    lesson.setId(resultSet.getInt(1));
                    log.trace("Set generated key={} from database", lesson.getId());
                }
            }
        } catch (SQLException e) {
            log.error("Error while creating of lesson!", e);
            throw new DAOException("Error while creating of lesson!", e);
        }
        log.info("Lesson: " + lesson + " was created");
        log.trace("Exited create() method");
        return lesson;
    }

    @Override
    public void remove(Integer id) {
        log.trace("Enered remove() with argument id = {}", id);
        final String sql = "DELETE FROM lesson WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement");
            statement.setInt(1, id);
            log.trace("Set param id={} into statment for query", id);
            statement.executeUpdate();
            log.debug("Removed lesson with id={}", id);
        } catch (SQLException e) {
            log.error("Error while removing of lesson!", e);
            throw new DAOException("Error while removing of lesson!", e);
        }
        log.info("Lesson with id=" + id + " was removed");
        log.trace("Exited remove() method");
    }

    @Override
    public void update(Lesson lesson) {
        log.trace("Enered update() method with argument lesson = {}", lesson);
        final String sql = "UPDATE lesson SET time_start=?, time_end=? , group_id=?, subject_id=?, teacher_id=?, room_id=?, schedule_id=? WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement");
            statement.setTimestamp(1, Timestamp.valueOf(lesson.getTimeStart()));
            log.trace("Set start time of lesson = {}", lesson.getTimeStart());
            statement.setTimestamp(2, Timestamp.valueOf(lesson.getTimeEnd()));
            log.trace("Set end time of lesson = {}", lesson.getTimeEnd());
            statement.setInt(3, lesson.getGroup().getId());
            log.trace("Set group of lesson = {}", lesson.getGroup().getId());
            statement.setInt(4, lesson.getSubject().getId());
            log.trace("Set subject of lesson = {}", lesson.getSubject().getId());
            statement.setInt(5, lesson.getTeacher().getId());
            log.trace("Set teacher of lesson = {}", lesson.getTeacher().getId());
            statement.setInt(6, lesson.getRoom().getId());
            log.trace("Set room of lesson = {}", lesson.getRoom().getId());
            statement.setInt(7, lesson.getSchedule().getId());
            log.trace("Set schedule of lesson = {}", lesson.getSchedule().getId());
            statement.setInt(8, lesson.getId());
            log.trace("Set id of lesson = {}", lesson.getId());
            statement.executeUpdate();
            log.debug("Updated lesson={}", lesson);
        } catch (SQLException e) {
            log.error("Error while updating of lesson!", e);
            throw new DAOException("Error while updating of lesson!", e);
        }
        log.info("Lesson after update: " + lesson);
        log.trace("Exited update() method");
    }

    @Override
    public List<Lesson> findListByGroupForTime(Group group, LocalDateTime start, LocalDateTime end) {
        log.trace("Entered findListByGroupForTime() method with aguments group = {}, start = {}, end = {}", group,
                start, end);
        List<Lesson> lessons = new ArrayList<>();
        final String sql = "SELECT * FROM lesson WHERE group_id=? and time_start>=? and time_end<=?";

        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement");
            statement.setInt(1, group.getId());
            log.trace("Set param group_id={} into statment for query", group.getId());
            statement.setTimestamp(2, Timestamp.valueOf(start));
            log.trace("Set param time_start={} into statment for query", start);
            statement.setTimestamp(3, Timestamp.valueOf(end));
            log.trace("Set param time_end={} into statment for query", end);
            try (ResultSet resultSet = statement.executeQuery()) {
                log.trace("Created result set");
                while (resultSet.next()) {
                    Lesson lesson = new Lesson();
                    lesson.setId(resultSet.getInt(1));
                    log.trace("Set id of lesson = {} from database", lesson.getId());
                    lesson.setTimeStart(resultSet.getTimestamp(2).toLocalDateTime());
                    log.trace("Set start time of lesson = {} from database", lesson.getTimeStart());
                    lesson.setTimeEnd(resultSet.getTimestamp(3).toLocalDateTime());
                    log.trace("Set end time of lesson = {} from database", lesson.getTimeEnd());
                    lesson.setGroup(new GroupDaoImpl().findOne(resultSet.getInt(4)));
                    log.trace("Set group of lesson by id={} from database", resultSet.getInt(4));
                    lesson.setSubject(new SubjectDaoImpl().findOne(resultSet.getInt(5)));
                    log.trace("Set subject of lesson by id={} from database", resultSet.getInt(5));
                    lesson.setTeacher(new TeacherDaoImpl().findOne(resultSet.getInt(6)));
                    log.trace("Set teacher of lesson by id={} from database", resultSet.getInt(6));
                    lesson.setRoom(new RoomDaoImpl().findOne(resultSet.getInt(7)));
                    log.trace("Set room of lesson by id={} from database", resultSet.getInt(7));
                    lesson.setSchedule(new ScheduleDaoImpl().findOne(resultSet.getInt(8)));
                    log.trace("Set schedule of lesson by id={} from database", resultSet.getInt(8));
                    lessons.add(lesson);
                    log.trace("Add to list lesson: " + lesson);
                }
            }
        } catch (SQLException e) {
            log.error("Error while finding of list of lessons!", e);
            throw new DAOException("Error while finding of list of lessons!", e);
        }
        log.info("List of " + lessons.size() + " lessons was found");
        log.trace("Exited findListByGroupForTime() method");
        return lessons;
    }

    @Override
    public List<Lesson> findListByTeacherForTime(Teacher teacher, LocalDateTime start, LocalDateTime end) {
        log.trace("Entered findListByTeacherForTime() method with aguments teacher = {}, start = {}, end = {}", teacher,
                start, end);
        List<Lesson> lessons = new ArrayList<>();
        final String sql = "SELECT * FROM lesson WHERE teacher_id=? and time_start>=? and time_end<=?";

        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement");
            statement.setInt(1, teacher.getId());
            log.trace("Set param teacher_id={} into statment for query", teacher.getId());
            statement.setTimestamp(2, Timestamp.valueOf(start));
            log.trace("Set param time_start={} into statment for query", start);
            statement.setTimestamp(3, Timestamp.valueOf(end));
            log.trace("Set param time_end={} into statment for query", end);
            try (ResultSet resultSet = statement.executeQuery()) {
                log.trace("Created result set");
                while (resultSet.next()) {
                    Lesson lesson = new Lesson();
                    lesson.setId(resultSet.getInt(1));
                    log.trace("Set id of lesson = {} from database", lesson.getId());
                    lesson.setTimeStart(resultSet.getTimestamp(2).toLocalDateTime());
                    log.trace("Set start time of lesson = {} from database", lesson.getTimeStart());
                    lesson.setTimeEnd(resultSet.getTimestamp(3).toLocalDateTime());
                    log.trace("Set end time of lesson = {} from database", lesson.getTimeEnd());
                    lesson.setGroup(new GroupDaoImpl().findOne(resultSet.getInt(4)));
                    log.trace("Set group of lesson by id={} from database", resultSet.getInt(4));
                    lesson.setSubject(new SubjectDaoImpl().findOne(resultSet.getInt(5)));
                    log.trace("Set subject of lesson by id={} from database", resultSet.getInt(5));
                    lesson.setTeacher(new TeacherDaoImpl().findOne(resultSet.getInt(6)));
                    log.trace("Set teacher of lesson by id={} from database", resultSet.getInt(6));
                    lesson.setRoom(new RoomDaoImpl().findOne(resultSet.getInt(7)));
                    log.trace("Set room of lesson by id={} from database", resultSet.getInt(7));
                    lesson.setSchedule(new ScheduleDaoImpl().findOne(resultSet.getInt(8)));
                    log.trace("Set schedule of lesson by id={} from database", resultSet.getInt(8));
                    lessons.add(lesson);
                    log.trace("Add to list lesson: " + lesson);
                }
            }
        } catch (SQLException e) {
            log.error("Error while finding of list of lessons!", e);
            throw new DAOException("Error while finding of list of lessons!", e);
        }
        log.info("List of " + lessons.size() + " lessons was found");
        log.trace("Exited findListByTeacherForTime() method");
        return lessons;
    }
}
