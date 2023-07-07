package com.example.tourplanner.UI.ViewModel;

import com.example.tourplanner.BL.service.TourLogService;
import com.example.tourplanner.BL.service.TourService;
import com.example.tourplanner.DAL.dal.Repository.MapQuestDirectionsAPI;
import com.example.tourplanner.UI.ViewModel.ShareData.*;
import com.example.tourplanner.models.Tour;
import com.example.tourplanner.models.TourLog;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Map;

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

    public TourLog addTourLog(Date dateTime, String comment, Float difficulty, String totalTime, Float rating) throws IOException {

        TourLog tourLog = new TourLog();

        tourLog.setTour_id(tour);
        tourLog.setComment(comment);
        tourLog.setDateTime(dateTime);
        tourLog.setDifficulty(difficulty);
        tourLog.setTotalTime(totalTime);
        tourLog.setRating(rating);
        tourLogService.create(tourLog);
        return tourLog;
        //IMPLEMENT TOURLOG LIST VIEW MODEL
        //SharedTourLogEvent sharedTourLogEvent = new SharedTourLogEvent(tourLog);
        //eventPublisher.publishToSingle(sharedTourLogEvent, "TourLogListViewModel");

    }

    @Override
    public void updateFromDb(SharedTourEvent event) {
        tour = event.returnTour();
    }
}
