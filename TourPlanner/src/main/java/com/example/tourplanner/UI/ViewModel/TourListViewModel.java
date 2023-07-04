package com.example.tourplanner.UI.ViewModel;

import com.example.tourplanner.BL.service.TourService;
import com.example.tourplanner.FXMLDependencyInjection;
import com.example.tourplanner.models.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class TourListViewModel {
    private final TourService tourService;
    private AddTourViewModel addTourViewModel;

    private ObservableList<Tour> observableList = FXCollections.observableArrayList();

    public TourListViewModel(TourService tourService, AddTourViewModel addTourViewModel){
        this.tourService = tourService;
        this.addTourViewModel = addTourViewModel;
        setTours(this.tourService.getAll());
    }

    private void setTours(List<Tour> tours) {
        observableList.clear();
        observableList.addAll(tours);
    }

    public ObservableList<Tour> getObservableTours() {
        return observableList;
    }

    public void addTour(){


    }
}
