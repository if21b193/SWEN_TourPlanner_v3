package com.example.tourplanner.BL.service;

public class CalculateTimeFromSeconds {
    
    public static String getTimeInfo(String time) {
        int calcTime = Integer.parseInt(time);
        int seconds = (calcTime % 60);
        int minutes = ((calcTime / 60) % 60);
        int hours = ((calcTime / 3600) % 24);

        String secsText = (seconds < 10 ? "0" : "") + seconds;
        String minText = (minutes < 10 ? "0" : "") + minutes;
        String hrsText = (hours < 10 ? "0" : "") + hours;

        return hrsText + ":" + minText + ":" + secsText;
    }

}
