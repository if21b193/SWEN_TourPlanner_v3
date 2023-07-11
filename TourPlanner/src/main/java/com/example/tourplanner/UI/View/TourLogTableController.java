package com.example.tourplanner.UI.View;

import com.example.tourplanner.UI.ViewModel.TourLogTableViewModel;
import com.example.tourplanner.models.TourLogs;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.util.Date;
import java.util.List;

public class TourLogTableController {
    @FXML
    public TableView<TourLogTableViewEntry> tourLogTable;
    @FXML
    public TableColumn<TourLogTableViewEntry, Date> dateColumn;
    @FXML
    public TableColumn<TourLogTableViewEntry, String> commentColumn;
    @FXML
    public TableColumn<TourLogTableViewEntry, Float> difficultyColumn;
    @FXML
    public TableColumn<TourLogTableViewEntry, String> timeColumn;
    @FXML
    public TableColumn<TourLogTableViewEntry, Float> ratingColumn;

    private final TourLogTableViewModel tourLogTableViewModel;
    public Pane tourLogTablePane;

    public TourLogTableController(TourLogTableViewModel tourLogTableViewModel) {
        this.tourLogTableViewModel = tourLogTableViewModel;
    }

    public TourLogTableViewModel getTourLogTableViewModel() {
        return tourLogTableViewModel;
    }
    @FXML
    public void initialize(){
        tourLogTable.setItems(tourLogTableViewModel.getTourLogEntries());
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
        difficultyColumn.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
    }

    public void addToursFromTour() {
        List<TourLogTableViewEntry> logsList = tourLogTableViewModel.getTourLogEntriesUnobservable();
        tourLogTable.getItems().clear();
        tourLogTable.getItems().addAll(logsList);
    }
}
