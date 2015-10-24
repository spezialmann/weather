package com.taeschma.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.taeschma.domain.CurrentWeather;
import com.taeschma.domain.Location;
import com.taeschma.repository.CurrentWeatherRepository;
import com.taeschma.util.ApiMapper;
import com.taeschma.wwo.ApiService;
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

	/**
	 * Fetching weather data Every xxx minutes
	 */
	@Scheduled(initialDelay = 5000, fixedDelay = 600000)
	public void updateWeather() {
		log.debug("Start api request");

		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();

		List<Location> locations = locationService.findAll();

		Weather weatherResponse;
		
		for (Location location : locations) {

			weatherResponse = apiService.doWeatherRequest(location.getLocationName());

			if (weatherResponse != null) {
				CurrentWeather current = ApiMapper
						.getCurrentWeather(weatherResponse.getData().getCurrent_condition().get(0), date, location.getLocationId());
				if (current != null) {
					currentWeatherRepository.save(current);
				}
			}
		}
		log.debug("End api request");

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
}
