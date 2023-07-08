package com.example.tourplanner.models;

public class MapQuestDirectionsReturn {
    private Float distance;
    private String time;

    public MapQuestDirectionsReturn(Float distance, String time) {
        this.distance = distance;
        this.time = time;
    }

    public Float getDistance() {
        return distance;
    }

    public String getTime() {
        return time;
    }
}
