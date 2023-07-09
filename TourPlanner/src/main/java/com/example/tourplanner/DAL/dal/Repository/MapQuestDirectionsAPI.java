package com.example.tourplanner.DAL.dal.Repository;

import com.example.tourplanner.DAL.dal.config.HibernateUtil;
import com.example.tourplanner.DAL.dal.config.HttpClientSingleton;
import com.example.tourplanner.models.MapQuestDirectionsReturn;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class MapQuestDirectionsAPI {

    //TODO: get key from a config file
    private static final Logger logger = LogManager.getLogger(MapQuestDirectionsAPI.class);
    public MapQuestDirectionsAPI() { }

    public MapQuestDirectionsReturn getTourInformation(String from, String to, String transportation) throws IOException {
        String key = HibernateUtil.getConfiguration().getProperty("api.key");
        logger.info("Getting tour information from {} to {} using transportation mode {}", from, to, transportation);
        String url="https://www.mapquestapi.com/directions/v2/route?key=" + key + "&from=" + from +"&to=" + to + "&routeType=" + transportation + "&unit=k";
        HttpClient httpClient = HttpClientSingleton.getInstance();
        HttpGet request = new HttpGet(url);
        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        String json = EntityUtils.toString(entity);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);

        Float distance = jsonNode.get("route").get("distance").floatValue();
        String time = jsonNode.get("route").get("time").toString();

        return new MapQuestDirectionsReturn(distance, time);
    }
}
