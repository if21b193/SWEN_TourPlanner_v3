package com.example.tourplanner.DAL.dal.config;

import com.example.tourplanner.models.Tour;
import com.example.tourplanner.models.TourLogs;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class HibernateUtil {

    //We have a hibernate.cfg.xml, similar to the hikari config file, we use that to build a session factory
    // A session factory basically manages a hibernate session, doing everything from mapping data to managing the connections
    // it also contains methods to perform crud operations, for which we would've had to write our own sql statements if we used hikari, if I understand correctly
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static final Logger logger = LogManager.getLogger(HibernateUtil.class);

    private static SessionFactory buildSessionFactory() {
        try {
            logger.info("Building session factory...");
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(Tour.class);
            configuration.addAnnotatedClass(TourLogs.class);

            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            logger.info("Session factory built successfully.");
            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            logger.error("Initial SessionFactory creation failed.", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {

        logger.info("Shutting down session factory...");
        getSessionFactory().close();
        logger.info("Session factory shut down successfully.");

    }
}
