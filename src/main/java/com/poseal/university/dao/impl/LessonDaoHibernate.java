package com.poseal.university.dao.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.poseal.university.HibernateConnectionUtils;
import com.poseal.university.dao.LessonDao;
import com.poseal.university.exception.DAOException;
import com.poseal.university.model.Group;
import com.poseal.university.model.Lesson;
import com.poseal.university.model.Teacher;

public class LessonDaoHibernate implements LessonDao {

    @Override
    public List<Lesson> findAll() {
        List<Lesson> lessons = new ArrayList<>();
        Transaction transaction;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            lessons = session.createQuery("from Lesson").list();
            transaction.commit();

        } catch (HibernateException e) {
            throw new HibernateException("Error while finding of list of lessons!", e);
        }
        return lessons;
    }

    @Override
    public Lesson findOne(Integer id) {
        Lesson lesson;
        Transaction transaction;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            lesson = session.get(Lesson.class, id);
            transaction.commit();

        } catch (HibernateException e) {
            throw new DAOException("Error while finding of lesson!", e);
        }
        return lesson;
    }

    @Override
    public Lesson create(Lesson lesson) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            session.save(lesson);
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            throw new DAOException("Error while creating of lesson!", e);
        }
        return lesson;
    }

    @Override
    public void remove(Integer id) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            session.delete(session.get(Lesson.class, id));
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            throw new DAOException("Error while removing of lesson!", e);
        }
    }

    @Override
    public void update(Lesson lesson) {
        Transaction transaction = null;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            session.update(lesson);
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            throw new DAOException("Error while updating of lesson!", e);
        }
    }

    @Override
    public List<Lesson> findSchedule(Group group, LocalDateTime start, LocalDateTime end) {
        List<Lesson> lessons = new ArrayList<>();
        Transaction transaction;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            Query<Lesson> query = session.createQuery(
                    "from Lesson L where L.group.id = :group_id AND L.timeStart >= :time_start AND L.timeEnd <= :time_end");
            query.setParameter("group_id", group.getId());
            query.setParameter("time_start", start);
            query.setParameter("time_end", end);
            lessons = query.list();
            transaction.commit();

        } catch (HibernateException e) {
            throw new DAOException("Error while finding of list of lessons!", e);
        }
        return lessons;
    }

    @Override
    public List<Lesson> findSchedule(Teacher teacher, LocalDateTime start, LocalDateTime end) {
        List<Lesson> lessons = new ArrayList<>();
        Transaction transaction;
        try (Session session = HibernateConnectionUtils.getSession()) {

            transaction = session.beginTransaction();
            Query<Lesson> query = session.createQuery(
                    "from Lesson L where L.teacher.id = :teacher_id AND L.timeStart >= :time_start AND L.timeEnd <= :time_end");
            query.setParameter("teacher_id", teacher.getId());
            query.setParameter("time_start", start);
            query.setParameter("time_end", end);
            lessons = query.list();
            transaction.commit();

        } catch (HibernateException e) {
            throw new DAOException("Error while finding of list of lessons!", e);
        }
        return lessons;
    }
}
