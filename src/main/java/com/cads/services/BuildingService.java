package com.cads.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cads.db.models.Building;
import com.cads.db.repository.BuildingRepository;

@Service
public class BuildingService {
	
	AtomicInteger counter = new AtomicInteger();
	Map<Integer,Building> mapBuildingIdToBuilding = new HashMap<Integer,Building>();
	@Autowired
	BuildingRepository buildingRepo;

	public Set<Building> getAllBuildings() {
		return mapBuildingIdToBuilding.values().stream().collect(Collectors.toSet());
	}

	public void addBuilding(Building building) {		
		mapBuildingIdToBuilding.put(building.getId(), building);
		//add to db
		buildingRepo.save(building);
		
	}

	public Building getSingleBuildingByID(int buldingID) {
		return buildingRepo.findById(buldingID);
	}

	public void addMultipleBuilding(Building[] buildings) {
		Arrays.asList(buildings).stream().forEach(building -> addBuilding(building));
	}
	
	@PostConstruct
	public void populateBuildingMapFromDB(){
		List<Building> allBuildings =buildingRepo.findAll();
		allBuildings.stream().forEach(building->
		addBuilding(building));		
	}

}
