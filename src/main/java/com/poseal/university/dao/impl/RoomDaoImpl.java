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
import com.poseal.university.dao.RoomDao;
import com.poseal.university.exception.DAOException;
import com.poseal.university.model.Department;
import com.poseal.university.model.Room;

public class RoomDaoImpl implements RoomDao {

    private static final Logger log = LogManager.getLogger(RoomDaoImpl.class);

    @Override
    public List<Room> findAll() {
        log.trace("Entered findAll() method");
        List<Room> rooms = new ArrayList<>();
        final String sql = "SELECT * FROM room";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            log.trace("Created prepared statement and result set");
            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getInt(1));
                log.trace("Set id of room = {} from database", room.getId());
                room.setNumber(resultSet.getInt(2));
                log.trace("Set id of room = {} from database", room.getNumber());
                room.setCapacity(resultSet.getInt(3));
                log.trace("Set id of room = {} from database", room.getCapacity());
                room.setDepartmentId(resultSet.getInt(4));
                log.trace("Set id of room = {} from database", room.getDepartmentId());
                rooms.add(room);
                log.trace("Add to list room: " + room);
            }
        } catch (SQLException e) {
            log.error("Error while finding of list of rooms!", e);
            throw new DAOException("Error while finding of list of rooms!", e);
        }
        log.info("List of " + rooms.size() + " rooms was found");
        log.trace("Exited findAll() method");
        return rooms;
    }

    @Override
    public Room findOne(Integer id) {
        log.trace("Enered findOne() method with argument id = {}", id);
        Room room = null;
        final String sql = "SELECT * FROM room WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement");
            statement.setInt(1, id);
            log.trace("Set param id={} into statment for query", id);
            try (ResultSet resultSet = statement.executeQuery()) {
                log.trace("Created result set");
                if (resultSet.next()) {
                    room = new Room();
                    room.setId(resultSet.getInt(1));
                    log.trace("Set id of room = {} from database", room.getId());
                    room.setNumber(resultSet.getInt(2));
                    log.trace("Set id of room = {} from database", room.getNumber());
                    room.setCapacity(resultSet.getInt(3));
                    log.trace("Set id of room = {} from database", room.getCapacity());
                    room.setDepartmentId(resultSet.getInt(4));
                    log.trace("Set id of room = {} from database", room.getDepartmentId());
                }
            }
        } catch (SQLException e) {
            log.error("Error while finding of room!", e);
            throw new DAOException("Error while finding of room!", e);
        }
        log.info("Room " + room + " was found");
        log.trace("Exited findOne() method");
        return room;
    }

    @Override
    public Room create(Room room) {
        log.trace("Enered create() method with argument room = {}", room);
        final String sql = "INSERT INTO room (number, capacity, department_id) VALUES (?, ?, ?)";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            log.trace("Created prepared statement");
            statement.setInt(1, room.getNumber());
            log.trace("Set name of room = {}", room.getNumber());
            statement.setInt(2, room.getCapacity());
            log.trace("Set name of room = {}", room.getCapacity());
            statement.setInt(3, room.getDepartmentId());
            log.trace("Set name of room = {}", room.getDepartmentId());
            statement.executeUpdate();
            log.debug("Inserted room={}", room);
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                log.trace("Created result set");
                if (resultSet.next()) {
                    log.debug("Return generated key from database");
                    room.setId(resultSet.getInt(1));
                    log.trace("Set generated key={} from database", room.getId());
                }
            }
        } catch (SQLException e) {
            log.error("Error while creating of room!", e);
            throw new DAOException("Error while creating of room!", e);
        }
        log.info("Room: " + room + " was created");
        log.trace("Exited create() method");
        return room;
    }

    @Override
    public void remove(Integer id) {
        log.trace("Enered remove() with argument id = {}", id);
        final String sql = "DELETE FROM room WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement");
            statement.setInt(1, id);
            log.trace("Set param id={} into statment for query", id);
            statement.executeUpdate();
            log.debug("Removed room with id={}", id);
        } catch (SQLException e) {
            log.error("Error while removing of room!", e);
            throw new DAOException("Error while removing of room!", e);
        }
        log.info("Room with id=" + id + " was removed");
        log.trace("Exited remove() method");
    }

    @Override
    public void update(Room room) {
        log.trace("Enered update() method with argument room = {}", room);
        final String sql = "UPDATE room SET number=?, capacity=?, department_id=? WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement");
            statement.setInt(1, room.getNumber());
            log.trace("Set name of room = {}", room.getNumber());
            statement.setInt(2, room.getCapacity());
            log.trace("Set name of room = {}", room.getCapacity());
            statement.setInt(3, room.getDepartmentId());
            log.trace("Set name of room = {}", room.getDepartmentId());
            statement.setInt(4, room.getId());
            log.trace("Set id of room = {}", room.getId());
            statement.executeUpdate();
            log.debug("Updated room={}", room);
        } catch (SQLException e) {
            log.error("Error while updating of room!", e);
            throw new DAOException("Error while updating of room!", e);
        }
        log.info("Room after update: " + room);
        log.trace("Exited update() method");
    }

    @Override
    public List<Room> findByDepartment(Department department) {
        log.trace("Entered findByDepartment() method with agument department = {}", department);
        List<Room> rooms = new ArrayList<>();
        final String sql = "SELECT * FROM room WHERE department_id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Created prepared statement and result set");
            statement.setInt(1, department.getId());
            log.trace("Set param id={} into statment for query", department.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Room room = new Room();
                    room.setId(resultSet.getInt(1));
                    log.trace("Set id of room = {} from database", room.getId());
                    room.setNumber(resultSet.getInt(2));
                    log.trace("Set id of room = {} from database", room.getNumber());
                    room.setCapacity(resultSet.getInt(3));
                    log.trace("Set id of room = {} from database", room.getCapacity());
                    room.setDepartmentId(resultSet.getInt(4));
                    log.trace("Set id of room = {} from database", room.getDepartmentId());
                    rooms.add(room);
                    log.trace("Add to list room: " + room);
                }
            }
        } catch (SQLException e) {
            log.error("Error while finding of list of rooms!", e);
            throw new DAOException("Error while finding of list of rooms!", e);
        }
        log.info("List of " + rooms.size() + " rooms was found");
        log.trace("Exited findByDepartment() method");
        return rooms;
    }
}
