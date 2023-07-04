package com.example.tourplanner.BL.service;

import com.example.tourplanner.DAL.dal.dao.TourDao;
import com.example.tourplanner.models.Tour;

import java.util.List;

public class ImplTourService implements TourService {

    private TourDao tourDao = new TourDao();

    public ImplTourService() {
    }

    @Override
    public List<Tour> getAll() {
        return tourDao.getAll();
    }

    @Override
    public void create(Tour tour) {
        tourDao.save(tour);
    }

    @Override
    public void delete(Tour tour) {
        tourDao.delete(tour);
    }

    @Override
    public void update(Tour tour) {
        tourDao.update(tour);
    }
}
