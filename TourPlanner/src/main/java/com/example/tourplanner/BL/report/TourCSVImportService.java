package com.example.tourplanner.BL.report;
import com.example.tourplanner.models.Tour;
import com.example.tourplanner.DAL.dal.dao.TourDao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class TourCSVImportService {
    @FXML
    private Button importButton;
    private TourDao tourDao;

    public TourCSVImportService(TourDao tourDao) {
        this.tourDao = tourDao;
    }

    @FXML
    public void importData() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                BufferedReader csvReader = new BufferedReader(new FileReader(selectedFile));
                String row;
                while ((row = csvReader.readLine()) != null) {
                    String[] data = row.split(",");
                    Tour tour = new Tour(data[0], data[1], data[2], data[3], data[4], Float.parseFloat(data[5]), data[6], data[7]);
                    tourDao.save(tour);
                }
                csvReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

