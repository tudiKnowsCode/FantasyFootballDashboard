package com.ffdashboard.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.ffdashboard.entity.LeagueEntity;
import com.ffdashboard.service.DashboardService;
import com.ffdashboard.service.LeagueService;
import com.google.gson.JsonObject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    private LeagueService leagueService;

    public DashboardController() {
        try {
            leagueService = new LeagueService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/leagues/{playerId}")
    List<LeagueEntity> getLeagues(@PathVariable Long playerId) throws IOException {
      return leagueService.getLeagues(String.valueOf(playerId));
    }
    
}
