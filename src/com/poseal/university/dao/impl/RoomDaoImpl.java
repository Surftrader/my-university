package com.poseal.university.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.poseal.university.dao.PostgreConnectionUtils;
import com.poseal.university.dao.RoomDao;
import com.poseal.university.model.Room;

public class RoomDaoImpl implements RoomDao {

    @Override
    public List<Room> findAll() {
        List<Room> rooms = new ArrayList<>();
        final String sql = "SELECT * FROM room";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getInt(1));
                room.setNumber(resultSet.getInt(2));
                room.setCapacity(resultSet.getInt(3));
                room.setDepartmentId(resultSet.getInt(4));
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public Room findOne(Integer id) {
        Room room = null;
        final String sql = "SELECT * FROM room WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    room = new Room();
                    room.setId(resultSet.getInt(1));
                    room.setNumber(resultSet.getInt(2));
                    room.setCapacity(resultSet.getInt(3));
                    room.setDepartmentId(resultSet.getInt(4));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    @Override
    public Room create(Room room) {
        final String sql = "INSERT INTO room (number, capacity, department_id) VALUES (?, ?, ?)";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, room.getNumber());
            statement.setInt(2, room.getCapacity());
            statement.setInt(3, room.getDepartmentId());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    room.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    @Override
    public void remove(Integer id) {
        final String sql = "DELETE FROM room WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Room room) {
        final String sql = "UPDATE room SET number=?, capacity=?, department_id=? WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, room.getNumber());
            statement.setInt(2, room.getCapacity());
            statement.setInt(3, room.getDepartmentId());
            statement.setInt(4, room.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
