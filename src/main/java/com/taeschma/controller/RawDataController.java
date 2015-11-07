package com.taeschma.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taeschma.domain.RawData;
import com.taeschma.service.RawDataService;

@Controller
public class RawDataController {
	
	private final Logger log = LoggerFactory.getLogger(RawDataController.class);
	
	@Autowired
	private RawDataService rawDataService;
	
	@RequestMapping(value = "/rawdata", method = RequestMethod.POST)
    public @ResponseBody RawData saveRawData(@RequestBody RawData rawData) {
		
		log.info("RawData " + rawData.toString());
		
        rawDataService.save(rawData);
        log.debug("RawData saved!");
        return rawData;
    }
}
