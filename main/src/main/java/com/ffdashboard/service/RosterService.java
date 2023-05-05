package com.ffdashboard.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ffdashboard.entity.PlayerEntity;
import com.ffdashboard.entity.RosterEntity;
import com.ffdashboard.entity.UserEntity;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.apache.commons.lang3.tuple.ImmutablePair;

public class RosterService extends DashboardService {

    public RosterService() throws IOException {
        super();
    }

    public Map<UserEntity,RosterEntity> getRosters(String leagueId) throws IOException {
        Map<UserEntity,RosterEntity> rosters = new HashMap<>();

        Map<String, String> teamNames = getTeamNames(leagueId);

        String urlString = "https://api.sleeper.app/v1/league/" + leagueId + "/rosters";

        JsonArray rostersArr = getJsonArray(urlString);

        for (JsonElement element : rostersArr)  {
            JsonObject currentRoster = element.getAsJsonObject();

            UserEntity currentUser = getUserInfo(currentRoster.get("owner_id").getAsString());

            List<PlayerEntity> players = new ArrayList<>();
            JsonArray playersArr = currentRoster.get("players").getAsJsonArray();
            String teamName = teamNames.get(currentUser.getUserId());

            for (JsonElement player : playersArr) {
                players.add(playerIdMap.get(player.getAsString()));
            }

            rosters.put(currentUser, populateRoster(currentUser, teamName, players));
        }

        return rosters;
    }

    private RosterEntity populateRoster(UserEntity user, String teamName, List<PlayerEntity> players) {
        ImmutablePair<Integer, RosterEntity> rosterInfoPair = getRosterPositions(players);
        RosterEntity roster = rosterInfoPair.right;

        roster.setTeamOwner(user.getUserName());
        roster.setTeamName(teamName);
        roster.setTotalValue(rosterInfoPair.left);

        return roster;
    }

    private Map<String, String> getTeamNames(String leagueId) throws IOException {
        Map<String, String> teamNamesMap = new HashMap<>();

        String urlString = "https://api.sleeper.app/v1/league/" + leagueId + "/users";

        JsonArray teamsArray = getJsonArray(urlString); 

        for (JsonElement element : teamsArray)  {
            JsonObject teamInfo = element.getAsJsonObject();
            String userId = teamInfo.get("user_id").getAsString();
            JsonObject metaData = teamInfo.get("metadata").getAsJsonObject();
            String teamName;
            if (metaData.get("team_name") == null) {
                teamName = teamInfo.get("display_name").getAsString();
            }
            else {
                teamName = metaData.get("team_name").getAsString();
            }
            teamNamesMap.put(userId, teamName);
        }

        return teamNamesMap;
    }

    private ImmutablePair<Integer, RosterEntity> getRosterPositions(List<PlayerEntity> playersList) {
        RosterEntity roster = new RosterEntity();
        Integer totalValue = 0;

        List<PlayerEntity> qbList = new ArrayList<>();
        List<PlayerEntity> rbList = new ArrayList<>();
        List<PlayerEntity> wrList = new ArrayList<>();
        List<PlayerEntity> teList = new ArrayList<>();
        List<PlayerEntity> defList = new ArrayList<>();
        List<PlayerEntity> kList = new ArrayList<>();

        for (PlayerEntity player : playersList) {
            totalValue += player.getValue();
            String playerPosition = player.getPosition();
            switch (playerPosition) {
                case "QB" : 
                    qbList.add(player);
                    break;
                case "RB" : 
                    rbList.add(player);
                    break;
                case "WR" : 
                    wrList.add(player);
                    break;
                case "TE" : 
                    teList.add(player);
                    break;
                case "DEF" : 
                    defList.add(player);
                    break;
                case "K" : 
                    kList.add(player);
                    break;
            }
        }

        PlayerEntity qb = getBestPlayerInList(qbList);
        qbList.remove(qb);
        roster.setQB(qb);

        PlayerEntity rb1 = getBestPlayerInList(rbList);
        rbList.remove(rb1);
        roster.setRB1(rb1);

        PlayerEntity rb2 = getBestPlayerInList(rbList);
        rbList.remove(rb2);
        roster.setRB2(rb2);

        PlayerEntity wr1 = getBestPlayerInList(wrList);
        wrList.remove(wr1);
        roster.setWR1(wr1);

        PlayerEntity wr2 = getBestPlayerInList(wrList);
        wrList.remove(wr2);
        roster.setWR2(wr2);

        PlayerEntity te = getBestPlayerInList(teList);
        teList.remove(te);
        roster.setTE(te);

        PlayerEntity def = getBestPlayerInList(defList);
        defList.remove(def);
        roster.setDEF(def);

        PlayerEntity k = getBestPlayerInList(kList);
        kList.remove(k);
        roster.setK(k);

        List<PlayerEntity> flexPlayers = Stream.of(rbList, wrList, teList)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());   

        PlayerEntity flx1 = getBestPlayerInList(flexPlayers);
        flexPlayers.remove(flx1);
        roster.setFLX1(flx1);   

        PlayerEntity flx2 = getBestPlayerInList(flexPlayers);
        flexPlayers.remove(flx2);
        roster.setFLX2(flx2);   

        List<PlayerEntity> benchPlayers = Stream.of(flexPlayers, qbList, defList, kList)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

        roster.setBench(benchPlayers);

        return ImmutablePair.of(totalValue, roster);
    }

    private PlayerEntity getBestPlayerInList(List<PlayerEntity> players) {
        Integer maxValue = -1;
        PlayerEntity bestPlayer = null;
        for (PlayerEntity player : players) {
            if (player.getValue() > maxValue) {
                maxValue = player.getValue();
                bestPlayer = player;
            }
        }
        return bestPlayer;
    }
    
}
