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
import com.poseal.university.dao.SubjectDao;
import com.poseal.university.exception.DAOException;
import com.poseal.university.model.Subject;

public class SubjectDaoImpl implements SubjectDao {

    private static final Logger log = LogManager.getLogger(SubjectDaoImpl.class);

    @Override
    public List<Subject> findAll() {
        log.trace("Entered findAll() method");
        List<Subject> subjects = new ArrayList<>();
        final String sql = "SELECT * FROM subject";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            log.trace("Created prepared statement and result set");
            while (resultSet.next()) {
                Subject subject = new Subject();
                subject.setId(resultSet.getInt(1));
                log.trace("Set id of subject = {} from database", subject.getId());
                subject.setName(resultSet.getString(2));
                log.trace("Set name of subject = {} from database", subject.getName());
                subjects.add(subject);
                log.trace("Add to list subject: " + subject);
            }
        } catch (SQLException e) {
            log.error("Error while finding of list of subjects!", e);
            throw new DAOException("Error while finding of list of subjects!", e);
        }
        log.info("List of " + subjects.size() + " subjects was found");
        log.trace("Exited findAll() method");
        return subjects;
    }

    @Override
    public Subject findOne(Integer id) {
        log.trace("Enered findOne() method with argument id = {}", id);
        Subject subject = null;
        final String sql = "SELECT * FROM subject WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement");
            statement.setInt(1, id);
            log.trace("Set param id={} into statment for query", id);
            try (ResultSet resultSet = statement.executeQuery()) {
                log.trace("Created result set");
                if (resultSet.next()) {
                    subject = new Subject();
                    subject.setId(resultSet.getInt(1));
                    log.trace("Set id of subject = {} from database", subject.getId());
                    subject.setName(resultSet.getString(2));
                    log.trace("Set name of subject = {} from database", subject.getName());
                }
            }
        } catch (SQLException e) {
            log.error("Error while finding of subject!", e);
            throw new DAOException("Error while finding of subject!", e);
        }
        log.info("Subject " + subject + " was found");
        log.trace("Exited findOne() method");
        return subject;
    }

    @Override
    public Subject create(Subject subject) {
        log.trace("Enered create() method with argument subject = {}", subject);
        final String sql = "INSERT INTO subject (name) VALUES (?)";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            log.trace("Created prepared statement");
            statement.setString(1, subject.getName());
            log.trace("Set name of subject = {}", subject.getName());
            statement.executeUpdate();
            log.debug("Inserted  subject={}", subject);
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                log.trace("Created result set");
                if (resultSet.next()) {
                    log.debug("Return generated key from database");
                    subject.setId(resultSet.getInt(1));
                    log.trace("Set generated key={} from database", subject.getId());
                }
            }
        } catch (SQLException e) {
            log.error("Error while creating of subject!", e);
            throw new DAOException("Error while creating of subject!", e);
        }
        log.info("Subject: " + subject + " was created");
        log.trace("Exited create() method");
        return subject;
    }

    @Override
    public void remove(Integer id) {
        log.trace("Enered remove() with argument id = {}", id);
        final String sql = "DELETE FROM subject WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement");
            statement.setInt(1, id);
            log.trace("Set param id={} into statment for query", id);
            statement.executeUpdate();
            log.debug("Removed subject with id={}", id);
        } catch (SQLException e) {
            log.error("Error while removing of subject!", e);
            throw new DAOException("Error while removing of subject!", e);
        }
        log.info("Subject with id=" + id + " was removed");
        log.trace("Exited remove() method");
    }

    @Override
    public void update(Subject subject) {
        log.trace("Enered update() method with argument department = {}", subject);
        final String sql = "UPDATE subject SET name=? WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement");
            statement.setString(1, subject.getName());
            log.trace("Set name of subject = {}", subject.getName());
            statement.setInt(2, subject.getId());
            log.trace("Set id of subject = {}", subject.getId());
            statement.executeUpdate();
            log.debug("Updated subject={}", subject);
        } catch (SQLException e) {
            log.error("Error while updating of subject!", e);
            throw new DAOException("Error while updating of subject!", e);
        }
        log.info("Subject after update: " + subject);
        log.trace("Exited update() method");
    }

}
