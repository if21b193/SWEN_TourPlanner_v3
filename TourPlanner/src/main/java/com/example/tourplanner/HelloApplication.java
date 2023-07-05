package com.example.tourplanner;
import javafx.application.Application;


import javafx.scene.Parent;
import javafx.scene.Scene;


import javafx.stage.Stage;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.Locale;


public class HelloApplication extends Application {

    private static SessionFactory tourFactory;
    private static SessionFactory tourLogFactory;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root =FXMLDependencyInjection.load("MainWindow.fxml", Locale.GERMAN);

        Scene scene = new Scene(root, 620, 540);
        primaryStage.setTitle("Tourplanner");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch();
    }
}