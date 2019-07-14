package com.cads.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.cads.db.models.Visitor;
import com.cads.services.VisitorService;


@RestController
public class VisitorController {

	@Autowired
	private VisitorService visitorService;
	
	@RequestMapping(com.cads.utils.URLConstants.VISITORURI+"/{visitorID}")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public Visitor getVisitor(@PathVariable int visitorID) {
		return visitorService.getVisitorById(visitorID);	
	}
	
	@RequestMapping(com.cads.utils.URLConstants.VISITORURI)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public List<Visitor> getAllVisitors() {
		return visitorService.getAllVisitors();
	}
	
	@RequestMapping(method=RequestMethod.POST, value=com.cads.utils.URLConstants.VISITORURI)
	@CrossOrigin(origins = "*")
	public void addVisitor(@RequestBody Visitor visitor) {
		visitorService.addAVisitor(visitor);	
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value=com.cads.utils.URLConstants.VISITORURI+"/{visitorID}")
	@CrossOrigin(origins = "*")
	public void deleteVisitor(@PathVariable String visitorID) {
		System.out.println("in visitor controller visitorID"+visitorID);
		visitorService.removeVisitor(Integer.parseInt(visitorID.trim()));
	}
}
