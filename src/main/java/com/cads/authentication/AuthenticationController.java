package com.cads.authentication;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cads.db.models.User;

@RestController
public class AuthenticationController {
	    @Autowired
	    @Qualifier("authenticationService")
	    AuthenticationService authenticationService;
	   
	    @RequestMapping(method=RequestMethod.POST,value="/login")
	    public AuthenticationDetails userLogin(LoginCredentials loginCredentials) {
	        AuthenticationDetails returnValue = new AuthenticationDetails();
	        User userProfile = null;
	        
	        // Perform User Authentication
	        try {
	            userProfile = authenticationService.authenticate(
	                    loginCredentials.getUsername(),
	                    loginCredentials.getPassword());
	        } catch (AuthenticationException ex) {	            
	            return returnValue;
	        }
	        
	        // Reset Access Token
	        authenticationService.resetSecurityCridentials(loginCredentials.getPassword(), 
	                userProfile);
	        // Issue a new Access token
	      //  String secureUserToken = authenticationService.issueSecureToken(userProfile);
	        
	      //  returnValue.setToken(secureUserToken);
	        returnValue.setId(userProfile.getId());
	        return returnValue;
	    }
	}


