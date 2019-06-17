package com.cads.controllers;

import java.util.Arrays;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cads.acess.UserGroup;

@RestController
public class UserGroupController {


    @RequestMapping(com.cads.utils.URLConstants.USERGROUPURI)
    @CrossOrigin(origins = "*")
    @ResponseBody
	public String[] getAllGroups() {
		//return userGroupService.getAllGroups();
    	return Arrays.toString(UserGroup.values()).replaceAll("^.|.$", "").split(", ");
		
	}
}
