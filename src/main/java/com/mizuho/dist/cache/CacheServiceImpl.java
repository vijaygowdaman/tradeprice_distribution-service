package com.mizuho.dist.cache;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mizuho.dist.vo.Instruments.Instrument;

@Component
public class CacheServiceImpl implements CacheService {
	
	
	private Cacheable cacheable;
	private CacheManager cacheManager;
	
	private final Logger log = LoggerFactory.getLogger(CacheServiceImpl.class);

	public CacheServiceImpl(CacheManager cacheManager) {
		this.cacheManager = cacheManager; 
	}

	public void addObjectInCache(List<Instrument> instrumentsList) {
		if ((instrumentsList != null) && (instrumentsList.size() > 0)) {
			//Create list of cacheable object
			for(Instrument inst : instrumentsList) {
				cacheable = new CachedObject(inst);
				//Loading the instruments in cache
				cacheManager.putCache(cacheable);
			}
		}
	}
}
