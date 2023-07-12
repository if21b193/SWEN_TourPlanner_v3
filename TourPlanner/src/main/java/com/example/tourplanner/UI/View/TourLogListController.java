package com.example.tourplanner.UI.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class TourLogListController {
    @FXML
    public VBox tourLogView;
    @FXML
    public Button addTourLogButton;
    @FXML
    public Button deleteTourLogButton;
    @FXML
    public Pane tourLogTable;
    @FXML
    public TourLogTableController tourLogTableController;
    public Button modifyTourLog;
    @FXML
    public void initialize() {
    }

    //functions notifying the tourLogTableController it has somethings to do

    public void addTourLog(ActionEvent actionEvent) {
        tourLogTableController.addTourLog();
    }

    public void modifyTourLog(ActionEvent actionEvent) {
        tourLogTableController.modifyTourLog();
    }

    public void deleteTourLog(ActionEvent actionEvent) {
        tourLogTableController.deleteTourLog();
    }

}
