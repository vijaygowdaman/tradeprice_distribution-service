package com.mizuho.dist.publish;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBException;

import com.mizuho.dist.client.ClientSecurityOfInterest;
import com.mizuho.dist.client.InstrumentSOI;
import com.mizuho.dist.translator.Translator;
import com.mizuho.dist.translator.TranslatorImpl;
import com.mizuho.dist.vo.Instruments.Instrument;

/**
 * Concrete implementation class for publishing the messages to the downstream clients
 * This class publishes the messages to vendor specific clients and instrument specific clients
 */
public class PublishServiceImpl implements PublishService {

	/**
	 * Top level end point method used to publish the messages to downstream clients
	 * @param instrumentsList
	 */
	public void publishMessage(List<Instrument> instrumentsList) throws FileNotFoundException, JAXBException {
		Translator transformer = new TranslatorImpl();
		Set<String> publishedClients = new HashSet<>();
		/*Publish the message to all the vendor specific clients*/
		publishVendorSpecificClients(instrumentsList, publishedClients, transformer);
		
		/*Publish the message to all the instrument specific clients */
		publishInstrumentSpecificClients(instrumentsList, publishedClients, transformer);
	}
	
	/**
	 * Publishing messages to vendor specific clients
	 * @param instrumentsList
	 * @param publishedClients
	 * @param transformer
	 * @throws FileNotFoundException
	 * @throws JAXBException
	 */
	private void publishVendorSpecificClients(List<Instrument> instrumentsList, Set<String> publishedClients, 
											  Translator transformer) throws FileNotFoundException, JAXBException {
		if ((instrumentsList != null) && (instrumentsList.size() > 0)) {
			//Retrieve the vendor detail
			String vendor = instrumentsList.get(0).getVendor();
			Set<String> clientSet = ClientSecurityOfInterest.vendorSOI.get(vendor);
			for(Instrument instrument : instrumentsList) {
				if(clientSet != null) {
					for(String client : clientSet) {
						publishedClients.add(client);
						/*
						 * Publish message to the client. This code will be replaced by the messaging JMS 
						 * out bound adapter code
						 */
						File file = new File("/outputfiles/"+instrument.getInstName()+instrument.getInstId()+".xml");
						transformer.marshallMessage(instrument, file);	
						//Publish the instrument to the respective clients
					}
				}
			}
		}
	}
	
	/**
	 * Publishing messages to instrument specific clients
	 * @param instrumentsList
	 * @param publishedClients
	 * @param transformer
	 * @throws FileNotFoundException
	 * @throws JAXBException
	 */
	private void publishInstrumentSpecificClients(List<Instrument> instrumentsList, Set<String> publishedClients, 
			                                      Translator transformer) throws FileNotFoundException, JAXBException {
		if ((instrumentsList != null) && (instrumentsList.size() > 0)) {
			//Retrieve the vendor detail
			String vendor = instrumentsList.get(0).getVendor();
			for(Instrument instrument : instrumentsList) {
				InstrumentSOI soi = new InstrumentSOI(instrument.getInstType(), instrument.getInstId());
				Set<String> clientSet = ClientSecurityOfInterest.instrumentSOI.get(soi);
				if(clientSet != null) {
					for(String client : clientSet) {
						/*To avoid sending the duplicates exclude the client to which message has been already published through
						vendor specific logic*/
						if(!(publishedClients.contains(client))) {
							publishedClients.add(client);
							/*
							* Publish message to the client. This code will be replaced by the messaging JMS 
							* out bound adapter code
							*/
							File file = new File("/outputfiles/"+instrument.getInstName()+instrument.getInstId()+".xml");
							transformer.marshallMessage(instrument, file);	
							//Publish the instrument to the respective clients.
						}										
					}
				}
			}
		}
	}
}
