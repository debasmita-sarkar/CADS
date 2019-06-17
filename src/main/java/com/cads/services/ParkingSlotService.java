package com.cads.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cads.db.models.VisitorParkingSlot;
import com.cads.db.repository.ParkingSlotRepository;
import com.cads.utils.Utility;

@Service
public class ParkingSlotService {
	
	List<VisitorParkingSlot> availableParkingSlots = new ArrayList<VisitorParkingSlot>();	
	Map<Integer, VisitorParkingSlot> mapSlotIdToSlot = new HashMap<Integer,VisitorParkingSlot>();
	
	@Autowired
	ParkingSlotRepository parkingRepo;
	
	public List<VisitorParkingSlot> getAvailableParkingSlots(){
		return availableParkingSlots;		
	}
	
	public List<VisitorParkingSlot> getAllParkingSlots(){
		return mapSlotIdToSlot.values().stream().collect(Collectors.toList());		
	}
	
	public void addToAvailableParkingSlots(VisitorParkingSlot parkingSlotDto) {
		if(parkingSlotDto == null) {
			return;
		}
		parkingSlotDto.setVisitorId(-1);
		availableParkingSlots.add(parkingSlotDto);
		parkingRepo.save(parkingSlotDto);
		mapSlotIdToSlot.put(parkingSlotDto.getId(), parkingSlotDto);				
	}	
	public void addToAvailableParkingSlots(int parkingSlotId) {
		VisitorParkingSlot parkingSlotDto = mapSlotIdToSlot.get(parkingSlotId);
		if(parkingSlotDto !=null ) {
			parkingSlotDto.setVisitorId(-1);
			parkingRepo.save(parkingSlotDto);
			addToAvailableParkingSlots(parkingSlotDto);
		}
	}
	
	public VisitorParkingSlot removeFromAvailableParkingSlots(int parkingSlotID, int visitorId) {
		VisitorParkingSlot parkingSlotDto = mapSlotIdToSlot.get(parkingSlotID);
		if(parkingSlotDto ==null) {
			return null;
		}
		parkingSlotDto.setVisitorId(visitorId);
		parkingRepo.save(parkingSlotDto);
		availableParkingSlots.remove(parkingSlotDto);		
		return parkingSlotDto;
	}
	
	@PostConstruct
	public void populateParkingSlotMapFromDB(){
		
		addMultipleParkingSlots(parkingRepo.findAll());
		System.out.println("in postconstruct - VisitorParkingSlots from db: "+ parkingRepo.findAll());
	}

	public void addToParkingSlots(VisitorParkingSlot parkingSlotDto) {
		if(parkingSlotDto == null) {
			return;
		}		
		availableParkingSlots.add(parkingSlotDto);
		mapSlotIdToSlot.put(parkingSlotDto.getId(), parkingSlotDto);
		parkingRepo.save(parkingSlotDto);
	}
	
	public void addMultipleParkingSlots(List<VisitorParkingSlot> parkingSlotDtos) {
		parkingSlotDtos.forEach(parkingSlotDto -> addToParkingSlots(parkingSlotDto));		
	}

	public VisitorParkingSlot removeFromParkingSlots(String parkingSlotID) {
		VisitorParkingSlot parkingSlotDto = mapSlotIdToSlot.get(parkingSlotID);
		if(parkingSlotDto ==null) {
			return null;
		}
		mapSlotIdToSlot.remove(parkingSlotID,parkingSlotDto);
		availableParkingSlots.remove(parkingSlotDto);		
		return parkingSlotDto;
	}
	
	public void removeMultipleParkingSlots(List<String> parkingSlotIds) {
		parkingSlotIds.forEach(parkingSlotId -> removeFromParkingSlots(parkingSlotId));		
	}

}
