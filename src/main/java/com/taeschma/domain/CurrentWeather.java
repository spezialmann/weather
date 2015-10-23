package com.taeschma.domain;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CurrentWeatherCollection")
public class CurrentWeather {

	@Id
    private ObjectId id;
	
	//Date
	private Date date;
	//Common weather
	private String weatherDesc;
	private Integer weatherCode; //
	//Wind
	private String winddir16Point; //W, NW, N, ... 
	private Integer windspeedKmph;
	//Temperature
    private Integer tempC;
    private Integer feelsLikeC;
    //Humidity
    private Float precipMM;
    private Integer humidity;
    
    
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getWeatherDesc() {
		return weatherDesc;
	}
	public void setWeatherDesc(String weatherDesc) {
		this.weatherDesc = weatherDesc;
	}
	public Integer getWeatherCode() {
		return weatherCode;
	}
	public void setWeatherCode(Integer weatherCode) {
		this.weatherCode = weatherCode;
	}
	public String getWinddir16Point() {
		return winddir16Point;
	}
	public void setWinddir16Point(String winddir16Point) {
		this.winddir16Point = winddir16Point;
	}
	public Integer getWindspeedKmph() {
		return windspeedKmph;
	}
	public void setWindspeedKmph(Integer windspeedKmph) {
		this.windspeedKmph = windspeedKmph;
	}
	public Integer getTempC() {
		return tempC;
	}
	public void setTempC(Integer tempC) {
		this.tempC = tempC;
	}
	public Integer getFeelsLikeC() {
		return feelsLikeC;
	}
	public void setFeelsLikeC(Integer feelsLikeC) {
		this.feelsLikeC = feelsLikeC;
	}
	public Float getPrecipMM() {
		return precipMM;
	}
	public void setPrecipMM(Float precipMM) {
		this.precipMM = precipMM;
	}
	public Integer getHumidity() {
		return humidity;
	}
	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}
    
    	
}
