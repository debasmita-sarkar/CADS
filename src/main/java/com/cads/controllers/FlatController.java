package com.cads.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cads.db.models.Flat;
import com.cads.services.FlatService;

@RestController
public class FlatController {
	
	@Autowired
	FlatService flatService;
	
	@RequestMapping(com.cads.utils.URLConstants.FLAT_NUMBERS)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public String[] getAllFlatNumbers(){
		return (String[]) flatService.getAllFlatNumbers().toArray(new String[0]) ;
	}
	
	@RequestMapping(com.cads.utils.URLConstants.FLATS)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public Set<Flat> getAllFlats(){
		return flatService.getAllFlats() ;
	}
	
	@RequestMapping(method=org.springframework.web.bind.annotation.RequestMethod.POST,value=com.cads.utils.URLConstants.FLATS)
	@CrossOrigin(origins = "*")
	public void addAFlat(@RequestBody Flat flat){
		flatService.addFlat(flat);
	}

}
