package com.taeschma.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taeschma.domain.CurrentStationWeather;
import com.taeschma.domain.CurrentWeather;
import com.taeschma.domain.Location;
import com.taeschma.domain.StationRawData;
import com.taeschma.service.LocationService;
import com.taeschma.service.RawDataService;
import com.taeschma.service.WeatherService;
import com.taeschma.util.WeatherMapper;

/**
 *
 * @author marco
 */
@Controller
public class IndexController {
    
    private final Logger log = LoggerFactory.getLogger(IndexController.class);
        
    @Value("${weather.station.default}")
	private String stationId;
    @Value("${weather.station.show.current.index}")
	private Integer measuringSpot;
        
    @Autowired
    private RawDataService rawDataService;
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private LocationService locationService;
    
    /**
     * Index page with weather station info
     * 
     * @param station
     * @param spot
     * @param model
     * @return
     */
    @RequestMapping("/")
    String index(
    		@RequestParam(value = "station", defaultValue="", required = false) String station,
    		@RequestParam(value = "spot", defaultValue="", required = false) Integer spot,
    		Model model) {
        log.info("index Action");
        List<StationRawData> findAllForToday = rawDataService.findAllForToday(station);
        List<CurrentStationWeather> currentStationWeatherList = WeatherMapper.getCurrentStationWeather(findAllForToday);
        model.addAttribute("currentStationWeatherList", currentStationWeatherList);
        return "index";
    }
      
    
    /**
     * Ajax request for current weather data
     * 
     * @param location
     * @return
     */
    @RequestMapping("showcurrent")
    public @ResponseBody CurrentWeather showCurrentWeather(@RequestParam(value = "location", defaultValue="", required = false) String location) {
    	if(location==null || location.isEmpty()) {
    		Location loc = locationService.findAll().get(0);
    		location = loc.getLocationId();
    	}
    	log.debug("Location: " + location);
    	Location loc = locationService.find(location);
    	log.debug("Show weather for: " + loc.getLocationName());
    	return weatherService.getCurrentWeatherForLocation(loc);
    }

}
