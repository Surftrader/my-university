package com.poseal.university.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.poseal.university.HibernateConnectionUtils;
import com.poseal.university.dao.RoomDao;
import com.poseal.university.exception.DAOException;
import com.poseal.university.model.Department;
import com.poseal.university.model.Room;

public class RoomDaoHibernate implements RoomDao {

    @Override
    public List<Room> findAll() {
        List<Room> rooms = new ArrayList<>();
        Transaction transaction;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            rooms = session.createQuery("from Room").list();
            transaction.commit();

        } catch (HibernateException e) {
            throw new DAOException("Error while finding of list of rooms!", e);
        }
        return rooms;
    }

    @Override
    public Room findOne(Integer id) {
        Room room;
        Transaction transaction;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            room = session.get(Room.class, id);
            transaction.commit();

        } catch (HibernateException e) {
            throw new DAOException("Error while finding of room!", e);
        }
        return room;
    }

    @Override
    public Room create(Room room) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            session.save(room);
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            throw new DAOException("Error while creating of room!", e);
        }
        return room;
    }

    @Override
    public void remove(Integer id) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            session.delete(session.get(Room.class, id));
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            throw new DAOException("Error while removing of room!", e);
        }
    }

    @Override
    public void update(Room room) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            session.update(room);
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            throw new DAOException("Error while updating of room!", e);
        }
    }

    @Override
    public List<Room> findAll(Department department) {
        List<Room> rooms = new ArrayList<>();
        Transaction transaction;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            Query<Room> query = session.createQuery("from Room R where R.department.id = :department_id");
            query.setParameter("department_id", department.getId());
            rooms = query.list();
            transaction.commit();

        } catch (HibernateException e) {
            throw new DAOException("Error while finding of list of rooms!", e);
        }
        return rooms;
    }
}
