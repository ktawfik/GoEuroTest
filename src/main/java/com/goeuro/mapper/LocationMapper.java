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

	private static final String ID_COL = "_id";
	private static final String LATIT_COL = "latitude";
	private static final String LONG_COL = "longitude";
	private static final String NAME_COL = "name";
	private static final String TYPE_COL = "type";
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
	 * This method map from Map have key as the field name and the value to a LocationCsvDto
	 * @return LocationCsvDto
	 */
	public  LocationCsvDto map(Map<String, String> nvp){
		if(nvp == null || nvp.isEmpty()){
			return null;
		}
		LocationCsvDto obj = new LocationCsvDto();
		for(Map.Entry<String, String> e : nvp.entrySet()){
			if(ID_COL.equals(e.getKey())){
				obj.set_id(Long.parseLong(e.getValue()));
			}else if(LATIT_COL.equals(e.getKey())){
				obj.setLatitude(Double.parseDouble(e.getValue()));
			}else if(LONG_COL.equals(e.getKey())){
				obj.setLongitude(Double.parseDouble(e.getValue()));
			}else if(NAME_COL.equals(e.getKey())){
				obj.setName(e.getValue());
			}else if(TYPE_COL.equals(e.getKey())){
				obj.setType(e.getValue());
			}
		}
		return obj;
	}
}
