package com.example.tourplanner.UI.View;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class listViewController {

    @FXML
    private ListView<String> listView;

    private ObservableList<String> tours;

    @FXML
    public void initialize() {
        tours = FXCollections.observableArrayList("Tour 1", "Tour 2", "Tour 3");
        listView.setItems(tours);
    }
}
