package com.goeuro.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author ktawfik
 * This dto represents the dto object to be written to CSV
 */
public class LocationCsvDto implements Serializable {

	private Long _id;
	
	private String name;
	
	private String type;
	
	private Double latitude;

	private Double longitude;

	public Long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public static List<String> getColumnsList(){
		return Arrays.asList("_id","name","type","latitude","longitude");
	}
	/**
	 * simple equals method that compare this.id with the passed object and the name as well
	 * @return boolean
	 */
	public boolean equals(Object o){
		if(o == null) return false;
		if(!(o instanceof LocationCsvDto)) return false;
		LocationCsvDto loc = (LocationCsvDto)o;
		return this._id.equals(loc._id) && name.equals(loc.name);
	}
	
}
