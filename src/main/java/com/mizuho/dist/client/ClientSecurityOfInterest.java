package com.mizuho.dist.client;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.mizuho.dist.cache.Cacheable;

/**
 * 
 * This is a concrete class to store the Client security of interest details. This class contains two static values 
 * to store the vendor specific details and client specific details.
 *
 */
public class ClientSecurityOfInterest {
	
	/*
	 * Static map to store client security of interest(SOI) for receiving all the messages 
	 * from particular vendor
	 * Key - vendor
	 * Value - List of clients
	 */
	public static ConcurrentMap<String, Set<String>> vendorSOI = new ConcurrentHashMap<>(); 
	
	/*
	 * Static map to store security of interest(SOI) for receiving specific messages from particular vendor
	 * Key - Instrument
	 * Value - List of clients
	 */
	public static ConcurrentMap<InstrumentSOI, Set<String>> instrumentSOI = new ConcurrentHashMap<>();
	
	
	
}
