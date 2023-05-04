package com.ffdashboard.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ffdashboard.entity.LeagueEntity;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class LeagueService extends DashboardService {

    public LeagueService() throws IOException {
        super();
    }

    public List<LeagueEntity> getLeagues(String playerId) throws IOException {
        List<LeagueEntity> leagues = new ArrayList<>();

        String urlString = "https://api.sleeper.app/v1/user/"+ playerId +
        "/leagues/nfl/2023";

        // Connect to the URL using java's native library
        URL url = new URL(urlString);
        URLConnection request = url.openConnection();
        request.connect();

        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
        JsonArray rootobj = root.getAsJsonArray(); //May be an array, may be an object. 
        for (JsonElement element : rootobj)  {
            JsonObject leagueInfo = element.getAsJsonObject();
            Integer numOfTeams = leagueInfo.get("total_rosters").getAsInt();
            String leagueName = leagueInfo.get("name").getAsString();
            String leagueId = leagueInfo.get("league_id").getAsString();
            leagues.add(new LeagueEntity(numOfTeams, leagueName, leagueId));
        }

        return leagues;
    }
    
}
