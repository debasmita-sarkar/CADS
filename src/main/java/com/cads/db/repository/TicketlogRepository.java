package com.cads.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cads.db.models.TicketLog;;

public interface TicketlogRepository extends JpaRepository<TicketLog, Integer> {

}
