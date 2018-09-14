package com.poseal.university.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.poseal.university.dao.PostgreConnectionUtils;
import com.poseal.university.dao.TeacherDao;
import com.poseal.university.model.Teacher;

public class TeacherDaoImpl implements TeacherDao {

    @Override
    public List<Teacher> findAll() {
        List<Teacher> teachers = new ArrayList<>();
        final String sql = "SELECT * FROM teacher";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(resultSet.getInt(1));
                teacher.setName(resultSet.getString(2));
                teacher.setSurname(resultSet.getString(3));
                teacher.setSubjectId(resultSet.getInt(4));
                teacher.setDepartmentId(resultSet.getInt(5));
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    @Override
    public Teacher findOne(Integer id) {
        Teacher teacher = null;
        final String sql = "SELECT * FROM teacher WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    teacher = new Teacher();
                    teacher.setId(resultSet.getInt(1));
                    teacher.setName(resultSet.getString(2));
                    teacher.setSurname(resultSet.getString(3));
                    teacher.setSubjectId(resultSet.getInt(4));
                    teacher.setDepartmentId(resultSet.getInt(5));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }

    @Override
    public Teacher create(Teacher teacher) {
        final String sql = "INSERT INTO teacher(name, surname, subject_id, department_id) VALUES (?, ?, ?, ?)";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, teacher.getName());
            statement.setString(2, teacher.getSurname());
            statement.setInt(3, teacher.getSubjectId());
            statement.setInt(4, teacher.getDepartmentId());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    teacher.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }

    @Override
    public void remove(Integer id) {
        final String sql = "DELETE FROM teacher WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Teacher teacher) {
        final String sql = "UPDATE teacher SET name=?, surname=?, subject_id=?, department_id=? WHERE id=?";
        try (Connection connection = PostgreConnectionUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, teacher.getName());
            statement.setString(2, teacher.getSurname());
            statement.setInt(3, teacher.getSubjectId());
            statement.setInt(4, teacher.getDepartmentId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
