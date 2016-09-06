package com.goeuro.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.goeuro.dto.LocationCsvDto;
import com.goeuro.exception.GoEuroClientBaseException;
import com.goeuro.exception.GoEuroError;
import com.goeuro.exception.LogicalException;
import com.goeuro.mapper.LocationMapper;
import com.goeuro.model.Location;
/**
 * @author ktawfik
 * this class implements the GoEuroApiClient and contains the logic for dealing with the REST end point.
 * this class act as the Repo class in normal MVC spring app, as it use a RestTemplate and contains required methods that we need to interact with the endpoint
 */
@Service
public class GoEuroApiClientImpl implements GoEuroApiClient {
	
	
	private static final RestTemplate REST_TEMPLATE = new RestTemplate();
	
	@Value(value="${goeuro.client.endpoint.url}")
	private String endpointUrl;
	
	/**
	 * Get list of suggested Locations based on the provided cityName
	 * @return List<City>
	 */
	public List<Location> findSuggestedLocationsByCityName(String cityName) throws GoEuroClientBaseException{
		if(StringUtils.isEmpty(cityName)){
			throw new LogicalException(GoEuroError.GOEURO_006);
		}
		ResponseEntity<Location[]> cityList =  REST_TEMPLATE.getForEntity(endpointUrl, Location[].class, cityName);
		if(cityList == null || cityList.getBody() == null || cityList.getBody().length == 0){
			throw new LogicalException(GoEuroError.GOEURO_002);
		}
		List<LocationCsvDto> cityCsvList = new ArrayList<LocationCsvDto>();
		LocationCsvDto newEntry = new LocationCsvDto();
		for(Location c : cityList.getBody()){
			newEntry = LocationMapper.map(c);
			if(newEntry != null){
				cityCsvList.add(newEntry);
			}
		}
		return Arrays.asList(cityList.getBody());
	}

}
