package com.taeschma.controller;

import com.taeschma.wwo.ApiService;
import com.taeschma.wwo.response.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author marco
 */
@Controller
public class IndexController {
    
    private final Logger log = LoggerFactory.getLogger(IndexController.class);
    
    @Autowired
    private ApiService apiService;
    
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
}
