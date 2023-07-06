package com.example.tourplanner.UI.View;

import com.example.tourplanner.UI.ViewModel.AddTourLogViewModel;
import com.example.tourplanner.UI.ViewModel.AddTourViewModel;
import com.example.tourplanner.models.TourLog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;

public class AddTourLogController {
    @FXML
    public DatePicker datePicker;
    private Stage stage;
    @FXML
    public TextField comment;
    @FXML
    public ChoiceBox<String> difficulty;
    @FXML
    public TextField totalTime;
    @FXML
    public ChoiceBox<String> rating;
    @FXML
    public Button saveTourLog;
    @FXML
    public Button cancel;
    private final AddTourLogViewModel addTourLogViewModel;

    static TourLog tourLog;

    public AddTourLogController(AddTourLogViewModel addTourLogViewModel){
        this.addTourLogViewModel = addTourLogViewModel;
    }
    public void saveTourLog(ActionEvent actionEvent) throws IOException {
        try {
            String dateTimeText = datePicker.getAccessibleText();
            String commentText = comment.getText();
            Float difficultyInput = Float.parseFloat(difficulty.getValue());
            String totalTimeValue = totalTime.getText();
            Float ratingInput = Float.parseFloat(rating.getValue());
            validateTourData(dateTimeText, commentText, difficultyInput, totalTimeValue, ratingInput);
            tourLog = addTourLogViewModel.addTourLog(dateTimeText, commentText, difficultyInput, totalTimeValue, ratingInput);
            clearTextFields();
        } catch (Exception e) {
            System.out.println("Error miss girl");
        }


    }

    private boolean validateTourData(String dateTimeText, String comment, Float difficultyInput, String totalTime, Float ratingInput) {
        // Check if any field is empty
        if (dateTimeText.isEmpty() || comment.isEmpty() || Float.toString(difficultyInput).isEmpty() || totalTime.isEmpty() || Float.toString(ratingInput).isEmpty()) {
            return false;
        }
        // Validate specific criteria for each field
        // Validate if datetime is really datetime
        // validate if total time is a float

        return true; // Input is valid
    }

    public void closeWindow(ActionEvent actionEvent) {
        clearTextFields();
        Stage stage = (Stage)saveTourLog.getScene().getWindow();
        stage.close();
    }
    private void clearTextFields() {
        comment.clear();
        difficulty.setValue(null);
        totalTime.clear();
    }

    public TourLog getTourLog() {
        return tourLog;
    }
}
