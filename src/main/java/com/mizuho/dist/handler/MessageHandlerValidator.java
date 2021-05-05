package com.mizuho.dist.handler;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

@Component
public class MessageHandlerValidator implements MessageHandler{
	
	private String vendor;

    @Autowired
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
    
    public String getVendor() {
        return this.vendor;
    }

	private final Logger log = LoggerFactory.getLogger(MessageHandlerValidator.class);
	
	MessageHandlerValidator() {
	}
	
	MessageHandlerValidator(String vendor) {
		this.vendor = vendor;
	}
	
	public boolean validateMessage(File file) throws SAXException, IOException {
		boolean isMessageValid =  false;
		Source xmlFile = new StreamSource(file);
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		URL reutersSchemaFile = getClass().getResource("/xsd/"+vendor+".xsd");
		Schema schema = schemaFactory.newSchema(reutersSchemaFile);
		Validator validator = schema.newValidator();
		try {
		  validator.validate(xmlFile);
		  isMessageValid =  true;
		 log.info(xmlFile.getSystemId() + " is valid");
		} catch (SAXException e) {
			log.info(xmlFile.getSystemId() + " is NOT valid");
			log.info("Reason: " + e.getLocalizedMessage());
		}
		return isMessageValid;
	}
}
