package com.example.tourplanner.UI.ViewModel;

import com.example.tourplanner.BL.service.TourLogService;
import com.example.tourplanner.FXMLDependencyInjection;
import com.example.tourplanner.UI.ViewModel.ShareData.*;
import com.example.tourplanner.models.Tour;
import com.example.tourplanner.models.TourLogs;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class TourLogListViewModel implements ITourLogEventListener {

    private final TourLogService tourLogService;
    private AddTourLogViewModel addTourLogViewModel;
    private final TourLogEventPublisher publisher;

    //private final StringProperty tourId = new SimpleStringProperty();
    private final IntegerProperty tourId = new SimpleIntegerProperty();
    private boolean isInit = false;

    private static Tour tour;
    private static TourLogs tourLogs;

    private ObservableList<TourLogs> observableList = FXCollections.observableArrayList();

    public TourLogListViewModel(TourLogEventPublisher eventPublisher, TourLogService tourLogService, AddTourLogViewModel addTourLogViewModel){
        this.publisher = eventPublisher;
        this.tourLogService = tourLogService;
        this.addTourLogViewModel = addTourLogViewModel;
        setTourLogs(this.tourLogService.getAll());
        publisher.addEventListener(this);
    }

    public Tour getTour(){
        return tour;
    }

    private void setTourLogs(List<TourLogs> tourLogs) {
        observableList.clear();
        if(tourLogs != null){
            observableList.addAll(tourLogs);
        }
    }


    public void addTourLog(TourLogs tourLogs){
        observableList.add(tourLogs);
    }

    // TODO UPDATE TOURLOG VIEW MODEL
    public void modifyTourLog(TourLogs tourLogs) {
        publisher.publishToSingle(new SharedTourLogEvent(tourLogs), "UpdateTourLogViewModel");
        try {
            Parent root = FXMLDependencyInjection.load("updateLogTourMask.fxml", Locale.GERMAN);
            Stage secondary = new Stage();
            secondary.setTitle("Add TourLog");
            secondary.setScene(new Scene(root));
            secondary.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //to add a new tour
    @Override
    public void updateTourLog(SharedTourLogEvent event) {
        TourLogs tourLogs = event.returnTourLog();
        observableList.add(tourLogs);
    }

    public void deleteTourLog(TourLogs tourLogs) {
        tourLogService.delete(tourLogs);
        observableList.remove(tourLogs);
    }
    public ObservableList<TourLogs> getObservableTourLogs() {
        return observableList;
    }
}
