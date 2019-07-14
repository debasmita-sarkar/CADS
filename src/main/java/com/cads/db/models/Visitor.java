

package com.cads.db.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name="visitor")
public class Visitor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id=0;
	@Column
	private String name=null;
	@Column
	private String phone=null;
	@Column(name="flatid")
	private int flatId=0;	
	@Column(name="buildingid")
	private int buildingId=0;	
	@Column
	private String purpose=null;
	@Column(name="intime")
	private String timeIn=null;
	@Column(name="outtime")
	private String timeOut=null;
	@Column
	private int parking =-1;	
	@Transient
	private String flatnumber=null;
	@Transient
	private String buildingName=null;		
	
	@Override
	public String toString() {
		return "Visitor [id=" + id + ", name=" + name + ", phone=" + phone + ", flatId=" + flatId + ", buildingId="
				+ buildingId + ", purpose=" + purpose + ", timeIn=" + timeIn + ", timeOut=" + timeOut + ", parking="
				+ parking + ", flatnumber=" + flatnumber + ", buildingName=" + buildingName + "]";
	}

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

	public int getFlatId() {
		return flatId;
	}

	public void setFlatId(int i) {
		this.flatId = i;
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int
			buildingId) {
		this.buildingId = buildingId;
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


	public void setParking(int parking) {
		this.parking = parking;
	}


	public String getFlatnumber() {
		return flatnumber;
	}


	public void setFlatnumber(String flatnumber) {
		this.flatnumber = flatnumber;
	}


	public String getBuildingName() {
		return buildingName;
	}


	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	
}
