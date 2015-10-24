package com.taeschma.util;

import java.util.Date;

import com.taeschma.domain.CurrentWeather;
import com.taeschma.wwo.response.Current;

public class ApiMapper {
	
	/**
	 * Map api response to CurrentWeather entity
	 * 
	 * @param current
	 * @param date
	 * @param locationId
	 * @return
	 */
	public static CurrentWeather getCurrentWeather(Current current, Date date, String locationId) {
		CurrentWeather currentWeather = new CurrentWeather();
		
		currentWeather.setDate(date);
		currentWeather.setLocationId(locationId);
		currentWeather.setFeelsLikeC(current.getFeelsLikeC());
		currentWeather.setHumidity(current.getHumidity());
		currentWeather.setPrecipMM(current.getPrecipMM());
		currentWeather.setTempC(current.getTemp_C());
		currentWeather.setWeatherCode(current.getWeatherCode());
		currentWeather.setWeatherDesc(current.getWeatherDesc().get(0).getValue());
		currentWeather.setWinddir16Point(current.getWinddir16Point());
		currentWeather.setWindspeedKmph(current.getWindspeedKmph());
		
		return currentWeather;
	}
}
