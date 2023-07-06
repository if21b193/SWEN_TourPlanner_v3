package com.example.tourplanner.BL.service;

import com.example.tourplanner.DAL.dal.dao.TourLogDao;

import com.example.tourplanner.models.TourLog;

import java.util.List;

public class ImplTourLogService implements TourLogService {

    private TourLogDao tourLogDao = new TourLogDao();
    public ImplTourLogService(){}

    @Override
    public List<TourLog> getAll() { return tourLogDao.getAll();}

    @Override
    public TourLog getById(int id) { return  tourLogDao.get(id).orElse(null);}
    @Override
    public void create (TourLog tourLog){ tourLogDao.save(tourLog);}

    @Override
    public void delete(TourLog tourLog) {
        tourLogDao.delete(tourLog);
    }

    @Override
    public void update(TourLog tourLog) {
        tourLogDao.update(tourLog);
    }
}
