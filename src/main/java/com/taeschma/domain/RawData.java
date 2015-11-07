package com.taeschma.domain;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.taeschma.util.ObjectID_Serializer;

@Document(collection = "RawDataCollection")
public class RawData {
	@Id
    private ObjectId id;
	
	private Date timeOfRecording;
    private String rawData;
    
    @JsonSerialize(using=ObjectID_Serializer.class) 
	public ObjectId getId() {
		return id;
	}
    @JsonSerialize(using=ObjectID_Serializer.class) 
	public void setId(ObjectId id) {
		this.id = id;
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
