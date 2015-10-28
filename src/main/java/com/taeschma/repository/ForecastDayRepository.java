package com.taeschma.repository;

import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.taeschma.domain.ForecastDay;

public interface ForecastDayRepository extends PagingAndSortingRepository<ForecastDay, ObjectId> {
	public ForecastDay findByDateLocationIndex(String dateLocationIndex);
}
