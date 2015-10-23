package com.taeschma.domain;

import java.util.Date;


public class ForecastDay
{
    private Date date;
    private Integer   weatherCode;
    private Integer   maxTempC;
    private Integer   minTempC;
    
    private String locationId;
    
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getWeatherCode() {
		return weatherCode;
	}
	public void setWeatherCode(Integer weatherCode) {
		this.weatherCode = weatherCode;
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
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

}

