/*package com.example.tourplanner.UI.View;

import com.example.tourplanner.UI.ViewModel.UpdateTourLogViewModel;
import com.example.tourplanner.UI.ViewModel.UpdateTourViewModel;
import com.example.tourplanner.models.Tour;
import com.example.tourplanner.models.TourLog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateTourLogController {

        private Stage stage;
        @FXML
        public Button cancel;

        @FXML
        public ChoiceBox<Float> difficulty;
        @FXML
        public ChoiceBox<Float> rating;

        @FXML
        public TextField comment;

        @FXML
        public TextField dateTime;
        @FXML
        public TextField totalTime;

        private final UpdateTourLogViewModel updateTourLogViewModel;

        public UpdateTourLogController(UpdateTourLogViewModel updateTourLogViewModel) {
            this.updateTourLogViewModel = updateTourLogViewModel;
        }

        public void initialize(){
            difficulty.valueProperty().bindBidirectional(updateTourLogViewModel.difficultyProperty());
            rating.valueProperty().bindBidirectional(updateTourLogViewModel.ratingProperty());
            comment.textProperty().bindBidirectional(updateTourLogViewModel.commentProperty());
            dateTime.textProperty().bindBidirectional(updateTourLogViewModel.dateTimeProperty());
            totalTime.textProperty().bindBidirectional(updateTourLogViewModel.totalTimeProperty());
            TourLog tourLog = updateTourLogViewModel.getSelectedTourLog();
            if(tourLog != null){
                fillInTourLogData(tourLog);
            }

        }

        private void fillInTourLogData(TourLog tourLog) {
            difficulty.setValue(tourLog.getDifficulty());
            rating.setValue(tourLog.getRating());
            comment.setText(tourLog.getComment());
            dateTime.setText(tourLog.getDateTime());
            totalTime.setText(tourLog.getTotalTime());
        }

        public void saveTourLog(ActionEvent actionEvent) throws IOException {
            String commentText = comment.getText();
            String dateTimeValue = dateTime.getText();
            String ratingValue = rating.getValue().toString();
            String difficultyValue = difficulty.getValue().toString();
            String totalTimeValue = totalTime.getText();
            updateTourLogViewModel.saveTourLog(commentText, dateTimeValue, ratingValue, difficultyValue, totalTimeValue);
            close();
        }

        private void close(){
            Stage stage = (Stage) modifyTourLog.getScene().getWindow();
            stage.close();
        }
        public void closeWindow(ActionEvent actionEvent) {
            close();
        }
}
*/

