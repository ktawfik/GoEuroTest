package com.goeuro.client;

import java.util.List;

import com.goeuro.exception.GoEuroClientBaseException;
import com.goeuro.model.Location;
/**
 * This is the interface that contains the functionalities for the ApiClient.
 * @author ktawfik
 *
 */
public interface GoEuroApiClient {

	/**
	 * List all suggested cities for the given city name, list will be empty in case of no suggestion
	 * @param cityName String
	 * @return List<City>
	 * @throws GoEuroClientBaseException 
	 */
	public List<Location> findSuggestedLocationsByCityName(String cityName) throws GoEuroClientBaseException;

}
