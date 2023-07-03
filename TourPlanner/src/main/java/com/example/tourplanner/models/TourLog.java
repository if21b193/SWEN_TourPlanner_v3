package com.example.tourplanner.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TourLog {
    private int id;
    private String dateTime;
    private String comment;
    private Double difficulty;
    private String totalTime;
    private Double rating;

    public TourLog(int id, String dateTime, String comment, Double difficulty, String totalTime, Double rating){
        this.id = id;
        this.dateTime = dateTime;
        this.comment = comment;
        this.difficulty = difficulty;
        this.totalTime = totalTime;
        this.rating = rating;
    }
}
