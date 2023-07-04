package com.example.tourplanner.BL.service;

import com.example.tourplanner.models.Tour;

import java.util.List;

public interface TourService {
    List<Tour> getAll();
    void create(Tour tour);
    void delete(Tour tour);
    void update(Tour tour);
}
