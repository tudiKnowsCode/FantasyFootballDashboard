package com.ffdashboard.entity;

import java.util.List;

public class RosterEntity {

    private Integer totalValue;

    private String teamName;

    private String teamOwner;

    private PlayerEntity QB;

    private PlayerEntity RB1;

    private PlayerEntity RB2;

    private PlayerEntity WR1;

    private PlayerEntity WR2;

    private PlayerEntity TE;

    private PlayerEntity FLX1;

    private PlayerEntity FLX2;

    private PlayerEntity DEF;

    private PlayerEntity K;

    private List<PlayerEntity> bench;


    public RosterEntity(Integer totalValue, String teamName, String teamOwner, PlayerEntity QB, PlayerEntity RB1, PlayerEntity RB2, PlayerEntity WR1, PlayerEntity WR2, PlayerEntity TE, PlayerEntity FLX1, PlayerEntity FLX2, PlayerEntity DEF, PlayerEntity K, List<PlayerEntity> bench) {
        this.totalValue = totalValue;
        this.teamName = teamName;
        this.teamOwner = teamOwner;
        this.QB = QB;
        this.RB1 = RB1;
        this.RB2 = RB2;
        this.WR1 = WR1;
        this.WR2 = WR2;
        this.TE = TE;
        this.FLX1 = FLX1;
        this.FLX2 = FLX2;
        this.DEF = DEF;
        this.K = K;
        this.bench = bench;
    }

    public RosterEntity() {
    }

    public void setTotalValue(Integer totalValue) {
        this.totalValue = totalValue;
    }

    public Integer getTotalValue() {
        return this.totalValue;
    }
    
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return this.teamName;
    }
    
    public void setTeamOwner(String teamOwner) {
        this.teamOwner = teamOwner;
    }

    public String getTeamOwner() {
        return this.teamOwner;
    }
    
    public void setQB(PlayerEntity QB) {
        this.QB = QB;
    }

    public PlayerEntity getQB() {
        return this.QB;
    }
    
    public void setRB1(PlayerEntity RB1) {
        this.RB1 = RB1;
    }

    public PlayerEntity getRB1() {
        return this.RB1;
    }
    
    public void setRB2(PlayerEntity RB2) {
        this.RB2 = RB2;
    }

    public PlayerEntity getRB2() {
        return this.RB2;
    }
    
    public void setWR1(PlayerEntity WR1) {
        this.WR1 = WR1;
    }

    public PlayerEntity getWR1() {
        return this.WR1;
    }
    
    public void setWR2(PlayerEntity WR2) {
        this.WR2 = WR2;
    }

    public PlayerEntity getWR2() {
        return this.WR2;
    }
    
    public void setTE(PlayerEntity TE) {
        this.TE = TE;
    }

    public PlayerEntity getTE() {
        return this.TE;
    }
    
    public void setFLX1(PlayerEntity FLX1) {
        this.FLX1 = FLX1;
    }

    public PlayerEntity getFLX1() {
        return this.FLX1;
    }
    
    public void setFLX2(PlayerEntity FLX2) {
        this.FLX2 = FLX2;
    }

    public PlayerEntity getFLX2() {
        return this.FLX2;
    }
    
    public void setDEF(PlayerEntity DEF) {
        this.DEF = DEF;
    }

    public PlayerEntity getDEF() {
        return this.DEF;
    }
    
    public void setK(PlayerEntity K) {
        this.K = K;
    }

    public PlayerEntity getK() {
        return this.K;
    }

    public void setBench(List<PlayerEntity> bench) {
        this.bench = bench;
    }

    public List<PlayerEntity> getBench() {
        return this.bench;
    }
        
    
}
