package com.example.tourplanner.DAL.dal.Repository;

import com.example.tourplanner.DAL.dal.config.HibernateUtil;

public class MapQuestStaticImageAPI {
    private static final String key = HibernateUtil.getConfiguration().getProperty("api.key");

    public MapQuestStaticImageAPI(){
    }

    public String getStaticImage(String from, String to) {
        return "https://www.mapquestapi.com/staticmap/v5/map?start=" + from + "&end=" + to + "&size=@2x&key=" + key;

    }
}
