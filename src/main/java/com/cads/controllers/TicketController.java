package com.cads.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cads.db.models.Ticket;
import com.cads.db.models.Workers;
import com.cads.services.TicketService;
import com.cads.utils.TicketType;


@RestController
public class TicketController {
	
	@Autowired
	private TicketService ticketService;

	@RequestMapping(com.cads.utils.URLConstants.TICKETURI+"/{ticketId}")
	@ResponseBody
	public Ticket getTicket(@PathVariable int ticketId) {
		return ticketService.getTicketById(ticketId);
		
	}
	
	@RequestMapping(com.cads.utils.URLConstants.TICKETURI)
	@ResponseBody
	public List<Ticket> getAllTickets() {
		return ticketService.getAllTickets();
		
	}
	
	@RequestMapping(method=RequestMethod.POST,value=com.cads.utils.URLConstants.TICKETURI)	
	public void postTicket(@RequestBody Ticket ticket) {
		ticketService.createTicket(ticket);		
	}
	
	@RequestMapping(method=RequestMethod.PUT,value=com.cads.utils.URLConstants.TICKETURI)	
	public void updateTicket(@RequestBody Ticket ticket) {
		ticketService.updateTicket(ticket);		
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value=com.cads.utils.URLConstants.TICKETURI+"/{ticketId}")	
	public void deleteTicket(@PathVariable int ticketId) {
		ticketService.deleteTicket(ticketId);		
	}
	
	@RequestMapping(com.cads.utils.URLConstants.ACTORURI)
	@ResponseBody
	public Map<TicketType, Set<Workers>> getAllActors() {
		return ticketService.getAllActors();
		
	}
	
	

}
