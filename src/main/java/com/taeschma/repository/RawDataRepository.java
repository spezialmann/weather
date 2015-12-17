package com.taeschma.repository;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.taeschma.domain.StationRawData;

public interface RawDataRepository extends MongoRepository<StationRawData, ObjectId> {
	public List<StationRawData> findByStationIdAndTimeOfRecordingBetween(String stationId, Date start, Date end, Sort sort);
}
