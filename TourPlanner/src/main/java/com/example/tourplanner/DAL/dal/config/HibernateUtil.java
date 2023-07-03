package com.example.tourplanner.DAL.dal.config;

import com.example.tourplanner.models.Tour;
import com.example.tourplanner.models.TourLog;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

public class HibernateUtil {

    //We have a hibernate.cfg.xml, similar to the hikari config file, we use that to build a session factory
    // A session factory basically manages a hibernate session, doing everything from mapping data to managing the connections
    // it also contains methods to perform crud operations, for which we would've had to write our own sql statements if we used hikari, if I understand correctly
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(Tour.class);
            configuration.addAnnotatedClass(TourLog.class);

            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
