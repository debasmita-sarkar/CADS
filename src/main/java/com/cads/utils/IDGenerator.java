package com.cads.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class IDGenerator {
	
	public int getNextUniqueIndex(AtomicInteger counter) {
	    return counter.getAndIncrement();
	}
	
	/**
	 * Generates unique identifier for resource
	 * @param counter 
	 * @return
	 */
	public String generateID(String prefix, AtomicInteger counter) {		
		int ticketId = getNextUniqueIndex(counter);
		if(prefix!=null && !prefix.isEmpty()) {
			return prefix+ticketId;
		}
		return new Integer(ticketId).toString();
	}

}
