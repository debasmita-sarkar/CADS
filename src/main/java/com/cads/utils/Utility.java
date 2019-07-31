package com.cads.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


public class Utility {
	public static String getUniqueID(String prefix,AtomicInteger counter) {
		IDGenerator idgenerator = new IDGenerator();
		String ticketId =idgenerator.generateID(prefix,counter);
		return ticketId;
	}
	public Map<Object, String> createLookUpMap(Map<String, Map<? super Object, String>> mapColToLookUps, String colName,Map<? super Object, String> map) {
		mapColToLookUps.put(colName, map);
		System.out.println("mapColToLookUps:"+mapColToLookUps);
		return map;
	}
	
	public Map<? super Object, String> convertStringArrayToMap(String[] enumData) {
		Map<? super Object,String> mapOfTicketStates = new HashMap <Object,String>();
		Arrays.asList(enumData).stream().forEach(elem -> mapOfTicketStates.put(elem, elem));
		return mapOfTicketStates;
	}
	
	public String[] generateStringArrayForBooleans(){
		String[] booleanArray = {"true","false"};
		return booleanArray;		
	}
}
