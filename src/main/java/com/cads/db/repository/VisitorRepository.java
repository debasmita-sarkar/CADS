package com.cads.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cads.db.models.Visitor;
import com.cads.db.models.VisitorParkingSlot;;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor,Integer> {
	
	public Visitor findById(int i);	
	public Visitor findByFlatID(int  flatId);
}

