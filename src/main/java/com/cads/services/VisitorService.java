package com.cads.services;

import java.util.ArrayList;
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
import com.cads.db.models.VisitorParkingSlot;
import com.cads.db.repository.BuildingRepository;
import com.cads.db.repository.FlatRepository;
import com.cads.db.repository.VisitorRepository;
import com.cads.services.ParkingSlotService;
import com.cads.tableHeaders.HeadersTemplate;
import com.cads.utils.Utility;
@Service
public class VisitorService {
	@Autowired
	VisitorRepository visitorRepo;
	
	@Autowired
	FlatService flatService;
	
	@Autowired
	ParkingSlotService parkingService;
	
	@Autowired
	BuildingService buildingService;
	
	@Autowired
	BuildingRepository buildingRepo;
	
	@Autowired
	FlatRepository flatRepo;
	
	Map<Integer, Visitor> mapVisitorIdToVisitor = new HashMap<Integer,Visitor>();
	Map<String, Map<? super Object,String>> mapColToLookUps = new HashMap<String, Map<? super Object,String>>();
	
	public Visitor getVisitorById(int visitorId){
		return mapVisitorIdToVisitor.get(visitorId);
	}
	
	public List<Visitor> getAllVisitors(){
		System.out.println("all visitor in get all visitors::"+mapVisitorIdToVisitor);
		return mapVisitorIdToVisitor.values().stream().collect(Collectors.toList());
	}
	
	public void addAVisitorFromUI(Visitor visitorDto){
	Visitor visitorDtoEdited = setFieldsForUIResponse(visitorDto);
	addAVisitor(visitorDtoEdited);
	}
	public void addAVisitor(Visitor visitorDto){
		if(visitorDto == null ) {
			return;
		}
		System.out.println("buildingid set in visitorDto from UI:"+visitorDto.getBuildingId());
		setBuildingDetails(visitorDto);
		setFlatDetails(visitorDto);
		System.out.println("in add a visitor"+visitorDto.getBuildingName()+":"+mapColToLookUps.get("buildingName"));
		mapVisitorIdToVisitor.put(visitorDto.getId(),visitorDto);
		System.out.println("in add after all setting visitor"+visitorDto);
		visitorRepo.save(visitorDto);
		parkingService.removeFromAvailableParkingSlots(visitorDto.getParking(),visitorDto.getId());
		System.out.println("while adding visitor:"+mapVisitorIdToVisitor.size()
		+"::"+mapVisitorIdToVisitor.get(visitorDto.getId())+":from db:"+visitorRepo.findById(visitorDto.getId()).toString());
	}

	private void setFlatDetails(Visitor visitorDto) {
		Flat flatById = flatService.getFlatById(visitorDto.getFlatId());
		if(flatById !=null) {
			System.out.println("flatById:"+flatById);
			visitorDto.setFlatnumber(flatById.getNumber());
		}
	}

	private void setBuildingDetails(Visitor visitorDto) {
		Building buildingById = buildingService.getSingleBuildingByID(visitorDto.getBuildingId());
		if(buildingById !=null) {
			System.out.println("buildingById:"+buildingById);
			visitorDto.setBuildingName(buildingById.getName());
		}
	}

	private Visitor setFieldsForUIResponse(Visitor visitorDto) {
		System.out.println("visitorDto from UI:"+visitorDto.toString());
		visitorDto.setBuildingId(Integer.parseInt(visitorDto.getBuildingName()!=null?visitorDto.getBuildingName():"0"));		
		//set flatid and flat number
		visitorDto.setFlatId(Integer.parseInt(visitorDto.getFlatnumber()!=null?visitorDto.getFlatnumber():"0"));
	    return visitorDto;
	}
	@SuppressWarnings("unlikely-arg-type")
	public void removeVisitor(int visitorId){
		//Visitor dto = mapVisitorIdToVisitor.get(visitorId);		
		Visitor dto = visitorRepo.findById(visitorId);
		if(dto !=null) {
			System.out.println("before deleting:"+mapVisitorIdToVisitor);
			mapVisitorIdToVisitor.remove(visitorId);
			visitorRepo.delete(visitorId);
			System.out.println("after deleting:");
			if(dto.getParking()!= -1 ) {
				parkingService.addToAvailableParkingSlots(dto.getParking());
			}
		}
	}
	
	@PostConstruct
	public void populateVisitorMapFromDB(){
		visitorRepo.findAll().stream().forEach(visitor -> addAVisitor(visitor));		
		System.out.println("in postconstruct -mapVisitorIdToVisitor "+ mapVisitorIdToVisitor);
	}

	public List<HeadersTemplate> getAllColumnHeaders() {
		
		return createColHeaderList();
	}

	private List<HeadersTemplate> createColHeaderList() {
		Utility utility = new Utility();
		List<HeadersTemplate> headers = new ArrayList<HeadersTemplate>();		
		HeadersTemplate col1 = new HeadersTemplate("Name","name");
		HeadersTemplate col2 = new HeadersTemplate("Phone number","phone");
		HeadersTemplate col3 = new HeadersTemplate("Building Name","buildingName",utility.createLookUpMap(mapColToLookUps,"buildingName",buildingService.getAllBuildings().stream().collect(Collectors.toMap(Building::getId,Building::getName))));
		HeadersTemplate col4 = new HeadersTemplate("Flat number","flatnumber",utility.createLookUpMap(mapColToLookUps,"flatnumber",flatService.getAllFlats().stream().collect(Collectors.toMap(Flat::getId,Flat::getNumber))));
		HeadersTemplate col5 = new HeadersTemplate("Purpose","purpose");
		HeadersTemplate col6 = new HeadersTemplate("Time in","timeIn");
		HeadersTemplate col7 = new HeadersTemplate("Time Out","timeOut");
		HeadersTemplate col8 = new HeadersTemplate("Parking slot allocated","parking",utility.createLookUpMap(mapColToLookUps,"parking",parkingService.getAvailableParkingSlots().stream().collect(Collectors.toMap(VisitorParkingSlot:: getId, VisitorParkingSlot::getSlot))));
		headers.add(col1);
		headers.add(col2);
		headers.add(col3);
		headers.add(col4);
		headers.add(col5);
		headers.add(col6);
		headers.add(col7);
		headers.add(col8);
		System.out.println("headers of table:"+headers);
		return headers;
	}
}
