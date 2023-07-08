package com.example.tourplanner.UI.View;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class DetailsController {

    @FXML
    public VBox tourLogList;
    @FXML
    public TableView tourDetails;
    @FXML
    public Pane tourMap;

    @FXML
    private TourDetailsController tourDetailsController;
    @FXML
    private TourMapController tourMapController;
    @FXML
    private TourLogListController tourLogListController;

    public void showTourDetails(Event event) {
    }

    public void createTourMap(Event event) {
        tourMapController.setMapImage();
    }

    public void showTourLogs(Event event) {
    }
}
