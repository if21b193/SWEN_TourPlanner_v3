package com.example.tourplanner.UI.ViewModel;

import com.example.tourplanner.BL.service.TourService;
import com.example.tourplanner.DAL.dal.Repository.MapQuestDirectionsAPI;
import com.example.tourplanner.UI.ViewModel.ShareData.EventPublisher;
import com.example.tourplanner.models.MapQuestDirectionsReturn;
import com.example.tourplanner.models.Tour;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public class AddTourViewModel {


    // In event-driven programming, components can publish events (such as user interactions or system events),
    // and other components can subscribe to those events and react accordingly. That is what the event publisher is there for.

    private final TourService tourService;

    // Constructor for the class, uses an event publisher and the tour service as parameters
    public AddTourViewModel(TourService tourService) {

        this.tourService = tourService;
    }

    //
    public Tour addTour(String name, String start, String end, String transportation, String description) throws IOException {
        MapQuestDirectionsReturn directionsReturn = new MapQuestDirectionsAPI().getTourInformation(start, end, transportation);
        Tour tour = new Tour();
        tour.setTransportType(transportation);
        tour.setName(name);
        tour.setTo(end);
        tour.setFrom(start);
        tour.setDistance(directionsReturn.getDistance());
        tour.setEstimatedTime(directionsReturn.getTime());
        tour.setDescription(description);
        tourService.create(tour);
        return tour;
    }

}
