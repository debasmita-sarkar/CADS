package com.cads.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import com.cads.db.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
	
	public User findById(int id);
	
	public User findByEmail(String email);
	
	public List<User> findByFirstName(String firstName);
	
	public List<User> findByLastName(String lastName);

	public User findByEmailAndPassword(String email, String password);
	
}
