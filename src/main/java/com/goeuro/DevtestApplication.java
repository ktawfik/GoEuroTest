package com.goeuro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringUtils;

import com.goeuro.exception.GoEuroClientBaseException;
import com.goeuro.exception.GoEuroError;
import com.goeuro.exception.LogicalException;
import com.goeuro.service.GoEuroTaskService;

/**
 * The main application entry point
 * @author ktawfik
 *
 */
@SpringBootApplication
@PropertySource("goeuro-client.props")
public class DevtestApplication implements CommandLineRunner{

	@Autowired
	GoEuroTaskService taskService;
	
	public static void main(String[] args) {
		SpringApplication.run(DevtestApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws GoEuroClientBaseException {
		if(arg0 == null || arg0.length == 0 || StringUtils.isEmpty(arg0[0])){
			throw new LogicalException(GoEuroError.GOEURO_001);
		}
		taskService.writeSuggestedLocationsToCSV(arg0[0]);
	}

}
