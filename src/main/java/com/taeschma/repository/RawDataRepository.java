package com.taeschma.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.taeschma.domain.StationRawData;

public interface RawDataRepository extends MongoRepository<StationRawData, ObjectId> {

}
