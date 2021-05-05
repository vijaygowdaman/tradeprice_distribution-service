package com.mizuho.dist.client;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.ui.context.Theme;


/**
 * Concrete repository implementation class. This class provides the concrete functinality to connect to the
 * Persistent store to add or remove the client's security of interest details.
 * 
 */
@Repository
public class ClientRepositoryImpl implements ClientRepository{
	
	private final Logger log = LoggerFactory.getLogger(ClientRepositoryImpl.class);
	
	/**
	 * Adds the client details to the specific vendor list
	 */
	@Override
	public void addVendorSOI(String vendor, String clientId) {
		
		if ((vendor != null) && (clientId !=null)) {
			Set<String> clientSet = ClientSecurityOfInterest.vendorSOI.get(vendor);
			if(clientSet != null) {
				clientSet.add(clientId);
			} else {
				clientSet = new ConcurrentSkipListSet<String>();
				clientSet.add(clientId);
				ClientSecurityOfInterest.vendorSOI.put(vendor, clientSet);
			}
		}
	}

	/**
	 * Adds the client details against the specific instrument
	 */
	@Override
	public void addInstrumentSOI(InstrumentSOI instrSOI, String clientId) {
		
		if ((instrSOI != null) && (clientId !=null)) {
			
			Set<String> clientSet = ClientSecurityOfInterest.instrumentSOI.get(instrSOI);
			log.info("instrumentSOI = "+ClientSecurityOfInterest.instrumentSOI);
			if(clientSet != null) {
				clientSet.add(clientId);
			} else {
				clientSet = new ConcurrentSkipListSet<String>();
				clientSet.add(clientId);
				ClientSecurityOfInterest.instrumentSOI.put(instrSOI, clientSet);
			}
			log.info("instrumentSOI after the addition = "+ClientSecurityOfInterest.instrumentSOI);
		}
		
	}

	/**
	 * Removes the client details from the specific vendor list
	 */
	@Override
	public void removeFromVendorSOI(String vendor, String clientId) {
		if ((vendor != null) && (clientId !=null)) {
			Set<String> clientSet = ClientSecurityOfInterest.vendorSOI.get(vendor);
			log.info("clientSet = "+clientSet);
			if(clientSet != null) {
				clientSet.remove(clientId);
			}
			log.info("clientSet after remove operation = "+clientSet);
		}
	}

	/**
	 * Removes the client details against the specific instrument
	 */
	@Override
	public void removeFromInstrumentSOI(InstrumentSOI instrSOI, String clientId) {
		if ((instrSOI != null) && (clientId !=null)) {
			Set<String> clientSet = ClientSecurityOfInterest.instrumentSOI.get(instrSOI);
			log.info("clientSet = "+clientSet);
			if(clientSet != null) {
				clientSet.remove(clientId);
			}
			log.info("clientSet after remove operation = "+clientSet);
		}
		
	}
}
