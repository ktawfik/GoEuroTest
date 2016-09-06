package com.goeuro.service;

import java.util.List;

import com.goeuro.exception.GoEuroClientBaseException;
import com.goeuro.model.Location;

public interface GoEuroTaskService {

	public List<Location> getSuggestedLocationsByCityName(String cityName) throws GoEuroClientBaseException;
	
	public boolean writeSuggestedLocationsToCSV(String cityName) throws GoEuroClientBaseException;
	
}
