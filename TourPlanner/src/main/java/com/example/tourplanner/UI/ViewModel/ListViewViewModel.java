package com.example.tourplanner.UI.ViewModel;

import com.example.tourplanner.BL.service.TourService;
import com.example.tourplanner.models.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ListViewViewModel {
    private final TourService tourService;

    private ObservableList<Tour> observableList = FXCollections.observableArrayList();

    public ListViewViewModel(TourService tourService){
        this.tourService = tourService;
        setTours(this.tourService.getAll());
    }

    private void setTours(List<Tour> tours) {
        observableList.clear();
        observableList.addAll(tours);
    }

    public ObservableList<Tour> getObservableTours() {
        return observableList;
    }
}
