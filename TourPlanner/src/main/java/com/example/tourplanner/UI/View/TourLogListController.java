package com.example.tourplanner.UI.View;

import com.example.tourplanner.FXMLDependencyInjection;
import com.example.tourplanner.UI.ViewModel.ShareData.EventPublisher;
import com.example.tourplanner.UI.ViewModel.ShareData.SharedTourEvent;
import com.example.tourplanner.UI.ViewModel.TourListViewModel;
import com.example.tourplanner.UI.ViewModel.TourLogListViewModel;
import com.example.tourplanner.models.Tour;
import com.example.tourplanner.models.TourLog;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.Flow;

public class TourLogListController {
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

    private ListView<Tour> tourListView;

    private final TourLogListViewModel tourLogListViewModel;
    private final TourListViewModel tourListViewModel;

    private final EventPublisher publisher;

    public TourLogListController(TourLogListViewModel tourLogListViewModel, TourListViewModel tourListViewModel, EventPublisher publisher){
        this.tourLogListViewModel = tourLogListViewModel;
        this.tourListViewModel = tourListViewModel;
        this.publisher = publisher;
    }

    public void setTourListView(ListView<Tour> listView){
        this.tourListView = listView;
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

    }

    private Stage setUpScene(FXMLLoader fxmlLoader){
        Scene newScene;
        try {
            newScene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage newStage = new Stage();
        newStage.initOwner(listView.getScene().getWindow());
        newStage.setScene(newScene);
        return newStage;
    }

    public void addTourLog(ActionEvent actionEvent) {
        //publisher.publishToSingle(new SharedTourEvent(tourLogListViewModel.getTour()), "AddTourLogViewModel");
        FXMLLoader loader = FXMLDependencyInjection.getLoader("addTourLogMask.fxml", Locale.GERMAN);
        Stage stage = setUpScene(loader);
        stage.setTitle("Add TourLog");
        stage.showAndWait();
        TourLog tourLog = loader.<AddTourLogController>getController().getTourLog();
        if(tourLog != null){
            tourLogListViewModel.addTourLog(tourLog);
        }
        listView.getSelectionModel().selectLast();
        stage.close();
    }


    public void modifyTourLog(ActionEvent actionEvent) {
        tourLogListViewModel.modifyTourLog(listView.getSelectionModel().getSelectedItem());
    }

    public void deleteTourLog(ActionEvent actionEvent) {
        tourLogListViewModel.deleteTourLog(listView.getSelectionModel().getSelectedItem());
    }



}
