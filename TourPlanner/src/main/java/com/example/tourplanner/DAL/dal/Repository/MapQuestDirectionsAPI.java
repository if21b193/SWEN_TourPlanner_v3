package com.example.tourplanner.DAL.dal.Repository;

import com.example.tourplanner.DAL.dal.config.HttpClientSingleton;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MapQuestDirectionsAPI {
    public static final String key = "0ciJA4jyfcyvaC3eIYj7OCVOlhW3O5rn";
    public MapQuestDirectionsAPI() { }

    public void getTourInformation(String from, String to) throws IOException {
        String url="https://www.mapquestapi.com/directions/v2/route?key=" + key + "&from=" + from +"&to" + to;
        HttpClient httpClient = HttpClientSingleton.getInstance();
        HttpGet request = new HttpGet(url);
        HttpResponse response = httpClient.execute(request);
    }
}
