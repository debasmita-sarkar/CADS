package com.cads.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cads.db.models.User;
import com.cads.services.UserService;

@Controller
public class UserController {

@Autowired
private UserService userService;

	@RequestMapping(com.cads.utils.URLConstants.AUTHENTICATE)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public boolean authenticate(@RequestParam String userName, @RequestParam String password) {
		
	 return userService.isValidUser(userName,password);
	}
	
	@RequestMapping(com.cads.utils.URLConstants.USERURI)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public List<User> getAllUsers() {
		return userService.getAllUsers();
		
	}
	
	@RequestMapping(com.cads.utils.URLConstants.USERURI+"/{userID}")
	@ResponseBody
	public User getUser(@PathVariable String userID) {
		return userService.getSingleUserDetailsByID(userID);		
	}
	
	//@RequestMapping(com.cads.utils.URLConstants.USERURI+"/{userID}")
	//@ResponseBody
	//public Object getSingleAttributeForUser(@PathVariable String userID,String userAttributeName) {
		//return userService.getSingleUserDetailForAUser(userID,userAttributeName);		
	//}
	
	@RequestMapping(method=RequestMethod.POST, value=com.cads.utils.URLConstants.USERURI)
	@CrossOrigin(origins = "*")
	public void addUser(@RequestBody User user) {
		System.out.println("inside users controller");
		userService.createUser(user);		
	}
	
	@RequestMapping(method=RequestMethod.POST, value=com.cads.utils.URLConstants.MULTI_USERURI)
	public void addUsers(@RequestBody List<User> user) {
		userService.createMultiuser(user);		
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value=com.cads.utils.URLConstants.USERURI)
	public void deleteUser(@PathVariable int userID) {
		userService.deleteUser(userID);		
	}
	
	/*
	 * Use this for adding/updating groups/tools and all other properties of user.
	 * The request body has to contain the property to be changed and corresponding new value.
	 */
	//@RequestMapping(method=RequestMethod.PUT, value=com.cads.utils.URLConstants.USERURI)
	//public void updateUser(@RequestBody User user) {
	//	userService.updateUser(user);		
	//}
	
	
}
