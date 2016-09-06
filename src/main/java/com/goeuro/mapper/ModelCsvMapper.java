package com.goeuro.mapper;

import java.util.Map;

/**
 * this interface is a base for any model or entity need to handle csv nvp mapping, and only contains 1 method
 * that handle mapping from map (Name-Value-Pair) to the target object, and the logic will be thrown on the implementer
 * of the interface
 * 
 * @author ktawfik
 *
 */
public interface ModelCsvMapper {

	public Object map(Map<String, String> nvp); 
}
