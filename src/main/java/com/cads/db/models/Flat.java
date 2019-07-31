package com.cads.db.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="flat")
public class Flat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id=0;

	@Column(name="flatnumber")
	private String number;

	@Column
	private String buildingID;

	@Column
	private float area;

	@Column (name="bedrooms")
	int noOfBedRooms;
	
	@Column
	String floor;

	@Column(name="parking")
	String parkingSlots;
	
	@Column(name="cankidsgoout")
	boolean canKidsGoOut=  false;
	
	@Override
	public String toString() {
		return "Flat [id=" + id + ", number=" + number + ", buildingID=" + buildingID + ", area=" + area
				+ ", noOfBedRooms=" + noOfBedRooms + ", floor=" + floor + ", parkingSlots=" + parkingSlots
				+ ", canKidsGoOut=" + canKidsGoOut + "]";
	}

	public boolean isCanKidsGoOut() {
		return canKidsGoOut;
	}

	public void setCanKidsGoOut(boolean canKidsGoOut) {
		this.canKidsGoOut = canKidsGoOut;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getBuildingID() {
		return buildingID;
	}

	public void setBuildingID(String buildingID) {
		this.buildingID = buildingID;
	}

	public float getArea() {
		return area;
	}

	public void setArea(float area) {
		this.area = area;
	}

	
	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getParkingSlots() {
		return parkingSlots;
	}

	public void setParkingSlots(String parkingSlots) {
		this.parkingSlots = parkingSlots;
	}

	public int getNoOfBedRooms() {
		return noOfBedRooms;
	}

	public void setNoOfBedRooms(int noOfBedRooms) {
		this.noOfBedRooms = noOfBedRooms;
	}

}
