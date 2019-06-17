package com.cads.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class HTTPRequestSender {
	
	public void ResponseEntity(String uri, Class reqClass) throws RestClientException{
		RestTemplate restTemplate = new RestTemplate();		
			ResponseEntity<Object> response
			  = restTemplate.getForEntity(uri, reqClass);
			
	}
}
