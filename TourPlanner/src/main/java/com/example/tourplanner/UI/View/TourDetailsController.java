package com.example.tourplanner.UI.View;

import com.example.tourplanner.UI.ViewModel.TourDetailsViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class TourDetailsController {
    @FXML
    public Label tourName;
    @FXML
    public Label fromInsert;
    @FXML
    public Label toInsert;
    @FXML
    public Label transportationInsert;
    @FXML
    public Label descriptionInsert;
    @FXML
    public Label accessibilityInsert;
    @FXML
    public Label childFriendlinessInsert;

    private final TourDetailsViewModel tourDetailsViewModel;
    @FXML
    public Label timeInsert;
    @FXML
    public Label distanceInsert;

    public TourDetailsController(TourDetailsViewModel tourDetailsViewModel){
        this.tourDetailsViewModel = tourDetailsViewModel;
    }
    @FXML
    public void initialize(){
        tourName.textProperty().bindBidirectional(tourDetailsViewModel.tourNameProperty());
        fromInsert.textProperty().bindBidirectional(tourDetailsViewModel.fromProperty());
        toInsert.textProperty().bindBidirectional(tourDetailsViewModel.toProperty());
        transportationInsert.textProperty().bindBidirectional(tourDetailsViewModel.transportationProperty());
        descriptionInsert.textProperty().bindBidirectional(tourDetailsViewModel.descriptionProperty());
        accessibilityInsert.textProperty().bindBidirectional(tourDetailsViewModel.accessibilityProperty());
        childFriendlinessInsert.textProperty().bindBidirectional(tourDetailsViewModel.childFriendlinessProperty());
        timeInsert.textProperty().bindBidirectional(tourDetailsViewModel.timeProperty());
        distanceInsert.textProperty().bindBidirectional(tourDetailsViewModel.distanceProperty());

    }
}
