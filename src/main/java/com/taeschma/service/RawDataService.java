package com.taeschma.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.taeschma.domain.StationRawData;
import com.taeschma.repository.RawDataRepository;

@Service
public class RawDataService {
	private final Logger log = LoggerFactory.getLogger(RawDataService.class);
	
	@Value("${weather.station.default}")
	private String stationId;
	
	@Autowired
	private RawDataRepository rawDataRepository;
	
	/**
	 * Save raw data from WDE1-2
	 *  
	 * @param rawData
	 * @return
	 */
	public StationRawData save(StationRawData rawData) {
		log.debug("Save RawData object");
		if(rawData.getTimeOfRecording()==null) {
			rawData.setTimeOfRecording(Calendar.getInstance().getTime());
		}
		if(rawData.getStationId()==null || rawData.getStationId().equals("") ) {
			rawData.setStationId(stationId);
		}
		return rawDataRepository.save(rawData);
	}
	
	/**
	 * find all raw data strings for station between start & end
	 * 
	 * @param stationId
	 * @param start
	 * @param end
	 * @return
	 */
	public List<StationRawData> findAllForPeriod(String stationId, Date start, Date end) {
        return rawDataRepository.findByStationIdAndTimeOfRecordingBetween(stationId, start, end, new Sort(Sort.Direction.ASC, "timeOfRecording"));
    }
	
	/**
	 * find all raw date string for a station for the current day
	 * 
	 * @param stationId
	 * @return
	 */
	public List<StationRawData> findAllForToday(String station) {
        Calendar startOfCurrentDay = Calendar.getInstance();
        startOfCurrentDay.set(Calendar.HOUR_OF_DAY, 0);
        startOfCurrentDay.set(Calendar.MINUTE, 0);
        startOfCurrentDay.set(Calendar.SECOND, 0);
        startOfCurrentDay.set(Calendar.MILLISECOND, 0);
        
        Calendar endOfCurrentDay = (Calendar) startOfCurrentDay.clone();
        endOfCurrentDay.add(Calendar.DAY_OF_MONTH, 1);

        if(station==null || station.equals("")) {
        	station = stationId;
        }
        
        return findAllForPeriod(station, startOfCurrentDay.getTime(), endOfCurrentDay.getTime());
    }
}
