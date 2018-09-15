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
import com.poseal.university.dao.StudentDao;
import com.poseal.university.exception.DAOException;
import com.poseal.university.model.Group;
import com.poseal.university.model.Student;

public class StudentDaoImpl implements StudentDao {

    private static final Logger log = LogManager.getLogger(StudentDaoImpl.class);

    @Override
    public List<Student> findAll() {
        log.trace("Entered findAll() method");
        List<Student> students = new ArrayList<>();
        final String sql = "SELECT * FROM student";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            log.trace("Created prepared statement and result set");
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt(1));
                log.trace("Set id of student = {} from database", student.getId());
                student.setName(resultSet.getString(2));
                log.trace("Set name of student = {} from database", student.getName());
                student.setSurname(resultSet.getString(3));
                log.trace("Set surname of student = {} from database", student.getSurname());
                student.setGroupId(resultSet.getInt(4));
                log.trace("Set group of student = {} from database", student.getGroupId());
                students.add(student);
                log.trace("Add to list student: " + student);
            }
        } catch (SQLException e) {
            log.error("Error while finding of list of students!", e);
            throw new DAOException("Error while finding of list of students!", e);
        }
        log.info("List of " + students.size() + " students was found");
        log.trace("Exited findAll() method");
        return students;
    }

    @Override
    public Student findOne(Integer id) {
        log.trace("Enered findOne() method with argument id = {}", id);
        Student student = null;
        final String sql = "SELECT * FROM student WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement");
            statement.setInt(1, id);
            log.trace("Set param id={} into statment for query", id);
            try (ResultSet resultSet = statement.executeQuery()) {
                log.trace("Created result set");
                if (resultSet.next()) {
                    student = new Student();
                    student.setId(resultSet.getInt(1));
                    log.trace("Set id of student = {} from database", student.getId());
                    student.setName(resultSet.getString(2));
                    log.trace("Set name of student = {} from database", student.getName());
                    student.setSurname(resultSet.getString(3));
                    log.trace("Set surname of student = {} from database", student.getSurname());
                    student.setGroupId(resultSet.getInt(4));
                    log.trace("Set group of student = {} from database", student.getGroupId());
                }
            }
        } catch (SQLException e) {
            log.error("Error while finding of student!", e);
            throw new DAOException("Error while finding of student!", e);
        }
        log.info("Student " + student + " was found");
        log.trace("Exited findOne() method");
        return student;
    }

    @Override
    public Student create(Student student) {
        log.trace("Enered create() method with argument student = {}", student);
        final String sql = "INSERT INTO student(name, surname, group_id) VALUES (?, ?, ?)";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            log.trace("Created prepared statement");
            statement.setString(1, student.getName());
            log.trace("Set name of student = {}", student.getName());
            statement.setString(2, student.getSurname());
            log.trace("Set surname of student = {}", student.getSurname());
            statement.setInt(3, student.getGroupId());
            log.trace("Set group of student = {}", student.getGroupId());
            statement.executeUpdate();
            log.debug("Inserted student={}", student);
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                log.trace("Created result set");
                if (resultSet.next()) {
                    log.debug("Return generated key from database");
                    student.setId(resultSet.getInt(1));
                    log.trace("Set generated key={} from database", student.getId());
                }
            }
        } catch (SQLException e) {
            log.error("Error while creating of student!", e);
            throw new DAOException("Error while creating of student!", e);
        }
        log.info("Student: " + student + " was created");
        log.trace("Exited create() method");
        return student;
    }

    @Override
    public void remove(Integer id) {
        log.trace("Enered remove() with argument id = {}", id);
        final String sql = "DELETE FROM student WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {
            log.trace("Created prepared statement");
            statement.setInt(1, id);
            log.trace("Set param id={} into statment for query", id);
            statement.executeUpdate();
            log.debug("Removed student with id={}", id);
        } catch (SQLException e) {
            log.error("Error while removing of student!", e);
            throw new DAOException("Error while removing of student!", e);
        }
        log.info("Student with id=" + id + " was removed");
        log.trace("Exited remove() method");
    }

    @Override
    public void update(Student student) {
        log.trace("Enered update() method with argument student = {}", student);
        final String sql = "UPDATE student SET name=?, surname=?, group_id=? WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {
            log.trace("Created prepared statement");
            statement.setString(1, student.getName());
            log.trace("Set name of student = {}", student.getName());
            statement.setString(2, student.getSurname());
            log.trace("Set surname of student = {}", student.getSurname());
            statement.setInt(3, student.getGroupId());
            log.trace("Set group of student = {}", student.getGroupId());
            statement.setInt(4, student.getId());
            log.trace("Set id of department = {}", student.getId());
            statement.executeUpdate();
            log.debug("Updated student={}", student);
        } catch (SQLException e) {
            log.error("Error while updating of student!", e);
            throw new DAOException("Error while updating of student!", e);
        }
        log.info("Student after update: " + student);
        log.trace("Exited update() method");
    }

    @Override
    public List<Student> findAll(Group group) {
        log.trace("Entered findStudentsByGroup() method with argument groupId = {}", group.getId());
        List<Student> students = new ArrayList<>();
        final String sql = "SELECT * FROM student WHERE group_id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement and result set");
            statement.setInt(1, group.getId());
            log.trace("Set param id={} into statment for query", group.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Student student = new Student();
                    student.setId(resultSet.getInt(1));
                    log.trace("Set id of student = {} from database", student.getId());
                    student.setName(resultSet.getString(2));
                    log.trace("Set name of student = {} from database", student.getName());
                    student.setSurname(resultSet.getString(3));
                    log.trace("Set surname of student = {} from database", student.getSurname());
                    student.setGroupId(resultSet.getInt(4));
                    log.trace("Set group of student = {} from database", student.getGroupId());
                    students.add(student);
                    log.trace("Add to list student: " + student);
                }
            }
        } catch (SQLException e) {
            log.error("Error while finding of list of students!", e);
            throw new DAOException("Error while finding of list of students!", e);
        }
        log.info("List of " + students.size() + " students was found");
        log.trace("Exited findStudentsByGroup() method");
        return students;
    }
}
