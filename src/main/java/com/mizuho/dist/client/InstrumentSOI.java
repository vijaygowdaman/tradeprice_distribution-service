package com.mizuho.dist.client;

/**
 * 
 * Value Object used to store the Instrument details which is used as the Key to the instrument specific SOI map.
 *
 */
public class InstrumentSOI {
	
	private String instrumentId; 
	
	private String identifier;

	public InstrumentSOI(String identifier, String instrumentId) {
		this.instrumentId = instrumentId;
		this.identifier = identifier;
	}

	public String getInstrumentId() {
		return instrumentId;
	}

	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identifier == null) ? 0 : identifier.length());
		result = prime * result + ((instrumentId == null) ? 0 : instrumentId.length());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean retrunValue = false;
		if ((obj != null) && (obj instanceof InstrumentSOI)) {
			InstrumentSOI vo = (InstrumentSOI)obj;
			if(this.getIdentifier().equals(vo.getIdentifier()) &&
			   this.getInstrumentId().equals(vo.getInstrumentId())) {
				retrunValue = true;
			}
		} else {
			retrunValue = false;
		}
		return retrunValue;
	}
}
