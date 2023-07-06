package com.example.tourplanner.BL.service;

import com.example.tourplanner.models.Tour;
import com.example.tourplanner.models.TourLog;

import java.util.List;

public interface TourLogService {
    List<TourLog> getAll();
    TourLog getById(int id);
    void create(TourLog tourLog);
    void delete(TourLog tourLog);
    void update(TourLog tourLog);
}
