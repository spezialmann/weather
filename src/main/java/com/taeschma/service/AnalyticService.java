package com.taeschma.service;

import com.taeschma.domain.Hour;
import com.taeschma.domain.StationRawData;
import com.taeschma.repository.HourDataRepository;
import com.taeschma.util.WeatherMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by marco on 03.08.16.
 */
@Service
public class AnalyticService {

    private final Logger log = LoggerFactory.getLogger(AnalyticService.class);

    @Value("${weather.station.default}")
    private String stationId;

    @Value("${weather.station.analytics.years.back}")
    private Integer yearsBack;

    @Autowired
    private RawDataService rawDataService;

    @Autowired
    private HourDataRepository hourDataRepository;


    @Scheduled(initialDelayString = "${schedule.analytic.service.initial.delay}",
            fixedDelayString = "${schedule.analytic.service.fixed.delay}")
    public void updateAllWeatherData() {
        log.debug("Start hourly analytics");

        ZonedDateTime startHour = getStartHour();
        ZonedDateTime currentHour = ZonedDateTime.now();
        ZonedDateTime nextHour = currentHour.plusHours(1);

        ZonedDateTime temp;
        Integer lastRainTic = null;

        Hour currentLastHour = hourDataRepository.findTopByOrderByTimestampHourDesc();

        while (startHour.isBefore(nextHour)) {


            ZonedDateTime endOfHour = startHour.plusHours(1);

            Date dateFrom = Date.from(startHour.toInstant());
            Date dateTo = Date.from(endOfHour.toInstant());

            Hour findByTimestampHour = hourDataRepository.findByTimestampHour(dateFrom);

            if (findByTimestampHour != null ) {  //hour already exists

                log.debug("findByTimestampHour.getId() == currentLastHour.getId()");
                log.debug(findByTimestampHour.getId() + "");
                log.debug(currentLastHour.getId() + "");
                log.debug("if true: " + (findByTimestampHour.getId().equals(currentLastHour.getId())));
                log.debug("");

                if(findByTimestampHour.getId() == currentLastHour.getId()) { //if the current hour == the last hour in the DB
                    log.info("++++++ Aktuelle Stunde loeschen und neu Berechnen ++++++");
                    hourDataRepository.delete(currentLastHour.getId());
                    findByTimestampHour = null;
                }
            }

            if(findByTimestampHour == null) {
                Hour newHour = new Hour(this.stationId, dateFrom, 0, 0.0F);

                List<StationRawData> allForPeriod = rawDataService.findAllForPeriod(this.stationId, dateFrom, dateTo);

                int counter = 0;
                int minute;
                Float currentPrecipMM;
                Float sumPrecipMM = 0.0F;
                Map<Integer, Float> valueMap = new HashMap<>();
                for (StationRawData srd : allForPeriod) {

                    try {
                        temp = ZonedDateTime.ofInstant(srd.getTimeOfRecording().toInstant(), ZoneOffset.UTC);
                        minute = temp.getMinute();

                        String[] dataValues = srd.getRawData().split(";");

                        // RAIN
                        Integer currentTics = Integer.parseInt(dataValues[22]);
                        Integer ticsSinceLast = 0;
                        // first entry of the day or no rain since last entry
                        /**
                         * TODO Calculation of the first value of the day need the
                         * last value from day before...
                         */
                        if (lastRainTic == null || lastRainTic.equals(currentTics)) {
                            currentPrecipMM = new Float("0.0");
                        }
                        // rain
                        else {
                            ticsSinceLast = WeatherMapper.getCurrentPrecipCount(lastRainTic, currentTics);
                            currentPrecipMM = (ticsSinceLast * 295 / new Float("1000.0"));

                        }

                        valueMap.put(minute, currentPrecipMM);
                        sumPrecipMM += currentPrecipMM;


                        lastRainTic = currentTics;
                        counter++;

                    } catch (Exception e) {
                        log.debug("Error parsing temperature: " + e.getMessage());
                    }

                }

                if(valueMap!=null && !valueMap.isEmpty()) {
                    newHour.setValues(valueMap);
                    newHour.setPrecipTotalMM(sumPrecipMM);
                    newHour.setCount(counter);

                    hourDataRepository.save(newHour);
                }

                log.debug("Hour: " + newHour.toString());
            }

            startHour = endOfHour;
        }


        log.info("End hourly analytics");
    }

    /**
     * start hour for hourly analytics
     * --> search last entry
     * --> if no entries available then calculate start hour NOW minus YEARS-BACK param
     *
     * @return
     */
    private ZonedDateTime getStartHour() {
        ZonedDateTime ret = ZonedDateTime.now();
        ret = ret.withMinute(0);
        ret = ret.withSecond(0);
        ret = ret.withNano(0);

        Hour lastHour = hourDataRepository.findTopByOrderByTimestampHourDesc();

        long count = hourDataRepository.count();


        //first hourly analytics
        if (lastHour == null) {
            ret = ret.minusYears(yearsBack);
            //ret = ret.minusWeeks(1);
        }
        else  {
            if (count>1) {
                hourDataRepository.delete(lastHour.getId());
                lastHour = hourDataRepository.findTopByOrderByTimestampHourDesc();
            }
            ret = ZonedDateTime.ofInstant(lastHour.getTimestampHour().toInstant(), ZoneOffset.UTC);
        }

        return ret;
    }
}
