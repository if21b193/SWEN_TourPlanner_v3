package com.example.tourplanner.UI.View;

import com.example.tourplanner.UI.ViewModel.AddTourViewModel;
import com.example.tourplanner.models.Tour;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddTourController {
    private Stage stage;
    @FXML
    public TextField from;
    @FXML
    public TextField to;
    @FXML
    public ChoiceBox<String> transportationMode;
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


    /*private void bindViewModel() {
        tourName.textProperty().bindBidirectional(addTourViewModel.tourNameProperty());
        tourDescription.textProperty().bindBidirectional(addTourViewModel.tourDescriptionProperty());
        from.textProperty().bindBidirectional(addTourViewModel.fromProperty());
        to.textProperty().bindBidirectional(addTourViewModel.toProperty());
        transportationMode.valueProperty().bindBidirectional(addTourViewModel.transportationModeProperty());
    }*/

    private void initialize(){
        //bindViewModel();
    }
// saveTour nimmt als parameter ein actions event, mit getText() kriegen wir den User Input. Dann wird mittels AddTourViewmodel die Tour in die Datebank reingegeben
    public void saveTour(ActionEvent actionEvent) throws IOException {
        try {
            String name = tourName.getText();
            String start = from.getText();
            String end = to.getText();
            String transportation = transportationMode.getValue();
            String description = tourDescription.getText();
            validateTourData(name, start, end, transportation, description);
            addTourViewModel.addTour(name, start, end, transportation, description);
            clearTextFields();
        } catch (Exception e) {
            System.out.println("Error miss girl");
        }


    }


    private void clearTextFields() {
        tourName.clear();
        from.clear();
        to.clear();
        transportationMode.setValue(null);
        tourDescription.clear();
    }

    public void closeWindow(ActionEvent actionEvent) {
        clearTextFields();
        Stage stage = (Stage)saveTour.getScene().getWindow();
        stage.close();
    }


    private boolean validateTourData(String name, String start, String end, String transportation, String description) {
        // Check if any field is empty
        if (name.isEmpty() || start.isEmpty() || end.isEmpty() || transportation.isEmpty() || description.isEmpty()) {
            return false;
        }
        // Validate specific criteria for each field
        if (name.length() > 50) {
            return false; // Name exceeds maximum length
        }

        return true; // Input is valid
    }

}
