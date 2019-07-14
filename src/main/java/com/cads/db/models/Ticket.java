package com.cads.db.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cads.tickets.util.TicketState;
import com.cads.utils.TicketType;
import com.cads.workers.WorkerTypes;

@Entity
@Table(name="ticket")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id=0;
	
	@Column
	private String description=null;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tickettype")
	private TicketType ticketType=null;
	
	@Enumerated(EnumType.STRING)
	@Column(name="workertype")
	private WorkerTypes workerType=null;
	
	@Enumerated(EnumType.STRING)
	private TicketState state=null;	
	@Column
	private int submitterid ;
	@Column
	private int ownerid ;
	
	@Column
	private String note = null;	
	
	@Column(name="duedate")
	private String dueDate=null;
	
	@Column(name="isrecurring")
	boolean isRecurring=  false;
	@Transient //decide if it is required as a column
	boolean isBlocked=false;
	
	@Transient
	public List<TicketLog> logList = new ArrayList<TicketLog>();
	
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
	
	public int getSubmitterid() {
		return submitterid;
	}
	public void setSubmitterid(int submitterid) {
		this.submitterid = submitterid;
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
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", description=" + description + ", ticketType=" + ticketType + ", workerType="
				+ workerType + ", state=" + state + ", submitterid=" + submitterid + ", ownerid=" + ownerid + ", note="
				+ note + ", dueDate=" + dueDate + ", isRecurring=" + isRecurring + ", isBlocked=" + isBlocked + "]";
	}
	public boolean isBlocked() {
		return isBlocked;
	}
	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}
	
	public int getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(int ownerid) {
		this.ownerid = ownerid;
	}
	
	
	public void addLog(TicketLog log) {
		logList.add(log);
	}
	
	public List<TicketLog> getAllLogs(){
		return logList;
	}
	
	//pass 1 to get the latest, 2 to get the last but one, so on
	public TicketLog getLogByPosition(int position) {
		int previousPosition = logList.size()-position;
		return logList.get(previousPosition);
	}
	
}
