package com.cads.db.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.cads.workers.WorkerTypes;

@Entity
public class Workers {

	public Workers(String name, String phone_number, String address, String agent,WorkerTypes type) {
		super();		
		this.name = name;
		this.phone_number = phone_number;
		this.address = address;		
		this.agentID = agent;
		this.type= type;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	String id =null;
	@Column
	String name =null;
	@Column
	String userGroup =null;
	@Column
	String phone_number =null;
	@Column
	String address =null;
	@Column
	WorkerTypes type = null;
	@Column
	String agentID = "None";
	@Column
	String feedback = null;
	@Column
	String rating = "None";
	
	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getName() {
		return name;
	}	

	public String getPhone_number() {
		return phone_number;
	}	

	public String getAddress() {
		return address;
	}	
	public WorkerTypes getType() {
		return type;
	}	
	public String getAgent() {
		return agentID;
	}
	public void setAgent(String agent) {
		this.agentID = agent;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAgentID() {
		return agentID;
	}

	public void setAgentID(String agentID) {
		this.agentID = agentID;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public void setType(WorkerTypes type) {
		this.type = type;
	}

	public String getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}

}
