package com.example.tourplanner.UI.View;


import com.example.tourplanner.UI.ViewModel.TourMapViewModel;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TourMapController {

    private final TourMapViewModel tourMapViewModel;

    @FXML
    public Image mapImage;
    @FXML
    public ImageView imageView;

    public TourMapController(TourMapViewModel tourMapViewModel) {
        this.tourMapViewModel = tourMapViewModel;
        }

    public void initialize(){
    }

    public void setMapImage() {
        String newUrl = tourMapViewModel.getMapDetails();
        if(newUrl != null){
            imageView.setImage(new Image(newUrl));
            imageView.fitHeightProperty().bind(imageView.getScene().getWindow().heightProperty());
            imageView.fitWidthProperty().bind(imageView.getScene().getWindow().widthProperty());
        }
    }

}
