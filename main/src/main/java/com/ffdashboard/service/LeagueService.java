package com.ffdashboard.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ffdashboard.entity.LeagueEntity;
import com.ffdashboard.entity.PlayerEntity;
import com.ffdashboard.entity.UserEntity;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class LeagueService extends DashboardService {

    public LeagueService() throws IOException {
        super();
    }

    public List<LeagueEntity> getLeagues(String username) throws IOException {
        List<LeagueEntity> leagues = new ArrayList<>();

        String userId = getUserInfo(username).getUserId();

        String urlString = "https://api.sleeper.app/v1/user/"+ userId +
        "/leagues/nfl/2023";

        JsonArray rootobj = getJsonArray(urlString); //May be an array, may be an object. 
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
