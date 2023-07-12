package com.example.tourplanner.UI.View;

import com.example.tourplanner.UI.ViewModel.MainWindowViewModel;
import com.example.tourplanner.UI.ViewModel.TourListViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindowController {

    @FXML
    public VBox TourList;
    @FXML
    public TabPane details;
    public VBox exportButton;
    public AnchorPane importButton;
    public SplitPane searchBar;

    private final TourListViewModel tourListViewModel;

    private final MainWindowViewModel mainWindowViewModel;

    public MainWindowController(MainWindowViewModel mainWindowViewModel, TourListViewModel tourListViewModel){
        this.mainWindowViewModel = mainWindowViewModel;
        this.tourListViewModel = tourListViewModel;
    }

    public void initialize() {
        // You can access the listView controller here
    }

}