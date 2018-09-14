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

import com.poseal.university.dao.DepartmentDao;
import com.poseal.university.dao.PostgreConnectionUtils;
import com.poseal.university.exception.DAOException;
import com.poseal.university.model.Department;

public class DepartmentDaoImpl implements DepartmentDao {

    private static final Logger log = LogManager.getLogger(DepartmentDaoImpl.class);

    @Override
    public List<Department> findAll() {
        log.trace("Entered findAll() method");
        List<Department> departments = new ArrayList<>();
        final String sql = "SELECT * FROM department";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            log.trace("Created prepared statement and result set");
            while (resultSet.next()) {
                Department department = new Department();
                department.setId(resultSet.getInt(1));
                log.trace("Set id of department = {} from database", department.getId());
                department.setName(resultSet.getString(2));
                log.trace("Set name of department = {} from database", department.getName());
                department.setFacultyId(resultSet.getInt(3));
                log.trace("Set faculty of department = {} from database", department.getFacultyId());
                departments.add(department);
                log.trace("Add to list department: " + department);
            }
        } catch (SQLException e) {
            log.error("Error while finding of list of departments!", e);
            throw new DAOException("Error while finding of list of departments!", e);
        }
        log.info("List of " + departments.size() + " departments was found");
        log.trace("Exited findAll() method");
        return departments;
    }

    @Override
    public Department findOne(Integer id) {
        log.trace("Enered findOne() method with argument id = {}", id);
        Department department = null;
        final String sql = "SELECT * FROM department WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement");
            statement.setInt(1, id);
            log.trace("Set param id={} into statment for query", id);
            try (ResultSet resultSet = statement.executeQuery()) {
                log.trace("Created result set");
                if (resultSet.next()) {
                    department = new Department();
                    department.setId(resultSet.getInt(1));
                    log.trace("Set id of department = {} from database", department.getId());
                    department.setName(resultSet.getString(2));
                    log.trace("Set name of department = {} from database", department.getName());
                    department.setFacultyId(resultSet.getInt(3));
                    log.trace("Set faculty of department = {} from database", department.getFacultyId());
                }
            }
        } catch (SQLException e) {
            log.error("Error while finding of department!", e);
            throw new DAOException("Error while finding of department!", e);
        }
        log.info("Department " + department + " was found");
        log.trace("Exited findOne() method");
        return department;
    }

    @Override
    public Department create(Department department) {
        log.trace("Enered create() method with argument department = {}", department);
        final String sql = "INSERT INTO department (name, faculty_id) VALUES (?,?)";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            log.trace("Created prepared statement");
            statement.setString(1, department.getName());
            log.trace("Set name of department = {}", department.getName());
            statement.setInt(2, department.getFacultyId());
            log.trace("Set faculty of department = {}", department.getFacultyId());
            statement.executeUpdate();
            log.debug("Inserted department = {}", department);
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                log.trace("Created result set");
                if (resultSet.next()) {
                    log.debug("Return generated key from database");
                    department.setId(resultSet.getInt(1));
                    log.trace("Set generated key={} from database", department.getId());
                }
            }
        } catch (SQLException e) {
            log.error("Error while creating of department!", e);
            throw new DAOException("Error while creating of department!", e);
        }
        log.info("Department: " + department + " was created");
        log.trace("Exited create() method");
        return department;
    }

    @Override
    public void remove(Integer id) {
        log.trace("Enered remove() with argument id = {}", id);
        final String sql = "DELETE FROM department WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement");
            statement.setInt(1, id);
            log.trace("Set param id={} into statment for query", id);
            statement.executeUpdate();
            log.debug("Removed department with id={}", id);
        } catch (SQLException e) {
            log.error("Error while removing of department!", e);
            throw new DAOException("Error while removing of department!", e);
        }
        log.info("Department with id=" + id + " was removed");
        log.trace("Exited remove() method");
    }

    @Override
    public void update(Department department) {
        log.trace("Enered update() method with argument department = {}", department);
        final String sql = "UPDATE department SET name=?, faculty_id=? WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement");
            statement.setString(1, department.getName());
            log.trace("Set name of department = {}", department.getName());
            statement.setInt(2, department.getFacultyId());
            log.trace("Set faculty of department = {}", department.getFacultyId());
            statement.setInt(3, department.getId());
            log.trace("Set id of department = {}", department.getId());
            statement.executeUpdate();
            log.debug("Updated department = {}", department);
        } catch (SQLException e) {
            log.error("Error while updating of department!", e);
            throw new DAOException("Error while updating of department!", e);
        }
        log.info("Department after update: " + department);
        log.trace("Exited update() method");
    }
}
