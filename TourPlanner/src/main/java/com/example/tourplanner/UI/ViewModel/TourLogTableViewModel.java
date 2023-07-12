package com.example.tourplanner.UI.ViewModel;

import com.example.tourplanner.BL.service.TourLogService;
import com.example.tourplanner.UI.View.TourLogTableViewEntry;
import com.example.tourplanner.UI.ViewModel.ShareData.EventListener;
import com.example.tourplanner.UI.ViewModel.ShareData.EventPublisher;
import com.example.tourplanner.UI.ViewModel.ShareData.SharedTourEvent;
import com.example.tourplanner.models.Tour;
import com.example.tourplanner.models.TourLogs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class TourLogTableViewModel implements EventListener {
    ObservableList<TourLogTableViewEntry> tourLogEntries = FXCollections.observableArrayList();
    private final TourLogService tourLogService;
    private static Tour tour;
    public TourLogTableViewModel(EventPublisher eventPublisher, TourLogService tourLogService){
        eventPublisher.addEventListener(this);
        this.tourLogService = tourLogService;
    }

    public TourLogs getTourLogById(String id) {
        return tourLogService.getById(Integer.parseInt(id));
    }

    @Override
    public void updateFromDb(SharedTourEvent event) {
        tour = event.returnTour();
        setTourLogListToTour(tour);
    }

    private void setTourLogListToTour(Tour tour) {
        tourLogEntries.clear();
        if(tour == null) {
            return;
        }
        List<TourLogs> logs = tourLogService.getAllFromTour(tour.getId());
        for(TourLogs log : logs){
            tourLogEntries.add(getEntryFromTourLog(log));
        }
    }

    public ObservableList<TourLogTableViewEntry> getTourLogEntries(){
        return tourLogEntries;
    }

    public TourLogTableViewEntry getEntryFromTourLog(TourLogs log) {
        return new TourLogTableViewEntry(log.getId(), log.getDateTime(), log.getComment(), log.getDifficulty(), log.getTotalTime(), log.getRating());
    }

    public void modifyTourLog() {
        setTourLogListToTour(tour);
    }
}
