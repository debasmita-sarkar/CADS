package com.cads.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cads.db.models.Flat;


@Repository
public interface FlatRepository extends JpaRepository<Flat,Integer> {
	
	public Flat findById(int i);	
	public Flat findByNumber(String number);
}
