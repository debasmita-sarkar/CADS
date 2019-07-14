package com.cads.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cads.db.models.Ticket;
import com.cads.db.models.TicketLog;
import com.cads.db.models.User;
import com.cads.db.models.Workers;
import com.cads.db.repository.TicketRepository;
import com.cads.db.repository.TicketlogRepository;
import com.cads.db.repository.UserRepository;
import com.cads.db.repository.WorkerRepository;
import com.cads.factory.TicketLogFactory;
import com.cads.tickets.util.TicketState;
import com.cads.utils.SMSSender;
import com.cads.utils.TicketType;
import com.cads.workers.WorkerTypes;
import com.cads.workers.Workersmap;

@Service
public class TicketService {
	@Resource
	Workersmap actorMap;
	
	@Autowired
	WorkerRepository workerRepository;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	TicketlogRepository logRepo;
	

	
	@Autowired
	TicketRepository ticketRepo;
	
	private Map<Integer,Ticket> ticketMapStore = new HashMap<Integer,Ticket>();	
	SMSSender smsSender = new SMSSender();

	public Ticket getTicketById(int ticketId) {		
		return ticketMapStore.get(ticketId);
	}
	
	//this will create a new ticket
	public void createTicket(Ticket ticket) {
		System.out.println("In create ticket service"+ticket.toString());
		//Store the ticket in cache
		ticketMapStore.put(ticket.getId(),ticket);
		System.out.println("In create ticket service : ticketMapStore"+ticketMapStore.size());
		ticketRepo.save(ticket);
		TicketLog log = TicketLogFactory.getNewTicketLog(ticket);
		logRepo.save(log);
		//Send sms
		//sendSMS(ticket);
		//Add logic of sending mail
	}
	
	public boolean assignTicket(int ticketId, int workerid) {
		Ticket ticket = ticketRepo.findById(ticketId);
		if(ticket != null) {
			Workers workerObj = workerRepository.findById(workerid);
			ticket.setWorkerType(workerObj.getType());
			ticket.setOwnerid(workerid);
			ticket.setState(TicketState.ASSIGNED);
			Ticket savedTicket = ticketRepo.save(ticket);
			if(savedTicket != null)
				return true;
			else 
				return false;
		}else {
			return false;
		}
	}
	
	public boolean submitTicket(Ticket ticket, int workerId) {
		if(ticket.getWorkerType() != WorkerTypes.user) {
			ticket.setOwnerid(ticket.getSubmitterid());
			ticket.setState(TicketState.SUBMITTED);
			return true;
		}else {
			return false;
		}		
	}
	
	//rejection reason must be there
	public boolean rejectTicket(Ticket ticket, int workerId) {
		if(ticket.getWorkerType() != WorkerTypes.user) {
			ticket.setOwnerid(ticket.getSubmitterid());
			ticket.setState(TicketState.ASSIGNED);
		}else{
			return false;
		}
		return false;
	}
	
	/*public boolean reopenTicket(int ticketId) {
		
	}*/
	
	public void closeTicket(Ticket ticket) {
		ticket.setState(TicketState.CLOSED);
	}
	/**
	 * @param ticket
	 */
	private void sendSMS(Ticket ticket) {
		Workers worker = workerRepository.findById(ticket.getOwnerid());
		//User user = userRepo.findById(ticket.getUserId());
		User user = null;
		if(worker == null && user == null) {
			return;
		}
		String workerName = (worker !=null && worker.getName() !=null)?worker.getName():"";
		String msg = smsSender.generateMsg(ticket,workerName);
		String userPhone = (user !=null)?user.getPhone():null;
		String recipientNumbers=smsSender.setRecipients(ticket,worker.getPhone_number(),userPhone);
		if(recipientNumbers != null && !recipientNumbers.isEmpty())
		smsSender.sendSms(msg, recipientNumbers);
	}
	
	public void deleteTicket(int ticketId) {
		ticketRepo.delete(ticketId);
		ticketMapStore.remove(ticketId);
	}
	
	public List<Ticket> getAllTickets() {
		System.out.println("In getAllTickets service"+ticketMapStore);
		return ticketRepo.findAll();
		//return ticketMapStore.values().stream().collect(Collectors.toList());
	}

	public Ticket updateTicket(Ticket ticket) {
		ticketMapStore.put(ticket.getId(),ticket);
		//update feedback to actor if any
		ticketRepo.save(ticket);
		return ticketMapStore.get(ticket.getId());		
	}

	public  Map<TicketType, Set<Workers>> getAllActors() {
		return actorMap.getActorDetailsMap();
	}

}
