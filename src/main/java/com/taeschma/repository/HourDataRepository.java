package com.taeschma.repository;

import com.taeschma.domain.Hour;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by marco on 03.08.16.
 */
public interface HourDataRepository extends MongoRepository<Hour, ObjectId> {
    public Hour findTopByOrderByTimestampHourDesc();
    public Hour findByTimestampHour(Date date);
    public List<Hour> findAllByOrderByTimestampHourDesc();
}
