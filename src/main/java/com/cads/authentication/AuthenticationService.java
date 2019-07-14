package com.cads.authentication;

import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cads.db.models.User;
import com.cads.db.repository.UserRepository;

@Service("authenticationService")
public class AuthenticationService {
	    
	    AuthenticationUtil authenticationUtil;
	    
	    @Autowired
		UserRepository userRepo;
	    
	    @Autowired
	    public AuthenticationService(AuthenticationUtil authenticationUtil)
	    {
	       
	        this.authenticationUtil = authenticationUtil;
	    }
	    public User authenticate(String userName, String userPassword) throws AuthenticationException, InvalidKeySpecException {
	    	User user = new User();
	        //User userEntity = getUserByName(userName); // User name must be unique in our system
	    	User userEntity = new User();
	    	userEntity.setEmail("debasmita@gmail.com");
	    	userEntity.setFlatId(6);
	    	userEntity.setPassword(authenticationUtil.
                    generateSecurePassword("debasmita", userEntity.getSalt()));
	    	
	        // as it is the emailid.Here we perform authentication business logic
	        // If authentication fails, we throw new AuthenticationException
	        // other wise we return UserProfile Details
	        String secureUserPassword = null;
	        try {
	            secureUserPassword = authenticationUtil.
	                    generateSecurePassword(userPassword, userEntity.getSalt());
	        } catch (InvalidKeySpecException ex) {	            
	            throw new AuthenticationException(ex.getLocalizedMessage());
	        }
	        boolean authenticated = false;
	        if (secureUserPassword != null && secureUserPassword.equalsIgnoreCase(userEntity.getPassword())) {
	            if (userName != null && userName.equalsIgnoreCase(userEntity.getEmail())) {
	                authenticated = true;
	            }
	        }
	        if (!authenticated) {
	            throw new AuthenticationException("Authentication failed");
	        }
	        BeanUtils.copyProperties(userEntity, user);
	        return user;
	    }
	    private User getUserByName(String userName) {			
			return userRepo.findByEmail(userName);
		}
		public User resetSecurityCridentials(String password, 
	            User user) {
	        // Generate salt
	        String salt = authenticationUtil.generateSalt(30);
	        // Generate secure user password 
	        String secureUserPassword = null;
	        try {
	            secureUserPassword = authenticationUtil.
	                    generateSecurePassword(password, salt);
	        } catch (InvalidKeySpecException ex) {	            
	            //throw new UserServiceException(ex.getLocalizedMessage());
	        }
	        user.setSalt(salt);
	        user.setPassword(secureUserPassword);
	        User userEntity = new User();
	        BeanUtils.copyProperties(user, userEntity);
	        // Connect to database 
	        
	        return user;
	    }
	    
	    public String issueSecureToken(User userProfile) throws AuthenticationException {
	        String returnValue = null;
	        // Get salt but only part of it
	        String newSaltAsPostfix = userProfile.getSalt();
	        String accessTokenMaterial = userProfile.getId() + newSaltAsPostfix;
	        byte[] encryptedAccessToken = null;
	        try {
	            encryptedAccessToken = authenticationUtil.encrypt(userProfile.getPassword(), accessTokenMaterial);
	        } catch (InvalidKeySpecException ex) {
	            System.out.println("Faled to issue secure access token.Exception:"+ ex);
	            throw new AuthenticationException("Faled to issue secure access token");
	        }
	        String encryptedAccessTokenBase64Encoded = Base64.getEncoder().encodeToString(encryptedAccessToken);
	        // Split token into equal parts
	        int tokenLength = encryptedAccessTokenBase64Encoded.length();
	        String tokenToSaveToDatabase = encryptedAccessTokenBase64Encoded.substring(0, tokenLength / 2);
	        returnValue = encryptedAccessTokenBase64Encoded.substring(tokenLength / 2, tokenLength);
	        userProfile.setToken(tokenToSaveToDatabase);
	        //storeAccessToken(userProfile);
	        return returnValue;
	    }
	}


