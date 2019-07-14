package com.cads.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cads.db.models.Building;
import com.cads.db.models.Flat;
import com.cads.db.models.Visitor;
import com.cads.db.repository.BuildingRepository;
import com.cads.db.repository.FlatRepository;
import com.cads.db.repository.VisitorRepository;
import com.cads.services.ParkingSlotService;
@Service
public class VisitorService {
	
	@Resource
	private ParkingSlotService parkingSlotService;
	
	@Autowired
	VisitorRepository visitorRepo;
	
	@Autowired
	BuildingRepository buildingRepo;
	
	@Autowired
	FlatRepository flatRepo;
	
	Map<Integer, Visitor> mapVisitorIdToVisitor = new HashMap<Integer,Visitor>();
	
	public Visitor getVisitorById(int visitorId){
		return mapVisitorIdToVisitor.get(visitorId);
	}
	
	public List<Visitor> getAllVisitors(){
		System.out.println("all visitor in get all visitors::"+mapVisitorIdToVisitor.size());
		return mapVisitorIdToVisitor.values().stream().collect(Collectors.toList());
	}
	
	public void addAVisitor(Visitor visitorDto){
		visitorDto.setBuildingId(addBuildingId(visitorDto));
		visitorDto.setFlatId(addFlatId(visitorDto));
		mapVisitorIdToVisitor.put(visitorDto.getId(),visitorDto);
		visitorRepo.save(visitorDto);
		parkingSlotService.removeFromAvailableParkingSlots(visitorDto.getParking(),visitorDto.getId());
		System.out.println("while adding visitor:"+mapVisitorIdToVisitor.size()
		+"::"+mapVisitorIdToVisitor.get(visitorDto.getId())+":from db:"+visitorRepo.findById(visitorDto.getId()).toString());
	}
	private int addFlatId(Visitor visitorDto) {
		String flatNumber = visitorDto.getFlatnumber();		
		if(flatNumber!=null || !flatNumber.isEmpty()) {		
		Flat flat= flatRepo.findByNumber(flatNumber) ;
		return((flat!=null)?flat.getId():0);		
		}
		return 0;
	}

	private int addBuildingId(Visitor visitorDto) {
		String buildingName = visitorDto.getBuildingName();		
		if(buildingName!=null || !buildingName.isEmpty()) {		
		Building building= buildingRepo.findByName(buildingName) ;
		return((building!=null)?building.getId():0);		
		}
		return 0;
		
	}

	@SuppressWarnings("unlikely-arg-type")
	public void removeVisitor(int visitorId){
		//Visitor dto = mapVisitorIdToVisitor.get(visitorId);		
		Visitor dto = visitorRepo.findById(visitorId);
		if(dto !=null) {
			System.out.println("before deleting:"+mapVisitorIdToVisitor);
			mapVisitorIdToVisitor.remove(dto);
			visitorRepo.delete(visitorId);
			System.out.println("after deleting:");
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
