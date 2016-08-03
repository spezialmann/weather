package com.taeschma.api;

import com.taeschma.domain.StationRawData;
import com.taeschma.service.RawDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by marco on 02.08.16.
 */
@RestController
public class RawDataApi {

    private final Logger log = LoggerFactory.getLogger(RawDataApi.class);

    @Autowired
    RawDataService rawDataService;

    /**
     *
     * List of RawData from weather station for 1 day (24 hours)
     *
     * @param stationId
     * @param dateAndTimeFrom //Example 02.08.2016-16:00:00.000CET
     * @return
     */
    @RequestMapping(value = "/api/raw-data", method = RequestMethod.GET)
    @ResponseBody
    public List<StationRawData> findRawData(
            @RequestParam(value = "station", defaultValue = "@home", required = false) String stationId,
            @RequestParam("datetimefrom") @DateTimeFormat(pattern = "dd.MM.yyyy-HH:mm:ss.SSSz") ZonedDateTime dateAndTimeFrom
    ) {
        ZonedDateTime dateAndTimeTo = dateAndTimeFrom.plusDays(1L);

        log.debug("From: " + dateAndTimeFrom.toString());
        Date from = Date.from(dateAndTimeFrom.toInstant());
        log.info("--> From (old) Date: " + from.toString());

        log.debug("To: " + dateAndTimeTo.toString());
        Date to = Date.from(dateAndTimeTo.toInstant());
        log.info("--> To (old) Date: " + to.toString());

        List<StationRawData> allForPeriod = rawDataService.findAllForPeriod(stationId, from, to);

        if (allForPeriod != null) {
            log.info("count: " + allForPeriod.size());
        }


        return allForPeriod;
    }

}
