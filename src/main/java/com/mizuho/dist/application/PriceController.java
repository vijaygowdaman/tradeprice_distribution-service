package com.mizuho.dist.application;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mizuho.dist.cache.CacheManager;
import com.mizuho.dist.vo.Instruments.Instrument;

/**
 * This is the Rest service controller used to call the API to get the particular instrument price from all the vendors, all the instrument price
 * from the particular vendor and particular instrument from the specific vendor
 *
 */
@RestController
public class PriceController {
	
	private final Logger log = LoggerFactory.getLogger(PriceController.class);
	
	CacheManager cacheMgr = CacheManager.INSTANCE;
	
	/**
	 * Clients calls this API by passing the vendor name and their unique id details. This API is for getting
	 * all the instruments received from particular vendor
	 * @param vendor
	 * @return
	 */
	@RequestMapping("/vendor/{vendorId}")
    public List<Instrument> getPricesByVendorId(@RequestParam(value="vendorId") String vendorId) throws ParseException {
    	log.info("calling getInstrument {}", vendorId);
        return cacheMgr.getInstrument(vendorId);
    }

	/**
	 * Clients calls this API by passing the vendor name and their unique id details. This API is for getting the
	 * spcific instruments received from all the vendor
	 * @param vendor
	 * @return
	 */
    @RequestMapping("/instrument/{instrumentId}")
    public List<Instrument> getPricesByInstrumentId(@RequestParam(value="instrumentType") String instrumentType, 
    		              @RequestParam(value="instrumentId") String instrumentId) throws ParseException {
    	log.info("getInstrument by InstrumentId{}", instrumentId);
        return cacheMgr.getInstrument(instrumentType, instrumentId);
    }
	
	/**
	 * Clients calls this API by passing the vendor name and their unique id details. This API is for getting the
	 * spcific instruments received from the particular vendor
	 * @param vendor
	 * @return
	 */
    @RequestMapping("vendor/instrument/{instrumentId}")
    public Instrument getPricesByVendorInstrumentId(@RequestParam(value="instrumentType") String instrumentType, 
    		              @RequestParam(value="instrumentId") String instrumentId,
						  @RequestParam(value="vendorId") String vendorId) throws ParseException {
    	log.info("getInstrument by VendorId{}", vendorId );
        return cacheMgr.getInstrument(instrumentType, instrumentId, vendorId);
    }
}
