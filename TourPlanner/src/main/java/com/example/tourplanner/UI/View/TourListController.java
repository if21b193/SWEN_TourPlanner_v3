package com.example.tourplanner.UI.View;
import com.example.tourplanner.FXMLDependencyInjection;
import com.example.tourplanner.UI.ViewModel.ShareData.EventPublisher;
import com.example.tourplanner.UI.ViewModel.ShareData.SharedTourEvent;
import com.example.tourplanner.UI.ViewModel.TourListViewModel;
import com.example.tourplanner.models.Tour;
import javafx.event.ActionEvent;
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

    private final EventPublisher publisher;

    public TourListController(TourListViewModel tourListViewModel, EventPublisher publisher){
        this.tourListViewModel = tourListViewModel;
        this.publisher = publisher;
    }

    public TourListViewModel getListViewViewModel(){
        return this.tourListViewModel;
    }

    @FXML
    public void initialize() {
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
        listView.getSelectionModel().selectedItemProperty().addListener(tourListViewModel.getChangeListener());
    }

    public void addTour(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = FXMLDependencyInjection.getLoader("addTourMask.fxml", Locale.GERMAN);
        Stage stage = setUpScene(fxmlLoader);
        stage.setTitle("Add a new tour");
        stage.showAndWait(); //allows us to wait for a result from the stage
        Tour tour = fxmlLoader.<AddTourController>getController().getTour();
        if(tour != null){
            tourListViewModel.addTour(tour);
        }
        listView.getSelectionModel().selectLast();
        stage.close();
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

    public void modifyTour(ActionEvent actionEvent) {
        Tour tour = listView.getSelectionModel().getSelectedItem();
        publisher.publishToSingle(new SharedTourEvent(tour), "UpdateTourViewModel");
        FXMLLoader fxmlLoader = FXMLDependencyInjection.getLoader("UpdateTourMask.fxml", Locale.GERMAN);
        Stage stage = setUpScene(fxmlLoader);
        stage.setTitle("Modify tour");
        stage.showAndWait();
        tour = fxmlLoader.<UpdateTourController>getController().getTour();
        tourListViewModel.modifyTour(tour);
        stage.close();
    }

    public void deleteTour(ActionEvent actionEvent) {
        tourListViewModel.deleteTour(listView.getSelectionModel().getSelectedItem());
    }

}
