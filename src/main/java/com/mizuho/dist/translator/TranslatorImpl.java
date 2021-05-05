package com.mizuho.dist.translator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mizuho.dist.vo.Instruments;
import com.mizuho.dist.vo.Instruments.Instrument;

/**
 * Concrete Transformer class to unmarshall the incoming trade message before loading in to the local 
 * data store. This class also marshall the instrument objects before publishing the messages to the 
 * downstream clients
 * 
 */
public class TranslatorImpl implements Translator {
	
	private final Logger log = LoggerFactory.getLogger(TranslatorImpl.class);
	
	/**
	 * Unmarshalling the input trade message before storing the message in the local data store.
	 */
	public List<Instrument> unmarshallMessage(File xml) throws JAXBException, FileNotFoundException {
		JAXBContext jc = JAXBContext.newInstance( "com.mizuho.dist.vo" );
        
		// create an Unmarshaller
		Unmarshaller u = jc.createUnmarshaller();
		           
		// unmarshal a instrument instance document into a tree of Java content
		// objects composed of classes from the com.mizuho.dist.vo package.
		Instruments inst = (Instruments)u.unmarshal( new FileInputStream( xml ) ); 
		List<Instrument> instrumentsList = inst.getInstrument();
		
		log.info("Cached Instruments = "+inst);
		
		return instrumentsList;
		
	}
	
	/**
	 * Marshalling the message to xml file before publishing the messages to the downstream consumers.
	 */
	public File marshallMessage(Instrument instr, File file) throws JAXBException, FileNotFoundException {
		JAXBContext jc = JAXBContext.newInstance( Instrument.class );
        
		// create an Unmarshaller
		Marshaller unmarshaller = jc.createMarshaller();
		unmarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);           
		// unmarshal a instrument instance document into a tree of Java content
		// objects composed of classes from the com.mizuho.dist.vo package.
		unmarshaller.marshal(Instrument.class, file);		
		return file;
		
	}

}
