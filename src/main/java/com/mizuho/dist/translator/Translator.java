package com.mizuho.dist.translator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.mizuho.dist.vo.Instruments.Instrument;

/**
 * Interface for the transformer layer to unmarshall the incoming trade message before loading in to the local 
 * data store. This interface also provides the method definition to marshall the instrument objects before 
 * publishing the messages to the downstream clients
 * 
 */
public interface Translator {

	List<Instrument> unmarshallMessage(File xml) throws JAXBException, FileNotFoundException;

	public File marshallMessage(Instrument instr, File file) throws JAXBException, FileNotFoundException;
	
}
