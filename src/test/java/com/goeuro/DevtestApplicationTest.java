package com.goeuro;

import static org.mockito.BDDMockito.given;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.goeuro.exception.GoEuroError;
import com.goeuro.exception.LogicalException;
import com.goeuro.service.GoEuroTaskService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@PropertySource("/src/test/resrouces/regoeuro-client-test.props")
public class DevtestApplicationTest {

	@Autowired
    private CommandLineRunner runner;
	
	private static final String CITY_NAME = "Berlin";
	
	private static final String INVALID_CITY_NAME = "invalid_city_name";
	
	@MockBean
	private GoEuroTaskService service;
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	@Ignore
	public void testCommandLineWithInvalidCity() throws Exception{
		exception.expect(LogicalException.class);
		exception.expectMessage(GoEuroError.GOEURO_006.getMessage());
		
		given(service.writeSuggestedLocationsToCSV(INVALID_CITY_NAME))
		.willThrow(LogicalException.class);
		
		runner.run(INVALID_CITY_NAME);
	}

	@Test
	@Ignore
	public void testCommandLineWithEmptyParameters() throws Exception{
		exception.expect(LogicalException.class);
		exception.expectMessage(GoEuroError.GOEURO_001.getMessage());
		
		given(service.getSuggestedLocationsByCityName(null))
		.willThrow(LogicalException.class);
		
		runner.run(null);
	}
	
	@Test
	public void testCommandLineWithValidCity() throws Exception{
		runner.run(CITY_NAME);
	}
}
