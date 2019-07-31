package com.cads.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cads.db.models.Workers;
import com.cads.workers.WorkerTypes;

@Repository
public interface WorkerRepository extends JpaRepository<Workers,Integer> {	
	public Workers findById(int id);
	public List<Workers> findByType(WorkerTypes type);
}
