package com.example.tourplanner;
import com.example.tourplanner.DAL.dal.dao.TourLogDao;
import com.example.tourplanner.models.TourLogs;
import javafx.application.Application;


import javafx.scene.Parent;
import javafx.scene.Scene;


import javafx.stage.Stage;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class HelloApplication extends Application {

    private static SessionFactory tourFactory;
    private static SessionFactory tourLogFactory;
    private static TourLogDao tourLogDao;
    @Override
    public void start(Stage primaryStage) throws IOException {
        TourLogDao tourLogDao = new TourLogDao();
        Parent root =FXMLDependencyInjection.load("View/MainWindow.fxml", Locale.GERMAN);
        Scene scene = new Scene(root, 600, 700);
        primaryStage.setTitle("Tourplanner");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
