package com.taeschma.domain;

import java.util.Date;

public class CurrentStationWeather {
	private String stationId;
    private Float temperature;
    private Float minTemperature;
    private Float maxTemperature;
    private Integer humidity;
    private Float windspeedKmph;
    private Integer precipCount;
    private Float precipMM;
    private Float precipMMSum;
    private Date timestamp;
    
    
    
	public CurrentStationWeather() {
		super();
		this.precipMMSum = new Float("0.0");
		
	}
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public Float getTemperature() {
		return temperature;
	}
	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}
	public Float getMinTemperature() {
		return minTemperature;
	}
	public void setMinTemperature(Float minTemperature) {
		this.minTemperature = minTemperature;
	}
	public Float getMaxTemperature() {
		return maxTemperature;
	}
	public void setMaxTemperature(Float maxTemperature) {
		this.maxTemperature = maxTemperature;
	}
	public Integer getHumidity() {
		return humidity;
	}
	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}
	
	public Float getWindspeedKmph() {
		return windspeedKmph;
	}
	public void setWindspeedKmph(Float windspeedKmph) {
		this.windspeedKmph = windspeedKmph;
	}
	
	public Integer getPrecipCount() {
		return precipCount;
	}
	public void setPrecipCount(Integer precipCount) {
		this.precipCount = precipCount;
	}
	public Float getPrecipMM() {
		return precipMM;
	}
	public void setPrecipMM(Float precipMM) {
		this.precipMM = precipMM;
	}
	
	public Float getPrecipMMSum() {
		return precipMMSum;
	}
	public void setPrecipMMSum(Float precipMMSum) {
		this.precipMMSum = precipMMSum;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "CurrentStationWeather [stationId=" + stationId + ", temperature=" + temperature + ", minTemperature="
				+ minTemperature + ", maxTemperature=" + maxTemperature + ", humidity=" + humidity + ", windspeedKmph="
				+ windspeedKmph + ", precipMM=" + precipMM + ", timestamp=" + timestamp + "]";
	}
    
    
}
