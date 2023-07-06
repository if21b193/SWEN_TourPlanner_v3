package com.example.tourplanner.UI.ViewModel;

import com.example.tourplanner.BL.service.ImplTourService;
import com.example.tourplanner.DAL.dal.Repository.MapQuestDirectionsAPI;
import com.example.tourplanner.UI.ViewModel.ShareData.EventListener;
import com.example.tourplanner.UI.ViewModel.ShareData.EventPublisher;
import com.example.tourplanner.UI.ViewModel.ShareData.SharedTourEvent;
import com.example.tourplanner.models.Tour;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Flow;

public class UpdateTourViewModel implements EventListener {
    StringProperty  name = new SimpleStringProperty();
    StringProperty transportType = new SimpleStringProperty();
    StringProperty start = new SimpleStringProperty();
    StringProperty end = new SimpleStringProperty();
    StringProperty distance = new SimpleStringProperty();
    StringProperty estimatedTime = new SimpleStringProperty();
    StringProperty description = new SimpleStringProperty();
    private EventPublisher publisher;
    private ImplTourService tourService;

    static int searchedId;

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

    public UpdateTourViewModel(EventPublisher eventPublisher, ImplTourService tourService){
        this.publisher = eventPublisher;
        this.tourService = tourService;
        publisher.addEventListener(this);
    }
    public Tour getSelectedTour() {
        return tourService.getById(searchedId);
    }

    @Override
    public void updateFromDb(SharedTourEvent event) {
        Tour tour = event.returnTour();
        searchedId = tour.getId();
    }

    public void saveTour(String transport, String end, String start, String description, String name) throws IOException {
        Map map = new MapQuestDirectionsAPI().getTourInformation(start, end);
        Tour tour = new Tour();
        tour.setId(searchedId);
        tour.setTransportType(transport);
        tour.setName(name);
        tour.setTo(end);
        tour.setFrom(start);
        tour.setDistance(Float.parseFloat(map.get("distance").toString()));
        tour.setEstimatedTime(map.get("time").toString());
        tour.setDescription(description);
        tourService.update(tour);
        SharedTourEvent sharedTourEvent = new SharedTourEvent(tour);
        publisher.publishToSingle(sharedTourEvent, "TourListViewModel");
    }
}
