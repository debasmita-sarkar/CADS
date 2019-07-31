package com.cads.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cads.db.models.Building;
import com.cads.db.models.Flat;
import com.cads.db.models.Ticket;
import com.cads.db.models.TicketLog;
import com.cads.db.models.User;
import com.cads.db.models.VisitorParkingSlot;
import com.cads.db.models.Workers;
import com.cads.db.repository.BuildingRepository;
import com.cads.db.repository.FlatRepository;
import com.cads.db.repository.TicketRepository;
import com.cads.db.repository.TicketlogRepository;
import com.cads.db.repository.UserRepository;
import com.cads.db.repository.WorkerRepository;
import com.cads.factory.TicketLogFactory;
import com.cads.tableHeaders.HeadersTemplate;
import com.cads.tickets.util.TicketState;
import com.cads.utils.SMSSender;
import com.cads.utils.TicketType;
import com.cads.utils.Utility;
import com.cads.workers.WorkerTypes;
import com.cads.workers.Workersmap;

@Service
public class TicketService {
	private static final String FINANCE = "Finance";

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
	
	@Autowired
	FlatService flatService;
	
	@Autowired
	WorkerRepository workerRepo;
	
	@Autowired
	BuildingService buildingService;
	
	@Autowired
	BuildingRepository buildingRepo;
	
	@Autowired
	FlatRepository flatRepo;
	
	private Map<Integer,Ticket> ticketMapStore = new HashMap<Integer,Ticket>();
	Map<String, Map<? super Object,String>> mapColToLookUps = new HashMap<String, Map<? super Object,String>>();
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
		int workerId = findWorkerId(ticket);
		assignTicket(ticket.getId(),workerId);
	}
	
	public boolean assignTicket(int ticketId, int workerid) {
		Ticket ticket = ticketRepo.findById(ticketId);
		if(ticket != null) {
			Workers workerObj = workerRepository.findById(workerid);
			int previousId = ticket.getOwnerid();
			TicketState previousState = ticket.getState();
			ticket.setWorkerType(workerObj.getType());
			ticket.setOwnerid(workerid);
			ticket.setState(TicketState.ASSIGNED);
			Ticket savedTicket = ticketRepo.save(ticket);
			TicketLog log = TicketLogFactory.getNewTicketLog(ticket);
			log.setCurrentid(workerid);
			log.setPreviousid(previousId);
			log.setCurrentState(TicketState.ASSIGNED);
			log.setPreviousState(previousState);
			logRepo.save(log);
			if(savedTicket != null)
				return true;
			else 
				return false;
		}else {
			return false;
		}
	}
	
	public boolean submitTicket(Ticket ticket, int workerId,String comment) {
		if(ticket.getWorkerType() != WorkerTypes.user) {
			ticket.setOwnerid(ticket.getSubmitterid());
			ticket.setState(TicketState.SUBMITTED);
			TicketLog log = TicketLogFactory.getNewTicketLog(ticket);
			log.setPreviousid(workerId);
			log.setCurrentid(ticket.getSubmitterid());
			ticket.setWorkerType(WorkerTypes.user);
			TicketState previousState = ticket.getState();
			log.setPreviousState(previousState);
			log.setCurrentState(TicketState.SUBMITTED);
			log.setComment(comment);
			logRepo.save(log);
			return true;
		}else {
			return false;
		}		
	}
	
	//rejection reason must be there
	public boolean rejectTicket(Ticket ticket, int workerId,String comment) {
		if(ticket.getWorkerType() != WorkerTypes.user) {
			ticket.setOwnerid(ticket.getSubmitterid());
			ticket.setState(TicketState.ASSIGNED);
			return true;
		}else{
			return false;
		}
	}
	
	/*public boolean reopenTicket(int ticketId) {
		
	}*/
	
	public void closeTicket(Ticket ticket) {
		ticket.setState(TicketState.CLOSED);
	}
	/**
	 * @param ticket
	 */
	
	private int findWorkerId(Ticket ticket) {
		//need to figure out an algo to get free workers and matching workers by type
		if(ticket.getTicketType().equals(TicketType.House_Work)){
			List<Workers> workerList = workerRepository.findByType(WorkerTypes.Electrician);
			return workerList.get(0).getId();
		}else {
			return -1;
		}
	}
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
	
	public String [] getAllWorkerTypes() {
		return  Arrays.toString(WorkerTypes.values()).replaceAll("^.|.$", "").split(", ");		
	}
	
	public String [] getAllTicketStates() {
		return  Arrays.toString(TicketState.values()).replaceAll("^.|.$", "").split(", ");		
	}
	
	public String [] getAllTicketTypes() {		
		return  Arrays.toString(TicketType.values()).replaceAll(", House_Work", "").replaceAll("^.|.$", "").split(", ");		
	}
	
    public List<HeadersTemplate> getAllColumnHeaders(String ticketcategory) {		
		return createColHeaderList(ticketcategory);
	}
    
	private List<HeadersTemplate> createColHeaderList(String ticketcategory) {
		Utility utility = new Utility();
		List<HeadersTemplate> headers = new ArrayList<HeadersTemplate>();
		HeadersTemplate col1 = new HeadersTemplate("Description","description");		
		HeadersTemplate col2 = new HeadersTemplate("Note","note");		
		HeadersTemplate col3 = new HeadersTemplate("Submitter","submitterid",utility.createLookUpMap(mapColToLookUps,"submitterid",flatService.getAllFlats().stream().collect(Collectors.toMap(Flat::getId,Flat::getNumber))));
		HeadersTemplate col4 = null;
		HeadersTemplate col5 = null;
		if(ticketcategory.equalsIgnoreCase(FINANCE)) {
		col4 = new HeadersTemplate("Owner","ownerid",utility.createLookUpMap(mapColToLookUps,"ownerid",flatService.getAllFlats().stream().collect(Collectors.toMap(Flat::getId,Flat::getNumber))));
		col5 = new HeadersTemplate("Type of Ticket","ticketType",utility.createLookUpMap(mapColToLookUps,"submitterid",utility.convertStringArrayToMap(getAllTicketTypes())));
		}else {
		col4 = new HeadersTemplate("Owner","ownerid",utility.createLookUpMap(mapColToLookUps,"ownerid",workerRepo.findAll().stream().collect(Collectors.toMap(Workers::getId,Workers::getNameAndPhoneCombo))));		
		col5 = new HeadersTemplate("Type of Job","workerType",utility.createLookUpMap(mapColToLookUps,"submitterid",utility.convertStringArrayToMap(getAllWorkerTypes())));
		}
		HeadersTemplate col6 = new HeadersTemplate("State","state",utility.createLookUpMap(mapColToLookUps,"submitterid",utility.convertStringArrayToMap(getAllTicketStates())));
		HeadersTemplate col7 = new HeadersTemplate("Is this ticket blocked?","isBlocked",utility.createLookUpMap(mapColToLookUps,"isBlocked",utility.convertStringArrayToMap(utility.generateStringArrayForBooleans())));
		//Recurring we will add later
		//HeadersTemplate col11 = new HeadersTemplate("State","state",utility.createLookUpMap(mapColToLookUps,"submitterid",utility.convertStringArrayToMap(utility.generateStringArrayForBooleans())));
		HeadersTemplate col8 = new HeadersTemplate("Due Date","dueDate");
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
