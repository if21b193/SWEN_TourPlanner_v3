package com.example.tourplanner.UI.View;

import com.example.tourplanner.FXMLDependencyInjection;
import com.example.tourplanner.UI.ViewModel.ShareData.EventPublisher;
import com.example.tourplanner.UI.ViewModel.TourListViewModel;
import com.example.tourplanner.UI.ViewModel.TourLogListViewModel;
import com.example.tourplanner.models.Tour;
import com.example.tourplanner.models.TourLogs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class TourLogListController {
    @FXML
    public VBox tourLogView;
    @FXML
    public Button addTourLogButton;
    @FXML
    public Button deleteTourLogButton;
    @FXML
    public Pane tourLogTable;
    @FXML
    public TourLogTableController tourLogTableController;

    private final TourLogListViewModel tourLogListViewModel;

    private final EventPublisher publisher;
    public Button modifyTourLog;

    public TourLogListController(TourLogListViewModel tourLogListViewModel, EventPublisher publisher){
        this.tourLogListViewModel = tourLogListViewModel;
        this.publisher = publisher;
    }

    public TourLogListViewModel getTourLogListViewModel(){
        return tourLogListViewModel;
    }

    @FXML
    public void initialize() {
    }



    //creating a new window and getting pack the new tourLog so it can be added to the listView
    public void addTourLog(ActionEvent actionEvent) {
        tourLogTableController.addTourLog();
    }

    public void modifyTourLog(ActionEvent actionEvent) {
        tourLogTableController.modifyTourLog();
    }

    public void deleteTourLog(ActionEvent actionEvent) {
        tourLogTableController.deleteTourLog();
    }

    public void fillInTourLogs() {
        tourLogTableController.addToursFromTour();
    }
}
