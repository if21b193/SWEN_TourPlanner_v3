package com.example.tourplanner.UI.View;

import com.example.tourplanner.UI.ViewModel.TourDetailsViewModel;
import com.example.tourplanner.models.Tour;
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
    }
    public void fillInTourDetails() {
        Tour tour = tourDetailsViewModel.getSelectedTour();
        tourName.setText(tour.getName());
        fromInsert.setText(tour.getFrom());
        toInsert.setText(tour.getTo());
        transportationInsert.setText(tour.getTransportType());
        descriptionInsert.setText(tour.getDescription());
        accessibilityInsert.setText(Tour.calculateAccessibility(tour.getTransportType(), (float) tour.getDistance()));
        childFriendlinessInsert.setText(tour.calculateChildFriendliness((float) tour.getDistance()));
    }

}