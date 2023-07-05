package com.example.tourplanner.UI.ViewModel;

import com.example.tourplanner.BL.service.TourService;
import com.example.tourplanner.DAL.dal.Repository.MapQuestDirectionsAPI;
import com.example.tourplanner.UI.ViewModel.ShareData.EventPublisher;
import com.example.tourplanner.UI.ViewModel.ShareData.SharedTourEvent;
import com.example.tourplanner.models.Tour;

import java.io.IOException;
import java.util.Map;

public class AddTourViewModel {

    private final EventPublisher eventPublisher;
    private final TourService tourService;

    public AddTourViewModel(EventPublisher eventPublisher, TourService tourService) {
        this.eventPublisher = eventPublisher;
        this.tourService = tourService;
    }

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
        SharedTourEvent sharedTourEvent = new SharedTourEvent(tour);
        eventPublisher.publishToSingle(sharedTourEvent, "TourListViewModel");
    }


}
