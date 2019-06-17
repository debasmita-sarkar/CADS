package com.cads.db.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VisitorParkingSlot {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id=0 ;
	@Column
	String slot = null;
	@Column
	int visitorId = -1;
	boolean isFree=false;
	
	public boolean isFree() {
		//If visitorId is not associated that means the parking slot is free.
		if(visitorId == -1) {
			this.isFree=true;
		}else {
			this.isFree=false;
		}
		return isFree;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(int visitorId) {
		this.visitorId = visitorId;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}	
	
}
