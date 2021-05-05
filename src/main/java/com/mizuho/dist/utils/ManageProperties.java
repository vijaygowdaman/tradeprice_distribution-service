package com.mizuho.dist.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ManageProperties {

	Properties prop = new Properties();
	InputStream input = null;
	OutputStream output = null;
	
	public void setProperties(String name, String value) {
		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream("distribution.properties");

			// set the properties value
			prop.setProperty("threadsleeptime", "5000");
			prop.setProperty("hoursToLive", "3000");

			// save properties to project root folder
			prop.store(output, null);
			getProperty("threadsleeptime");

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	  }
	
	public String getProperty(String key){
		
		Properties prop = new Properties();
		InputStream input = null;
		String value = "";

		try {

			input = new FileInputStream("distribution.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			value = prop.getProperty(key);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	  }
}
