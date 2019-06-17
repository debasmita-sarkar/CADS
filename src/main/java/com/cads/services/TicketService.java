package com.cads.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cads.db.models.Ticket;
import com.cads.db.models.User;
import com.cads.db.models.Workers;
import com.cads.db.repository.TicketRepository;
import com.cads.db.repository.UserRepository;
import com.cads.db.repository.WorkerRepository;
import com.cads.utils.SMSSender;
import com.cads.utils.TicketType;
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
	TicketRepository ticketRepo;
	
	private Map<Integer,Ticket> ticketMapStore = new HashMap<Integer,Ticket>();	
	SMSSender smsSender = new SMSSender();

	public Ticket getTicketById(int ticketId) {		
		return ticketMapStore.get(ticketId);
	}
	
	public void createTicket(Ticket ticket) {
		
		//Store the ticket in cache
		ticketMapStore.put(ticket.getId(),ticket);
		ticketRepo.save(ticket);
		//Send sms
		sendSMS(ticket);
		//Add logic of sending mail
	}
	/**
	 * @param ticket
	 */
	private void sendSMS(Ticket ticket) {
		Workers worker = workerRepository.findById(ticket.getWorkerId());
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
		return ticketMapStore.values().stream().collect(Collectors.toList());
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
