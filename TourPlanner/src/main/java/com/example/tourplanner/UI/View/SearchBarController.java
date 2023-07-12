package com.example.tourplanner.UI.View;

import com.example.tourplanner.UI.ViewModel.SearchBarViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SearchBarController {
    @FXML
    public TextField searchInputField;
    @FXML
    public Button searchButton;
    @FXML
    public Button clearButton;
    private final SearchBarViewModel searchBarViewModel;

    public SearchBarController(SearchBarViewModel searchBarViewModel){
        this.searchBarViewModel = searchBarViewModel;
    }
    @FXML
    public void initialize(){

    }

    public void searchButtonClicked(ActionEvent actionEvent) {
        searchBarViewModel.searchFor(searchInputField.getText());
    }

    public void clearSearchField(ActionEvent actionEvent) {
        searchInputField.setText("");
        searchBarViewModel.searchFor("");
    }
}
