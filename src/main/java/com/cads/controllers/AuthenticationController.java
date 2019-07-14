package com.cads.controllers;

import java.security.spec.InvalidKeySpecException;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cads.authentication.AuthenticationDetails;
import com.cads.authentication.AuthenticationService;
import com.cads.db.models.User;

@RestController
public class AuthenticationController {	
	@Autowired
	@Qualifier("authenticationService")
	AuthenticationService authenticationService;
	
	//@RequestMapping(method=RequestMethod.POST,value="/auth")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public AuthenticationDetails userLogin(@RequestParam String userName, @RequestParam String password) throws AuthenticationException, InvalidKeySpecException {
		AuthenticationDetails returnValue = new AuthenticationDetails();
		User userProfile = null;

		// Perform User Authentication
		try {
			System.out.println("inside auth");
			userProfile = authenticationService.authenticate(
					userName,
					password);
		} catch (AuthenticationException ex) {
			System.out.println("inside auth exception");
			return returnValue;
		}

		// Reset Access Token
		authenticationService.resetSecurityCridentials(password, 
				userProfile);
		// Issue a new Access token
		String secureUserToken = authenticationService.issueSecureToken(userProfile);
		System.out.println("inside auth: secureUserToken:"+secureUserToken+":"+userProfile.getPassword());
		returnValue.setToken(secureUserToken);
		returnValue.setId(userProfile.getId());
		return returnValue;
	}
}


