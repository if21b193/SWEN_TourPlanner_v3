package com.example.tourplanner.UI.ViewModel;

import com.example.tourplanner.BL.service.ImplTourLogService;
import com.example.tourplanner.BL.service.ImplTourService;
import com.example.tourplanner.DAL.dal.Repository.MapQuestDirectionsAPI;
import com.example.tourplanner.UI.ViewModel.ShareData.*;
import com.example.tourplanner.models.Tour;
import com.example.tourplanner.models.TourLog;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.util.Map;

public class UpdateTourLogViewModel implements ITourLogEventListener {


        StringProperty comment = new SimpleStringProperty();
        StringProperty dateTime = new SimpleStringProperty();
        StringProperty totalTime = new SimpleStringProperty();
        StringProperty rating = new SimpleStringProperty();
        StringProperty difficulty = new SimpleStringProperty();

        private TourLogEventPublisher publisher;
        private ImplTourLogService tourLogService;

        static int searchedId;

        public StringProperty commentProperty() {
            return comment;
        }

        public StringProperty dateTimeProperty() {
            return dateTime;
        }
        public StringProperty totalTimeProperty() {
            return totalTime;
        }

        public Property ratingProperty() {
            return rating;
        }

        public Property difficultyProperty() {
            return difficulty;
        }

        public UpdateTourLogViewModel(TourLogEventPublisher eventPublisher, ImplTourLogService tourLogService){
            this.publisher = eventPublisher;
            this.tourLogService = tourLogService;
            publisher.addEventListener(this);
        }
        public TourLog getSelectedTourLog() {
            return tourLogService.getById(searchedId);
        }

        @Override
        public void updateTourLog(SharedTourLogEvent event) {
            TourLog tourLog = event.returnTourLog();
            searchedId = tourLog.getId();
        }

        public void saveTourLog(String comment, String dateTime, String totalTime, String rating, String difficulty) throws IOException {
            TourLog tourLog = new TourLog();
            tourLog.setId(searchedId);
            tourLog.setComment(comment);
            tourLog.setDateTime(dateTime);
            tourLog.setTotalTime(totalTime);
            tourLog.setRating(Float.parseFloat(rating));
            tourLog.setDifficulty(Float.parseFloat(difficulty));
            tourLogService.update(tourLog);
            SharedTourLogEvent sharedTourLogEvent = new SharedTourLogEvent(tourLog);
            publisher.publishToSingle(sharedTourLogEvent, "TourLogViewModel");
        }
}

