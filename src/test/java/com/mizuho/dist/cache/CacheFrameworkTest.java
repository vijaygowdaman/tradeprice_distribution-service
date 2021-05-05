package com.mizuho.dist.cache;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.mizuho.dist.cache.CacheManager;
import com.mizuho.dist.cache.CacheService;
import com.mizuho.dist.cache.CacheServiceImpl;
import com.mizuho.dist.exception.MessageException;
import com.mizuho.dist.translator.Translator;
import com.mizuho.dist.translator.TranslatorImpl;
import com.mizuho.dist.vo.Instruments.Instrument;

public class CacheFrameworkTest {
	
	CacheService cacheService;
	CacheManager cacheMgr;
	Translator translator;
	File reutersFile;
	File bbgFile;
	File idcFile;

	@Before
	public void setUp() throws Exception {
		cacheMgr = CacheManager.INSTANCE;
		cacheService = new CacheServiceImpl(cacheMgr);
		translator = new TranslatorImpl();
		reutersFile = new File(getClass().getResource("/inputfiles/reuters_instrument.xml").getFile());
		bbgFile = new File(getClass().getResource("/inputfiles/bbg_instrument.xml").getFile());
		idcFile = new File(getClass().getResource("/inputfiles/idc_instrument.xml").getFile());
	}

	@After
	public void tearDown() throws Exception {
		
		cacheMgr = null;
		cacheService = null;
		translator = null;
	}

	
	
	@Test
	public void DataNotAvailableTest() throws MessageException, JAXBException {
		Instrument instr = cacheMgr.getInstrument("SEDOL",  "1234567", "XXX");
		assertEquals(null, instr);
	}
	
	//@Tested the cache timeout functionality by setting the lower timeout value in the property file
	public void CacheTimeoutTest() throws MessageException, SAXException, IOException, JAXBException, InterruptedException {
		List<Instrument> instrumentsList = translator.unmarshallMessage(reutersFile);
		cacheService.addObjectInCache(instrumentsList);
		List<Instrument> instList = cacheMgr.getInstrument();
		assertEquals(3, cacheMgr.getInstrument().size());
		Thread.sleep(6000);
		instList = cacheMgr.getInstrument();
		assertEquals(0, cacheMgr.getInstrument().size());
		
	}
	
	@Test
	public void GetVendorSpecificInstrumentTest() throws MessageException, JAXBException, FileNotFoundException {
		
		List<Instrument> instrumentsList = translator.unmarshallMessage(reutersFile);
		cacheService.addObjectInCache(instrumentsList);
		instrumentsList = translator.unmarshallMessage(bbgFile);
		cacheService.addObjectInCache(instrumentsList);
		instrumentsList = translator.unmarshallMessage(idcFile);
		cacheService.addObjectInCache(instrumentsList);
		List<Instrument> instList = cacheMgr.getInstrument("Reuters");
		assertEquals(3, instList.size());
		instList = cacheMgr.getInstrument("BBG");
		assertEquals(2, instList.size());
		instList = cacheMgr.getInstrument("IDC");
		assertEquals(2, instList.size());
	}
	
	@Test
	public void GetSpecificInstrumentFromAllVendorsTest() throws MessageException, JAXBException, FileNotFoundException {
		
		List<Instrument> instrumentsList = translator.unmarshallMessage(reutersFile);
		cacheService.addObjectInCache(instrumentsList);
		instrumentsList = translator.unmarshallMessage(bbgFile);
		cacheService.addObjectInCache(instrumentsList);
		instrumentsList = translator.unmarshallMessage(idcFile);
		cacheService.addObjectInCache(instrumentsList);
		List<Instrument> instList = cacheMgr.getInstrument("SEDOL",  "1234567");
		assertEquals(2, instList.size());
	}
	
	@Test
	public void GetSpecificInstrumentFromSpecificVendorsTest() throws MessageException, JAXBException, FileNotFoundException {
		
		List<Instrument> instrumentsList = translator.unmarshallMessage(reutersFile);
		cacheService.addObjectInCache(instrumentsList);
		instrumentsList = translator.unmarshallMessage(bbgFile);
		cacheService.addObjectInCache(instrumentsList);
		instrumentsList = translator.unmarshallMessage(idcFile);
		cacheService.addObjectInCache(instrumentsList);
		List<Instrument> instList = cacheMgr.getInstrument("SEDOL",  "1234567");
		Instrument instr = cacheMgr.getInstrument("SEDOL",  "1234567", "IDC");
		assertEquals("ABC Securities", instr.getInstName());
	}

}
