package com.mizuho.dist.publish;

import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.mizuho.dist.vo.Instruments.Instrument;

/**
 * Interface provide the method definition details for publishing the messages to the downstream clients
 */
public interface PublishService {

	void publishMessage(List<Instrument> instrumentsList)  throws FileNotFoundException, JAXBException;

}
