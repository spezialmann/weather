package com.taeschma.wwo.response;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author marco
 */
public class Day {

    private String date;

    private List<Map<String, Object>> hourly;

    @JsonProperty("maxtempC")
    private Integer maxTempC;
    @JsonProperty("mintempC")
    private Integer minTempC;

    public String getDate() {
        return date;
    }

    public List<Map<String, Object>> getHourly() {
        return hourly;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHourly(List<Map<String, Object>> hourly) {
        this.hourly = hourly;
    }

	public Integer getMaxTempC() {
		return maxTempC;
	}

	public void setMaxTempC(Integer maxTempC) {
		this.maxTempC = maxTempC;
	}

	public Integer getMinTempC() {
		return minTempC;
	}

	public void setMinTempC(Integer minTempC) {
		this.minTempC = minTempC;
	}



}
