package com.poseal.university.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.poseal.university.exception.DAOException;

public class PostgreConnectionUtils {

    private static final Logger log = LogManager.getLogger(PostgreConnectionUtils.class);

    private static final String DATASOURCE_NAME = "jdbc/my_university_db";
    private static DataSource dataSource;


    public static Connection getConnection() {
        Connection connection = null;
        log.debug("Creating connection to database");
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup(DATASOURCE_NAME);
            connection = dataSource.getConnection();
            log.debug("Connection opened");
        } catch (NamingException | SQLException e) {
            log.error("Error while creating connection to database!", e);
            throw new DAOException("Error while creating connection to database!", e);
        }
        return connection;
    }
}
