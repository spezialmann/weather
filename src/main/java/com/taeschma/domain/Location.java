package com.taeschma.domain;

import java.math.BigDecimal;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.taeschma.util.ObjectID_Serializer;

/**
 *
 * @author marco
 */
@Document(collection = "LocationCollection")
public class Location {

	@Id
	private ObjectId id;

	@Version
	private Integer version;

	@Indexed(unique = true)
	private String locationId;
	private String locationName;
	private BigDecimal latitude;
	private BigDecimal longitude;

	@JsonSerialize(using = ObjectID_Serializer.class)
	public ObjectId getId() {
		return id;
	}

	@JsonSerialize(using = ObjectID_Serializer.class)
	public void setId(ObjectId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Location{" + "id=" + id + ", version=" + version + ", locationId=" + locationId + ", locationName="
				+ locationName + ", latitude=" + latitude + ", longitude=" + longitude + '}';
	}

}
