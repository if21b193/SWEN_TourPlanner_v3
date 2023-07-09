package com.example.tourplanner.UI.ViewModel;

import com.example.tourplanner.BL.service.TourService;
import com.example.tourplanner.UI.ViewModel.ShareData.EventListener;
import com.example.tourplanner.UI.ViewModel.ShareData.EventPublisher;
import com.example.tourplanner.UI.ViewModel.ShareData.SharedTourEvent;
import com.example.tourplanner.models.Tour;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.util.ArrayList;
import java.util.List;

public class TourListViewModel implements EventListener {
    private final TourService tourService;
    private AddTourViewModel addTourViewModel;
    private final EventPublisher publisher;

    private ObservableList<Tour> observableList = FXCollections.observableArrayList();

    public TourListViewModel(EventPublisher eventPublisher, TourService tourService, AddTourViewModel addTourViewModel){
        this.publisher = eventPublisher;
        this.tourService = tourService;
        this.addTourViewModel = addTourViewModel;
        setTours(this.tourService.getAll());
        publisher.addEventListener(this);
    }

    void setTours(List<Tour> tours) {
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
