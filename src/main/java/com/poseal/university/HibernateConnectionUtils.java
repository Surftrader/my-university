package com.poseal.university;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.poseal.university.exception.HibernateConnectionException;

public class HibernateConnectionUtils {
    private static final Logger log = LogManager.getLogger(HibernateConnectionUtils.class);
    private static SessionFactory sessionFactory;

    public static Session getSession() {

        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException e) {
            log.trace("Session factory couldn`t be created.", e);
            throw new HibernateConnectionException("Session factory couldn`t be created.", e);
        }

        return sessionFactory.getCurrentSession();
    }
}
