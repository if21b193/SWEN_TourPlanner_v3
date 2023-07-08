package com.example.tourplanner.UI.View;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class DetailsController {

    @FXML
    public VBox tourLogList;
    @FXML
    public GridPane tourDetails;
    @FXML
    public Pane tourMap;
    @FXML
    public TabPane tabPane;

    @FXML
    private TourDetailsController tourDetailsController;
    @FXML
    private TourMapController tourMapController;
    @FXML
    private TourLogListController tourLogListController;

    @FXML
    private void initialize(){
        tabPane.getSelectionModel().selectedItemProperty().addListener((observableValue, tab, t1) -> {
            if(t1 != null){
                handleTabSelection(t1);
            }
        });
    }

    private void handleTabSelection(Tab t1) {
        if(t1.getText().equals("TourLogs")){
            //
        } else if(t1.getText().equals("TourDetails")){
            tourDetailsController.fillInTourDetails();
        } else if(t1.getText().equals("Map")){
            tourMapController.setMapImage();
        }
    }

    public void showTourDetails(Event event) {
       tourDetailsController.fillInTourDetails();
    }

    public void createTourMap(Event event) {
        tourMapController.setMapImage();
    }

    public void showTourLogs(Event event) {
    }
}
