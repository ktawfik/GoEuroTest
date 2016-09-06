package com.goeuro.service;

import java.util.List;

import com.goeuro.exception.GoEuroClientBaseException;
import com.goeuro.model.Location;
/**
 * 
 * @author ktawfik
 * The service interface that handle the business logic
 */
public interface GoEuroTaskService {

	public List<Location> getSuggestedLocationsByCityName(String cityName) throws GoEuroClientBaseException;
	
	public void writeSuggestedLocationsToCSV(String cityName) throws GoEuroClientBaseException;
	
}
