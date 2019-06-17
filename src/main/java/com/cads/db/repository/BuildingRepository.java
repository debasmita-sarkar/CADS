package com.cads.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cads.db.models.Building;

@Repository
public interface BuildingRepository extends JpaRepository<Building,Integer> {
	
	public Building findById(int i);	
	public Building findByName(String name);
}
