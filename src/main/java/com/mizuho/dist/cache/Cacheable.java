package com.mizuho.dist.cache;

import com.mizuho.dist.vo.Instruments.Instrument;

public interface Cacheable {
	
	public boolean isExpired();
	
	public CacheObjectIdentifierVO getUniqueKey();

	Instrument getInstrument();

}
