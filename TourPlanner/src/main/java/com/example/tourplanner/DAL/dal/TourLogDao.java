package com.example.tourplanner.DAL.dal;

import com.example.tourplanner.models.TourLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TourLogDao implements Dao<TourLog>{

    private List<TourLog> tourLogs = new ArrayList<>();


    @Override
    public Optional<TourLog> get(int id) {
        if(tourLogs.get(id) != null) {
            return Optional.ofNullable(tourLogs.get(id));
        }
        return Optional.empty();
    }

    @Override
    public List<TourLog> getAll() {
        return tourLogs;
    }

    @Override
    public void save(TourLog tourLog) {
        tourLogs.add(tourLog);
    }

    @Override
    public void update(TourLog tourLog) {

    }

    @Override
    public void delete(TourLog tourLog) {

    }
}
