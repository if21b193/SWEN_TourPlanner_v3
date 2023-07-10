package com.example.tourplanner.UI.View;

import com.example.tourplanner.UI.ViewModel.TourLogTableViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.Date;

public class TourLogTableController {
    @FXML
    public TableView<TourLogTableViewEntry> tourLogTable;
    @FXML
    public TableColumn<TourLogTableViewEntry, Date> dateColumn;
    @FXML
    public TableColumn<TourLogTableViewEntry, String> commentColumn;
    @FXML
    public TableColumn<TourLogTableViewEntry, Float> difficultyColumn;
    @FXML
    public TableColumn<TourLogTableViewEntry, String> timeColumn;
    @FXML
    public TableColumn<TourLogTableViewEntry, Float> ratingColumn;

    private final TourLogTableViewModel tourLogTableViewModel;

    public TourLogTableController(TourLogTableViewModel tourLogTableViewModel) {
        this.tourLogTableViewModel = tourLogTableViewModel;
    }
}
