package com.example.tourplanner;

import com.example.tourplanner.DAL.dal.config.HibernateUtil;
import com.example.tourplanner.models.Tour;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/* ! ! ! ! IMPORTANT COMMENT BEFORE U START <3 ! ! ! !
- since the tour id value in the table is a varchar and not an int, i have also changed our tour id type to string
- the errors we are getting after trying to run the code are caused by the id not being properly set, the issue is with it being null when it is not allowed to be null
- if I am not wrong, the connection works and the db is able to be read properly, the issue is with the id

- A good thing to do would be to remove all the hikari elements so we can declutter our code a bit if you still have time after figuring out the ID Issue
*/


public class HelloApplication extends Application {

    private static SessionFactory tourFactory;
    private static SessionFactory tourLogFactory;
    private BorderPane mainLayout = new BorderPane();
    private final ArrayList<String> tours = new ArrayList<>(Arrays.asList("Tour 1", "Tour 2", "Tour 3"));
    private final String[] tourLogs = {"Log 1", "Log 2", "Log 3"};

    @Override

    public void start(Stage primaryStage) throws IOException {
        Parent root =FXMLDependencyInjection.load("MainWindow.fxml", Locale.GERMAN);

        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainWindow.fxml"));
        Scene scene = new Scene(root, 620, 540);
        primaryStage.setTitle("Tourplanner");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session tourSession = sessionFactory.openSession();
        // start transaction
        Transaction transaction = tourSession.beginTransaction();

        // create new instance of Tour and set values in it
        Tour tour = new Tour();
        Integer tourID;
        tour.setName("Test Tour");
        tour.setDescription("proof of concept");
        tour.setDistance(300.33f);
        tourID = (Integer) tourSession.save(tour);


        // commit transaction
        transaction.commit();

        // close the tourSession
        tourSession.close();

        launch();
    }


}