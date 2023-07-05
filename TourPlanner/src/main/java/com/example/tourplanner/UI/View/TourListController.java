package com.example.tourplanner.UI.View;
import com.example.tourplanner.UI.ViewModel.ShareData.EventListener;
import com.example.tourplanner.UI.ViewModel.ShareData.SharedTourEvent;
import com.example.tourplanner.UI.ViewModel.TourListViewModel;
import com.example.tourplanner.models.Tour;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

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
        tourListViewModel.addTour();
        listView.getSelectionModel().selectLast();
    }


    public void modifyTour(ActionEvent actionEvent) {
        tourListViewModel.modifyTour(listView.getSelectionModel().getSelectedItem());
    }

    public void deleteTour(ActionEvent actionEvent) {
        tourListViewModel.deleteTour(listView.getSelectionModel().getSelectedItem());
    }

}
