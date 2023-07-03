package com.example.tourplanner.UI.View;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class TourList extends ListView<String> {
        public TourList(ArrayList<String> tours) {
        super();
        this.setId("tourList");  // Set the CSS id
        this.getItems().addAll(tours);

    }
    public void addTour(String tour) {
        this.getItems().add(tour);
    }
}

