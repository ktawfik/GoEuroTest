package com.goeuro.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.goeuro.client.GoEuroApiClient;
import com.goeuro.dto.LocationCsvDto;
import com.goeuro.exception.GoEuroClientBaseException;
import com.goeuro.exception.GoEuroError;
import com.goeuro.exception.LogicalException;
import com.goeuro.mapper.LocationMapper;
import com.goeuro.model.Location;
import com.goeuro.util.CSVUtil;

/**
 * 
 * @author ktawfik
 * this class add the required business logic after dealing with the ClientAPI and retrieve the suggested cities - or whatever other methods 
 * added afterwards-
 */
@Controller
public class GoEuroTaskServiceImpl implements GoEuroTaskService{

	@Autowired
	GoEuroApiClient client;
	
	/**
	 * List all suggested locations by city name.
	 * @return List<Location>
	 */
	@Override
	public List<Location> getSuggestedLocationsByCityName(String cityName) throws GoEuroClientBaseException{
		if(StringUtils.isEmpty(cityName)){
			throw new LogicalException(GoEuroError.GOEURO_006);
		}
		return client.findSuggestedLocationsByCityName(cityName);
	}

	/**
	 * write a list of suggested locations of city name to a csv file.
	 * 
	 */
	@Override
	public boolean writeSuggestedLocationsToCSV(String cityName) throws GoEuroClientBaseException {
		List<Location> locationsList = getSuggestedLocationsByCityName(cityName);
		
		if(CollectionUtils.isEmpty(locationsList)){
			throw new LogicalException(GoEuroError.GOEURO_002);
		}
		
		List<LocationCsvDto> locationCsvList = new ArrayList<LocationCsvDto>();
		LocationCsvDto newEntry = null;
		for(Location l : locationsList){
			newEntry = LocationMapper.map(l);
			if(newEntry != null){
				locationCsvList.add(newEntry);
			}
		}
		String fileName = cityName + "_" + LocalDateTime.now();
		CSVUtil.writeListToCSV(fileName, locationCsvList, LocationCsvDto.getColumnsList());
		return true;
	}
}
