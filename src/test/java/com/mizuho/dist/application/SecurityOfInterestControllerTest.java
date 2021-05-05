package com.mizuho.dist.application;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = Application.class)
//@ContextConfiguration
public class SecurityOfInterestControllerTest {
	
	@Test
	public void testRestEndPoint() {
		
//		TestRestTemplate template = new TestRestTemplate();
//		HttpHeaders header = new HttpHeaders();
//		HttpEntity entity = new HttpEntity(header);
//		ResponseEntity<String> response = template.getForEntity("http://localhost:8080/removeFromInstrumentSOI?identifier=SEDOL&instrumentId=123456B&clientId=Client3" + "/1", String.class);
		//assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

}
