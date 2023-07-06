package com.example.tourplanner.UI.ViewModel;

import com.example.tourplanner.BL.service.TourLogService;
import com.example.tourplanner.FXMLDependencyInjection;
import com.example.tourplanner.UI.ViewModel.ShareData.*;
import com.example.tourplanner.models.TourLog;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

    public class TourLogListViewModel implements ITourLogEventListener {
    private final TourLogService tourLogService;
    private AddTourLogViewModel addTourLogViewModel;
    private final TourLogEventPublisher publisher;

    private ObservableList<TourLog> observableList = FXCollections.observableArrayList();

    public TourLogListViewModel(TourLogEventPublisher eventPublisher, TourLogService tourLogService, AddTourLogViewModel addTourLogViewModel){
        this.publisher = eventPublisher;
        this.tourLogService = tourLogService;
        this.addTourLogViewModel = addTourLogViewModel;
        setTourLogs(this.tourLogService.getAll());
        publisher.addEventListener(this);
    }
    private void setTourLogs(List<TourLog> tourLogs) {
        observableList.clear();
        observableList.addAll(tourLogs);
    }

    // TODO ADD TOURLOG MASK
    public void addTourLog(TourLog tourLog){
        observableList.add(tourLog);
    }

    // TODO UPDATE TOURLOG VIEW MODEL
        public void modifyTourLog(TourLog tourLog) {
            publisher.publishToSingle(new SharedTourLogEvent(tourLog), "UpdateTourLogViewModel");
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

    @Override
    public void updateTourLog(SharedTourLogEvent event) {
        TourLog tourLog = event.returnTourLog();
        observableList.add(tourLog);
    }

        public void deleteTourLog(TourLog tourLog) {
            tourLogService.delete(tourLog);
        }
        public ObservableList<TourLog> getObservableTourLogs() {
            return observableList;
        }

        public interface SelectionChangedListener {
            void changeSelection(TourLog tourLog);
        }
        private List<TourLogListViewModel.SelectionChangedListener> listeners = new ArrayList<>();

        private void notifyListeners(TourLog newTourLog){
            for (var listener : listeners){
                listener.changeSelection(newTourLog);
            }
        }
        public ChangeListener<TourLog> getChangeListener(){
            return((observableValue, tourLog, tl1) -> notifyListeners(tourLog));
        }
}
