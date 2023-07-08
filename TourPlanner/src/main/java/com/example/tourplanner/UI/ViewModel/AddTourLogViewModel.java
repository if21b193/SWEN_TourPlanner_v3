package com.example.tourplanner.UI.ViewModel;

import com.example.tourplanner.BL.service.TourLogService;
import com.example.tourplanner.UI.ViewModel.ShareData.*;
import com.example.tourplanner.models.Tour;
import com.example.tourplanner.models.TourLogs;

import java.io.IOException;
import java.sql.Date;

public class AddTourLogViewModel implements EventListener {
    private final TourLogEventPublisher eventPublisher;
    private final EventPublisher publisher;
    private final TourLogService tourLogService;
    private static Tour tour;

    public AddTourLogViewModel(EventPublisher eventPublisher, TourLogEventPublisher tourLogEventPublisher, TourLogService tourLogService) {
        this.eventPublisher = tourLogEventPublisher;
        this.tourLogService = tourLogService;
        this.publisher = eventPublisher;
        this.publisher.addEventListener(this);
    }

    public TourLogs addTourLog(Date dateTime, String comment, Float difficulty, String totalTime, Float rating) throws IOException {

        TourLogs tourLogs = new TourLogs();
        tourLogs.setTour_id(tour);
        tourLogs.setComment(comment);
        tourLogs.setDateTime(dateTime);
        tourLogs.setDifficulty(difficulty);
        tourLogs.setTotalTime(totalTime);
        tourLogs.setRating(rating);
        tourLogService.create(tourLogs);
        return tourLogs;
    }

    @Override
    public void updateFromDb(SharedTourEvent event) {
        tour = event.returnTour();
    }
}
