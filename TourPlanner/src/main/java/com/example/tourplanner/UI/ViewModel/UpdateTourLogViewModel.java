package com.example.tourplanner.UI.ViewModel;

import com.example.tourplanner.BL.service.TourLogService;
import com.example.tourplanner.UI.ViewModel.ShareData.*;
import com.example.tourplanner.models.TourLogs;
import javafx.beans.property.*;

import java.sql.Date;
import java.time.LocalDate;

public class UpdateTourLogViewModel implements ITourLogEventListener{
    ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
    StringProperty comment = new SimpleStringProperty();
    StringProperty totalTime = new SimpleStringProperty();
    StringProperty rating = new SimpleStringProperty();
    StringProperty difficulty = new SimpleStringProperty();
    private final TourLogService tourLogService;

    private static TourLogs tourLog;

    public UpdateTourLogViewModel(TourLogEventPublisher publisher, TourLogService service) {
        publisher.addEventListener(this);
        this.tourLogService = service;
    }

    public StringProperty commentProperty() {
        return comment;
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public StringProperty totalTimeProperty() {
        return totalTime;
    }

    public StringProperty ratingProperty() {
        return rating;
    }

    public StringProperty difficultyProperty() {
        return difficulty;
    }

    @Override
    public void updateTourLog(SharedTourLogEvent event) {
        tourLog = event.returnTourLog();
    }

    public TourLogs getTourLog(){
        return tourLog;
    }

    public TourLogs saveTourLog(Date date, String commentary, Float difficultyInput, String time, Float ratingInput) {
        TourLogs currentTourLog = new TourLogs(tourLog.getTour_id(), date, commentary, difficultyInput, time, ratingInput);
        currentTourLog.setId(tourLog.getId());
        tourLogService.update(currentTourLog);
        return tourLog;
    }

}