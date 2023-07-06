package com.example.tourplanner.UI.ViewModel;

import com.example.tourplanner.BL.service.TourService;
import com.example.tourplanner.DAL.dal.Repository.MapQuestDirectionsAPI;
import com.example.tourplanner.UI.ViewModel.ShareData.EventPublisher;
import com.example.tourplanner.models.Tour;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public class AddTourViewModel {

    /*StringProperty name = new SimpleStringProperty();
    StringProperty transportType = new SimpleStringProperty();
    StringProperty start = new SimpleStringProperty();
    StringProperty end = new SimpleStringProperty();
    StringProperty description = new SimpleStringProperty();

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty transportTypeProperty() {
        return transportType;
    }
    public StringProperty startProperty() {
        return start;
    }

    public StringProperty endProperty() {
        return end;
    }

    public StringProperty descriptionProperty() {
        return description;
    }
    */

    // In event-driven programming, components can publish events (such as user interactions or system events),
    // and other components can subscribe to those events and react accordingly. That is what the event publisher is there for.
    private final EventPublisher eventPublisher;
    private final TourService tourService;

    // Constructor for the class, uses an event publisher and the tour service as parameters
    public AddTourViewModel(EventPublisher eventPublisher, TourService tourService) {
        this.eventPublisher = eventPublisher;
        this.tourService = tourService;
    }

    //
    public Tour addTour(String name, String start, String end, String transportation, String description) throws IOException {
        Map map = new MapQuestDirectionsAPI().getTourInformation(start, end);
        Tour tour = new Tour();
        tour.setTransportType(transportation);
        tour.setName(name);
        tour.setTo(end);
        tour.setFrom(start);
        tour.setDistance(Float.parseFloat(map.get("distance").toString()));
        tour.setEstimatedTime(map.get("time").toString());
        tour.setDescription(description);
        tourService.create(tour);
        return tour;
    }

    /*public Tour addTour() throws IOException {
        Map map = new MapQuestDirectionsAPI().getTourInformation(start.get(), end.get());
        Tour tour = new Tour();
        tour.setTransportType(transportType.get());
        tour.setTo(end.get());
        tour.setDescription(description.get());
        tour.setDistance(Float.parseFloat(map.get("distance").toString()));
        tour.setFrom(start.get());
        tour.setName(name.get());
        tour.setEstimatedTime(map.get("time").toString());
        tourService.create(tour);
        return tour;
    }*/


}
