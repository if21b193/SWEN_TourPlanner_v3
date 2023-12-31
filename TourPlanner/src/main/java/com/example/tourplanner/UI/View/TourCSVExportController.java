package com.example.tourplanner.UI.View;

import com.example.tourplanner.BL.report.TourCSVExport;
import com.example.tourplanner.DAL.dal.dao.TourDao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
public class TourCSVExportController {
    @FXML
    private Button exportButton;

    private final TourCSVExport exporter;

    public TourCSVExportController() {
        TourDao tourDao = new TourDao();
        this.exporter = new TourCSVExport(tourDao);
    }

    @FXML
    protected void exportToCSV(ActionEvent event) {
        exporter.export("Tours.csv");
    }
}
