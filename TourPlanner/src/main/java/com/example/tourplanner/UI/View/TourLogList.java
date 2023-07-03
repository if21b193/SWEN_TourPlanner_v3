package com.example.tourplanner.UI.View;

import javafx.scene.control.ListView;

public class TourLogList extends ListView<String> {
    public TourLogList(String[] tourLogs) {
        this.getItems().addAll(tourLogs);
    }
}