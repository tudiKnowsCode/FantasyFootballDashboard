package com.ffdashboard.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    private HashMap<String, String> playerIdMap = new HashMap<>();

    public DashboardController() {

    }
    
}
