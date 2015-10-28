package com.taeschma.controller;

import com.taeschma.domain.Location;
import com.taeschma.service.LocationService;
import com.taeschma.service.WeatherService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author marco
 */
@Controller
public class LocationController {

    private final Logger log = LoggerFactory.getLogger(LocationController.class);
    
    private LocationService locationService;
    private WeatherService weatherService;

    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }
    @Autowired
    public void setweatherService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    public String list(Model model) {
        log.debug("list action");
        model.addAttribute("locations", locationService.findAll());
        return "locations";
    }

    @RequestMapping("/location/{id}")
    public String showLocation(@PathVariable String id, Model model) {
        model.addAttribute("location", locationService.find(id));
        return "locationshow";
    }

    @RequestMapping("/location/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("location", locationService.find(id));
        return "locationform";
    }

    @RequestMapping("/location/new")
    public String newLocation(Model model) {
        model.addAttribute("location", new Location());
        return "locationform";
    }

    @RequestMapping(value = "/location", method = RequestMethod.POST)
    public String saveLocation(Location location) {
    	log.debug("Location-Name-vor: " + location.getLocationName());
        locationService.saveOrUpdate(location);
        log.debug("Location-Name-nach: " + location.getLocationName());
        weatherService.updateWeatherForLocation(location);
        return "redirect:/location/" + location.getLocationId();
    }

    @RequestMapping("/location/delete/{id}")
    public String delete(@PathVariable String id) {
        locationService.deleteLocation(id);
        return "redirect:/locations";
    }
}
