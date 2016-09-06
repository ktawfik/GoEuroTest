package com.goeuro.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.goeuro.client.GoEuroApiClient;
import com.goeuro.exception.GoEuroClientBaseException;
import com.goeuro.exception.GoEuroError;
import com.goeuro.exception.LogicalException;
import com.goeuro.model.Location;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@PropertySource("classpath:goeuro-client-test.props")
public class GoEuroTaskServicelTest {

	@MockBean
	private GoEuroApiClient client;
	
	@Autowired
	private GoEuroTaskService service;
	
	private static final String CITY_NAME = "Berlin";
	
	private static final String INVALID_CITY_NAME = "invalid_city_name";
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testFindSuggestedLocationByValidCityName() throws GoEuroClientBaseException{
		List<Location> result = new ArrayList<Location>();
		Location l = new Location();
		l.setId(1l);
		l.setName("Berlino");
		l.setType("City");
		l.setLocationId(1l);
		result.add(l);
		given(client.findSuggestedLocationsByCityName(CITY_NAME))
				.willReturn(result);
		
		List<Location> ll = service.getSuggestedLocationsByCityName(CITY_NAME);
		assertTrue(ll.size() == result.size());
		assertTrue(ll.get(0).getId() == result.get(0).getId());
		assertTrue(ll.get(0).getName() == result.get(0).getName());
		
	}
	
	@Test
	public void testFindSuggestedLocationByInvalidCityName() throws GoEuroClientBaseException {
		exception.expect(LogicalException.class);
		exception.expectMessage(GoEuroError.GOEURO_006.getMessage());
		
		given(client.findSuggestedLocationsByCityName(null))
		.willThrow(new LogicalException(GoEuroError.GOEURO_006));
		
		service.getSuggestedLocationsByCityName(null);
	}
	
	

}
