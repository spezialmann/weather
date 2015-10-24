package com.taeschma.repository;

import com.taeschma.domain.CurrentWeather;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author marco
 */
public interface CurrentWeatherRepository extends PagingAndSortingRepository<CurrentWeather, ObjectId> {
	
	public List<CurrentWeather> findByLocationId(String locationId, Pageable pageable);
}
