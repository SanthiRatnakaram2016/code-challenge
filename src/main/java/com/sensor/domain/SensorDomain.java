package com.sensor.domain;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * This is the parent class of Alert and Metric POJOs.
 * It contains the common attributes of both of these POJOs.
 */
@Entity
public class SensorDomain {

	@Id private ObjectId id;
	private Integer value;
	private Integer baseWeight;
	private Long timeStamp;
	
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public Integer getBaseWeight() {
		return baseWeight;
	}
	public void setBaseWeight(Integer baseWeight) {
		this.baseWeight = baseWeight;
	}
	public Long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	@Override
    public String toString() {
        return String.format(
                "SensorDomain[id=%s, value='%s', baseWeight='%s', timeStamp='%s']",
               id, value, baseWeight,timeStamp);
    }
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
}
