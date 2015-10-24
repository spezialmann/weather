package com.taeschma.controller;

import com.taeschma.domain.CurrentWeather;
import com.taeschma.domain.Location;
import com.taeschma.service.LocationService;
import com.taeschma.service.WeatherService;
import com.taeschma.wwo.ApiService;
import com.taeschma.wwo.response.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author marco
 */
@Controller
public class IndexController {
    
    private final Logger log = LoggerFactory.getLogger(IndexController.class);
    
    @Autowired
    private ApiService apiService;
    
    @Autowired
    private LocationService locationService;
    
    @Autowired
    private WeatherService weatherService;
    
    @RequestMapping("/")
    String index(){
        log.debug("index Action");
        return "index";
    }
    
    @RequestMapping("/weathercheck")
    String weathercheck(){
        log.info("weathercheck Action");
        
        Weather weatherResponse = apiService.doWeatherRequest("London");
        log.error("Min Temp morgen: " + weatherResponse.getData().getWeather().get(1).getMintempC() + " MIN Grad ");
        
        log.warn("" + weatherResponse.getData().toString());
        
        log.warn("Current Temp: : " + weatherResponse.getData().getCurrent_condition().get(0).getWinddir16Point()); 
        log.warn("Current Weather ID: : " + weatherResponse.getData().getCurrent_condition().get(0).getWeatherCode()); 
        log.warn("Current Icon: : " + weatherResponse.getData().getCurrent_condition().get(0).getWeatherIconUrl().get(0).getValue());
        
        log.error("AreaName: " + weatherResponse.getData().getNearest_area().get(0).getAreaName().get(0).getValue());
        
        
        return "index";
    }
    
    @RequestMapping("showcurrent")
    public @ResponseBody CurrentWeather showCurrentWeather() {
    	Location loc = locationService.findAll().get(0);
    	log.info("Show weather for: " + loc.getLocationName());
    	return weatherService.getCurrentWeatherForLocation(loc);
    	
    }
}
