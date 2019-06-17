package com.cads.services;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cads.db.models.User;
import com.cads.db.repository.UserRepository;
import com.cads.utils.Utility;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	Map<String, String> setOfValidUserCredentials = new HashMap<String,String>();
	Map<Integer, User> mapUserIDToUser = new HashMap<Integer,User>();	

	public void setListOfValidUserCredentials(Map<String, String> setOfValidUserCredentials) {
		//Remove the dummy data after integrating with db
		setOfValidUserCredentials.put("antara@gmail.com", "antara");
		setOfValidUserCredentials.put("Champak", "Champak");
		setOfValidUserCredentials.put("Debasmita", "Debasmita");
		setOfValidUserCredentials.put("Sourav", "Sourav");
		this.setOfValidUserCredentials = setOfValidUserCredentials;
	}

	public List<User> getAllUsers() {
		return userRepo.findAll();
		//List<User> userList= mapUserIDToUser.values().parallelStream().collect(Collectors.toList());
	   // return userList;
	}
	
	public User getSingleUserDetailsByID(String userID) {
		return mapUserIDToUser.get(userID);
	}

	public void createUser(User user) {		
		System.out.println("inside service:"+ user);
		mapUserIDToUser.put(user.getId(), user);
		setOfValidUserCredentials.put(user.getEmail(), user.getPassword());		
		System.out.println("all done"+ mapUserIDToUser.get(user.getId()));
		//add to db
		userRepo.save(user);
	}

	public void deleteUser(int userId) {
		setOfValidUserCredentials.remove(mapUserIDToUser.get(userId).getEmail());
		userRepo.delete(userId);
		mapUserIDToUser.remove(userId);
		//remove from db
		userRepo.delete(userId);
	}

	public User getUser(int id) {		
		return userRepo.findById(id);
	}
	
	public void updateUser(User user) {		
		setOfValidUserCredentials.put(user.getEmail(), user.getPassword());
		mapUserIDToUser.put(user.getId(), user);
		//add to db
	}

	public boolean isValidUser(String userName, String password) {
		System.out.println("setOfValidUserCredentials"+setOfValidUserCredentials);
		System.out.println("passed creds:"+userName+password);
		return(this.setOfValidUserCredentials.get(userName).equalsIgnoreCase(password));
	}

	public Object getSingleUserDetailForAUser(String userID, String userAttributeName) {

		Class<? extends User> cls = null;
		//cls = Class.forName("com.cads.users.dto.UserDto");
		cls = mapUserIDToUser.get(userID).getClass();
		//call the printIt method
		Method method = null;
		try {
			method = cls.getDeclaredMethod("get"
					+ userAttributeName.substring(0, 1).toUpperCase()+userAttributeName.substring(1));
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object attributeValue = null;
		try {
			attributeValue = (method == null) ? null : method.invoke(mapUserIDToUser.get(userID), null);
		} catch (IllegalAccessException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
		} catch(InvocationTargetException e) {
			e.printStackTrace();
		}
		return attributeValue;
	}

	public void createMultiuser(List<User> userList) {
		userList.forEach(user-> createUser(user));
	}
	
	public void removeMultiuser(List<Integer> userIdList) {
		userIdList.forEach(userId-> deleteUser(userId));
	}
	
	@PostConstruct
	public void populateUserMapFromDB(){
		setListOfValidUserCredentials(setOfValidUserCredentials);
		// Add from db 		
		createMultiuser(userRepo.findAll());
	}

	
}
