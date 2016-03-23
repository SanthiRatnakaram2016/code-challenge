package com.sensor.dao;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sensor.domain.Alert;

/**
 * This is AlertDAO, which helps to perform all persistent/database related operations 
 * pertaining to alerts.
 */
@Repository("alertDAO")
public class AlertDAO extends BasicDAO<Alert, String> implements SensorDAO{

	@Autowired
    protected AlertDAO(Datastore dataStore) {
        super(dataStore);
        ensureIndexes();
    }
	
	/**
	 * This method retreives all the alerts saved into the database..
	 */
	public List<Alert> read() {
		return this.getDatastore().find(Alert.class).asList();
	}
	
	/**
	 * This method retreives all the alerts within a specific time range.
	 * Boundary values are not considered while retreival.
	 * @param  startTime  start timestamp.
	 * @param  endTime  end timestamp.
	 */
	public List<Alert> readByTimeRange(Long startTime, Long endTime) {
		
		Query<Alert> query = this.getDatastore().find(Alert.class);
		query.and(query.criteria("timeStamp").greaterThan(new Long(startTime)), query.criteria("timeStamp").lessThan(new Long(endTime)));
		
		return query.asList();
	}

	
}
