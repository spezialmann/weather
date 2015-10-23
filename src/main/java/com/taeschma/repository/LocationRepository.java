package com.taeschma.repository;

import com.taeschma.domain.Location;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author marco
 */
public interface LocationRepository extends MongoRepository<Location, ObjectId> {
       public Location findByLocationId(String locationId);
}
