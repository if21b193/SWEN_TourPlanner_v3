package com.example.tourplanner.UI.View;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ButtonLayout extends VBox {
    private final TourList tourList;

    public ButtonLayout(TourList tourList) {
        super(10);  // 10px spacing
        this.tourList = tourList;

        Button addButton = new Button("Add Tour");
        Button deleteButton = new Button("Delete Tour");
        Button editButton = new Button("Edit Tour");

        addButton.setMaxWidth(Double.MAX_VALUE);
        deleteButton.setMaxWidth(Double.MAX_VALUE);
        editButton.setMaxWidth(Double.MAX_VALUE);

        // Set VBox alignment to center
        this.setAlignment(Pos.CENTER);
        addButton.setOnAction(e -> addNewTour("New Tour"));  // Add a new tour called "New Tour" each time the button is clicked

        this.getChildren().addAll(addButton, deleteButton, editButton);
    }

    private void addNewTour(String tour) {
        tourList.addTour(tour);
    }
}
