

package com.cads.db.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Visitor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column
	private String name=null;
	@Column
	private String phone=null;
	@Column
	private String flatID=null;	
	@Column
	private String purpose=null;
	@Column(name="intime")
	private String timeIn=null;
	@Column(name="outtime")
	private String timeOut=null;
	@Column
	private int parking =-1;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFlatID() {
		return flatID;
	}
	public void setFlatID(String flatID) {
		this.flatID = flatID;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getTimeIn() {
		return timeIn;
	}
	public void setTimeIn(String timeIn) {
		this.timeIn = timeIn;
	}
	public String getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
	}
	public int getParking() {
		return parking;
	}
	public void setParking(int parkingSlotId) {
		this.parking = parkingSlotId;
	}
	
}
