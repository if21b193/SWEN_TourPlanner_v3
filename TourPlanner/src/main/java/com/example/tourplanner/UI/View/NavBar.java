package com.example.tourplanner.UI.View;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;

public class NavBar extends HBox {
    public NavBar() {
        MenuItem homeMenuItem = new MenuItem("Home");
        MenuItem aboutMenuItem = new MenuItem("About");
        MenuButton menuButton = new MenuButton("Menu", null, homeMenuItem, aboutMenuItem);

        // Add the MenuButton to the NavBar
        this.getChildren().addAll(menuButton);

        // Add any necessary styling, event handlers, etc. here
    }
}


