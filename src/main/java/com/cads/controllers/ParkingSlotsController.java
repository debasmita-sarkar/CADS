

package com.cads.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cads.db.models.VisitorParkingSlot;
import com.cads.services.ParkingSlotService;
import com.cads.services.UserService;

@RestController
public class ParkingSlotsController {
	
	@Autowired
	private ParkingSlotService parkingSlotService;
	
	@RequestMapping(com.cads.utils.URLConstants.PARKINGSLOTSURI)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public List<VisitorParkingSlot> getAllParkingSlots() {
		return parkingSlotService.getAllParkingSlots();
	}
	
	@RequestMapping(com.cads.utils.URLConstants.AVAILABLE_PARKINGSLOTSURI)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public List<VisitorParkingSlot> getAvailableParkingSlots() {
		return parkingSlotService.getAvailableParkingSlots();
	}
	
	@RequestMapping(method=RequestMethod.POST,value = com.cads.utils.URLConstants.AVAILABLE_PARKINGSLOTSURI)
	@CrossOrigin(origins = "*")
	public void addInAvailableParkingSlots(@RequestBody VisitorParkingSlot parkingSlotDto) {
		parkingSlotService.addToAvailableParkingSlots(parkingSlotDto);
	}
	
	@RequestMapping(method=RequestMethod.POST,value = com.cads.utils.URLConstants.PARKINGSLOTSURI)
	@CrossOrigin(origins = "*")
	public void addToParkingSlots(@RequestBody VisitorParkingSlot parkingSlotDto) {
		parkingSlotService.addToParkingSlots(parkingSlotDto);
	}
	
	@RequestMapping(method=RequestMethod.POST,value = com.cads.utils.URLConstants.MULTIPARKINGSLOTSURI)
	@CrossOrigin(origins = "*")
	public void addToParkingSlots(@RequestBody List<VisitorParkingSlot> parkingSlotDtos) {
		System.out.println("inside parking controller:"+ parkingSlotDtos);
		parkingSlotService.addMultipleParkingSlots(parkingSlotDtos);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value = com.cads.utils.URLConstants.AVAILABLE_PARKINGSLOTSURI)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public VisitorParkingSlot deleteFromAvailableParkingSlots(int parkingSlotID,int visitorID) {
		return parkingSlotService.removeFromAvailableParkingSlots(parkingSlotID,visitorID);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value = com.cads.utils.URLConstants.PARKINGSLOTSURI)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public VisitorParkingSlot deleteFromParkingSlots(String parkingSlotID,String visitorID) {
		return parkingSlotService.removeFromParkingSlots(parkingSlotID);
	}

}
