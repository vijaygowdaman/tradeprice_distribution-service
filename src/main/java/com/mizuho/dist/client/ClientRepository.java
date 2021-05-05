package com.mizuho.dist.client;

import org.springframework.data.repository.CrudRepository;

//public interface ClientRepository extends CrudRepository{
/**
 * 
 * Interface for Repository connection and method definition to provide the SOI functionalities to clients.
 *
 */
public interface ClientRepository {

	void addVendorSOI(String vendor, String clientId); 
	
	void addInstrumentSOI(InstrumentSOI instrSOI, String clientId); 
	
	void removeFromVendorSOI(String vendor, String clientId); 
	
	void removeFromInstrumentSOI(InstrumentSOI instrSOI, String clientId); 
}
