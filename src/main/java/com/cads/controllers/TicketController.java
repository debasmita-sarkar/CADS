package com.cads.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cads.db.models.Ticket;
import com.cads.db.models.Workers;
import com.cads.services.TicketService;
import com.cads.tableHeaders.HeadersTemplate;
import com.cads.tickets.util.TicketState;
import com.cads.utils.TicketType;


@RestController
public class TicketController {
	
	@Autowired
	private TicketService ticketService;

	@RequestMapping(com.cads.utils.URLConstants.TICKETURI+"/{ticketId}")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public Ticket getTicket(@PathVariable int ticketId) {
		return ticketService.getTicketById(ticketId);
		
	}
	
	@RequestMapping(com.cads.utils.URLConstants.TICKETURI)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public List<Ticket> getAllTickets() {
		List<Ticket> ticketReturnList =  ticketService.getAllTickets();
		return ticketReturnList;
		
	}
	
	@RequestMapping(method=RequestMethod.POST,value=com.cads.utils.URLConstants.TICKETURI)
	@CrossOrigin(origins = "*")
	public void postTicket(@RequestBody Ticket ticket) {
		if(ticket.getState() == null) {
			ticket.setState(TicketState.NEW);
		}
		ticketService.createTicket(ticket);		
	}
	
	@RequestMapping(method=RequestMethod.PUT,value=com.cads.utils.URLConstants.TICKETURI)
	@CrossOrigin(origins = "*")
	public void updateTicket(@RequestBody Ticket ticket) {
		ticketService.updateTicket(ticket);		
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value=com.cads.utils.URLConstants.TICKETURI+"/{ticketId}")	
	@CrossOrigin(origins = "*")
	public void deleteTicket(@PathVariable int ticketId) {
		ticketService.deleteTicket(ticketId);		
	}
	
	@RequestMapping(com.cads.utils.URLConstants.ACTORURI)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public Map<TicketType, Set<Workers>> getAllActors() {
		return ticketService.getAllActors();
		
	}
	
	@RequestMapping(com.cads.utils.URLConstants.WORKERTYPES)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public String [] getAllWorkerTypes() {
		return  ticketService.getAllWorkerTypes();		
	}
	
	@RequestMapping(com.cads.utils.URLConstants.TICKETSTATES)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public String [] getAllTicketStates() {
		return  ticketService.getAllTicketStates();		
	}
	
	@RequestMapping(com.cads.utils.URLConstants.TICKETTYPES)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public String [] getAllTicketTypes() {		
		return  ticketService.getAllTicketTypes();		
	}
	
	@RequestMapping(com.cads.utils.URLConstants.TICKETCOLUMNS)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public List<HeadersTemplate> getAllColumns(@RequestParam(value="ticketcategory", required=true) String ticketcategory) {
		return ticketService.getAllColumnHeaders(ticketcategory);
	}
}
