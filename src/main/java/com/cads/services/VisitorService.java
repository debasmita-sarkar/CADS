package com.cads.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cads.db.models.Visitor;
import com.cads.db.repository.VisitorRepository;
import com.cads.services.ParkingSlotService;
@Service
public class VisitorService {
	
	@Resource
	private ParkingSlotService parkingSlotService;
	
	@Autowired
	VisitorRepository visitorRepo;
	
	Map<Integer, Visitor> mapVisitorIdToVisitor = new HashMap<Integer,Visitor>();
	
	public Visitor getVisitorById(int visitorId){
		return mapVisitorIdToVisitor.get(visitorId);
	}
	
	public List<Visitor> getAllVisitors(){
		return mapVisitorIdToVisitor.values().stream().collect(Collectors.toList());
	}
	
	public void addAVisitor(Visitor visitorDto){		
		mapVisitorIdToVisitor.put(visitorDto.getId(),visitorDto);
		parkingSlotService.removeFromAvailableParkingSlots(visitorDto.getParking(),visitorDto.getId());
	}
	@SuppressWarnings("unlikely-arg-type")
	public void removeVisitor(String visitorId){
		Visitor dto = mapVisitorIdToVisitor.get(visitorId);
		if(dto !=null) {
			mapVisitorIdToVisitor.remove(dto);
			if(dto.getParking()!= -1 ) {
				parkingSlotService.addToAvailableParkingSlots(dto.getParking());
			}
		}
	}
	
	@PostConstruct
	public void populateVisitorMapFromDB(){
		visitorRepo.findAll().stream().forEach(visitor -> addAVisitor(visitor));		
		System.out.println("in postconstruct -mapVisitorIdToVisitor "+ mapVisitorIdToVisitor);
	}

	

}
