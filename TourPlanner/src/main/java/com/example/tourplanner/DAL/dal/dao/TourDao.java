package com.example.tourplanner.DAL.dal.dao;

import com.example.tourplanner.DAL.dal.config.HibernateUtil;
import com.example.tourplanner.models.Tour;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class TourDao implements Dao<Tour>{

    private static SessionFactory tourFactory;
    private static final Logger logger = LogManager.getLogger(TourDao.class);

    public TourDao(){
       tourFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public Optional<Tour> get(int id) {
        Session session = tourFactory.openSession();
        Transaction tx = session.beginTransaction();
        Tour tour = null;
        try {
            logger.info("Getting tour with id {}", id);
            tour = session.get(Tour.class, id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.error("Error getting tour with id {}", id, e);
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
            logger.info("Getting all tours");
            tours = session.createQuery("from Tour", Tour.class).getResultList();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.error("Error getting all tours", e);
        }
        session.close();

        return tours;
    }

    @Override
    public void save(Tour tour) {
        Session session = tourFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            logger.info("Saving tour with id {}", tour.getId());
            session.save(tour);
            tx.commit();
        } catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            logger.error("Error saving tour with id {}", tour.getId(), e);
        }
        session.close();
    }

    @Override
    public void update(Tour tour) {
        Session session = tourFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            logger.info("Updating tour with id {}", tour.getId());
            session.update(tour);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
            logger.error("Error updating tour with id {}", tour.getId(), e);
        }
        session.close();
    }

    @Override
    public void delete(Tour tour) {
        Session session = tourFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            logger.info("Deleting tour with id {}", tour.getId());
            session.delete(tour);
            tx.commit();
        } catch(Exception e) {
            if(tx != null) {
                tx.rollback();
            }
            logger.error("Error deleting tour with id {}", tour.getId(), e);
        } finally {
            session.close();
        }
    }
}
