package com.taeschma.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.taeschma.util.ObjectID_Serializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;

/**
 * Created by marco on 03.08.16.
 */
@Document(collection = "analytic_hour")
public class Hour {
    @Id
    private ObjectId id;
    private String stationId;
    private Date timestampHour;
    private int count;
    private Float precipTotalMM;
    private Map<Integer, Float> values;

    public Hour(String stationId, Date timestampHour, int count, Float precipTotalMM) {
        this.stationId = stationId;
        this.timestampHour = timestampHour;
        this.count = count;
        this.precipTotalMM = precipTotalMM;
    }

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

    public Date getTimestampHour() {
        return timestampHour;
    }

    public void setTimestampHour(Date timestampHour) {
        this.timestampHour = timestampHour;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Float getPrecipTotalMM() {
        return precipTotalMM;
    }

    public void setPrecipTotalMM(Float precipTotalMM) {
        this.precipTotalMM = precipTotalMM;
    }

    public Map<Integer, Float> getValues() {
        return values;
    }

    public void setValues(Map<Integer, Float> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "Hour{" +
                "id=" + id +
                ", timestampHour=" + timestampHour +
                ", count=" + count +
                ", precipTotalMM=" + precipTotalMM +
                ", values=" + values +
                '}';
    }
}
