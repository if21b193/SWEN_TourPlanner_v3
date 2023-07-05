package com.example.tourplanner.UI.ViewModel.ShareData;

import com.example.tourplanner.models.Tour;

public class SharedTourEvent {
    private final Tour tour;

    public SharedTourEvent(Tour tour) {
        super();
        this.tour = tour;
    }

    public Tour returnTour(){
        return tour;
    }
}
