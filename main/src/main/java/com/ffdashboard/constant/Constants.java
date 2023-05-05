package com.ffdashboard.constant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Constants {

    private HashMap<String, String> nameConverterMap = new HashMap<>();

    private List<String> validPositions = Arrays.asList("QB", "DEF", "WR", "TE", "RB", "K");

    public Constants() {
        nameConverterMap.put("AJ Dillon", "A.J. Dillon");
        nameConverterMap.put("DK Metcalf", "D.K. Metcalf");
        nameConverterMap.put("Brian Robinson", "Brian Robinson Jr.");
        nameConverterMap.put("Joshua Palmer", "Josh Palmer");
        nameConverterMap.put("DJ Moore", "D.J. Moore");
        nameConverterMap.put("Michael Pittman", "Michael Pittman Jr.");
        nameConverterMap.put("Gabe Davis", "Gabriel Davis");
        nameConverterMap.put("Kenneth Walker", "Kenneth Walker III");
        nameConverterMap.put("Jeff Wilson", "Jeff Wilson Jr.");
    }
    
    public HashMap<String, String> getNameConverterMap(){
        return this.nameConverterMap;
    }

    public List<String> getValidPositions() {
        return this.validPositions;
    }
}
