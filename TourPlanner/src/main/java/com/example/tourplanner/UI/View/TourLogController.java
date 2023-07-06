package com.example.tourplanner.UI.View;

import com.example.tourplanner.UI.ViewModel.TourListViewModel;
import com.example.tourplanner.UI.ViewModel.TourLogListViewModel;
import com.example.tourplanner.models.Tour;
import com.example.tourplanner.models.TourLog;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class TourLogController {
    @FXML
    public VBox tourLogView;
    @FXML
    public Button addTourLogButton;
    @FXML
    public Button updateTourLogButton;
    @FXML
    public Button deleteTourLogButton;

    @FXML
    private ListView<TourLog> listView;

    private final TourLogListViewModel tourLogListViewModel;

    public TourLogController(TourLogListViewModel tourLogListViewModel){
        this.tourLogListViewModel = tourLogListViewModel;
    }

    public TourLogListViewModel getTourLogListViewViewModel(){
        return this.tourLogListViewModel;
    }

    @FXML
    public void initialize() {
        listView.setItems(tourLogListViewModel.getObservableTourLogs());

        listView.setCellFactory(param -> new ListCell<TourLog>() {
            @Override
            protected void updateItem(TourLog tourLog, boolean empty) {
                super.updateItem(tourLog, empty);
                if (empty || tourLog == null) {
                    setText(null);
                } else {
                    setText(tourLog.getTour_id().toString());
                }
            }
        });
        listView.getSelectionModel().selectedItemProperty().addListener(tourLogListViewModel.getChangeListener());
    }

    public void addTourLog(ActionEvent actionEvent) {
        tourLogListViewModel.addTourLog();
        listView.getSelectionModel().selectLast();
    }


    public void modifyTourLog(ActionEvent actionEvent) {
        tourLogListViewModel.modifyTourLog(listView.getSelectionModel().getSelectedItem());
    }

    public void deleteTourLog(ActionEvent actionEvent) {
        tourLogListViewModel.deleteTourLog(listView.getSelectionModel().getSelectedItem());
    }



}
