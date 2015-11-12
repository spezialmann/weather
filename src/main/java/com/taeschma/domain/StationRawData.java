package com.taeschma.domain;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.taeschma.util.ObjectID_Serializer;

@Document(collection = "StationRawDataCollection")
public class StationRawData {
	@Id
	private ObjectId id;

	private String stationId;

	private Date timeOfRecording;
	private String rawData;

	@JsonSerialize(using = ObjectID_Serializer.class)
	public ObjectId getId() {
		return id;
	}

	@JsonSerialize(using = ObjectID_Serializer.class)
	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public Date getTimeOfRecording() {
		return timeOfRecording;
	}

	public void setTimeOfRecording(Date timeOfRecording) {
		this.timeOfRecording = timeOfRecording;
	}

	public String getRawData() {
		return rawData;
	}

	public void setRawData(String rawData) {
		this.rawData = rawData;
	}

	@Override
	public String toString() {
		return "RawData [id=" + id + ", timeOfRecording=" + timeOfRecording + ", rawData=" + rawData + "]";
	}

}