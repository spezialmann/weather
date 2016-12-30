package com.taeschma.service;

import com.taeschma.domain.DiagramData;
import com.taeschma.domain.Hour;
import com.taeschma.repository.HourDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.*;

/**
 * Created by marco on 08.08.16.
 */
@Service
public class RainService {

    private final Logger log = LoggerFactory.getLogger(RainService.class);

    @Autowired
    private HourDataRepository hourDataRepository;

    public RainService() {
    }

    public List<DiagramData> getRain() {

        List<DiagramData> ret = new ArrayList<>();


        ZoneId berlin = ZoneId.of("Europe/Berlin");
        ZoneId utc = ZoneId.of("UTC");

        //Start
        ZonedDateTime berlinDateTimeStart = ZonedDateTime.now(berlin);
        berlinDateTimeStart = berlinDateTimeStart.withHour(0);
        berlinDateTimeStart = berlinDateTimeStart.withMinute(0);
        berlinDateTimeStart = berlinDateTimeStart.withSecond(0);
        berlinDateTimeStart = berlinDateTimeStart.withNano(0);
        berlinDateTimeStart = berlinDateTimeStart.minusMinutes(15L); //otherwise the query "between" not contains the first hour
        //berlinDateTimeStart = berlinDateTimeStart.minusDays(1L); //only for dev -> if data only for special date available

        //End
        ZonedDateTime berlinDateTimeEnd = berlinDateTimeStart.plusDays(1L);
        berlinDateTimeEnd = berlinDateTimeEnd.plusMinutes(30L);  //otherwise the query "between" not contains the last hour

        Date from = Date.from(berlinDateTimeStart.toInstant());
        Date until = Date.from(berlinDateTimeEnd.toInstant());
        log.debug("from (Date): " + from);
        log.debug("until (Date): " + until);

        List<Hour> byDatesBetween = hourDataRepository.findByTimestampHourBetweenOrderByTimestampHourAsc(from, until);


        ZonedDateTime temp;
        for (Hour h : byDatesBetween) {
            Date timestampHour = h.getTimestampHour();
            temp = toZonedDateTime(timestampHour);
            temp = temp.withZoneSameInstant(berlin);

            log.debug("TEMP:::: " + temp.toString());

            Float precipTotalMM = h.getPrecipTotalMM();
            DiagramData dd = new DiagramData(temp.getHour(), h.getPrecipTotalMM());

            ret.add(dd);
        }


        return ret;
    }

    public static ZonedDateTime toZonedDateTime(Date utilDate) {
        if (utilDate == null) {
            return null;
        }
        return ZonedDateTime.ofInstant(utilDate.toInstant(), ZoneOffset.UTC);
    }


}
