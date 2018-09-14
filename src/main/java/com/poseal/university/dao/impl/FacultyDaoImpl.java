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

import com.poseal.university.dao.FacultyDao;
import com.poseal.university.dao.PostgreConnectionUtils;
import com.poseal.university.exception.DAOException;
import com.poseal.university.model.Faculty;

public class FacultyDaoImpl implements FacultyDao {

    private static final Logger log = LogManager.getLogger(FacultyDaoImpl.class);

    @Override
    public List<Faculty> findAll() {
        log.trace("Entered findAll() method");
        List<Faculty> faculties = new ArrayList<>();
        final String sql = "SELECT * FROM faculty";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            log.trace("Created prepared statement and result set");
            while (resultSet.next()) {
                Faculty faculty = new Faculty();
                faculty.setId(resultSet.getInt(1));
                log.trace("Set id of faculty = {} from database", faculty.getId());
                faculty.setName(resultSet.getString(2));
                log.trace("Set name of faculty = {} from database", faculty.getName());
                faculties.add(faculty);
                log.trace("Add to list faculty: " + faculty);
            }
        } catch (SQLException e) {
            log.error("Error while finding of list of faculties!", e);
            throw new DAOException("Error while finding of list of faculties!", e);
        }
        log.info("List of " + faculties.size() + " faculties was found");
        log.trace("Exited findAll() method");
        return faculties;
    }

    @Override
    public Faculty findOne(Integer id) {
        log.trace("Enered findOne() method with argument id = {}", id);
        Faculty faculty = null;
        final String sql = "SELECT * FROM faculty WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement");
            statement.setInt(1, id);
            log.trace("Set param id={} into statment for query", id);
            try (ResultSet resultSet = statement.executeQuery()) {
                log.trace("Created result set");
                if (resultSet.next()) {
                    faculty = new Faculty();
                    faculty.setId(resultSet.getInt(1));
                    log.trace("Set id of faculty = {} from database", faculty.getId());
                    faculty.setName(resultSet.getString(2));
                    log.trace("Set name of faculty = {} from database", faculty.getName());
                }
            }
        } catch (SQLException e) {
            log.error("Error while finding of faculty!", e);
            throw new DAOException("Error while finding of faculty!", e);
        }
        log.info("Faculty " + faculty + " was found");
        log.trace("Exited findOne() method");
        return faculty;
    }

    @Override
    public Faculty create(Faculty faculty) {
        log.trace("Enered create() method with argument faculty = {}", faculty);
        final String sql = "INSERT INTO faculty (name) VALUES (?)";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            log.trace("Created prepared statement");
            statement.setString(1, faculty.getName());
            log.trace("Set name of faculty = {}", faculty.getName());
            statement.executeUpdate();
            log.debug("Inserted faculty={}", faculty);
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                log.trace("Created result set");
                if (resultSet.next()) {
                    log.debug("Return generated key from database");
                    faculty.setId(resultSet.getInt(1));
                    log.trace("Set generated key={} from database", faculty.getId());
                }
            }
        } catch (SQLException e) {
            log.error("Error while creating of faculty!", e);
            throw new DAOException("Error while creating of faculty!", e);
        }
        log.info("Faculty: " + faculty + " was created");
        log.trace("Exited create() method");
        return faculty;
    }

    @Override
    public void remove(Integer id) {
        log.trace("Enered remove() with argument id = {}", id);
        final String sql = "DELETE FROM faculty WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement");
            statement.setInt(1, id);
            log.trace("Set param id={} into statment for query", id);
            statement.executeUpdate();
            log.debug("Removed faculty with id={}", id);
        } catch (SQLException e) {
            log.error("Error while removing of faculty!", e);
            throw new DAOException("Error while removing of faculty!", e);
        }
        log.info("Faculty with id=" + id + " was removed");
        log.trace("Exited remove() method");
    }

    @Override
    public void update(Faculty faculty) {
        log.trace("Enered update() method with argument faculty = {}", faculty);
        final String sql = "UPDATE faculty SET name=? WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement");
            statement.setString(1, faculty.getName());
            log.trace("Set name of faculty = {}", faculty.getName());
            statement.setInt(2, faculty.getId());
            log.trace("Set id of faculty = {}", faculty.getId());
            statement.executeUpdate();
            log.debug("Updated faculty={}", faculty);
        } catch (SQLException e) {
            log.error("Error while updating of faculty!", e);
            throw new DAOException("Error while updating of faculty!", e);
        }
        log.info("Faculty after update: " + faculty);
        log.trace("Exited update() method");
    }
}
