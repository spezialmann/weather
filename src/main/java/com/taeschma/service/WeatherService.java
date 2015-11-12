package com.taeschma.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.taeschma.domain.CurrentWeather;
import com.taeschma.domain.ForecastDay;
import com.taeschma.domain.Location;
import com.taeschma.repository.CurrentWeatherRepository;
import com.taeschma.repository.ForecastDayRepository;
import com.taeschma.util.ApiMapper;
import com.taeschma.wwo.ApiService;
import com.taeschma.wwo.response.Day;
import com.taeschma.wwo.response.Weather;

@Service
public class WeatherService {

	private final Logger log = LoggerFactory.getLogger(WeatherService.class);

	@Autowired
	private ApiService apiService;

	@Autowired
	private LocationService locationService;

	@Autowired
	private CurrentWeatherRepository currentWeatherRepository;
	
	@Autowired
	private ForecastDayRepository forecastDayRepository;

	/**
	 * Fetching weather data Every xxx minutes
	 */
	@Scheduled(initialDelay = 5000000, fixedDelay = 600000)
	public void updateAllWeatherData() {
		log.debug("Start api requests");
		List<Location> locations = locationService.findAll();
		for (Location location : locations) {
			updateWeatherForLocation(location);
		}
		log.debug("End api requests");
	}
	
	/**
	 * Update single location
	 * 
	 * @param location
	 */
	public void updateWeatherForLocation(Location location) {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		
		Weather weatherResponse = apiService.doWeatherRequest(location.getLatitude() + "," + location.getLongitude());

		if (weatherResponse != null) {
			//save current weather
			CurrentWeather current = ApiMapper
					.getCurrentWeather(weatherResponse.getData().getCurrent_condition().get(0), date, location.getLocationId());
			if (current != null) {
				currentWeatherRepository.save(current);
			}
			//save forecast weather
			List<Day> dailyList = weatherResponse.getData().getWeather();
			if(dailyList != null && dailyList.size()>0) {
				dailyList.forEach(day->{
					ForecastDay forecast = ApiMapper.getForecast(day, location.getLocationId());
					if(forecast != null) {
						saveForecastDay(forecast);
					}
				});
			}
		}
	}

	/**
	 * get latest CurrentWeather for location
	 */
	public CurrentWeather getCurrentWeatherForLocation(Location location) {
		CurrentWeather ret = null;

		PageRequest pr = new PageRequest(0, 1, new Sort(new Order(Direction.DESC, "date")));
		List<CurrentWeather> result = currentWeatherRepository.findByLocationId(location.getLocationId(), pr);

		if (result != null && !result.isEmpty()) {
			ret = result.get(0);
		}
		return ret;
	}
	
	/**
	 * save or update ForecastDay
	 */
	public void saveForecastDay(ForecastDay forecastDay) {
		log.debug("Start saving ForecastDay");
		ForecastDay fromDb = forecastDayRepository.findByDateLocationIndex(forecastDay.getDateLocationIndex());
		
		if (fromDb == null) {
            log.debug("ForecastDay neu Anlegen" + forecastDay.getDateLocationIndex());
            forecastDayRepository.save(forecastDay);
        } else {
        	log.debug("Update ForecastDay " + forecastDay.getDateLocationIndex());
        	BeanUtils.copyProperties(forecastDay, fromDb, "dateLocationIndex", "id", "date", "locationId");
            forecastDayRepository.save(fromDb);
        }
		log.debug("End saving ForecastDay");

	}
}
