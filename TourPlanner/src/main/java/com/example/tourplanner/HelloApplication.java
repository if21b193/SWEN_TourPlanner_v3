package com.example.tourplanner;
import javafx.application.Application;


import javafx.scene.Parent;
import javafx.scene.Scene;


import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;


public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLDependencyInjection.load("View/MainWindow.fxml", Locale.GERMAN);
        Scene scene = new Scene(root, 600, 700);
        primaryStage.setTitle("Tourplanner");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
