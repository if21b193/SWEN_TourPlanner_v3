package com.example.tourplanner.DAL.dal.dao;

import com.example.tourplanner.DAL.dal.config.HibernateUtil;
import com.example.tourplanner.models.Tour;
import com.example.tourplanner.models.TourLogs;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class TourLogDao implements Dao<TourLogs>{

    // The class has a SessionFactory named tourLogFactory which is used to manage Hibernate sessions.
    // The SessionFactory is obtained from the HibernateUtil class, which is responsible for
    // initializing and providing the Hibernate session factory.
    private static SessionFactory tourLogFactory;
    // HibernateUtil is a utility class that provides access to the Hibernate session factory.
    // The getSessionFactory() method returns an instance of SessionFactory,
    // which is responsible for creating and managing Hibernate sessions.
    private static final Logger logger = LogManager.getLogger(TourLogDao.class);

    public TourLogDao(){
        tourLogFactory = HibernateUtil.getSessionFactory();
    }


    @Override
    public Optional<TourLogs> get(int id) {
        Session session = tourLogFactory.openSession();
        Transaction tx = session.beginTransaction();
        TourLogs tourLogs = null;
        try {
            logger.info("Getting tour log with id {}", id);
            tourLogs = session.get(TourLogs.class, id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.error("Error getting tour log with id {}", id, e);
        }
        session.close();
        return Optional.of(tourLogs);
    }

    @Override
    public List<TourLogs> getAll() {
        Session session = tourLogFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<TourLogs> tourLogs = null;
        try {
            logger.info("Getting all tour logs");
            tourLogs = session.createQuery("from TourLogs", TourLogs.class).getResultList();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.error("Error getting all tour logs", e);
        }
        session.close();

        return tourLogs;
    }
    //
    @Override
    public void save(TourLogs tourLogs) {
        Session session = tourLogFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            logger.info("Saving tour log with id {}", tourLogs.getId());
            session.save(tourLogs);
            tx.commit();
        } catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            logger.error("Error saving tour log");
        }
        session.close();
    }

    @Override
    public void update(TourLogs tourLogs) {
        Session session = tourLogFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            logger.info("Updating tour log with id {}", tourLogs.getId());
            session.update(tourLogs);
            tx.commit();
        } catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            logger.error("Error updating tour log with id {}", tourLogs.getId(), e);
        }
        session.close();
    }

    @Override
    public void delete(TourLogs tourLogs) {
        Session session = tourLogFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            logger.info("Deleting tour log with id {}", tourLogs.getId());
            session.delete(tourLogs);
            tx.commit();
        } catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            logger.error("Error deleting tour log with id {}", tourLogs.getId(), e);
        }
        session.close();
    }
    public List<TourLogs> getAllFromTour(int tourID) {
        Session session = tourLogFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<TourLogs> tourLogs = new ArrayList<>();

        try {
            logger.info("Getting all tour logs for tour id {}", tourID);
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<TourLogs> criteriaQuery = builder.createQuery(TourLogs.class);
            Root<TourLogs> root = criteriaQuery.from(TourLogs.class);
            Join<TourLogs, Tour> tourJoin = root.join("tour_id");

            Predicate tourIdPredicate = builder.equal(tourJoin.get("id"), tourID);
            criteriaQuery.where(tourIdPredicate);

            tourLogs = session.createQuery(criteriaQuery).getResultList();
            tx.commit();
        } catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            logger.error("Error getting all tour logs for tour id {}", tourID, e);
        } finally {
            session.close();
        }
        return tourLogs;
    }
}
