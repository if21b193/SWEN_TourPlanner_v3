package com.example.tourplanner.UI.ViewModel;

import com.example.tourplanner.BL.service.CalculateTimeFromSeconds;
import com.example.tourplanner.UI.ViewModel.ShareData.EventListener;
import com.example.tourplanner.UI.ViewModel.ShareData.EventPublisher;
import com.example.tourplanner.UI.ViewModel.ShareData.SharedTourEvent;
import com.example.tourplanner.models.Tour;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.text.DecimalFormat;

public class TourDetailsViewModel implements EventListener {
    private StringProperty tourName = new SimpleStringProperty();
    private StringProperty from = new SimpleStringProperty();
    private StringProperty to = new SimpleStringProperty();
    private StringProperty transportation = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private StringProperty accessibility = new SimpleStringProperty();
    private StringProperty childFriendliness = new SimpleStringProperty();
    private StringProperty time = new SimpleStringProperty();
    private StringProperty distance = new SimpleStringProperty();

    private static Tour tour;

    public StringProperty tourNameProperty() {
        return tourName;
    }

    public StringProperty fromProperty() {
        return from;
    }
    public StringProperty toProperty() {
        return to;
    }
    public StringProperty transportationProperty() {
        return transportation;
    }
    public StringProperty descriptionProperty() {
        return description;
    }
    public StringProperty accessibilityProperty() {
        return accessibility;
    }
    public StringProperty childFriendlinessProperty() {
        return childFriendliness;
    }
    public StringProperty timeProperty(){
        return time;
    }

    public StringProperty distanceProperty(){
        return distance;
    }

    public static Tour getTour() {
        return tour;
    }

    public static void setTour(Tour tour) {
        TourDetailsViewModel.tour = tour;
    }

    public TourDetailsViewModel(EventPublisher publisher) {
        publisher.addEventListener(this);
    }

    @Override
    public void updateFromDb(SharedTourEvent event) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        tour = event.returnTour();
        tourName.set(tour.getName());
        from.set(tour.getFrom());
        to.set(tour.getTo());
        transportation.set(tour.getTransportType());
        description.set(tour.getDescription());
        accessibility.set(Tour.calculateAccessibility(tour.getTransportType(), (float) tour.getDistance()));
        childFriendliness.set(tour.calculateChildFriendliness((float) tour.getDistance()));
        time.set(CalculateTimeFromSeconds.getTimeInfo(tour.getEstimatedTime()));
        distance.set(decimalFormat.format(tour.getDistance()) + " km");
    }

}
