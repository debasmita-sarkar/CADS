package com.cads.tickets.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.cads.acess.AccessRights;
import com.cads.utils.TicketType;

@Component
public class TicketTypeVsAccessRightsMapping {
	
	Map<TicketType,Set<AccessRights>> ticketTypeVsAccessRights = new HashMap<TicketType,Set<AccessRights>>();
	
	public Map<TicketType, Set<AccessRights>> getTicketTypeVsUserGroup() {
		return ticketTypeVsAccessRights;
	}

	public void addToTicketTypeVsAccessRights(TicketType type, Set<AccessRights> access) {
		ticketTypeVsAccessRights.put(type, access);
		
	}
	
	public void deleteTypeFromTicketTypeVsAccessRights(TicketType type) {
		ticketTypeVsAccessRights.remove(type);
		
	}
	
	public void deleteGroupFromTicketTypeVsAccessRights(TicketType type,AccessRights access) {
		if(access == null) {
			return;
		}
		Set<AccessRights> rights = ticketTypeVsAccessRights.get(type);		
		rights.remove(access);
		ticketTypeVsAccessRights.put(type, rights);
		
	}
	
	@PostConstruct
	public void populateMap() {
		Set<AccessRights> rights = new HashSet<AccessRights>();
		rights.add(AccessRights.Create);
		ticketTypeVsAccessRights.put(TicketType.maintenance, rights);
		ticketTypeVsAccessRights.put(TicketType.casual_expense, rights);
		ticketTypeVsAccessRights.put(TicketType.salary_deposit, rights);
		rights.add(AccessRights.Update);
		ticketTypeVsAccessRights.put(TicketType.House_Work, rights  );
		
	}

}
