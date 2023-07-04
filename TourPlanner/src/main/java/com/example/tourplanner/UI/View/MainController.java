package com.example.tourplanner.UI.View;

import javafx.fxml.FXML;

public class MainController {

        @FXML
        private listViewController listViewController;

        public void initialize() {
            // You can access the listView controller here
            System.out.println(listViewController);
        }

        public listViewController getTourListController() {
            return listViewController;
        }
    }

