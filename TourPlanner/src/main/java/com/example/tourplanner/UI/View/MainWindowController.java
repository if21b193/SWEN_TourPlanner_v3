package com.example.tourplanner.UI.View;

import com.example.tourplanner.UI.ViewModel.MainWindowViewModel;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class MainWindowController {

    @FXML
    public VBox TourList;
    @FXML
    private TourListController tourListController;

    private final MainWindowViewModel mainWindowViewModel;

    public MainWindowController(MainWindowViewModel mainWindowViewModel){
        this.mainWindowViewModel = mainWindowViewModel;
    }

    public void initialize() {
        // You can access the listView controller here
        System.out.println(tourListController);
    }

    public TourListController getTourListController() {
        return tourListController;
    }
}