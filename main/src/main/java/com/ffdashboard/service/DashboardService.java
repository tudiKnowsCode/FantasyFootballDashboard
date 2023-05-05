package com.ffdashboard.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import com.ffdashboard.constant.Constants;
import com.ffdashboard.entity.PlayerEntity;
import com.ffdashboard.entity.UserEntity;
import com.google.gson.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DashboardService {

    protected HashMap<String, PlayerEntity> playerIdMap = new HashMap<>();

    protected HashMap<String, Integer> playerValues = new HashMap<>();

    private Constants constants = new Constants();
    
    public DashboardService() throws IOException {
        initializePlayerValues();
        initializePlayerMap();
    }

    private void initializePlayerMap() throws IOException {
        String urlString = "https://api.sleeper.app/v1/players/nfl";

        JsonObject rootobj = getJsonObject(urlString);
        Map<String, JsonElement> rawMap = rootobj.asMap();
        for (Map.Entry<String,JsonElement> entry : rawMap.entrySet())  {
            JsonObject playerInfo = entry.getValue().getAsJsonObject();
            Boolean active = playerInfo.get("active").getAsBoolean();
            if(active) {
                String position = playerInfo.get("position").toString().replaceAll("\"", "");
                if(constants.getValidPositions().contains(position)){
                    String playerId = entry.getKey();
                    String playerName;
                    String team;
                    Integer value;
                    if(playerInfo.get("full_name") == null) {
                        playerName = playerId + " DEF";
                    }
                    else{
                        playerName = playerInfo.get("full_name").getAsString();
                    }
                    if (playerInfo.get("team").isJsonNull()) {
                        team = "FA";
                    }
                    else {
                        team = playerInfo.get("team").getAsString();
                    }
                    if(!playerValues.containsKey(playerName)) {
                        if(constants.getNameConverterMap().containsKey(playerName)){
                            String convertedName = constants.getNameConverterMap().get(playerName);
                            value = playerValues.get(convertedName);
                        }
                        else {
                            value = 0;
                        }
                    }
                    else {
                        value = playerValues.get(playerName);
                    }
                    PlayerEntity tempPlayer = new PlayerEntity(playerId, playerName, position, value, team);
                    playerIdMap.put(playerId, tempPlayer);
                }
            }    
        }
    }

    public void initializePlayerValues() throws IOException {
        Document doc = Jsoup.connect("https://www.pff.com/news/fantasy-football-2023-dynasty-fantasy-football-trade-value-chart").get();

        Elements tableElements = doc.select("table");
        for (Element tableElement : tableElements) {
            Elements rows = tableElement.select("tr");
            for(Element row :rows)
            {
                Elements columns = row.select("td");
                if(columns.size() == 4) {
                    String playerName = columns.get(0).text();
                    String value = columns.get(3).text();
                    if(!playerName.equals("Player")){
                        playerValues.put(playerName, Integer.parseInt(value));
                    }
                }
            }
        }

    }

    protected JsonArray getJsonArray(String urlString) throws IOException {
    
        return getJsonElement(urlString).getAsJsonArray();
    
    }

    protected JsonObject getJsonObject(String urlString) throws IOException {

        return getJsonElement(urlString).getAsJsonObject();
    
    }

    private JsonElement getJsonElement(String urlString)throws IOException {
    
        // Connect to the URL using java's native library
        URL url = new URL(urlString);
        URLConnection request = url.openConnection();
        request.connect();

        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element

        return root;
    
    }        

    protected UserEntity getUserInfo(String username) throws IOException {

        String urlString = "https://api.sleeper.app/v1/user/"+ username;

        JsonObject userInfo = getJsonObject(urlString);

        return new UserEntity(userInfo.get("display_name").getAsString(), userInfo.get("user_id").getAsString());

    }

}
