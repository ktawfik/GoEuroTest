package com.goeuro.mapper;

import java.util.Map;

import com.goeuro.dto.LocationCsvDto;
import com.goeuro.model.Location;
/**
 * 
 * @author ktawfik
 * Helper class that map City <-> City*Dto objects, for any form of CityDto.
 */
public class LocationMapper implements ModelCsvMapper{

	/**
	 * Map City to CityCsvDto object.
	 * @param c
	 * @return CityCsvDto
	 */
	public static LocationCsvDto map(Location c){
		if(c == null){
			return null;
		}
		LocationCsvDto cityCsv = new LocationCsvDto();
		cityCsv.set_id(c.getId());
		cityCsv.setName(c.getName());
		cityCsv.setType(c.getType());
		if(c.getGeoPosition() != null){
			cityCsv.setLatitude(c.getGeoPosition().getLatitude());
			cityCsv.setLongitude(c.getGeoPosition().getLongitude());
		}
		return cityCsv;
	}
	/**
	 * 
	 */
	public  LocationCsvDto map(Map<String, String> nvp){
		if(nvp == null || nvp.isEmpty()){
			return null;
		}
		LocationCsvDto obj = new LocationCsvDto();
		for(Map.Entry<String, String> e : nvp.entrySet()){
			if("_id".equals(e.getKey())){
				obj.set_id(Long.parseLong(e.getValue()));
			}else if("latitude".equals(e.getKey())){
				obj.setLatitude(Double.parseDouble(e.getValue()));
			}else if("longitude".equals(e.getKey())){
				obj.setLongitude(Double.parseDouble(e.getValue()));
			}else if("name".equals(e.getKey())){
				obj.setName(e.getValue());
			}else if("type".equals(e.getKey())){
				obj.setType(e.getValue());
			}
		}
		return obj;
	}
}
