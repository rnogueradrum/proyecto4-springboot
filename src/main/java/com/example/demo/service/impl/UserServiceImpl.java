package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.rest.UserController;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private final Logger log = LoggerFactory.getLogger(UserController.class);
	
	private final UserRepository userRepository;

	/**
	 * 
	 * Inyección por constructor del Servicio
	 * 
	 */
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	/**
	 * 
	 * save
	 * 
	 * @return a saved user
	 */
	@Override
	public User save(User user) {
		log.info("REST request to create a new user");
		if (user == null) {
			log.warn("Trying to create null user");
			return null;
		}
		User userDB = this.userRepository.save(user);
		return userDB;
	}
	
	/**
	 * 
	 * findAll
	 * 
	 * @return an users list
	 */
	@Override
	public List<User> findAll() {
		log.info("Executing findAll users");
		return this.userRepository.findAll();
	}
	
	/**
	 * 
	 * findById
	 * 
	 * @return an Optional with an user on it
	 */
	@Override
	public Optional<User> findById(Long id) {
		log.info("Executing findById");
		return this.userRepository.findById(id);
	}

	@Override
	public Long count() {
		log.info("Get Total number of users");
		return this.userRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		log.info("Deleting user by id");
		if (id == null || id < 0 || id == 0)
			return;
		
		this.userRepository.deleteById(id);
		
	}

	@Override
	public void deleteAll() {
		log.info("Deleting all users");
		this.userRepository.deleteAll();
	}
	
	/**
	 * 
	 * deleteAll
	 * 
	 * @param list of users
	 * 
	 */
	@Override
	public void deleteAll(List<User> users) {
		if (CollectionUtils.isEmpty(users)) {
			log.warn("Trying to delete an invalid users list");
			return;
		}
		
		this.userRepository.deleteAll(users);
		
	}

	/**
	 * 
	 * findByUsername
	 * 
	 * @return an users list
	 */
	@Override
	public List<User> findByUsername(String username) {
		if(username.isBlank() || username.isEmpty()) {
			log.warn("Trying to search an invalid username");
			return new ArrayList<>();
		}
		return this.userRepository.findByUsername(username);
	}

	/**
	 * 
	 * findByEmail
	 * 
	 * @return an users list
	 */
	@Override
	public List<User> findByEmail(String email) {
		if(email.isBlank() || email.isEmpty()) {
			log.warn("Trying to search an invalid email");
			return new ArrayList<>();
		}
		return this.userRepository.findByEmail(email);
	}
	
	

}
