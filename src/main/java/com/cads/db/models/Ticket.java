package com.cads.db.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.cads.tickets.util.TicketState;
import com.cads.utils.TicketType;
import com.cads.workers.WorkerTypes;

@Entity
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id=0;
	@Column
	private String description=null;
	@Column
	private TicketType ticketType=null;
	@Column
	private WorkerTypes workerType=null;
	@Column
	private TicketState state=null;
	@Column
	private int workerId ;	
	@Column
	private int userId ;	
	@Column
	private String note = null;	
	@Column
	private String dueDate=null;	
	@Column
	boolean isRecurring=  false;
	@Column //decide if it is required as a column
	boolean isBlocked=false;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public TicketType getTicketType() {
		return ticketType;
	}
	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}
	public WorkerTypes getWorkerType() {
		return workerType;
	}
	public void setWorkerType(WorkerTypes workerType) {
		this.workerType = workerType;
	}
	public TicketState getState() {
		return state;
	}
	public void setState(TicketState state) {
		this.state = state;
	}
	public int getWorkerId() {
		return workerId;
	}
	public void setWorkerId(int workerId) {
		this.workerId = workerId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public boolean isRecurring() {
		return isRecurring;
	}
	public void setRecurring(boolean isRecurring) {
		this.isRecurring = isRecurring;
	}
	public boolean isBlocked() {
		return isBlocked;
	}
	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}
	
	
}
