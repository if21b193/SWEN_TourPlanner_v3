package com.example.tourplanner.UI.ViewModel;

import com.example.tourplanner.UI.ViewModel.ShareData.EventListener;
import com.example.tourplanner.UI.ViewModel.ShareData.EventPublisher;
import com.example.tourplanner.UI.ViewModel.ShareData.SharedTourEvent;
import com.example.tourplanner.models.Tour;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class TourDetailsViewModel implements EventListener {

    private final EventPublisher publisher;
    private static Tour tour;

    public TourDetailsViewModel(EventPublisher publisher) {
        this.publisher = publisher;
        this.publisher.addEventListener(this);
    }

    public Tour getSelectedTour() {
        return tour;
    }

    @Override
    public void updateFromDb(SharedTourEvent event) {
        tour = event.returnTour();
    }

}
