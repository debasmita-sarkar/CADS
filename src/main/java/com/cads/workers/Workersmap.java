package com.cads.workers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import com.cads.db.models.Workers;
import com.cads.utils.TicketType;

@Component
public class Workersmap {

	Map<TicketType,Set<Workers>> actorDetailsMap = new HashMap<TicketType,Set<Workers>>();

	public Map<TicketType, Set<Workers>> getActorDetailsMap() {
		return actorDetailsMap;
	}

	public void updateActorDetailsMap(TicketType type, Workers actor) {		
		deleteActorDetailsMap(type,actor);
		addActorDetailsMap(type,actor);		
	}

	public void addActorDetailsMap(TicketType type, Workers actor) {		
		if(actorDetailsMap.get(type)!=null) {
			actorDetailsMap.get(type).add(actor);
		}else {
			Set<Workers> actorList =new HashSet<Workers>();
			actorList.add(actor);
			actorDetailsMap.put(type, actorList);
		}

	}

	public void deleteActorDetailsMap(TicketType type, Workers actor) {		
		if(actorDetailsMap.get(type)!=null) {
			actorDetailsMap.get(type).remove(actor);
		}
	}

	@PostConstruct
	public void populateActors(){
		//read from db

		//remove this code after adding from db	
		Set<Workers> plumberList =new HashSet<Workers>();
		plumberList.add(new Workers("Plumber-1","9886752345","Bangalore",null,WorkerTypes.Plumber));
		
		Set<Workers> carpenterList =new HashSet<Workers>();
		carpenterList.add(new Workers("Carpenter-1","9886752345","Bangalore",null,WorkerTypes.Carpenter));
		
		actorDetailsMap.put(TicketType.House_Work,plumberList );
		actorDetailsMap.put(TicketType.House_Work, carpenterList);

	}

}
