package com.goeuro.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;
import com.goeuro.dto.LocationCsvDto;
import com.goeuro.exception.GoEuroClientBaseException;
import com.goeuro.exception.GoEuroError;
import com.goeuro.exception.LogicalException;
import com.goeuro.exception.SystemException;
import com.goeuro.mapper.CsvMapperFactory;
import com.goeuro.mapper.ModelCsvMapper;

/**
 * 
 * @author ktawfik 
 * 
 * Util class to deal with CSV files, the implemented functionality is 
 * the write only as this is the only required for the task.
 */
public class CSVUtil {


	private static final String CSV_EXT = ".csv";
	/**
	 * Generic method to write any Object to csv file.
	 * @param fileName, the csv file name to be created
	 * @param l, the list of object to be written to the file
	 * @param columnNames, the required columns headers
	 * @throws GoEuroClientBaseException
	 */
	public static void writeListToCSV(String fileName, List l, List<String> columnNames) throws GoEuroClientBaseException{
		if(StringUtils.isEmpty(fileName)){
			throw new LogicalException(GoEuroError.GOEURO_003);
		}
		if(CollectionUtils.isEmpty(l)){
			throw new LogicalException(GoEuroError.GOEURO_004);
		}
		if(CollectionUtils.isEmpty(columnNames)){
			throw new LogicalException(GoEuroError.GOEURO_005);
		}
		if(!fileName.endsWith(CSV_EXT)){
			fileName += CSV_EXT;
		}
		CsvMapper mapper = new CsvMapper();
		Builder b = CsvSchema.builder();
		for(String col : columnNames){
			b.addColumn(col);
		}
		CsvSchema schema = b.build().withHeader();
		FileOutputStream fs;
		try {
			fs = new FileOutputStream(new File(fileName));
			mapper.writer(schema).writeValue(fs, l);
			fs.flush();
			fs.close();
		} catch (FileNotFoundException e) {
			throw new SystemException(GoEuroError.GOEURO_006);
		} catch (JsonGenerationException e){
			throw new SystemException(GoEuroError.GOEURO_008);
		} catch (JsonMappingException e){
				throw new SystemException(GoEuroError.GOEURO_009);
		} catch (IOException e){
			throw new SystemException(GoEuroError.GOEURO_010);
		}
	}
	/**
	 * 
	 * @param fileName
	 * @param c
	 * @return
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 */
	public static List readCvsFile(String fileName, Class c) throws JsonProcessingException, IOException{
		List objList = new ArrayList();


		File csvFile = new File(fileName);
		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = CsvSchema.emptySchema().withHeader();
		MappingIterator<Map<String,String>> it = mapper.readerFor(Map.class)
		   .with(schema)
		   .readValues(csvFile);
		
		ModelCsvMapper modelMapper = CsvMapperFactory.getInstance();
		while (it.hasNext()) {
		  Map<String,String> rowAsMap = it.next();
		  objList.add(modelMapper.map(rowAsMap));
		}
		return objList;
	}

}