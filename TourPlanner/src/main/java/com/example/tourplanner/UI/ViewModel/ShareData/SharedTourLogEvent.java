package com.example.tourplanner.UI.ViewModel.ShareData;

import com.example.tourplanner.models.TourLogs;

public class SharedTourLogEvent {
    private final TourLogs tourLogs;

    public SharedTourLogEvent(TourLogs tourLogs) {
        super();
        this.tourLogs = tourLogs;
    }

    public TourLogs returnTourLog(){
        return tourLogs;
    }
}


