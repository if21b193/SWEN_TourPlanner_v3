package com.example.tourplanner.DAL.dal.config;


import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpClientSingleton {
    private static CloseableHttpClient instance;

    private HttpClientSingleton(){

    }

    public static  synchronized CloseableHttpClient getInstance(){
        if(instance == null){
            instance = HttpClientBuilder.create().build();
        }
        return instance;
    }
}
