package com.cads.factory;

import com.cads.db.models.Ticket;
import com.cads.db.models.TicketLog;


public class TicketLogFactory {
	
	public static TicketLog getNewTicketLog(Ticket ticket) {
		TicketLog logObject  = new TicketLog();
		logObject.setTicket(ticket);
		logObject.setCurrentid(ticket.getSubmitterid());//owner id?
		logObject.setCurrentState(ticket.getState());
		ticket.addLog(logObject);
		return logObject;
	}

}
