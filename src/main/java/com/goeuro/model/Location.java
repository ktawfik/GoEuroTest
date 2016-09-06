package com.goeuro.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 837349374972L;

	@JsonProperty("_id")
	private long id;
	
	@JsonProperty("key")
	private String key;

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("fullName")
	private String fullName; 
	
	@JsonProperty("iata_airport_code")
	private String iataAirportCode;
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("country")
	private String country;
	
	@JsonProperty("locationId")
	private long locationId;
	
	@JsonProperty("inEurope")
	private boolean inEurope;
	
	@JsonProperty("countryCode")
	private String countryCode;
	
	@JsonProperty("distance")
	private double distance;
	
	@JsonProperty("coreCountry")
	private boolean  coreCountry;
	
	@JsonProperty("geo_position")
	private GeoLocation geoPosition;
	
	
	public long getId() {
		return id;
	}
	public void setId(long _id) {
		this.id = _id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getIataAirportCode() {
		return iataAirportCode;
	}
	public void setIataAirportCode(String iata_airport_code) {
		this.iataAirportCode = iata_airport_code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public long getLocationId() {
		return locationId;
	}
	public void setLocationId(long location_id) {
		this.locationId = location_id;
	}
	public boolean isInEurope() {
		return inEurope;
	}
	public void setInEurope(boolean inEurope) {
		this.inEurope = inEurope;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public boolean isCoreCountry() {
		return coreCountry;
	}
	public void setCoreCountry(boolean coreCountry) {
		this.coreCountry = coreCountry;
	}
	public GeoLocation getGeoPosition() {
		return geoPosition;
	}
	public void setGeoPosition(GeoLocation geoPosition) {
		this.geoPosition = geoPosition;
	}
	
	public String toString(){
		return id+"," + name+","+type +","+geoPosition.getLatitude() + ","+geoPosition.getLongitude(); 
	}
}
