package com.taeschma.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.taeschma.domain.CurrentWeather;
import com.taeschma.domain.ForecastDay;
import com.taeschma.wwo.response.Current;
import com.taeschma.wwo.response.Day;

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
	
	/**
	 * Map api response to CurrentWeather entity
	 * 
	 * @param current
	 * @param date
	 * @param locationId
	 * @return
	 */
	public static ForecastDay getForecast(Day day, String locationId) {
		ForecastDay forecastDay = new ForecastDay();
		
		String dateString = day.getDate();
		
		forecastDay.setDateLocationIndex(dateString + locationId);
		forecastDay.setDate(stringToDate(dateString));
		forecastDay.setLocationId(locationId);
		forecastDay.setMaxTempC(day.getMaxTempC());
		forecastDay.setMinTempC(day.getMinTempC());
		
		Map<String, Object> hourMap = day.getHourly().get(0);
		
		BigDecimal precipMM = new BigDecimal((String)hourMap.get("precipMM"));
		forecastDay.setPrecipMM(precipMM);
		
		Integer windspeedKmph = new Integer((String)hourMap.get("windspeedKmph"));
		forecastDay.setWindspeedKmh(windspeedKmph);
		
		return forecastDay;
	}
	
	private static Date stringToDate(String dateString) {
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		try {
			date = formatter.parse(dateString);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
