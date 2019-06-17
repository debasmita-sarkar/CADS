package com.cads.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cads.db.models.VisitorParkingSlot;;

@Repository
public interface ParkingSlotRepository extends JpaRepository<VisitorParkingSlot,Integer> {
	
	public VisitorParkingSlot findById(int i);	
	public VisitorParkingSlot findBySlot(String slot);
}
