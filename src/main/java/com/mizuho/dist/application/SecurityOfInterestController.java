package com.mizuho.dist.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mizuho.dist.client.ClientRepositoryImpl;
import com.mizuho.dist.client.ClientSecurityOfInterest;
import com.mizuho.dist.client.ClientService;
import com.mizuho.dist.client.ClientServiceImpl;
import com.mizuho.dist.client.InstrumentSOI;

/**
 * This is the Rest service controller used to call the API to register the clients security of interest 
 * details. Client can register their interest to receive all the messages from the particular vendor or 
 * receive the particular traded instruments from all the vendors.  This class also provide API's to 
 * unregister the security of interest details.
 *
 */
@RestController
public class SecurityOfInterestController {
	
	private final Logger log = LoggerFactory.getLogger(SecurityOfInterestController.class);
	
	//@Autowired
	//private ClientServiceImpl clientService;
	ClientService clientService = new ClientServiceImpl(new ClientRepositoryImpl());
	
	public ClientService getClientService() {
		return clientService;
	}

	
	public void setClientService(ClientService service) {
		this.clientService = service;
	}

	/**
	 * Clients calls this API by passing the vendor name and their unique id details. This API is for client 
	 * registering their details to receive all the messages from the particular vendor
	 * @param vendor
	 * @param clientId
	 * @return
	 */
	@RequestMapping("/addVendorSOI")
    public String addVendorSOI(@RequestParam(value="vendor") String vendor, 
    		              @RequestParam(value="clientId") String clientId) {
        //return "Vendor = "+vendor+", Client = "+clientId;
		
		//if(service != null)
		clientService.addVendorSOI(vendor, clientId);
		log.info(vendor +"Size = "+ClientSecurityOfInterest.vendorSOI.get(vendor).size());
		return String.valueOf(ClientSecurityOfInterest.vendorSOI.size());
    }
	
	/**
	 * Clients calls this API by passing the instrument identifier, identifier value and their unique 
	 * id details. This API is for client registering their details to receive all the messages for this
	 * specific traded instruments from all the vendors.
	 * @param identifier
	 * @param instrumentId
	 * @param clientId
	 * @return
	 */
	@RequestMapping("/addInstrumentSOI")
    public String addInstrumentSOI(@RequestParam(value="identifier") String identifier, 
    		              @RequestParam(value="instrumentId") String instrumentId, 
    		              @RequestParam(value="clientId") String clientId) {
        InstrumentSOI instrSOI = new InstrumentSOI(identifier, instrumentId);
        InstrumentSOI instrSOI2 = new InstrumentSOI(identifier, instrumentId);
        log.info("Equals checking = "+instrSOI.equals(instrSOI2));
		clientService.addInstrumentSOI(instrSOI, clientId);
		
		return String.valueOf(ClientSecurityOfInterest.instrumentSOI.size());
    }
	
	/**
	 * Clients calls this API by passing the vendor name and their unique id details. This API is for client 
	 * unregistering their details to receive all the messages from the particular vendor
	 * @param vendor
	 * @param clientId
	 * @return
	 */
	@RequestMapping("/removeFromVendorSOI")
    public String removeFromVendorSOI(@RequestParam(value="vendor") String vendor, 
    		              @RequestParam(value="clientId") String clientId) {
		clientService.removeFromVendorSOI(vendor, clientId);
		log.info(vendor +"Size = "+ClientSecurityOfInterest.vendorSOI.get(vendor).size());
		return String.valueOf(ClientSecurityOfInterest.vendorSOI.size());
    }
	
	/**
	 * Clients calls this API by passing the instrument identifier, identifier value and their unique 
	 * id details. This API is for client unregistering their details to receive all the messages for this
	 * specific traded instruments from all the vendors.
	 * @param identifier
	 * @param instrumentId
	 * @param clientId
	 * @return
	 */
	@RequestMapping("/removeFromInstrumentSOI")
    public String removeFromInstrumentSOI(@RequestParam(value="identifier") String identifier, 
    		              @RequestParam(value="instrumentId") String instrumentId, 
    		              @RequestParam(value="clientId") String clientId) {
        InstrumentSOI instrSOI = new InstrumentSOI(identifier, instrumentId);
		clientService.removeFromInstrumentSOI(instrSOI, clientId);
		return String.valueOf(ClientSecurityOfInterest.instrumentSOI.size());
    }

}
