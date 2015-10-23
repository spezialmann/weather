package com.taeschma.service;

import com.taeschma.domain.Location;
import com.taeschma.repository.LocationRepository;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

/**
 *
 * @author marco
 */
@Service
public class LocationService {

    private final Logger log = LoggerFactory.getLogger(LocationService.class);
    
    @Autowired
    private LocationRepository locationRepository;

    public List<Location> findAll() {
        return locationRepository.findAll(
                new Sort(
                    new Order(ASC, "locationName")
                )
        );
    }

    public Location find(String locationId) {
        return locationRepository.findByLocationId(locationId);
    }

    public boolean deleteLocation(String locationId) {
        Location findByLocationId = locationRepository.findByLocationId(locationId);
        if ( findByLocationId == null ) {
            throw new RuntimeException("Location with Id does not exist: " + locationId);
        }
        locationRepository.delete(findByLocationId);
        return true;
    }

    public Location saveOrUpdate(Location location) {
        if (location.getLocationId() == null || location.getLocationId().equals("")) {
            log.info("Location neu Anlegen" + location.toString());
            location.setLocationId(UUID.randomUUID().toString());
            location = locationRepository.insert(location);
        } else {
            location = locationRepository.save(location);
        }
        return location;
    }

}
