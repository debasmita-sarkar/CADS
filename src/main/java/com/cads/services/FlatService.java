package com.cads.services;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cads.db.models.Building;
import com.cads.db.models.Flat;
import com.cads.db.repository.BuildingRepository;
import com.cads.db.repository.FlatRepository;
import com.cads.utils.Utility;

@Service
public class FlatService {
	Map<Integer,Flat> mapFlatIdToFlat = new HashMap<Integer,Flat>();
	@Autowired
	FlatRepository flatRepo;
	
	public List<String> getAllFlatNumbers(){		
		return mapFlatIdToFlat.values().stream().map(flat-> flat.getNumber()).collect(Collectors.toList());		
	}
	
	public Set<Flat> getAllFlats(){
		return mapFlatIdToFlat.values().stream().collect(Collectors.toSet());
	}
	
	public void addFlat(Flat flat){
		mapFlatIdToFlat.put(flat.getId(), flat);
		//Add in db
		flatRepo.save(flat);
	}
	
	public void addFlats(List<Flat> flats){
		flats.stream().forEach(flat -> addFlat(flat));
		
	}
	
	public void deleteFlat(String flatId){
		//delete from db
		flatRepo.delete(mapFlatIdToFlat.get(flatId));
		mapFlatIdToFlat.remove(flatId);
	}
	
	public void deleteFlats(List<String> flatIds){		
		flatIds.forEach(flatId -> deleteFlat(flatId));
		//delete from db
	}
	
	@PostConstruct
	public void populateFlats(){		
		//get data from db
		addFlats(flatRepo.findAll());		
	}

}
