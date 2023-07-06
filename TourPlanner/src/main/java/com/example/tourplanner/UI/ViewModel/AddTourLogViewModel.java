package com.example.tourplanner.UI.ViewModel;

import com.example.tourplanner.BL.service.TourLogService;
import com.example.tourplanner.BL.service.TourService;
import com.example.tourplanner.DAL.dal.Repository.MapQuestDirectionsAPI;
import com.example.tourplanner.UI.ViewModel.ShareData.EventPublisher;
import com.example.tourplanner.UI.ViewModel.ShareData.SharedTourEvent;
import com.example.tourplanner.UI.ViewModel.ShareData.SharedTourLogEvent;
import com.example.tourplanner.UI.ViewModel.ShareData.TourLogEventPublisher;
import com.example.tourplanner.models.Tour;
import com.example.tourplanner.models.TourLog;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Map;

public class AddTourLogViewModel {
    private final TourLogEventPublisher eventPublisher;
    private final TourLogService tourLogService;
    public AddTourLogViewModel(TourLogEventPublisher eventPublisher, TourLogService tourLogService) {
        this.eventPublisher = eventPublisher;
        this.tourLogService = tourLogService;
    }

    public void addTourLog(String dateTime, String comment, Float difficulty, String totalTime, Float rating) throws IOException {

        TourLog tourLog = new TourLog();

        tourLog.setComment(comment);
        tourLog.setDateTime(dateTime);
        tourLog.setDifficulty(difficulty);
        tourLog.setTotalTime(totalTime);
        tourLog.setRating(rating);
        tourLogService.create(tourLog);
        //IMPLEMENT TOURLOG LIST VIEW MODEL
        SharedTourLogEvent sharedTourLogEvent = new SharedTourLogEvent(tourLog);
        eventPublisher.publishToSingle(sharedTourLogEvent, "TourLogListViewModel");

    }
}
