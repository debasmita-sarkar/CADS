package com.cads.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.cads.db.models.Visitor;


@RestController
public class VisitorController {
	
	@RequestMapping(com.cads.utils.URLConstants.VISITORURI+"/{visitorID}")
	@ResponseBody
	public Visitor getVisitor(@PathVariable String visitorID) {
		return null;	
	}
	
	@RequestMapping(com.cads.utils.URLConstants.VISITORURI)
	@ResponseBody
	public List<Visitor> getAllVisitors() {
		return null;
	}
	
	@RequestMapping(method=RequestMethod.POST, value=com.cads.utils.URLConstants.VISITORURI)	
	public void addVisitor(@RequestBody Visitor user) {
			
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value=com.cads.utils.URLConstants.VISITORURI+"/{visitorID}")
	public void deleteVisitor(@PathVariable String visitorID) {
			
	}
}
