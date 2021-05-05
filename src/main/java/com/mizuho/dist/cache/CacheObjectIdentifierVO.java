package com.mizuho.dist.cache;

public class CacheObjectIdentifierVO {

	private String instrumentType;
	private String instrumentId;
	private String vendor;

	public CacheObjectIdentifierVO(String instType, String instId, String vendor) {
		
		this.instrumentType = instType;
		this.instrumentId = instId;
		this.vendor = vendor;
	}
	
	public CacheObjectIdentifierVO(String instType, String instId) {
		
		this.instrumentType = instType;
		this.instrumentId = instId;
	}

	public String getInstrumentType() {
		return instrumentType;
	}

	public void setInstrumentType(String instrmentType) {
		this.instrumentType = instrmentType;
	}

	public String getInstrumentId() {
		return instrumentId;
	}

	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean retrunValue = false;
		if ((obj != null) && (obj instanceof CacheObjectIdentifierVO)) {
			CacheObjectIdentifierVO vo = (CacheObjectIdentifierVO)obj;
			if(this.getInstrumentType().equals(vo.getInstrumentType()) &&
			   this.getInstrumentId().equals(vo.getInstrumentId()) &&
			   this.getVendor().equals(vo.getVendor())) {
				retrunValue = true;
			}
		} else {
			retrunValue = false;
		}
		return retrunValue;
	}
	
	@Override
	public int hashCode() {
		final int randomNo = 23;
		int hashcode = 1;
		hashcode = this.getInstrumentId().hashCode()*randomNo;
		hashcode = this.getInstrumentType().hashCode() + hashcode*randomNo;
		hashcode = this.vendor.hashCode() + hashcode*randomNo;
		return hashcode;
	}

}
