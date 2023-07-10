package com.example.tourplanner.UI.View;

import java.util.Date;

public class TourLogTableViewEntry {
    private Date date;
    private String comment;
    private Float difficulty;
    private String time;
    private Float rating;

    public TourLogTableViewEntry(Date date, String comment, Float difficulty, String time, Float rating) {
        this.date = date;
        this.comment = comment;
        this.difficulty = difficulty;
        this.time = time;
        this.rating = rating;
    }


}
