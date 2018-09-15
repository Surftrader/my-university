package com.poseal.university.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.poseal.university.exception.DAOException;

public class PostgreConnectionUtils {

    private static final Logger log = LogManager.getLogger(PostgreConnectionUtils.class);

    private static ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    public static Connection getConnection() {
        Connection connection = null;
        DataSource dataSource = ((DataSource) context.getBean("dataSource"));

        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            log.error("Error while creating connection to database!", e);
            throw new DAOException("Error while creating connection to database!", e);
        }
        return connection;
    }
}
