package com.taeschma.wwo.response;

import java.util.List;

/**
 *
 * @author marco
 */
public class WeatherForecast {

    private List<Current> current_condition;
    private List<Day> weather;
    private List<Area> nearest_area;

    public List<Current> getCurrent_condition() {
        return current_condition;
    }

    public void setCurrent_condition(List<Current> current_condition) {
        this.current_condition = current_condition;
    }

    public List<Area> getNearest_area() {
        return nearest_area;
    }

    public void setNearest_area(List<Area> nearest_area) {
        this.nearest_area = nearest_area;
    }

    public List<Day> getWeather() {
        return weather;
    }

    public void setWeather(List<Day> weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "WeatherForecast{" + "current_condition=" + current_condition + ", weather=" + weather + ", nearest_area=" + nearest_area + '}';
    }
    
    
}
