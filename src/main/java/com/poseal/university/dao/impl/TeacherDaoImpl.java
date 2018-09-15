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
import com.poseal.university.dao.TeacherDao;
import com.poseal.university.exception.DAOException;
import com.poseal.university.model.Department;
import com.poseal.university.model.Subject;
import com.poseal.university.model.Teacher;

public class TeacherDaoImpl implements TeacherDao {

    private static final Logger log = LogManager.getLogger(TeacherDaoImpl.class);

    @Override
    public List<Teacher> findAll() {
        log.trace("Entered findAll() method");
        List<Teacher> teachers = new ArrayList<>();
        final String sql = "SELECT * FROM teacher";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            log.trace("Created prepared statement and result set");
            while (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(resultSet.getInt(1));
                log.trace("Set id of teacher = {} from database", teacher.getId());
                teacher.setName(resultSet.getString(2));
                log.trace("Set name of teacher = {} from database", teacher.getName());
                teacher.setSurname(resultSet.getString(3));
                log.trace("Set surname of teacher = {} from database", teacher.getSurname());
                teacher.setSubjectId(resultSet.getInt(4));
                log.trace("Set subject of teacher = {} from database", teacher.getSubjectId());
                teacher.setDepartmentId(resultSet.getInt(5));
                log.trace("Set department of teacher = {} from database", teacher.getDepartmentId());
                teachers.add(teacher);
                log.trace("Add to list teacher: " + teacher);
            }
        } catch (SQLException e) {
            log.error("Error while finding of list of teachers!", e);
            throw new DAOException("Error while finding of list of teachers!", e);
        }
        log.info("List of " + teachers.size() + " teachers was found");
        log.trace("Exited findAll() method");
        return teachers;
    }

    @Override
    public Teacher findOne(Integer id) {
        log.trace("Enered findOne() method with argument id = {}", id);
        Teacher teacher = null;
        final String sql = "SELECT * FROM teacher WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement");
            statement.setInt(1, id);
            log.trace("Set param id={} into statment for query", id);
            try (ResultSet resultSet = statement.executeQuery()) {
                log.trace("Created result set");
                if (resultSet.next()) {
                    teacher = new Teacher();
                    teacher.setId(resultSet.getInt(1));
                    log.trace("Set id of teacher = {} from database", teacher.getId());
                    teacher.setName(resultSet.getString(2));
                    log.trace("Set name of teacher = {} from database", teacher.getName());
                    teacher.setSurname(resultSet.getString(3));
                    log.trace("Set surname of teacher = {} from database", teacher.getSurname());
                    teacher.setSubjectId(resultSet.getInt(4));
                    log.trace("Set subject of teacher = {} from database", teacher.getSubjectId());
                    teacher.setDepartmentId(resultSet.getInt(5));
                    log.trace("Set department of teacher = {} from database", teacher.getDepartmentId());
                }
            }
        } catch (SQLException e) {
            log.error("Error while finding of teacher!", e);
            throw new DAOException("Error while finding of teacher!", e);
        }
        log.info("Teacher " + teacher + " was found");
        log.trace("Exited findOne() method");
        return teacher;
    }

    @Override
    public Teacher create(Teacher teacher) {
        log.trace("Enered create() method with argument teacher = {}", teacher);
        final String sql = "INSERT INTO teacher(name, surname, subject_id, department_id) VALUES (?, ?, ?, ?)";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            log.trace("Created prepared statement");
            statement.setString(1, teacher.getName());
            log.trace("Set name of teacher = {}", teacher.getName());
            statement.setString(2, teacher.getSurname());
            log.trace("Set surname of teacher = {}", teacher.getSurname());
            statement.setInt(3, teacher.getSubjectId());
            log.trace("Set subject of teacher = {}", teacher.getSubjectId());
            statement.setInt(4, teacher.getDepartmentId());
            log.trace("Set department of teacher = {}", teacher.getDepartmentId());
            statement.executeUpdate();
            log.debug("Inserted teacher={}", teacher);
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                log.trace("Created result set");
                if (resultSet.next()) {
                    log.debug("Return generated key from database");
                    teacher.setId(resultSet.getInt(1));
                    log.trace("Set generated key={} from database", teacher.getId());
                }
            }
        } catch (SQLException e) {
            log.error("Error while creating of teacher!", e);
            throw new DAOException("Error while creating of teacher!", e);
        }
        log.info("Teacher: " + teacher + " was created");
        log.trace("Exited create() method");
        return teacher;
    }

    @Override
    public void remove(Integer id) {
        log.trace("Enered remove() with argument id = {}", id);
        final String sql = "DELETE FROM teacher WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement");
            statement.setInt(1, id);
            log.trace("Set param id={} into statment for query", id);
            statement.executeUpdate();
            log.debug("Removed teacher with id={}", id);
        } catch (SQLException e) {
            log.error("Error while removing of teacher!", e);
            throw new DAOException("Error while removing of teacher!", e);
        }
        log.info("Teacher with id=" + id + " was removed");
        log.trace("Exited remove() method");
    }

    @Override
    public void update(Teacher teacher) {
        log.trace("Enered update() method with argument department = {}", teacher);
        final String sql = "UPDATE teacher SET name=?, surname=?, subject_id=?, department_id=? WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement");
            statement.setString(1, teacher.getName());
            log.trace("Set name of teacher = {}", teacher.getName());
            statement.setString(2, teacher.getSurname());
            log.trace("Set surname of teacher = {}", teacher.getSurname());
            statement.setInt(3, teacher.getSubjectId());
            log.trace("Set subject of teacher = {}", teacher.getSubjectId());
            statement.setInt(4, teacher.getDepartmentId());
            log.trace("Set department of teacher = {}", teacher.getDepartmentId());
            statement.setInt(5, teacher.getId());
            log.trace("Set id of teacher = {}", teacher.getId());
            statement.executeUpdate();
            log.debug("Updated teacher={}", teacher);
        } catch (SQLException e) {
            log.error("Error while updating of teacher!", e);
            throw new DAOException("Error while updating of teacher!", e);
        }
        log.info("Teacher after update: " + teacher);
        log.trace("Exited update() method");
    }

    @Override
    public List<Teacher> findAll(Department department) {
        log.trace("Entered findByDepartment() method with agument department = {}", department);
        List<Teacher> teachers = new ArrayList<>();
        final String sql = "SELECT * FROM teacher WHERE department_id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement and result set");
            statement.setInt(1, department.getId());
            log.trace("Set param id={} into statment for query", department.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                log.trace("Created result set");
                while (resultSet.next()) {
                    Teacher teacher = new Teacher();
                    teacher.setId(resultSet.getInt(1));
                    log.trace("Set id of teacher = {} from database", teacher.getId());
                    teacher.setName(resultSet.getString(2));
                    log.trace("Set name of teacher = {} from database", teacher.getName());
                    teacher.setSurname(resultSet.getString(3));
                    log.trace("Set surname of teacher = {} from database", teacher.getSurname());
                    teacher.setSubjectId(resultSet.getInt(4));
                    log.trace("Set subject of teacher = {} from database", teacher.getSubjectId());
                    teacher.setDepartmentId(resultSet.getInt(5));
                    log.trace("Set department of teacher = {} from database", teacher.getDepartmentId());
                    teachers.add(teacher);
                    log.trace("Add to list teacher: " + teacher);
                }
            }
        } catch (SQLException e) {
            log.error("Error while finding of list of teachers!", e);
            throw new DAOException("Error while finding of list of teachers!", e);
        }
        log.info("List of " + teachers.size() + " teachers was found");
        log.trace("Exited findByDepartment() method");
        return teachers;
    }

    @Override
    public Teacher findOne(Subject subject) {
        log.trace("Enered findBySubject() method with argument subject = {}", subject);
        Teacher teacher = null;
        final String sql = "SELECT * FROM teacher WHERE subject_id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement");
            statement.setInt(1, subject.getId());
            log.trace("Set param id={} into statment for query", subject.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                log.trace("Created result set");
                if (resultSet.next()) {
                    teacher = new Teacher();
                    teacher.setId(resultSet.getInt(1));
                    log.trace("Set id of teacher = {} from database", teacher.getId());
                    teacher.setName(resultSet.getString(2));
                    log.trace("Set name of teacher = {} from database", teacher.getName());
                    teacher.setSurname(resultSet.getString(3));
                    log.trace("Set surname of teacher = {} from database", teacher.getSurname());
                    teacher.setSubjectId(resultSet.getInt(4));
                    log.trace("Set subject of teacher = {} from database", teacher.getSubjectId());
                    teacher.setDepartmentId(resultSet.getInt(5));
                    log.trace("Set department of teacher = {} from database", teacher.getDepartmentId());
                }
            }
        } catch (SQLException e) {
            log.error("Error while finding of teacher!", e);
            throw new DAOException("Error while finding of teacher!", e);
        }
        log.info("Teacher " + teacher + " was found");
        log.trace("Exited findBySubject() method");
        return teacher;
    }
}
