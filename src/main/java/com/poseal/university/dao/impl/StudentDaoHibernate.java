package com.poseal.university.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.poseal.university.HibernateConnectionUtils;
import com.poseal.university.dao.StudentDao;
import com.poseal.university.exception.DAOException;
import com.poseal.university.model.Group;
import com.poseal.university.model.Student;

public class StudentDaoHibernate implements StudentDao {

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        Transaction transaction;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            students = session.createQuery("from Student").list();
            transaction.commit();

        } catch (HibernateException e) {
            throw new DAOException("Error while finding of list of students!", e);
        }
        return students;
    }

    @Override
    public Student findOne(Integer id) {
        Student student;
        Transaction transaction;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            student = session.get(Student.class, id);
            transaction.commit();

        } catch (HibernateException e) {
            throw new DAOException("Error while finding of student!", e);
        }
        return student;
    }

    @Override
    public Student create(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            throw new DAOException("Error while creating of student!", e);
        }
        return student;
    }

    @Override
    public void remove(Integer id) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            session.delete(session.get(Student.class, id));
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            throw new DAOException("Error while removing of student!", e);
        }
    }

    @Override
    public void update(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            throw new DAOException("Error while updating of student!", e);
        }
    }

    @Override
    public List<Student> findAll(Group group) {
        List<Student> students = new ArrayList<>();
        Transaction transaction;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            Query<Student> query = session.createQuery("from Student S where S.group.id = :group_id");
            query.setParameter("group_id", group.getId());
            students = query.list();
            transaction.commit();

        } catch (HibernateException e) {
            throw new DAOException("Error while finding of list of students!", e);
        }
        return students;
    }
}
