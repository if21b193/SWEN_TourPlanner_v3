package com.example.tourplanner.DAL.dal.Repository;

import com.example.tourplanner.DAL.dal.config.HttpClientSingleton;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.util.EntityUtils;

public class MapQuestDirectionsAPI {

    //TODO: ADD IN ANOTHER RETURN TYPE THAN MAP (OWN CLASS)
    public static final String key = "0ciJA4jyfcyvaC3eIYj7OCVOlhW3O5rn";
    public MapQuestDirectionsAPI() { }

    public Map<String, Object> getTourInformation(String from, String to) throws IOException {
        String url="https://www.mapquestapi.com/directions/v2/route?key=" + key + "&from=" + from +"&to=" + to;
        HttpClient httpClient = HttpClientSingleton.getInstance();
        HttpGet request = new HttpGet(url);
        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        String json = EntityUtils.toString(entity);

        Map<String, Object> resp = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);

        resp.put("distance", jsonNode.get("route").get("distance").doubleValue());
        resp.put("time", jsonNode.get("route").get("time").intValue());
        return resp;
    }
}
