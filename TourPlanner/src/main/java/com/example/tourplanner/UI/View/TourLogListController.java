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
    public Button updateTourLogButton;
    @FXML
    public Button deleteTourLogButton;
    @FXML
    public Pane tourLogTable;
    @FXML
    public TourLogTableController tourLogTableController;

    @FXML
    private ListView<TourLogs> listView;

    private final TourLogListViewModel tourLogListViewModel;

    private final EventPublisher publisher;

    public TourLogListController(TourLogListViewModel tourLogListViewModel, EventPublisher publisher){
        this.tourLogListViewModel = tourLogListViewModel;
        this.publisher = publisher;
    }

    public TourLogListViewModel getTourLogListViewModel(){
        return tourLogListViewModel;
    }

    @FXML
    public void initialize() {
        listView.setItems(tourLogListViewModel.getObservableTourLogs());
        listView.setCellFactory(param -> new ListCell<TourLogs>() {
            @Override
            protected void updateItem(TourLogs tourLogs, boolean empty) {
                super.updateItem(tourLogs, empty);
                if (empty || tourLogs == null) {
                    setText(null);
                } else {
                    setText(tourLogs.getTour_id().toString());
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

    //creating a new window and getting pack the new tourLog so it can be added to the listView
    public void addTourLog(ActionEvent actionEvent) {
        FXMLLoader loader = FXMLDependencyInjection.getLoader("View/addTourLogMask.fxml", Locale.GERMAN);
        Stage stage = setUpScene(loader);
        stage.setTitle("Add TourLog");
        stage.showAndWait();
        TourLogs tourLogs = loader.<AddTourLogController>getController().getTourLog();
        if(tourLogs != null){
            tourLogListViewModel.addTourLog(tourLogs);
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

    public void fillInTourLogs() {
        tourLogTableController.addToursFromTour();
    }
}
