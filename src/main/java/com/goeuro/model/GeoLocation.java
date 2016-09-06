package com.goeuro.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author ktawfik
 *
 */
public class GeoLocation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 93728742618L;
	
	public GeoLocation(){}

	@JsonProperty("latitude")
	private double latitude;
	@JsonProperty("longitude")
	private double longitude;
	
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
