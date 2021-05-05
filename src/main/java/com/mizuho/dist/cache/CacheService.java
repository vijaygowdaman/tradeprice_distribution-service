package com.mizuho.dist.cache;

import java.util.List;

import com.mizuho.dist.vo.Instruments.Instrument;

public interface CacheService {

	void addObjectInCache(List<Instrument> instrumentsList);

}
