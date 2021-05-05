package com.mizuho.dist.translator;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.mizuho.dist.exception.MessageException;
import com.mizuho.dist.vo.Instruments.Instrument;

public class TranslatorTest {
	
	Translator translator;
	File reutersFile;

	@Before
	public void setUp() throws Exception {
		translator = new TranslatorImpl();
		reutersFile = new File(getClass().getResource("/inputfiles/reuters_instrument.xml").getFile());
	}

	@After
	public void tearDown() throws Exception {
		
		translator = null;
	}

	
	@Test
	public void translatorUnmarshallTest() throws MessageException, SAXException, IOException, JAXBException, InterruptedException {
		List<Instrument> instrumentsList = translator.unmarshallMessage(reutersFile);
		assertEquals(3, instrumentsList.size());
	}

}
