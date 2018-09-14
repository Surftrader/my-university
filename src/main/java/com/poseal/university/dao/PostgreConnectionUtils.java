package com.poseal.university.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.poseal.university.exception.DAOException;

public class PostgreConnectionUtils {

    private static final Logger log = LogManager.getLogger(PostgreConnectionUtils.class);
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/my_university_db";
    private static final String USERNAME = "rector";
    private static final String PASSWORD = "rector";

    public static Connection getConnection() {
        Connection connection = null;
        log.debug("Creating connection to database");
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            log.debug("Connection opened");
        } catch (ClassNotFoundException | SQLException e) {
            log.error("Error while creating connection to database!", e);
            throw new DAOException("Error while creating connection to database!", e);
        }
        return connection;
    }
}
