package com.ffdashboard.constant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Constants {

    private HashMap<String, String> nameConverterMap = new HashMap<>();

    private List<String> validPositions = Arrays.asList("QB", "DEF", "WR", "TE", "RB");

    public Constants() {
        nameConverterMap.put("AJ Dillon", "A.J. Dillon");
        nameConverterMap.put("DK Metcalf", "D.K. Metcalf");
    }
    
    public HashMap<String, String> getNameConverterMap(){
        return this.nameConverterMap;
    }

    public List<String> getValidPositions() {
        return this.validPositions;
    }
}
