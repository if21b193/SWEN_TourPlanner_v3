package com.example.tourplanner.UI.ViewModel;

import com.example.tourplanner.BL.service.TourLogService;
import com.example.tourplanner.BL.service.TourService;
import com.example.tourplanner.UI.ViewModel.ShareData.EventListener;
import com.example.tourplanner.UI.ViewModel.ShareData.EventPublisher;
import com.example.tourplanner.UI.ViewModel.ShareData.SharedTourEvent;
import com.example.tourplanner.models.Tour;
import com.example.tourplanner.models.TourLogs;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.util.ArrayList;
import java.util.List;

public class TourListViewModel implements EventListener {
    private final TourService tourService;
    private final TourLogService tourLogService;
    private AddTourViewModel addTourViewModel;
    private final EventPublisher publisher;

    private ObservableList<Tour> observableList = FXCollections.observableArrayList();

    public TourListViewModel(EventPublisher eventPublisher, TourService tourService, TourLogService tourLogService, AddTourViewModel addTourViewModel){
        this.publisher = eventPublisher;
        this.tourService = tourService;
        this.addTourViewModel = addTourViewModel;
        setTours(this.tourService.getAll());
        publisher.addEventListener(this);
        this.tourLogService = tourLogService;
    }

    public void setTours(List<Tour> tours) {
        observableList.clear();
        if(tours != null){
            observableList.addAll(tours);
        }
    }

    public ObservableList<Tour> getObservableTours() {
        return observableList;
    }

    public void addTour(Tour tour){
        observableList.add(tour);
    }


    //adding tour to observable List works
    @Override
    public void updateFromDb(SharedTourEvent event) {
        Tour tour = event.returnTour();
        //observableList.add(tour);
    }

    public void deleteTour(Tour tour) {
        tourService.delete(tour);
        observableList.remove(tour);
    }

    public void modifyTour(Tour tour) {
        setTours(this.tourService.getAll());
    }

    public List<TourLogs> getTourLogsFromTour(int id) {
        return tourLogService.getAllFromTour(id);
    }

    public void searchFor(String text) {
        List<TourLogs> tourLogs = this.tourLogService.getAll();
        List<TourLogs> foundTourLogs = new ArrayList<>();
        List<Tour> tours = this.tourService.getAll();
        List<Tour> foundTours = new ArrayList<>();

        for(Tour tour : tours){
            if(tour.getDescription().contains(text) || String.valueOf(tour.getDistance()).contains(text)
                || tour.getFrom().contains(text) || tour.getName().contains(text) || tour.getEstimatedTime().contains(text) ||
                tour.getTo().contains(text) || tour.getTransportType().contains(text)){
                foundTours.add(tour);
            }
        }
        for (TourLogs tourLog: tourLogs) {
            if(tourLog.getComment().contains(text) || tourLog.getTotalTime().contains(text)){
                foundTourLogs.add(tourLog);
            }
        }
        for (TourLogs tourLog: foundTourLogs){
            Tour tour = tourLog.getTour_id();
            if(!foundTours.contains(tour)){
                foundTours.add(tour);
            }
        }
        setTours(foundTours);
    }

    public interface SelectionChangedListener {
        void changeSelection(Tour tour);
    }

    private List<SelectionChangedListener> listeners = new ArrayList<>();
    public ChangeListener<Tour> getChangeListener(){
        return((observableValue, tour, t1) -> notifyListeners(tour));
    }
    private void notifyListeners(Tour newTour){
        for (var listener : listeners){
            listener.changeSelection(newTour);
        }
    }
    public void addSelectionChangedListener(SelectionChangedListener listener) {
        listeners.add(listener);
    }
}
