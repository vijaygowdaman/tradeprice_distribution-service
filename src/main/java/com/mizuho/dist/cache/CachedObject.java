package com.mizuho.dist.cache;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mizuho.dist.utils.ManageProperties;
import com.mizuho.dist.vo.Instruments.Instrument;

public class CachedObject implements Cacheable {

	private CacheObjectIdentifierVO uniqueKey;
	private Instrument instrument;
	private Date dateofExpiration;
	private int hoursToLive = 0; //represnts 30 days expiration period
	private final ManageProperties prop = new ManageProperties();
	
	private final Logger log = LoggerFactory.getLogger(CachedObject.class);

	public CachedObject(Instrument inst) {
		this.instrument = inst;
		this.uniqueKey = buildUniqueKey(inst);
		hoursToLive = Integer.parseInt(prop.getProperty("hoursToLive"));
		if (hoursToLive != 0) {
	        dateofExpiration = new Date();
	        java.util.Calendar cal = java.util.Calendar.getInstance();
	        cal.setTime(dateofExpiration);
	        cal.add(cal.HOUR, hoursToLive);
	        dateofExpiration = cal.getTime();
	      }
	}

	public Instrument getInstrument() {
		return instrument;
	}

	public boolean isExpired() {
		boolean isExpired = false;
		if (dateofExpiration != null) {
          // date of expiration is compared.
          if (dateofExpiration.before(new Date())) {
        	  isExpired = true;
          }
        }
		log.info("Instrument expired");
		return isExpired;
	}

	public CacheObjectIdentifierVO getUniqueKey() {
		// TODO Auto-generated method stub
		return this.uniqueKey;
	}
	
	private CacheObjectIdentifierVO buildUniqueKey(Instrument inst) {
		uniqueKey = new CacheObjectIdentifierVO(inst.getInstType(), inst.getInstId(), inst.getVendor());
		return uniqueKey;
	}

}
