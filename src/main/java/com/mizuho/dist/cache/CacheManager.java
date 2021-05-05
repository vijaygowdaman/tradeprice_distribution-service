package com.mizuho.dist.cache;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mizuho.dist.utils.ManageProperties;
import com.mizuho.dist.vo.Instruments.Instrument;

public enum CacheManager {
	
	INSTANCE;
	
	private final Logger log = LoggerFactory.getLogger(CacheManager.class);
	
	/* This is the HashMap that contains all objects in the cache. */
	  private static ConcurrentMap<CacheObjectIdentifierVO, Cacheable> cacheHashMap = new ConcurrentHashMap<CacheObjectIdentifierVO, Cacheable>();
	    static {
	        try {
	        	final ManageProperties prop = new ManageProperties();
	            /* This background thread will be responsible for removing the expired items. */
	            Thread threadCleanerUpper = new Thread(
	            new Runnable() {
	              /*  The default time the thread should sleep between scans.
	                  The sleep method takes in a millisecond value so 3600000 = 1 hour*/
	              //final int milliSecondSleepTime = 5000;
	              int milliSecondSleepTime = Integer.parseInt(prop.getProperty("threadsleeptime"));
	              public void run() {
	                try {
	                  /* Sets up an infinite loop. The thread will continue looping forever. */
	                  while (true) {
	                    /* Get the set of all keys that are in cache.  These are the unique identifiers */
	                    Set<CacheObjectIdentifierVO> keySet = cacheHashMap.keySet();
	                    Iterator<CacheObjectIdentifierVO> keys = keySet.iterator();
	                    while(keys.hasNext()) {
	                      /* Get the individual key.  We need to hold on to this key in case it needs to be removed */
	                    	CacheObjectIdentifierVO key = keys.next(); 
	                      /* Get the cacheable object associated with the key inside the cache */
	                      Cacheable value = (Cacheable)cacheHashMap.get(key);
	                      if (value.isExpired()) {
	                        /* Instrument is expired, Remove it from the cache */
	                        cacheHashMap.remove(key);
	                      }
	                    }
	                    
	                    /* Puts the thread to sleep.  Don't need to check it continuously */
	                    Thread.sleep(this.milliSecondSleepTime);
	                  }
	                }
	                catch (Exception e) {
	                    e.printStackTrace();
	                }
	                return;
	              }
	            });
	            
	            // Sets the thread's priority to the minimum value.
	            threadCleanerUpper.setPriority(Thread.MIN_PRIORITY);
	            // Starts the thread.
	            threadCleanerUpper.start();
	        }
	        catch(Exception e) {
	              e.printStackTrace();
	        }
	    } 
	    
	 
	  public void putCache(Cacheable instrument) {
	    cacheHashMap.put(instrument.getUniqueKey(), instrument);
	  }
	  
	  /**
	   * getInstrument(vendor) – Return all the prices for requested vendor
	   * @param vendor
	   * @return
	   */
	  public List<Instrument> getInstrument(String vendor) {
		  List<Instrument> returnList = new ArrayList<Instrument>();
		  for(Map.Entry<CacheObjectIdentifierVO, Cacheable> entry: cacheHashMap.entrySet()) {
			  CacheObjectIdentifierVO key = entry.getKey();
			  	if(getCacheable(key) != null) {
			  		if(key.getVendor().equals(vendor)) {
			  			Cacheable cachedObject = cacheHashMap.get(key);
			  			if(cachedObject != null)
			  			returnList.add(cachedObject.getInstrument());
			  		}
			  	}
			}
		  return returnList;
	  }
	  
	  /**
	   * getInstrument(inst_type, inst_id) – Return this instrument price for all the vendors
	   * Example input types
	   		SEDOL, 1234567
	   		RIC, VOD.LN
	   * @param vendor
	   * @return
	   */
	  public List<Instrument> getInstrument(String inst_type, String inst_id) {
		  List<Instrument> returnList = new ArrayList<Instrument>();
		  for(Map.Entry<CacheObjectIdentifierVO, Cacheable> entry: cacheHashMap.entrySet()) {
			  CacheObjectIdentifierVO key = entry.getKey();
			  	if(getCacheable(key) != null) {
			  		if ((key.getInstrumentType().equals(inst_type)) && (key.getInstrumentId().equals(inst_id))) {
			  			Cacheable cachedObject = cacheHashMap.get(key);
			  			if(cachedObject != null)
			  			returnList.add(cachedObject.getInstrument());
			  		}
			  	}
			}
		  return returnList;
	  }
	  
	  /**
	   * getInstrument(inst_type, inst_id, vendor) – Return this specific instrument for the specific vendor
	   * Example input types
	   		SEDOL, 1234567, BBG
	   * @param vendor
	   * @return
	   */
	  public Instrument getInstrument(String inst_type, String inst_id, String vendor) {
		  Instrument instr = null;
		  if((inst_type != null) && (inst_id != null) && (vendor != null)) {
			  CacheObjectIdentifierVO inputKey = new CacheObjectIdentifierVO(inst_type, inst_id, vendor);
			  Cacheable cachedObject = getCacheable(inputKey);
			  if(cachedObject != null) {
				  instr = cachedObject.getInstrument();
			  }
		  }
		return instr;  
	  }
	  
	  /**
	   * getAllInstrument() – Return all instruments for all the vendors
	   * @param vendor
	   * @return
	   */
	  public List<Instrument> getInstrument() {
		  List<Instrument> returnList = new ArrayList<Instrument>();
		  for(Map.Entry<CacheObjectIdentifierVO, Cacheable> entry: cacheHashMap.entrySet()) {
			  CacheObjectIdentifierVO key = entry.getKey();
			  	if(getCacheable(key) != null) {
		  			Cacheable cachedObject = getCacheable(key);
		  			if(cachedObject != null)
		  			returnList.add(cachedObject.getInstrument());
			  	}
			}
		  return returnList;
	  }
	  
	  
	  private Cacheable getCacheable(CacheObjectIdentifierVO uniqueKey) {
		  Cacheable object = (Cacheable)cacheHashMap.get(uniqueKey);
	      if (object == null)
	        return null;
	      if (object.isExpired()) {
	        cacheHashMap.remove(uniqueKey);
	        return null;
	      }
	      else {
	        return object;
	      }
	  }
}
