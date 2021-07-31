package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.User;



public interface UserService {
	
	// Spring repository methods
			User save(User user);
			
			List<User> findAll();
			
			Optional<User> findById(Long id);
			
			Long count();
			
			void deleteById(Long id);
			
			void deleteAll();
			
			void deleteAll(List<User> users);
			
			// Custom methods
			
			List<User> findByUsername(String username);
			
			List<User> findByEmail(String email);

}
