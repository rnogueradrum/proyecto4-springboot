package com.example.demo.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	
	List<User> findByUsername(String username);
	
	List<User> findByEmail(String email);
	
//	Boolean existsByUsername(String username);
//	
//	Boolean existsByEmail(String email);
	
}
