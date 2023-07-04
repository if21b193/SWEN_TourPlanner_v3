package com.example.tourplanner;

import com.example.tourplanner.UI.View.ControllerFactory;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class FXMLDependencyInjection {
    public static Parent load(String location,
                              Locale locale) throws IOException {
        FXMLLoader loader = getLoader(location, locale);
        return loader.load();
    }

    public static FXMLLoader getLoader(String location,
                                       Locale locale) {
        return new FXMLLoader(
                FXMLDependencyInjection.class.getResource("/com/example/tourplanner/" + location),
                ResourceBundle.getBundle("com.example.tourplanner." + "gui_strings", locale),
                new JavaFXBuilderFactory(),
                controllerClass-> ControllerFactory.getInstance().create(controllerClass)
        );
    }
}
