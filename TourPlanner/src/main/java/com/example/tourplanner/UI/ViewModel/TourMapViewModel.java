package com.example.tourplanner.UI.ViewModel;

import com.example.tourplanner.DAL.dal.Repository.MapQuestStaticImageAPI;
import com.example.tourplanner.UI.ViewModel.ShareData.EventListener;
import com.example.tourplanner.UI.ViewModel.ShareData.EventPublisher;
import com.example.tourplanner.UI.ViewModel.ShareData.SharedTourEvent;
import com.example.tourplanner.models.Tour;

import java.io.IOException;

public class TourMapViewModel implements EventListener {

    private final EventPublisher publisher;
    private static Tour tour;

    public TourMapViewModel(EventPublisher eventPublisher){
        publisher = eventPublisher;
        publisher.addEventListener(this);
    }
    public String getMapDetails() {
        String from = tour.getFrom();
        String to = tour.getTo();
        return new MapQuestStaticImageAPI().getStaticImage(from, to);
    }

    @Override
    public void updateFromDb(SharedTourEvent event) {
       tour = event.returnTour();
    }
}
