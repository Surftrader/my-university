package com.poseal.university.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.poseal.university.HibernateConnectionUtils;
import com.poseal.university.dao.SubjectDao;
import com.poseal.university.exception.DAOException;
import com.poseal.university.model.Subject;

public class SubjectDaoHibernate implements SubjectDao {

    @Override
    public List<Subject> findAll() {
        List<Subject> subjects = new ArrayList<>();
        Transaction transaction;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            subjects = session.createQuery("from Subject").list();
            transaction.commit();

        } catch (HibernateException e) {
            throw new DAOException("Error while finding of list of subjects!", e);
        }
        return subjects;
    }

    @Override
    public Subject findOne(Integer id) {
        Subject subject;
        Transaction transaction;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            subject = session.get(Subject.class, id);
            transaction.commit();

        } catch (HibernateException e) {
            throw new DAOException("Error while finding of subject!", e);
        }
        return subject;
    }

    @Override
    public Subject create(Subject subject) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            session.save(subject);
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            throw new DAOException("Error while creating of subject!", e);
        }
        return subject;
    }

    @Override
    public void remove(Integer id) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            session.delete(session.get(Subject.class, id));
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            throw new DAOException("Error while removing of subject!", e);
        }
    }

    @Override
    public void update(Subject subject) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            session.update(subject);
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            throw new DAOException("Error while updating of subject!", e);
        }
    }
}
