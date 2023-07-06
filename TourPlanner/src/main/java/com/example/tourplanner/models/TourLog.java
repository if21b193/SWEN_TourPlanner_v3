package com.example.tourplanner.models;



import javax.persistence.Entity;
import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "tourLogs")
public class TourLog {
    @Id
    @GeneratedValue
    @Column(name = "log_id")
    private int id;
    @ManyToOne
    @JoinColumn(name="tour_id")
    private Tour tour_id;
    @Column(name="date_time")
    private String dateTime;
    @Column(name = "comment")
    private String comment;
    @Column(name = "difficulty")
    private Float difficulty;
    @Column(name ="total_time")
    private String totalTime;
    @Column(name = "rating")
    private Float rating;

    public TourLog() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tour getTour_id() {
        return tour_id;
    }

    public void setTour_id(Tour tour_id) {
        this.tour_id = tour_id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Float getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Float difficulty) {
        this.difficulty = difficulty;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public TourLog(Tour tourID, String dateTime, String comment, Float difficulty, String totalTime, Float rating){
        this.tour_id = tourID;
        this.dateTime = dateTime;
        this.comment = comment;
        this.difficulty = difficulty;
        this.totalTime = totalTime;
        this.rating = rating;
    }
}
