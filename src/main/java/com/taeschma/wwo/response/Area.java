package com.taeschma.wwo.response;

import java.util.List;

/**
 *
 * @author marco
 */
public class Area {
    private List<Value> areaName;
    private String      latitude;
    private String      longitude;

    public List<Value> getAreaName() {
        return areaName;
    }

    public void setAreaName(List<Value> areaName) {
        this.areaName = areaName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    
    
}
