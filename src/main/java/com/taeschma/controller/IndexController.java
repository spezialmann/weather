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

import com.taeschma.domain.CurrentStationWeather;
import com.taeschma.domain.StationRawData;
import com.taeschma.service.RawDataService;
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
    
    @RequestMapping("/")
    String index(
    		@RequestParam(value = "station", defaultValue="", required = false) String station,
    		@RequestParam(value = "spot", defaultValue="", required = false) Integer spot,
    		Model model) {
        log.error("index Action");
        
        List<StationRawData> findAllForToday = rawDataService.findAllForToday(station);
        
        List<CurrentStationWeather> currentStationWeatherList = WeatherMapper.getCurrentStationWeather(findAllForToday);
        
        
        model.addAttribute("currentStationWeatherList", currentStationWeatherList);
        
        return "index";
    }
      
}
