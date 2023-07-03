package com.example.tourplanner.DAL.dal;

import com.example.tourplanner.models.TourLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TourLogDao implements Dao<TourLog>{

    private List<TourLog> tourLogs = new ArrayList<>();

    private int nextID = 0;

    public TourLogDao(){
        tourLogs.add(new TourLog(nextID++, "00:00", "comment", 1.0, "3.5hr", 3.5));
        tourLogs.add(new TourLog(nextID++, "00:01", "comment", 2.0, "3.5hr", 3.5));
        tourLogs.add(new TourLog(nextID++, "00:02", "comment", 3.0, "3.5hr", 3.5));
    }

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
    public void update(TourLog tourLog, List<?> params) {

    }

    @Override
    public void update(TourLog tourLog) {

    }

    @Override
    public void delete(TourLog tourLog) {

    }
}
