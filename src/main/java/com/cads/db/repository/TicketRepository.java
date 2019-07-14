package com.cads.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cads.db.models.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>{
	public Ticket findById(int id);
	
	/*@Query(value = "select * from ticket", nativeQuery = true)
	public List<Ticket> findAll();*/

}
