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

public class TourLogDao implements Dao<TourLogs>{

    private List<TourLogs> tourLogs = new ArrayList<>();
    // The class has a SessionFactory named tourLogFactory which is used to manage Hibernate sessions.
    // The SessionFactory is obtained from the HibernateUtil class, which is responsible for
    // initializing and providing the Hibernate session factory.
    private static SessionFactory tourLogFactory;
    // HibernateUtil is a utility class that provides access to the Hibernate session factory.
    // The getSessionFactory() method returns an instance of SessionFactory,
    // which is responsible for creating and managing Hibernate sessions.
    public TourLogDao(){
        tourLogFactory = HibernateUtil.getSessionFactory();
    }


    @Override
    public Optional<TourLogs> get(int id) {
        Session session = tourLogFactory.openSession();
        Transaction tx = session.beginTransaction();
        TourLogs tourLogs = null;
        try {
            tourLogs = session.get(TourLogs.class, id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
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
            tourLogs = session.createQuery("from TourLogs", TourLogs.class).getResultList();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
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
            session.save(tourLogs);
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
    public void update(TourLogs tourLogs) {
        Session session = tourLogFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(tourLogs);
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
    public void delete(TourLogs tourLogs) {
        Session session = tourLogFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(tourLogs);
            tx.commit();
        } catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        session.close();
    }
    public List<TourLogs> getAllFromTour(int tourID) {
        Session session = tourLogFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<TourLogs> tourLogs = new ArrayList<>();

        try {
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
            e.printStackTrace();
        } finally {
            session.close();
        }
        return tourLogs;
    }
}
