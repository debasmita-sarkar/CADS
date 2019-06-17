package com.cads.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class Utility {
	public static String getUniqueID(String prefix,AtomicInteger counter) {
		IDGenerator idgenerator = new IDGenerator();
		String ticketId =idgenerator.generateID(prefix,counter);
		return ticketId;
	}

}
