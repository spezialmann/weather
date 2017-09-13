package com.taeschma.controller;

import java.util.List;

import com.taeschma.domain.*;
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
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author marco
 */
@Controller
public class IndexController {

    private final Logger log = LoggerFactory.getLogger(IndexController.class);

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
            @RequestParam(value = "station", defaultValue = "", required = false) String station,
            @RequestParam(value = "spot", defaultValue = "", required = false) Integer spot,
            Model model) {
        log.debug("index Action");
        List<StationRawData> findAllForToday = rawDataService.findAllForToday(station);
        List<CurrentStationWeather> currentStationWeatherList = WeatherMapper.getCurrentStationWeather(findAllForToday);
        log.debug("Anzahl: " + currentStationWeatherList.size());

        if (currentStationWeatherList != null && currentStationWeatherList.size() > 0) {
            Date lastUpdate = currentStationWeatherList.get(0).getTimestamp();

            log.info(lastUpdate.toString());

            ZoneId defaultZoneId = ZoneId.of("CEST");
            //1. Convert Date -> Instant
            Instant instant = lastUpdate.toInstant();
            //System.out.println("instant : " + instant); //Zone : UTC+0
            //3. Instant + system default time zone + toLocalDateTime() = LocalDateTime
            LocalDateTime localDateTime = instant.atZone(defaultZoneId).toLocalDateTime();
            //System.out.println("localDateTime : " + localDateTime);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

            String formatDateTime = localDateTime.format(formatter);

            model.addAttribute("lastUpdate", formatDateTime);
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
    public @ResponseBody
    CurrentWeather showCurrentWeather(@RequestParam(value = "location", defaultValue = "", required = false) String location) {
        String tempLocationId = "";
        if (location == null || location.isEmpty()) {
            Location loc = locationService.findAll().get(0);
            tempLocationId = loc.getLocationId();
        } else {
            tempLocationId = location;
        }
        log.debug("Location: " + tempLocationId);
        Location loc = locationService.find(tempLocationId);
        log.debug("Show weather for: " + loc.getLocationName());
        return weatherService.getCurrentWeatherForLocation(loc);
    }

    @RequestMapping(value = "/rain", method = RequestMethod.GET)
    public String rainChart(Model model) {
        List<DiagramData> rain = rainService.getRain();
        log.info(rain.toString());

        model.addAttribute("rain", rain);

        return "rain";
    }

    /**
     * Start aggregation manually
     *
     * @return
     */
    @RequestMapping(value = "/analytics")
    public String testAnalytics() {
        analyticService.updateAllWeatherData();
        return "rain";
    }

}
