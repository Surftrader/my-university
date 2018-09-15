package com.poseal.university.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.poseal.university.HibernateConnectionUtils;
import com.poseal.university.dao.TeacherDao;
import com.poseal.university.exception.DAOException;
import com.poseal.university.model.Department;
import com.poseal.university.model.Subject;
import com.poseal.university.model.Teacher;

public class TeacherDaoHibernate implements TeacherDao {

    @Override
    public List<Teacher> findAll() {
        List<Teacher> teachers = new ArrayList<>();
        Transaction transaction;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            teachers = session.createQuery("from Teacher").list();
            transaction.commit();

        } catch (HibernateException e) {
            throw new DAOException("Error while finding of list of teachers!", e);
        }
        return teachers;
    }

    @Override
    public Teacher findOne(Integer id) {
        Teacher teacher;
        Transaction transaction;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            teacher = session.get(Teacher.class, id);
            transaction.commit();

        } catch (HibernateException e) {
            throw new DAOException("Error while finding of teacher!", e);
        }
        return teacher;
    }

    @Override
    public Teacher create(Teacher teacher) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            session.save(teacher);
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            throw new DAOException("Error while creating of teacher!", e);
        }
        return teacher;
    }

    @Override
    public void remove(Integer id) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            session.delete(session.get(Teacher.class, id));
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            throw new DAOException("Error while removing of teacher!", e);
        }
    }

    @Override
    public void update(Teacher teacher) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            session.update(teacher);
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            throw new DAOException("Error while updating of teacher!", e);
        }
    }

    @Override
    public List<Teacher> findAll(Department department) {
        List<Teacher> teachers = new ArrayList<>();
        Transaction transaction;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            Query<Teacher> query = session.createQuery("from Teacher T where T.department.id = :department_id");
            query.setParameter("department_id", department.getId());
            teachers = query.list();
            transaction.commit();

        } catch (HibernateException e) {
            throw new DAOException("Error while finding of list of teachers!", e);
        }
        return teachers;
    }

    @Override
    public Teacher findOne(Subject subject) {
        Teacher teacher;
        Transaction transaction;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            Query<Teacher> query = session.createQuery("from Teacher T where T.subject.id = :subject_id");
            query.setParameter("subject_id", subject.getId());
            teacher = query.uniqueResult();
            transaction.commit();

        } catch (HibernateException e) {
            throw new DAOException("Error while finding of teacher!", e);
        }
        return teacher;
    }
}
