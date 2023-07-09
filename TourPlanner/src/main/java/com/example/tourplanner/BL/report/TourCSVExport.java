package com.example.tourplanner.BL.report;
import com.example.tourplanner.DAL.dal.dao.TourDao;
import com.example.tourplanner.models.Tour;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;

public class TourCSVExport {

        private TourDao tourDao;

        public TourCSVExport(TourDao tourDao){
            this.tourDao = tourDao;
        }

        public void export(String fileName) {
            try(PrintWriter writer = new PrintWriter(new FileWriter(fileName))){
                // Write the header line
                writer.println("ID,Name,Start,End,Description,Distance");

                // Write data lines
                List<Tour> tours = tourDao.getAll();
                for (Tour tour : tours) {
                    writer.println(String.format("%d,%s,%s,%s,%s,%f",
                            tour.getId(),
                            tour.getName(),
                            tour.getTo(),
                            tour.getFrom(),
                            tour.getDescription(),
                            tour.getDistance()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


