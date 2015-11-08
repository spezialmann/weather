package com.taeschma.service;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taeschma.domain.StationRawData;
import com.taeschma.repository.RawDataRepository;

@Service
public class RawDataService {
	private final Logger log = LoggerFactory.getLogger(RawDataService.class);
	
	@Autowired
	private RawDataRepository rawDataRepository;
	
	public StationRawData save(StationRawData rawData) {
		log.debug("Save RawData object");
		if(rawData.getTimeOfRecording()==null) {
			rawData.setTimeOfRecording(Calendar.getInstance().getTime());
		}
		return rawDataRepository.save(rawData);
	}
}
