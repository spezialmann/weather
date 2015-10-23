package com.taeschma.wwo.response;

/**
 *
 * @author marco
 */
public class Weather {
    private WeatherForecast data;

    public WeatherForecast getData()
    {
        return data;
    }

    public void setData(WeatherForecast data)
    {
        this.data = data;
    }
}
