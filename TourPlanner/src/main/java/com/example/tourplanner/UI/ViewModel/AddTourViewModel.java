package com.example.tourplanner.UI.ViewModel;

import com.example.tourplanner.BL.service.TourService;
import com.example.tourplanner.DAL.dal.Repository.MapQuestDirectionsAPI;
import com.example.tourplanner.models.Tour;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.util.EventListener;
import java.util.Map;
import java.util.function.Consumer;

public class AddTourViewModel {
    /*private StringProperty from = new SimpleStringProperty();
    private StringProperty to = new SimpleStringProperty();
    private StringProperty transportationMode = new SimpleStringProperty();
    private StringProperty tourName = new SimpleStringProperty();
    private StringProperty tourDescription = new SimpleStringProperty();*/

    private final TourService tourService;
    private Consumer<Tour> onTourCreatedListener;
    public void setOnTourCreatedListener(Consumer<Tour> eventListener){
        this.onTourCreatedListener = eventListener;
    }

    public AddTourViewModel(TourService tourService) {
        this.tourService = tourService;
    }
/*
    public StringProperty fromProperty() {
        return from;
    }
    public StringProperty toProperty() {
        return to;
    }
    public StringProperty transportationModeProperty() {
        return transportationMode;
    }

    public StringProperty tourNameProperty() {
        return tourName;
    }

    public StringProperty tourDescriptionProperty() {
        return tourDescription;
    }

    public void addTour() throws IOException {
        Tour tour = new Tour();
        tour.setDescription(tourDescription.get());
        Map map = new MapQuestDirectionsAPI().getTourInformation(from.get(), to.get());
        tour.setDistance(Float.parseFloat(map.get("distance").toString()));
        tour.setFrom(from.get());
        tour.setTo(to.get());
        tour.setName(tourName.get());
        tour.setTransportType(transportationMode.get());
        tourService.create(tour);
    }*/

    public void addTour(String name, String start, String end, String transportation, String description) throws IOException {
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
        if(onTourCreatedListener != null){
            onTourCreatedListener.accept(tour);
        }
    }


}
