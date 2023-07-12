package com.example.tourplanner.UI.View;


import com.example.tourplanner.UI.ViewModel.TourMapViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TourMapController {

    private final TourMapViewModel tourMapViewModel;
    private StringProperty imageURL = new SimpleStringProperty();

    @FXML
    public Image mapImage;
    @FXML
    public ImageView imageView;

    public TourMapController(TourMapViewModel tourMapViewModel) {
        this.tourMapViewModel = tourMapViewModel;
        }

    public void initialize() {
        imageURL.bindBidirectional(tourMapViewModel.imageURLProperty());
        imageURL.addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                imageView.setImage(new Image(newValue));
                imageView.fitHeightProperty().bind(imageView.getScene().getWindow().heightProperty());
                imageView.fitWidthProperty().bind(imageView.getScene().getWindow().widthProperty());
            }
        });
    }
}
