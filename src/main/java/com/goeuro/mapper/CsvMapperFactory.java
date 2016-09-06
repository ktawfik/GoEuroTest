package com.goeuro.mapper;

/**
 * 
 * @author ktawfik
 * this class is a factory class that handle the creation of a Mapper based on the passed param to get instance.
 * I've added it to make code more generic to be used with the CsvUtil so that CsvUtil doesn't need to know about which
 * Mapper it will call, while the Factory who will be responsible for this task.
 *
 */
public class CsvMapperFactory {

	/**
	 * for simplecity i'll just add a return of the 1 and only 1 existing mapper in the system which is LocationMapper.
	 * but this design make extendability of the code mush more easier, and already do a SOC from the CsvUtil and the class
	 * we need to read
	 * @return CsvMapper
	 */
	public static ModelCsvMapper getInstance(){
		return new LocationMapper(); 
	}
}
