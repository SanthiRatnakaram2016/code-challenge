package com.sensor.dao;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sensor.domain.Metric;

/**
 * This is MetricDAO, which helps to perform all persistent/database related operations 
 * pertaining to metrics sensed by the sensor.
 */
@Repository("metricDAO")
public class MetricDAO extends BasicDAO<Metric, String> implements SensorDAO {

	/**
	 * Initializing the dataStore through the constructor.
	 */
	@Autowired
    protected MetricDAO(Datastore dataStore) {
        super(dataStore);
        ensureIndexes();
    }
	
	/**
	 * This method retreives all the metrics saved into the database..
	 */
	public List<Metric> read() {
		return this.getDatastore().find(Metric.class).asList();
	}
	
	/**
	 * This method retreives all the metrics within a specific time range.
	 * Boundary values are not considered while retreival.
	 * @param  startTime  start timestamp.
	 * @param  endTime  end timestamp.
	 */
	public List<Metric> readByTimeRange(Long startTime, Long endTime) {
		
		Query<Metric> query = this.getDatastore().find(Metric.class);
		query.and(query.criteria("timeStamp").greaterThan(new Long(startTime)), query.criteria("timeStamp").lessThan(new Long(endTime)));
		
		return query.asList();
	}
	
}
