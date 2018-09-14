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

import com.poseal.university.dao.GroupDao;
import com.poseal.university.dao.PostgreConnectionUtils;
import com.poseal.university.exception.DAOException;
import com.poseal.university.model.Group;

public class GroupDaoImpl implements GroupDao {

    private static final Logger log = LogManager.getLogger(GroupDaoImpl.class);

    @Override
    public List<Group> findAll() {
        log.trace("Entered findAll() method");
        List<Group> groups = new ArrayList<>();
        final String sql = "SELECT * FROM u_group";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            log.trace("Created prepared statement and result set");
            while (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt(1));
                log.trace("Set id of group = {} from database", group.getId());
                group.setName(resultSet.getString(2));
                log.trace("Set name of group = {} from database", group.getName());
                group.setFacultyId(resultSet.getInt(3));
                log.trace("Set faculty of group = {} from database", group.getFacultyId());
                groups.add(group);
                log.trace("Add to list group: " + group);
            }
        } catch (SQLException e) {
            log.error("Error while finding of list of groups!", e);
            throw new DAOException("Error while finding of list of groups!", e);
        }
        log.info("List of " + groups.size() + " groups was found");
        log.trace("Exited findAll() method");
        return groups;
    }

    @Override
    public Group findOne(Integer id) {
        log.trace("Enered findOne() method with argument id = {}", id);
        Group group = null;
        final String sql = "SELECT * FROM u_group WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement");
            statement.setInt(1, id);
            log.trace("Set param id={} into statment for query", id);
            try (ResultSet resultSet = statement.executeQuery()) {
                log.trace("Created result set");
                if (resultSet.next()) {
                    group = new Group();
                    group.setId(resultSet.getInt(1));
                    log.trace("Set id of group = {} from database", group.getId());
                    group.setName(resultSet.getString(2));
                    log.trace("Set name of group = {} from database", group.getName());
                    group.setFacultyId(resultSet.getInt(3));
                    log.trace("Set faculty of group = {} from database", group.getFacultyId());
                }
            }
        } catch (SQLException e) {
            log.error("Error while finding of group!", e);
            throw new DAOException("Error while finding of group!", e);
        }
        log.info("Group " + group + " was found");
        log.trace("Exited findOne() method");
        return group;
    }

    @Override
    public Group create(Group group) {
        log.trace("Enered create() method with argument group = {}", group);
        final String sql = "INSERT INTO u_group (name, faculty_id) VALUES (?, ?)";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            log.trace("Created prepared statement");
            statement.setString(1, group.getName());
            log.trace("Set name of group = {}", group.getName());
            statement.setInt(2, group.getFacultyId());
            log.trace("Set faculty of group = {}", group.getFacultyId());
            statement.executeUpdate();
            log.debug("Inserted group={}", group);
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                log.trace("Created result set");
                if (resultSet.next()) {
                    log.debug("Return generated key from database");
                    group.setId(resultSet.getInt(1));
                    log.trace("Set generated key={} from database", group.getId());
                }
            }
        } catch (SQLException e) {
            log.error("Error while creating of group!", e);
            throw new DAOException("Error while creating of group!", e);
        }
        log.info("Group: " + group + " was created");
        log.trace("Exited create() method");
        return group;
    }

    @Override
    public void remove(Integer id) {
        log.trace("Enered remove() with argument id = {}", id);
        final String sql = "DELETE FROM u_group WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement");
            statement.setInt(1, id);
            log.trace("Set param id={} into statment for query", id);
            statement.executeUpdate();
            log.debug("Removed group with id={}", id);
        } catch (SQLException e) {
            log.error("Error while removing of group!", e);
            throw new DAOException("Error while removing of group!", e);
        }
        log.info("Group with id=" + id + " was removed");
        log.trace("Exited remove() method");
    }

    @Override
    public void update(Group group) {
        log.trace("Enered update() method with argument group = {}", group);
        final String sql = "UPDATE u_group SET name=?, faculty_id=? WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement");
            statement.setString(1, group.getName());
            log.trace("Set name of group = {}", group.getName());
            statement.setInt(2, group.getFacultyId());
            log.trace("Set faculty of group = {}", group.getFacultyId());
            statement.setInt(3, group.getId());
            log.trace("Set id of group = {}", group.getId());
            statement.executeUpdate();
            log.debug("Updated group={}", group);
        } catch (SQLException e) {
            log.error("Error while updating of group!", e);
            throw new DAOException("Error while updating of group!", e);
        }
        log.info("Group after update: " + group);
        log.trace("Exited update() method");
    }
}
