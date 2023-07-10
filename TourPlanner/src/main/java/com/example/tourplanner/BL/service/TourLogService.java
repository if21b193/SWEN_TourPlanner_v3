package com.example.tourplanner.BL.service;

import com.example.tourplanner.models.TourLogs;

import java.util.List;

public interface TourLogService {
    List<TourLogs> getAll();
    TourLogs getById(int id);
    void create(TourLogs tourLogs);
    void delete(TourLogs tourLogs);
    void update(TourLogs tourLogs);
    List<TourLogs> getAllFromTour(int tourID);
}
