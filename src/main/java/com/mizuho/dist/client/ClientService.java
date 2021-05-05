package com.mizuho.dist.client;

/**
 * ClientService Interface to provide the method definition for all security of interest maintanence functinalities
 */
public interface ClientService {

	public void setRepository(ClientRepository repository);
	
	public void addVendorSOI(String vendor, String clientId);
	
	public void addInstrumentSOI(InstrumentSOI instrSOI, String clientId);
	
	public void removeFromVendorSOI(String vendor, String clientId);
	
	public void removeFromInstrumentSOI(InstrumentSOI instrSOI, String clientId);
}
