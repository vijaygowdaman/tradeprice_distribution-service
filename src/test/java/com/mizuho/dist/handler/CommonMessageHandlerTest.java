package com.mizuho.dist.handler;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.mizuho.dist.exception.MessageException;

public class CommonMessageHandlerTest {
	
	File reutersFile;
	File bbgFile;
	File idcFile;
	MessageHandlerValidator validator;
	
 
	@Before
	public void setUp() throws Exception {
		validator = new MessageHandlerValidator();
		reutersFile = new File(getClass().getResource("/inputfiles/reuters_instrument.xml").getFile());
		bbgFile = new File(getClass().getResource("/inputfiles/bbg_instrument.xml").getFile());
		idcFile = new File(getClass().getResource("/inputfiles/idc_instrument.xml").getFile());
	}

	@After
	public void tearDown() throws Exception {
		
		validator = null;
	}

	@Test
	public void validatingMessageTest() throws MessageException, JAXBException, SAXException, IOException {
		validator.setVendor("reuters");
		assertEquals(true, validator.validateMessage(reutersFile));
		validator.setVendor("bbg");
		assertEquals(true, validator.validateMessage(bbgFile));
		validator.setVendor("idc");
		assertEquals(true, validator.validateMessage(idcFile));
	}

}
