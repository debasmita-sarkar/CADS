package com.cads.db.repository;

import com.cads.db.models.Signup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SignupRepository extends JpaRepository<Signup, Integer> {
    public Signup findBySignupId(String signupId);
    public List<Signup> findByEmail(String email);
}
