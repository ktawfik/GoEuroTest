package com.goeuro.util;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.goeuro.dto.LocationCsvDto;
import com.goeuro.exception.GoEuroClientBaseException;
import com.goeuro.exception.GoEuroError;
import com.goeuro.exception.LogicalException;
import com.goeuro.exception.SystemException;

public class CSVUtilTest {
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	private static final String FILE_NAME = "data.csv";
	
	private static final String CITY_NAME = "Berlin";

	/**
	 * Unit testing for writing a successfull csv file and read it back successfully with no data loss or corruption
	 * @throws GoEuroClientBaseException
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	@Test
	public void testWriteListToCSVSuccessfully() throws GoEuroClientBaseException, JsonProcessingException, IOException {
		LocationCsvDto dto = new LocationCsvDto();
		dto.set_id(1l);
		dto.setLatitude(1.2123438);
		dto.setLongitude(3.9894773);
		dto.setName(CITY_NAME);
		dto.setType("Location");
		List<LocationCsvDto> l = new ArrayList<LocationCsvDto>();
		l.add(dto);
		CSVUtil.writeListToCSV(FILE_NAME, l, LocationCsvDto.getColumnsList());
		// make sure that what I've written I can reverse it and read as object
		List<LocationCsvDto> readData = CSVUtil.readCvsFile(FILE_NAME, LocationCsvDto.class);
		assertTrue(readData.size()==1);
		assertTrue(readData.get(0) != null);
		assertTrue(dto.equals(readData.get(0)));
		assertTrue(Files.exists(Paths.get(FILE_NAME)));
		
		try {
			Files.delete(Paths.get(FILE_NAME));
		} catch (IOException e) {
		}
	}
	
	@Test
	public void testWriteListToCsvWithInvalidColHeaders() throws GoEuroClientBaseException {
		LocationCsvDto dto = new LocationCsvDto();
		dto.set_id(1l);
		dto.setLatitude(1.2123438);
		dto.setLongitude(3.9894773);
		dto.setName(CITY_NAME);
		dto.setType("Location");
		List<LocationCsvDto> l = new ArrayList<LocationCsvDto>();
		l.add(dto);
		List<String> columnNames = Arrays.asList("_id","name","type","latitude");
		exception.expect(SystemException.class);
		exception.expectMessage(GoEuroError.GOEURO_009.getMessage());
		CSVUtil.writeListToCSV(FILE_NAME, l,columnNames);
	}
	
	@Test
	public void testWriteListToCsvWithEmptyOrNullColHeaders() throws GoEuroClientBaseException {
		LocationCsvDto dto = new LocationCsvDto();
		dto.set_id(1l);
		dto.setLatitude(1.2123438);
		dto.setLongitude(3.9894773);
		dto.setName(CITY_NAME);
		dto.setType("Location");
		List<LocationCsvDto> l = new ArrayList<LocationCsvDto>();
		l.add(dto);
		List<String> columnNames = new ArrayList<String>();
		exception.expect(LogicalException.class);
		exception.expectMessage(GoEuroError.GOEURO_005.getMessage());
		CSVUtil.writeListToCSV(FILE_NAME, l,columnNames);
		
		exception.expect(LogicalException.class);
		exception.expectMessage(GoEuroError.GOEURO_005.getMessage());
		CSVUtil.writeListToCSV(FILE_NAME, l,null);
	}
	
	@Test
	public void testWriteListToCSVWithEmptyOrNullLocationList() throws GoEuroClientBaseException {
		exception.expect(LogicalException.class);
		exception.expectMessage(GoEuroError.GOEURO_004.getMessage());
		CSVUtil.writeListToCSV(FILE_NAME, null, LocationCsvDto.getColumnsList());
		
		List<LocationCsvDto> l = new ArrayList<LocationCsvDto>();
		exception.expect(LogicalException.class);
		exception.expectMessage(GoEuroError.GOEURO_004.getMessage());
		CSVUtil.writeListToCSV(FILE_NAME, l, LocationCsvDto.getColumnsList());
	}

}
