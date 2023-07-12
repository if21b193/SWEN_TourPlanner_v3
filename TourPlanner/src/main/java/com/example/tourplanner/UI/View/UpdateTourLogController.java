package com.example.tourplanner.UI.View;

import com.example.tourplanner.UI.ViewModel.UpdateTourLogViewModel;
import com.example.tourplanner.models.TourLogs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Date;

public class UpdateTourLogController {


    @FXML
    public DatePicker datePicker;
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
    private static TourLogs tourLog;

    private final UpdateTourLogViewModel updateTourLogViewModel;
    public UpdateTourLogController(UpdateTourLogViewModel updateTourLogViewModel) {
        this.updateTourLogViewModel = updateTourLogViewModel;
    }

    @FXML
    public void initialize(){
        datePicker.valueProperty().bindBidirectional(updateTourLogViewModel.dateProperty());
        comment.textProperty().bindBidirectional(updateTourLogViewModel.commentProperty());
        difficulty.valueProperty().bindBidirectional(updateTourLogViewModel.difficultyProperty());
        totalTime.textProperty().bindBidirectional(updateTourLogViewModel.totalTimeProperty());
        rating.valueProperty().bindBidirectional(updateTourLogViewModel.ratingProperty());
        TourLogs tourLog = updateTourLogViewModel.getTourLog();
        if(tourLog != null){
            fillInTourLogData(tourLog);
        }
    }

    private void fillInTourLogData(TourLogs tourLog) {
        datePicker.setValue(tourLog.getDateTime().toLocalDate());
        comment.setText(tourLog.getComment());
        difficulty.setValue(String.valueOf(tourLog.getDifficulty()));
        totalTime.setText(tourLog.getTotalTime());
        rating.setValue(String.valueOf(tourLog.getRating()));
    }

    public void saveTourLog(ActionEvent actionEvent) {
        Date date = Date.valueOf(datePicker.getValue());
        String commentary = comment.getText();
        Float difficultyInput = Float.parseFloat(difficulty.getValue());
        String time = totalTime.getText();
        Float ratingInput = Float.parseFloat(rating.getValue());
        tourLog = updateTourLogViewModel.saveTourLog(date, commentary, difficultyInput, time, ratingInput);
        close();
    }

    private void close(){
        Stage stage = (Stage) saveTourLog.getScene().getWindow();
        stage.close();
    }

    public void closeWindow(ActionEvent actionEvent) {
        close();
    }

    public TourLogs getTourLog(){
        return tourLog;
    }
}

