package com.example.tourplanner.UI.View;

import com.example.tourplanner.UI.ViewModel.AddTourViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class AddTourController {
    @FXML
    public TextField from;
    @FXML
    public TextField to;
    @FXML
    public ChoiceBox transportationMode;
    @FXML
    public Button saveTour;
    @FXML
    public Button cancel;
    private final AddTourViewModel addTourViewModel;
    @FXML
    public TextField tourName;
    @FXML
    public TextField tourDescription;

    public AddTourController(AddTourViewModel addTourViewModel){
        this.addTourViewModel = addTourViewModel;
    }

    public void saveTour(ActionEvent actionEvent) {

    }

    public void closeWindow(ActionEvent actionEvent) {
    }
}
