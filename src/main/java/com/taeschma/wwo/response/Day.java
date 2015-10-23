package com.taeschma.wwo.response;

import java.util.List;
import java.util.Map;

/**
 *
 * @author marco
 */
public class Day {

    private String date;

    private List<Map<String, Object>> hourly;

    private Integer maxtempC;
    private Integer maxtempF;
    private Integer mintempC;
    private Integer mintempF;

    public String getDate() {
        return date;
    }

    public List<Map<String, Object>> getHourly() {
        return hourly;
    }

    public Integer getMaxtempC() {
        return maxtempC;
    }

    public Integer getMaxtempF() {
        return maxtempF;
    }

    public Integer getMintempC() {
        return mintempC;
    }

    public Integer getMintempF() {
        return mintempF;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHourly(List<Map<String, Object>> hourly) {
        this.hourly = hourly;
    }

    public void setMaxtempC(Integer maxtempC) {
        this.maxtempC = maxtempC;
    }

    public void setMaxtempF(Integer maxtempF) {
        this.maxtempF = maxtempF;
    }

    public void setMintempC(Integer mintempC) {
        this.mintempC = mintempC;
    }

    public void setMintempF(Integer mintempF) {
        this.mintempF = mintempF;
    }

}
