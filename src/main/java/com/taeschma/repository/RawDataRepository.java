package com.taeschma.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.taeschma.domain.RawData;

public interface RawDataRepository extends MongoRepository<RawData, ObjectId> {

}
