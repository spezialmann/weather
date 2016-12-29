package com.taeschma.domain;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.taeschma.util.ObjectID_Serializer;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;

@Document(collection = "StationRawDataCollection")
@CompoundIndexes({
    @CompoundIndex(name = "ind_time_of_rec_desc", def = "{'timeOfRecording' : -1}")
})
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
