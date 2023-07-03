package com.example.tourplanner;

import com.example.tourplanner.models.Tour;
import com.example.tourplanner.UI.View.ButtonLayout;
import com.example.tourplanner.UI.View.NavBar;
import com.example.tourplanner.UI.View.TourList;
import com.example.tourplanner.UI.View.TourLogList;
import com.example.tourplanner.models.TourLog;
import javafx.application.Application;

import javafx.scene.Scene;

import javafx.scene.layout.BorderPane;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

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

    public void start(Stage primaryStage) {


        TourList tourList = new TourList(tours);
        NavBar navBar = new NavBar();
        mainLayout.setTop(navBar);
        TourLogList tourLogList = new TourLogList(tourLogs);

        // When a tour is selected, update the tour logs list
        tourList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // replace with code to update tourLogList based on selected tour
        });

        // Create a layout for the tours side
        VBox toursLayout = new VBox(10);
        toursLayout.getChildren().addAll(tourList, new ButtonLayout(tourList));

        // Create the main layout

        mainLayout.setLeft(toursLayout);
        mainLayout.setCenter(tourLogList);

        // Create the scene and show the stage
        Scene scene = new Scene(mainLayout, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Tour Planner");
        primaryStage.show();
    }


    public static void main(String[] args) {
        //creating a tour factory
        try {
            tourFactory = new Configuration().
                    configure().addAnnotatedClass(Tour.class).
                    buildSessionFactory();
        } catch (Throwable e) {
            throw new ExceptionInInitializerError("Setting up  tourFactory didn't work " + e);
        }

        //creating a tourLog factory
        try {
            tourLogFactory = new Configuration().
                    configure().addAnnotatedClass(TourLog.class).
                    buildSessionFactory();
        } catch (Throwable e) {
            throw new ExceptionInInitializerError("Setting up tourLogFactory didn't work " + e);
        }

        Session tourSession = tourFactory.openSession();
        Session tourLogSession = tourLogFactory.openSession();

        // start transaction
        Transaction transaction = tourSession.beginTransaction();

        // create new instance of Tour and set values in it
        Tour tour = new Tour();
        Integer tourID;
        tour.setName("Test Tour");
        tour.setDescription("This is a test tour");
        tour.setDistance(300.33f);
        tourID = (Integer) tourSession.save(tour);


        // commit transaction
        transaction.commit();

        // close the tourSession
        tourSession.close();

        launch();
    }


}