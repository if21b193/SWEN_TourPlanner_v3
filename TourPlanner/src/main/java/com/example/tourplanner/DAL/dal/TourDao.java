package com.example.tourplanner.DAL.dal;

import com.example.tourplanner.models.Tour;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TourDao implements Dao<Tour>{

    private List<Tour> tours = new ArrayList<>();

    private int nextID = 0;

   /* public TourDao(){
        tours.add(new Tour(nextID++, "tour1", "...", "..", "..", "..", 20.3, "...", "..."));
        tours.add(new Tour(nextID++, "tour2", "...", "..", "..", "..", 20.3, "...", "..."));
        tours.add(new Tour(nextID++, "tour1", "...", "..", "..", "..", 20.3, "...", "..."));
    }*/

    @Override
    public Optional<Tour> get(int id) {
        if(tours.get(id) != null){
            return Optional.ofNullable(tours.get(id));
        }
        return Optional.empty();
    }

    @Override
    public List<Tour> getAll() {
        return tours;
    }

    @Override
    public void save(Tour tour) {
        tours.add(tour);
    }

    @Override
    public void update(Tour tour, List<?> params) {
        tour.setName(Objects.requireNonNull(params.get(1), "Name cannot be null").toString());
        tour.setDescription(Objects.requireNonNull(params.get(2), "Description cannot be null").toString());
        tour.setFrom(Objects.requireNonNull(params.get(3), "Starting point cannot be null").toString());
        tour.setTo(Objects.requireNonNull(params.get(4), "Ending point cannot be null").toString());
        tour.setTransportType(Objects.requireNonNull(params.get(5), "Transport type cannot be null").toString());
        tour.setDistance(Float.parseFloat(params.get(6).toString()));
        tour.setEstimatedTime(Objects.requireNonNull(params.get(7), "Estimated time can't be null").toString());
        tour.setRouteInfo(Objects.requireNonNull(params.get(8), "RouteInfo cannot be null").toString());
    }

    @Override
    public void update(Tour tour) {

    }

    @Override
    public void delete(Tour tour) {
        if(tours.contains(tour)){
            tours.remove(tour);
        }
    }
}
