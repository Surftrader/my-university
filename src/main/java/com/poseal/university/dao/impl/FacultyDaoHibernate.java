package com.poseal.university.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.poseal.university.HibernateConnectionUtils;
import com.poseal.university.dao.FacultyDao;
import com.poseal.university.exception.DAOException;
import com.poseal.university.model.Faculty;

public class FacultyDaoHibernate implements FacultyDao {

    @Override
    public List<Faculty> findAll() {
        List<Faculty> faculties = new ArrayList<>();
        Transaction transaction;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            faculties = session.createQuery("from Faculty").list();
            transaction.commit();

        } catch (HibernateException e) {
            throw new DAOException("Error while finding of list of faculties!", e);
        }
        return faculties;
    }

    @Override
    public Faculty findOne(Integer id) {
        Faculty faculty;
        Transaction transaction;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            faculty = session.get(Faculty.class, id);
            transaction.commit();

        } catch (HibernateException e) {
            throw new DAOException("Error while finding of faculty!", e);
        }
        return faculty;
    }

    @Override
    public Faculty create(Faculty faculty) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            session.save(faculty);
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            throw new DAOException("Error while creating of faculty!", e);
        }
        return faculty;
    }

    @Override
    public void remove(Integer id) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            session.delete(session.get(Faculty.class, id));
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            throw new DAOException("Error while removing of faculty!", e);
        }
    }

    @Override
    public void update(Faculty faculty) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            session.update(faculty);
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            throw new DAOException("Error while updating of faculty!", e);
        }
    }
}
