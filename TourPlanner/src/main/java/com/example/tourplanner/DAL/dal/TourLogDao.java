package com.example.tourplanner.DAL.dal;

import com.example.tourplanner.DAL.dal.config.HibernateUtil;
import com.example.tourplanner.models.Tour;
import com.example.tourplanner.models.TourLog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TourLogDao implements Dao<TourLog>{

    private List<TourLog> tourLogs = new ArrayList<>();
    private static SessionFactory tourLogFactory;
    public TourLogDao(){
        tourLogFactory = HibernateUtil.getSessionFactory();
    }


    @Override
    public Optional<TourLog> get(int id) {
        Session session = tourLogFactory.openSession();
        Transaction tx = session.beginTransaction();
        TourLog tourLog = null;
        try {
            tourLog = session.get(TourLog.class, id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        session.close();
        return Optional.of(tourLog);
    }

    @Override
    public List<TourLog> getAll() {
        Session session = tourLogFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<TourLog> tourLogs = null;
        try {
            tourLogs = session.createQuery("from TourLog", TourLog.class).getResultList();
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

    @Override
    public void save(TourLog tourLog) {
        Session session = tourLogFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(tourLog);
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
    public void update(TourLog tourLog) {
        Session session = tourLogFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(tourLog);
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
    public void delete(TourLog tourLog) {
        Session session = tourLogFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(tourLog);
            tx.commit();
        } catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        session.close();
    }
}
