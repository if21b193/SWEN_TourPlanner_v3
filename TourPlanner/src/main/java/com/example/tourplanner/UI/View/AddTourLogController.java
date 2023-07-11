package com.example.tourplanner.UI.View;

import com.example.tourplanner.UI.ViewModel.AddTourLogViewModel;
import com.example.tourplanner.models.TourLogs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;

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
    static TourLogs tourLogs;

    public AddTourLogController(AddTourLogViewModel addTourLogViewModel){
        this.addTourLogViewModel = addTourLogViewModel;
    }

    //saving a TourLog in the database, works similar/the same as the AddTourController
    public void saveTourLog(ActionEvent actionEvent) throws IOException {
        try {
            Date dateTimeText = Date.valueOf(datePicker.getValue());
            String commentText = comment.getText();
            Float difficultyInput = Float.parseFloat(difficulty.getValue());
            String totalTimeValue = totalTime.getText();
            Float ratingInput = Float.parseFloat(rating.getValue());
            validateTourData(dateTimeText, commentText, difficultyInput, totalTimeValue, ratingInput);
            tourLogs = addTourLogViewModel.addTourLog(dateTimeText, commentText, difficultyInput, totalTimeValue, ratingInput);

            clearTextFields();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private boolean validateTourData(Date dateTimeText, String comment, Float difficultyInput, String totalTime, Float ratingInput) {
        // Check if any field is empty
        if (dateTimeText == null || comment.isEmpty() || Float.toString(difficultyInput).isEmpty() || totalTime == null || Float.toString(ratingInput).isEmpty()) {
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

    public TourLogs getTourLog() {
        return tourLogs;
    }
}
