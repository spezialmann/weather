package com.taeschma.wwo.response;

import java.util.List;

/**
 *
 * @author marco
 */
public class Current {
    
    private Integer temp_C;
    private Integer weatherCode;
    private List<Value> weatherDesc;
    private List<Value> weatherIconUrl;
    private Integer humidity;
    private Integer windspeedKmph;
    private String winddir16Point;

    public Integer getTemp_C() {
        return temp_C;
    }

    public void setTemp_C(Integer temp_C) {
        this.temp_C = temp_C;
    }

    public Integer getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(Integer weatherCode) {
        this.weatherCode = weatherCode;
    }

    public List<Value> getWeatherDesc() {
        return weatherDesc;
    }

    public void setWeatherDesc(List<Value> weatherDesc) {
        this.weatherDesc = weatherDesc;
    }

    public List<Value> getWeatherIconUrl() {
        return weatherIconUrl;
    }

    public void setWeatherIconUrl(List<Value> weatherIconUrl) {
        this.weatherIconUrl = weatherIconUrl;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getWindspeedKmph() {
        return windspeedKmph;
    }

    public void setWindspeedKmph(Integer windspeedKmph) {
        this.windspeedKmph = windspeedKmph;
    }

    public String getWinddir16Point() {
        return winddir16Point;
    }

    public void setWinddir16Point(String winddir16Point) {
        this.winddir16Point = winddir16Point;
    }
    
    
}
