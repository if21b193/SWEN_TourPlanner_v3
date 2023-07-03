package com.example.tourplanner.DAL.dal.Repository;

import com.example.tourplanner.DAL.dal.config.HttpClientSingleton;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;

public class MapQuestStaticImageAPI {
    private static final String key = "0ciJA4jyfcyvaC3eIYj7OCVOlhW3O5rn";

    public MapQuestStaticImageAPI(){
    }

    public void getStaticImage(String from, String to) throws IOException {
        String url = "https://www.mapquestapi.com/staticmap/v5/map?start=" + from + "&end=" + to + "&size=@2x&key=" + key;
        HttpClient httpClient = HttpClientSingleton.getInstance();
        HttpGet request = new HttpGet(url);
        HttpResponse response = httpClient.execute(request);
    }
}
