package com.example.tourplanner.DAL.dal;

import com.example.tourplanner.DAL.dal.config.DataSource;
import com.example.tourplanner.DAL.dal.config.DbConnector;
import com.example.tourplanner.DAL.dal.config.HibernateUtil;
import com.example.tourplanner.models.Tour;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TourDao implements Dao<Tour>{

    private static SessionFactory tourFactory;

    public TourDao(){
       tourFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public Optional<Tour> get(int id) {
        Session session = tourFactory.openSession();
        Transaction tx = session.beginTransaction();
        Tour tour = null;
        try {
            tour = session.get(Tour.class, id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        session.close();
        return Optional.of(tour);
    }

    @Override
    public List<Tour> getAll() {
        Session session = tourFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Tour> tours = null;
        try {
            tours = session.createQuery("from Tour", Tour.class).getResultList();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        session.close();

        return tours;
    }

    @Override
    public void save(Tour tour) {
        Session session = tourFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(tour);
            tx.commit();
        } catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        session.close();
    }

    @Override
    public void update(Tour tour) {
        Session session = tourFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(tour);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        session.close();
    }

    @Override
    public void delete(Tour tour) {
        Session session = tourFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(tour);
            tx.commit();
        } catch(Exception e) {
            if(tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
