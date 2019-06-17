package com.cads.db.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity
public class Payment {
	
	@Id
	private int id=0;
	
	@Column
	private String from;
	
	@Column
	private String to;
	
	@Column	
	private String purpose;
	
	@Column	
	private String BillId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getBillId() {
		return BillId;
	}

	public void setBillId(String billId) {
		BillId = billId;
	}
}
