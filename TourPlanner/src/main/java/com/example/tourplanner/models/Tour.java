package com.example.tourplanner.models;


import javax.persistence.Entity;
import javax.persistence.*;

/* We add @Entity to show hibernate that this whole class is a table in the DB and then we say @table and specify which one
* Hibernate needs an ID so we specify that with our tourid thingy, and with column we state the corresponding name of the column in the table
* */

@Entity
@Table(name = "tours")
public class Tour {
    @Id
    @GeneratedValue
    @Column(name = "tour_id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "start_location")
    private String from;
    @Column(name = "end_location")
    private String to;
    @Column(name = "transport_type")
    private String transportType;
    @Column(name = "distance")
    private float distance;
    @Column(name = "estimated_time")
    private String estimatedTime;
    @Column(name = "route_info")
    private String routeInfo;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getTransportType() {
        return transportType;
    }

    public double getDistance() {
        return distance;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public String getRouteInfo() {
        return routeInfo;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setId(int id){
        this.id = id;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setFrom(String from){
        this.from = from;
    }

    public void setTo(String to){
        this.to = to;
    }

    public void setTransportType(String transportType){
        this.transportType = transportType;
    }

    public void setDistance(float distance){
        this.distance = distance;
    }

    public void setEstimatedTime(String estimatedTime){
        this.estimatedTime = estimatedTime;
    }
    public void setRouteInfo (String routeInfo){
        this.routeInfo = routeInfo;
    }

    public Tour(String name, String description, String from, String to, String transportType, float distance, String estimatedTime, String routeInfo) {
        this.name = name;
        this.description = description;
        this.from = from;
        this.to = to;
        this.transportType = transportType;
        this.distance = distance;
        this.estimatedTime = estimatedTime;
        this.routeInfo = routeInfo;
    }


    public String calculateChildFriendliness(float distance) {
        if (distance < 3.0f) {
            return "Very High";
        } else if (distance < 5.0f){
            return "High";
        } else if (distance < 7.0f) {
            return "Middle";
        } else if (distance < 9.0f) {
            return "Low";
        } else {
            return "Very low";
        }
    }

    public static String calculateAccessibility(String transportation, float distance) {
        if (transportation.equals("by car")) {
            if (distance < 5.00f) {
                return "Very High Accessibility";
            } else if (distance < 7.00f) {
                return "High Accessibility";
            } else {
                return "Moderate Accessibility";
            }
        } else if (transportation.equals("Bicycle")) {
            if (distance < 3.00f) {
                return "High Accessibility";
            } else if (distance < 5.00f) {
                return "Moderate Accessibility";
            } else {
                return "Low Accessibility";
            }
        } else if (transportation.equals("Pedestrian")) {
            if (distance < 3.00f) {
                return "Moderate Accessibility";
            } else if (distance < 5.00f) {
                return "Low Accessibility";
            } else {
                return "Very Low Accessibility";
            }
        } else {
            return "Invalid transportation";
        }
    }


    // float tourDistance = tour.getDistance();
    // boolean isChildFriendly = calculateChildFriendliness(tourDistance);
    // tour.setChildFriendly(isChildFriendly);//

    // no-arg constructor
    public Tour() {}
}
