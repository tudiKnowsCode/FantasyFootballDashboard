package com.ffdashboard.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.ffdashboard.entity.PlayerEntity;
import com.google.gson.*;

public class DashboardService {

    private HashMap<Integer, PlayerEntity> playerIdMap = new HashMap<>();

    private List<String> validPositions = Arrays.asList("QB", "DEF", "WR", "TE", "RB");
    
    public DashboardService() throws IOException {
        String urlString = "https://api.sleeper.app/v1/players/nfl";

        // Connect to the URL using java's native library
        URL url = new URL(urlString);
        URLConnection request = url.openConnection();
        request.connect();

        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
        JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object. 
        Map<String, JsonElement> rawMap = rootobj.asMap();
        for (Map.Entry<String,JsonElement> entry : rawMap.entrySet())  {
            JsonObject playerInfo = entry.getValue().getAsJsonObject();
            Boolean active = playerInfo.get("active").getAsBoolean();
            if(active) {
                String position = playerInfo.get("position").toString().replaceAll("\"", "");
                if(validPositions.contains(position)){
                    String playerId = entry.getKey();
                    String playerName;
                    if(playerInfo.get("full_name") == null) {
                        playerName = "Defense";
                    }
                    else{
                        playerName = playerInfo.get("full_name").getAsString();
                    }
                    System.out.println(playerId + ": " + playerName);
                }
            }    
        }
    }


    
}
