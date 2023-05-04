package com.ffdashboard.entity;

public class LeagueEntity {

    private Integer numOfTeams;

    private String leagueName;

    private String leagueId;

    public LeagueEntity(Integer numOfTeams, String leagueName, String leagueId) {
        this.numOfTeams = numOfTeams;
        this.leagueName = leagueName;
        this.leagueId = leagueId;
    }
        public void setNumOfTeams(Integer numOfTeams) {
            this.numOfTeams = numOfTeams;
        }

        public Integer getNumOfTeams() {
            return this.numOfTeams;
        }
        
        public void setLeagueName(String leagueName) {
            this.leagueName = leagueName;
        }

        public String getLeagueName() {
            return this.leagueName;
        }
        
        public void setLeagueId(String leagueId) {
            this.leagueId = leagueId;
        }

        public String getLeagueId() {
            return this.leagueId;
        }
        
}
