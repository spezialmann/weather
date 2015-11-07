package com.taeschma.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.taeschma.util.ObjectID_Serializer;

@Document(collection = "ForecastDayCollection")
public class ForecastDay {

	@Id
	private ObjectId id;

	@Indexed(unique = true)
	private String dateLocationIndex;

	private Date date;
	private Integer weatherCode;
	private Integer maxTempC;
	private Integer minTempC;

	private BigDecimal precipMM;
	private Integer windspeedKmh;

	private String locationId;

	@JsonSerialize(using = ObjectID_Serializer.class)
	public ObjectId getId() {
		return id;
	}

	@JsonSerialize(using = ObjectID_Serializer.class)
	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getDateLocationIndex() {
		return dateLocationIndex;
	}

	public void setDateLocationIndex(String dateLocationIndex) {
		this.dateLocationIndex = dateLocationIndex;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getWeatherCode() {
		return weatherCode;
	}

	public void setWeatherCode(Integer weatherCode) {
		this.weatherCode = weatherCode;
	}

	public Integer getMaxTempC() {
		return maxTempC;
	}

	public void setMaxTempC(Integer maxTempC) {
		this.maxTempC = maxTempC;
	}

	public Integer getMinTempC() {
		return minTempC;
	}

	public void setMinTempC(Integer minTempC) {
		this.minTempC = minTempC;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public BigDecimal getPrecipMM() {
		return precipMM;
	}

	public void setPrecipMM(BigDecimal precipMM) {
		this.precipMM = precipMM;
	}

	public Integer getWindspeedKmh() {
		return windspeedKmh;
	}

	public void setWindspeedKmh(Integer windspeedKmh) {
		this.windspeedKmh = windspeedKmh;
	}

}
