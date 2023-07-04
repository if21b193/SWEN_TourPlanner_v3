package com.example.tourplanner.UI.View;
import com.example.tourplanner.FXMLDependencyInjection;
import com.example.tourplanner.UI.ViewModel.TourListViewModel;
import com.example.tourplanner.models.Tour;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class TourListController {

    @FXML
    public VBox tourView;
    @FXML
    public Button addTourButton;
    @FXML
    public Button updateTourButton;
    @FXML
    public Button deleteTourButton;

    @FXML
    private ListView<Tour> listView;

    private final TourListViewModel tourListViewModel;

    public TourListController(TourListViewModel tourListViewModel){
        this.tourListViewModel = tourListViewModel;
    }

    public TourListViewModel getListViewViewModel(){
        return this.tourListViewModel;
    }

    //private ObservableList<String> tours;

    @FXML
    public void initialize() {
        /*tours = FXCollections.observableArrayList("Tour 1", "Tour 2", "Tour 3");
        listView.setItems(tours);*/
        listView.setItems(tourListViewModel.getObservableTours());
        listView.setCellFactory(param -> new ListCell<Tour>() {
            @Override
            protected void updateItem(Tour tour, boolean empty) {
                super.updateItem(tour, empty);
                if (empty || tour == null) {
                    setText(null);
                } else {
                    setText(tour.getName());
                }
            }
        });
    }

    public void addTour(ActionEvent actionEvent) {
        tourListViewModel.addTour();
    }

    public void updateTour(ActionEvent actionEvent) {
    }

    public void deleteTour(ActionEvent actionEvent) {
    }
}
