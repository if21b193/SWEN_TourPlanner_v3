package com.example.tourplanner.UI.View;

import com.example.tourplanner.UI.ViewModel.UpdateTourViewModel;
import com.example.tourplanner.models.Tour;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateTourController {
    private Stage stage;
    @FXML
    public Button cancel;
    @FXML
    public Button modifyTour;
    @FXML
    public ChoiceBox<String> transportationMode;
    @FXML
    public TextField to;
    @FXML
    public TextField from;
    @FXML
    public TextField tourDescription;
    @FXML
    public TextField tourName;

    private static Tour tour;

    private final UpdateTourViewModel updateTourViewModel;

    public UpdateTourController(UpdateTourViewModel updateTourViewModel) {
        this.updateTourViewModel = updateTourViewModel;
    }

    public UpdateTourViewModel getUpdateTourViewModel() {
        return updateTourViewModel;
    }

    //for some reason binding works here, but far be it from me to change a running system
    public void initialize(){
        transportationMode.valueProperty().bindBidirectional(updateTourViewModel.transportTypeProperty());
        to.textProperty().bindBidirectional(updateTourViewModel.endProperty());
        from.textProperty().bindBidirectional(updateTourViewModel.startProperty());
        tourDescription.textProperty().bindBidirectional(updateTourViewModel.descriptionProperty());
        tourName.textProperty().bindBidirectional(updateTourViewModel.nameProperty());
        Tour tour = updateTourViewModel.getSelectedTour();
        if(tour != null){
            fillInTourData(tour);
        }
    }


    private void fillInTourData(Tour tour) {
        transportationMode.setValue(tour.getTransportType());
        to.setText(tour.getTo());
        from.setText(tour.getFrom());
        tourDescription.setText(tour.getDescription());
        tourName.setText(tour.getName());
    }

    public void saveTour(ActionEvent actionEvent) throws IOException {
        String transport = transportationMode.getValue();
        String end = to.getText().replace(" ", "+");
        String start = from.getText().replace(" ", "+");
        String description = tourDescription.getText();
        String name = tourName.getText();
        tour = updateTourViewModel.saveTour(transport, end, start, description, name);
        close();
    }

    public Tour getTour(){
        return tour;
    }

    private void close(){
        Stage stage = (Stage) modifyTour.getScene().getWindow();
        stage.close();
    }
    public void closeWindow(ActionEvent actionEvent) {
        close();
    }
}
