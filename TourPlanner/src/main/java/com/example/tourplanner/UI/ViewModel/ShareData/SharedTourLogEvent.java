package com.example.tourplanner.UI.ViewModel.ShareData;

import com.example.tourplanner.models.TourLog;

public class SharedTourLogEvent {
    private final TourLog tourLog;

    public SharedTourLogEvent(TourLog tourLog) {
        super();
        this.tourLog = tourLog;
    }

    public TourLog returnTourLog(){
        return tourLog;
    }
}


