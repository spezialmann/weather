package com.taeschma.domain;

/**
 * Created by marco on 08.08.16.
 */
public class DiagramData {
    private int hour;
    private Float dataValue;

    public DiagramData(int hour, Float dataValue) {
        this.hour = hour;
        this.dataValue = dataValue;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public Float getDataValue() {
        return dataValue;
    }

    public void setDataValue(Float dataValue) {
        this.dataValue = dataValue;
    }

    @Override
    public String toString() {
        return "DiagramData{" +
                "hour=" + hour +
                ", dataValue=" + dataValue +
                '}';
    }
}
