package com.ffdashboard.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ffdashboard.entity.LeagueEntity;
import com.ffdashboard.entity.PlayerEntity;
import com.ffdashboard.entity.RosterEntity;
import com.ffdashboard.entity.UserEntity;
import com.ffdashboard.service.LeagueService;
import com.ffdashboard.service.RosterService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    private LeagueService leagueService;
    private RosterService rosterService;

    public DashboardController() {
        try {
            leagueService = new LeagueService();
            rosterService = new RosterService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/leagues/{username}")
    List<LeagueEntity> getLeagues(@PathVariable String username) throws IOException {
      return leagueService.getLeagues(username);
    }

    @GetMapping("/leagues/rosters/{leagueId}")
    Map<UserEntity, RosterEntity> getLeagueRosters(@PathVariable String leagueId) throws IOException {
      return rosterService.getRosters(leagueId);
    }
    
}
