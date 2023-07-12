package com.example.tourplanner.BL.service;

import com.example.tourplanner.DAL.dal.dao.TourLogDao;

import com.example.tourplanner.models.TourLogs;

import java.util.List;

public class ImplTourLogService implements TourLogService {

    private final TourLogDao tourLogDao = new TourLogDao();
    public ImplTourLogService(){}

    @Override
    public List<TourLogs> getAll() { return tourLogDao.getAll();}

    @Override
    public TourLogs getById(int id) { return  tourLogDao.get(id).orElse(null);}
    @Override
    public void create (TourLogs tourLogs){ tourLogDao.save(tourLogs);}

    @Override
    public void delete(TourLogs tourLogs) {
        tourLogDao.delete(tourLogs);
    }

    @Override
    public void update(TourLogs tourLogs) {
        tourLogDao.update(tourLogs);
    }

    @Override
    public List<TourLogs> getAllFromTour(int tourID) {
        return tourLogDao.getAllFromTour(tourID);
    }
}
