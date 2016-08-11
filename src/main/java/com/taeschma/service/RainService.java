package com.taeschma.service;

import com.taeschma.domain.DiagramData;
import com.taeschma.domain.Hour;
import com.taeschma.repository.HourDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by marco on 08.08.16.
 */
@Service
public class RainService {

    private final Logger log = LoggerFactory.getLogger(RainService.class);

    @Autowired
    HourDataRepository hourDataRepository;

    public RainService() {
    }

    public List<DiagramData> getRain() {

        List<DiagramData> ret = new ArrayList<>();

        ZonedDateTime start = ZonedDateTime.now();
        start = start.withHour(0);
        start = start.withMinute(0);
        start = start.withSecond(0);
        start = start.withNano(0);

        start = start.minusDays(7L);

        ZonedDateTime end = start.plusDays(1L);

        log.info("Von: " + start);
        log.info("Bis: " + end);


        List<Hour> byDatesBetween = hourDataRepository.findByTimestampHourBetweenOrderByTimestampHourAsc(Date.from(start.toInstant()), Date.from(end.toInstant()));



        ZonedDateTime temp;
        for (Hour h : byDatesBetween) {
            Date timestampHour = h.getTimestampHour();
            temp = toZonedDateTime(timestampHour);

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
