package com.cads.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import com.cads.db.models.Ticket;
import com.cads.db.models.Workers;
 
public class SMSSender {
	private static final String API_KEY_VALUE = "O7kTp5/GASc-v68BoBGCOq66fM2Hf5qOsuvGnDEeYs";
	private static final String SEND_MSG_API = "https://api.textlocal.in/send/?";
	
	public String sendSms(String msg,String phoneNumbers) {
		try {
			// Construct data
			String apiKey = GeneralConstant.APIKEY + API_KEY_VALUE;
			//String message = MESSAGE + "Your first message from CADS";
			String message = GeneralConstant.MESSAGE + msg;
			String sender = "&sender=" + GeneralConstant.TXTLCL;
			//String numbers = "&numbers=" + "9886949365,8880583862,9986650974,9591038936,8001787699";
			String numbers = GeneralConstant.NUMBERS_KEY + phoneNumbers;
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL(SEND_MSG_API).openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod(GeneralConstant.POST);
			conn.setRequestProperty(GeneralConstant.CONTENT_LENGTH, Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes(GeneralConstant.UTF_8));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			System.out.println(stringBuffer.toString());
			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
	}

	public String generateMsg(Ticket ticket,String workerName) {
		StringJoiner messageBody = new StringJoiner("\n");
        
		messageBody.add ("Ticket id ="+ ticket.getId());
		messageBody.add ("Description= "+ticket.getDescription());
		messageBody.add ("State = "+ticket.getState());
		messageBody.add ("Ticket assigned to = "+workerName);		
		return messageBody.toString();
		
	}

	public String setRecipients(Ticket ticket, String worker_phone_number,String userPhone) {
		StringJoiner recipientNumbers = new StringJoiner(",");
		
		if(worker_phone_number!=null && !worker_phone_number.isEmpty() ) {
			recipientNumbers.add (worker_phone_number);
		}
		if(userPhone!=null && !userPhone.isEmpty() && validatePhoneNumber(userPhone) ) {
			recipientNumbers.add (userPhone);
		}
		return recipientNumbers.toString();
	}
	
	private static boolean validatePhoneNumber(String phoneNo) {
		//validate phone numbers of format "1234567890"
		if (phoneNo.matches("\\d{10}")) return true;
		//validating phone number with -, . or spaces
		else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
		//validating phone number with extension length from 3 to 5
		else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
		//validating phone number where area code is in braces ()
		else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
		//return false if nothing matches the input
		else return false;
		
	}
}
