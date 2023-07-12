package com.example.tourplanner.UI.View;

import com.example.tourplanner.FXMLDependencyInjection;
import com.example.tourplanner.UI.ViewModel.ShareData.SharedTourLogEvent;
import com.example.tourplanner.UI.ViewModel.ShareData.TourLogEventPublisher;
import com.example.tourplanner.UI.ViewModel.TourLogTableViewModel;
import com.example.tourplanner.models.TourLogs;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

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

    private final TourLogEventPublisher publisher;
    public Pane tourLogTablePane;

    public TourLogTableController(TourLogTableViewModel tourLogTableViewModel, TourLogEventPublisher publisher) {
        this.tourLogTableViewModel = tourLogTableViewModel;
        this.publisher = publisher;
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

    public void addTourLog() {
        FXMLLoader loader = FXMLDependencyInjection.getLoader("View/addTourLogMask.fxml", Locale.GERMAN);
        Stage stage = setUpScene(loader);
        stage.setTitle("Add TourLog");
        stage.showAndWait();
        TourLogs tourLogs = loader.<AddTourLogController>getController().getTourLog();
        if(tourLogs != null){
            TourLogTableViewEntry entry = tourLogTableViewModel.getEntryFromTourLog(tourLogs);
            tourLogTable.getItems().add(entry);
        }
        stage.close();
    }

    public void modifyTourLog() {
        TourLogTableViewEntry selectedItem = tourLogTable.getSelectionModel().getSelectedItem();
        if(selectedItem == null){
            return;
        }
        TourLogs tourLog = tourLogTableViewModel.getTourLogById(selectedItem.getId());
        publisher.publishToSingle(new SharedTourLogEvent(tourLog), "UpdateTourLogViewModel");
        FXMLLoader loader = FXMLDependencyInjection.getLoader("View/updateTourLogMask.fxml", Locale.GERMAN);
        Stage stage = setUpScene(loader);
        stage.setTitle("Modify TourLog");
        stage.showAndWait();
        TourLogs tourLogs = loader.<UpdateTourLogController>getController().getTourLog();
        if(tourLogs != null){
            tourLogTableViewModel.modifyTourLog();
        }
    }

    public void deleteTourLog() {
        TourLogTableViewEntry entry = tourLogTable.getSelectionModel().getSelectedItem();
        tourLogTable.getItems().remove(entry);
    }

    private Stage setUpScene(FXMLLoader fxmlLoader){
        Scene newScene;
        try {
            newScene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage newStage = new Stage();
        newStage.initOwner(tourLogTable.getScene().getWindow());
        newStage.setScene(newScene);
        return newStage;
    }

}
