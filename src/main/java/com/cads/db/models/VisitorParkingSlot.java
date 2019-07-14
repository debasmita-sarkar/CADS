package com.cads.db.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="parking")
public class VisitorParkingSlot {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id=0 ;
	@Column
	String slot = null;
	@Column
	int visitorid = -1;
	@Transient
	boolean isFree=false;

	public boolean isFree() {
		//If visitorId is not associated that means the parking slot is free.
		if(visitorid == -1) {
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
		return visitorid;
	}

	public void setVisitorId(int visitorId) {
		this.visitorid = visitorId;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}	

}
