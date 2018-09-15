package com.poseal.university.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.poseal.university.HibernateConnectionUtils;
import com.poseal.university.dao.GroupDao;
import com.poseal.university.exception.DAOException;
import com.poseal.university.model.Faculty;
import com.poseal.university.model.Group;

public class GroupDaoHibernate implements GroupDao {

    @Override
    public List<Group> findAll() {
        List<Group> groups = new ArrayList<>();
        Transaction transaction;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            groups = session.createQuery("from Group").list();
            transaction.commit();

        } catch (HibernateException e) {
            throw new DAOException("Error while finding of list of groups!", e);
        }
        return groups;
    }

    @Override
    public Group findOne(Integer id) {
        Group group;
        Transaction transaction;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            group = session.get(Group.class, id);
            transaction.commit();

        } catch (HibernateException e) {
            throw new DAOException("Error while finding of group!", e);
        }
        return group;
    }

    @Override
    public Group create(Group group) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            session.save(group);
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            throw new DAOException("Error while creating of group!", e);
        }
        return group;
    }

    @Override
    public void remove(Integer id) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            session.delete(session.get(Group.class, id));
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            throw new DAOException("Error while removing of group!", e);
        }
    }

    @Override
    public void update(Group group) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            session.update(group);
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            throw new DAOException("Error while updating of group!", e);
        }
    }

    @Override
    public List<Group> findAll(Faculty faculty) {
        List<Group> groups = new ArrayList<>();
        Transaction transaction;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            Query<Group> query = session.createQuery("from Group G where G.faculty.id = :faculty_id");
            query.setParameter("faculty_id", faculty.getId());
            groups = query.list();
            transaction.commit();

        } catch (HibernateException e) {
            throw new DAOException("Error while finding of list of groups!", e);
        }
        return groups;
    }
}
