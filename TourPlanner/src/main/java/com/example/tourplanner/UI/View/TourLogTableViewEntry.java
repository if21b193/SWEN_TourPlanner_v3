package com.example.tourplanner.UI.View;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class TourLogTableViewEntry {
    private StringProperty id;
    private StringProperty date;
    private StringProperty comment;
    private StringProperty difficulty;
    private StringProperty time;
    private StringProperty rating;

    public TourLogTableViewEntry(int id, Date date, String comment, Float difficulty, String time, Float rating) {
        this.id = new SimpleStringProperty(String.valueOf(id));
        this.date = new SimpleStringProperty(date.toString());
        this.comment = new SimpleStringProperty(comment);
        this.difficulty = new SimpleStringProperty(difficulty.toString());
        this.time = new SimpleStringProperty(time);
        this.rating = new SimpleStringProperty(rating.toString());
    }

    public String getId() {
        return id.get();
    }
    public String getDate() {
        return date.get();
    }

    public String getComment() {
        return comment.get();
    }

    public String getDifficulty() {
        return difficulty.get();
    }

    public String getTime() {
        return time.get();
    }

    public String getRating() {
        return rating.get();
    }
}