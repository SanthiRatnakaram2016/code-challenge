package com.sensor.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sensor.domain.SensorDomain;

/**
 * This is the interface for all the concrete DAO classes.
 * It defines the common methods used in various DAOs.
 */
@Repository("SensorDAO")
public interface SensorDAO {

	public abstract List<? extends SensorDomain> read();
	public abstract List<? extends SensorDomain> readByTimeRange(Long startTime, Long endTime);
	
}
