package com.mizuho.dist.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * This is the service layer and entry point for the Restful controller to invoke the business 
 * Functionalities to provide the security of interest process for clients to register or unregister their interest.
 *
 */
@Service
@Transactional
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientRepository repository;

	public ClientRepository getRepository() {
		return repository;
	}

	public void setRepository(ClientRepository repository) {
		this.repository = repository;
	}
	
	public ClientServiceImpl(ClientRepository repository) {
		this.repository = repository;
	}
	
	/**
	 * Adds the client details to the specific vendor list
	 */
	public void addVendorSOI(String vendor, String clientId) {
		repository.addVendorSOI(vendor, clientId);
	}
	
	/**
	 * Adds the client details against the specific instrument
	 */
	public void addInstrumentSOI(InstrumentSOI instrSOI, String clientId) {
		repository.addInstrumentSOI(instrSOI, clientId);
	}
	
	/**
	 * Removes the client details from the specific vendor list
	 */
	public void removeFromVendorSOI(String vendor, String clientId) {
		repository.removeFromVendorSOI(vendor, clientId);
	}
	
	/**
	 * Removes the client details against the specific instrument
	 */
	public void removeFromInstrumentSOI(InstrumentSOI instrSOI, String clientId) {
		repository.removeFromInstrumentSOI(instrSOI, clientId);
	}
}
