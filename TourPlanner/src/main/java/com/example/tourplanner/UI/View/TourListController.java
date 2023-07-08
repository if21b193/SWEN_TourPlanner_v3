package com.example.tourplanner.UI.View;
import com.example.tourplanner.FXMLDependencyInjection;
import com.example.tourplanner.UI.ViewModel.ShareData.EventPublisher;
import com.example.tourplanner.UI.ViewModel.ShareData.SharedTourEvent;
import com.example.tourplanner.UI.ViewModel.TourListViewModel;
import com.example.tourplanner.UI.ViewModel.TourLogListViewModel;
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
        //setup the view so the name of the tours is shown
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

        //make sure selection is usable in this list
        listView.getSelectionModel().selectedItemProperty().addListener(tourListViewModel.getChangeListener());
        //send out an event whenever selection is changed, so the AddTourLogViewModel knows which tour it is supposed to work with

        //TODO: Still needs work to figure out whether there's a more elegant solution
        listView.getSelectionModel().selectedItemProperty().addListener((observableValue, tour, t1) -> {
            publisher.publishEvent(new SharedTourEvent(t1));
        });
    }

    public void addTour(ActionEvent actionEvent) {
        //opens up a new window
        FXMLLoader fxmlLoader = FXMLDependencyInjection.getLoader("addTourMask.fxml", Locale.GERMAN);
        Stage stage = setUpScene(fxmlLoader);
        stage.setTitle("Add a new tour");
        stage.showAndWait();
        //allows us to wait for a result from the stage
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

    //works similar to the addTour, with the help of the publisher, the selected tour is shown to the UpdateTourViewModel
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
        if(listView.getSelectionModel().getSelectedItem() == null){
            return;
        }
        tourListViewModel.deleteTour(listView.getSelectionModel().getSelectedItem());
    }

}
