package com.taeschma.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.taeschma.domain.*;
import com.taeschma.repository.HourDataRepository;
import com.taeschma.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@Value("${weather.stations.forecast.location.location.id}")
	private String locationId;
        
    @Autowired
    private RawDataService rawDataService;
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private AnalyticService analyticService;
    @Autowired
    private HourDataRepository hourDataRepository;
    @Autowired
    private RainService rainService;
    
    /**
     * Index page with weather station info
     * 
     * @param station
     * @param spot
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String index(
    		@RequestParam(value = "station", defaultValue="", required = false) String station,
    		@RequestParam(value = "spot", defaultValue="", required = false) Integer spot,
    		Model model) {
        log.debug("index Action");
        List<StationRawData> findAllForToday = rawDataService.findAllForToday(station);
        List<CurrentStationWeather> currentStationWeatherList = WeatherMapper.getCurrentStationWeather(findAllForToday);
        log.debug("Anzahl: " + currentStationWeatherList.size());
        
        if(currentStationWeatherList!=null && currentStationWeatherList.size()>0) {
        	model.addAttribute("lastUpdate", currentStationWeatherList.get(0).getTimestamp());
        }
        
        
        model.addAttribute("currentStationWeatherList", currentStationWeatherList);
        model.addAttribute("currentStationWeather", weatherService.getCurrentWeatherForStationId(locationId));
        
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


    @RequestMapping(value = "/analytics")
    public String testAnalytics() {
        analyticService.updateAllWeatherData();
        return "test";
    }

    @RequestMapping(value = "/rain", method = RequestMethod.GET)
    public String rainAnalytics(Model model) {


        List<DiagramData> rain = rainService.getRain();

        StringBuilder keys = new StringBuilder("[");
        StringBuilder vals = new StringBuilder("[");

        int i = 0;
        for (DiagramData dd : rain) {
            if(i>0) {
                keys.append(", ");
                vals.append(", ");
            }

            keys.append("\"" + dd.getHour() + "\"");
            vals.append("\"" + dd.getDataValue() + "\"");

            i++;
        }
        keys.append("]");
        vals.append("]");

        log.info("Anzahl: " + rain.size());
        log.info(rain.toString());
        log.info(keys.toString());
        log.info(vals.toString());

        model.addAttribute("keys", keys);
        model.addAttribute("vals", vals);

        return "rain";
    }


}
