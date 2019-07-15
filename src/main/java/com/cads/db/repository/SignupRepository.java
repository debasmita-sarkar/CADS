package com.cads.db.repository;

import com.cads.db.models.Signup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignupRepository extends JpaRepository<Signup, Integer> {
    public Signup findBySignupId();
    public List<Signup> findByEmail();
}
