package com.example.tourplanner.UI.ViewModel;

import com.example.tourplanner.UI.View.TourLogTableViewEntry;
import com.example.tourplanner.UI.ViewModel.ShareData.EventListener;
import com.example.tourplanner.UI.ViewModel.ShareData.EventPublisher;
import com.example.tourplanner.UI.ViewModel.ShareData.SharedTourEvent;
import com.example.tourplanner.models.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TourLogTableViewModel implements EventListener {
    ObservableList<TourLogTableViewEntry> tourLogEntries = FXCollections.observableArrayList();

    private static EventPublisher publisher;

    public TourLogTableViewModel(EventPublisher eventPublisher){
        this.publisher = eventPublisher;
        this.publisher.addEventListener(this);
    }


    @Override
    public void updateFromDb(SharedTourEvent event) {
        setTourLogListToTour(event.returnTour());
    }

    private void setTourLogListToTour(Tour tour) {
        tourLogEntries.clear();
    }
}
