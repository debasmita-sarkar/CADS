package com.cads.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cads.db.models.Building;
import com.cads.services.BuildingService;

@RestController
public class BuildingController {
	
	@Autowired
	BuildingService buildingService;
	
	@RequestMapping(com.cads.utils.URLConstants.Buildings)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public Building[] getAllBuildings(){
		return buildingService.getAllBuildings().toArray(new Building[0]) ;
	}
	
	@RequestMapping(com.cads.utils.URLConstants.Buildings+"/{buildingID}")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public Building getBuilding(@PathVariable int buldingID){
		return buildingService.getSingleBuildingByID(buldingID);
	}
	
	@RequestMapping(method=org.springframework.web.bind.annotation.RequestMethod.POST,value=com.cads.utils.URLConstants.Buildings)
	@CrossOrigin(origins = "*")
	public void addABuilding(@RequestBody Building building){
		buildingService.addBuilding(building);
	}
	
	@RequestMapping(method=org.springframework.web.bind.annotation.RequestMethod.POST,value=com.cads.utils.URLConstants.MultiBuildings)
	@CrossOrigin(origins = "*")
	public void addABuilding(@RequestBody Building[] buildings){
		buildingService.addMultipleBuilding(buildings);
	}

}
