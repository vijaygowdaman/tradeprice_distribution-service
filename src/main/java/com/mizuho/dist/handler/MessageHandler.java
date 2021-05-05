package com.mizuho.dist.handler;

import java.io.File;
import java.io.IOException;

import org.xml.sax.SAXException;

public interface MessageHandler {

	boolean validateMessage(File xml) throws SAXException, IOException;

}
