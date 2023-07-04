package com.example.tourplanner.UI.View;

import com.example.tourplanner.UI.ViewModel.MainViewModel;
import javafx.fxml.FXML;

public class MainController {

    @FXML
    private ListViewController listViewController;

    private final MainViewModel mainViewModel;

    public MainController(MainViewModel mainViewModel){
        this.mainViewModel = mainViewModel;
    }

    public void initialize() {
        // You can access the listView controller here
        System.out.println(listViewController);
    }

    public ListViewController getTourListController() {
        return listViewController;
    }
}