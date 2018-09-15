package com.poseal.university.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.poseal.university.HibernateConnectionUtils;
import com.poseal.university.dao.DepartmentDao;
import com.poseal.university.exception.DAOException;
import com.poseal.university.model.Department;
import com.poseal.university.model.Faculty;

public class DepartmentDaoHibernate implements DepartmentDao {

    @Override
    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();
        Transaction transaction;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            departments = session.createQuery("from Department").list();
            transaction.commit();

        } catch (HibernateException e) {
            throw new DAOException("Error while finding of list of departments!", e);
        }
        return departments;
    }

    @Override
    public Department findOne(Integer id) {
        Department department;
        Transaction transaction;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            department = session.get(Department.class, id);
            transaction.commit();

        } catch (HibernateException e) {
            throw new DAOException("Error while finding of department!", e);
        }
        return department;
    }

    @Override
    public Department create(Department department) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            session.save(department);
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            throw new DAOException("Error while creating of department!", e);
        }
        return department;
    }

    @Override
    public void remove(Integer id) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            session.delete(session.get(Department.class, id));
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            throw new DAOException("Error while removing of department!", e);
        }
    }

    @Override
    public void update(Department department) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            session.update(department);
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            throw new DAOException("Error while updating of department!", e);
        }
    }

    @Override
    public List<Department> findAll(Faculty faculty) {
        List<Department> departments = new ArrayList<>();
        Transaction transaction;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            Query<Department> query = session.createQuery("from Department D where D.faculty.id = :faculty_id");
            query.setParameter("faculty_id", faculty.getId());
            departments = query.list();
            transaction.commit();

        } catch (HibernateException e) {
            throw new DAOException("Error while finding of list of departments!", e);
        }
        return departments;
    }
}
