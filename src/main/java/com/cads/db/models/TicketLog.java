package com.cads.db.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cads.tickets.util.TicketState;

@Entity
@Table(name="ticketlog")
public class TicketLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id=0;

	@ManyToOne
	@JoinColumn(name="ticketid")
	private Ticket ticket;

	@Column
	private int previousid;

	@Column
	private int currentid;
	
	

	@Enumerated(EnumType.STRING)
	@Column(name="previousstate")
	private TicketState previousState;

	@Enumerated(EnumType.STRING)
	@Column(name="currentstate")
	private TicketState currentState;

	@Column
	private String comment;
	
	//Need to add the timestamp

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public int getPreviousid() {
		return previousid;
	}

	public void setPreviousid(int previousid) {
		this.previousid = previousid;
	}

	public int getCurrentid() {
		return currentid;
	}

	public void setCurrentid(int currentid) {
		this.currentid = currentid;
	}

	public TicketState getPreviousState() {
		return previousState;
	}

	public void setPreviousState(TicketState previousState) {
		this.previousState = previousState;
	}

	public TicketState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(TicketState currentState) {
		this.currentState = currentState;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
