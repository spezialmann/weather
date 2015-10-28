package com.taeschma.controller;

import com.taeschma.domain.CurrentWeather;
import com.taeschma.domain.Location;
import com.taeschma.service.LocationService;
import com.taeschma.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author marco
 */
@Controller
public class IndexController {
    
    private final Logger log = LoggerFactory.getLogger(IndexController.class);
        
    @Autowired
    private LocationService locationService;
    
    @Autowired
    private WeatherService weatherService;
    
    @RequestMapping("/")
    String index(){
        log.debug("index Action");
        return "index";
    }
    
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
