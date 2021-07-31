package com.example.demo.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;

import com.example.demo.dto.CountDTO;
import com.example.demo.dto.UserListDTO;
import com.example.demo.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RequestMapping("/api") 
@RestController
public class UserController {
	
	private final Logger log = LoggerFactory.getLogger(UserController.class);
	
	/**
	 * 
	 * Inyección por constructor del Servicio
	 * 
	 */
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	
	/**
	 * Long count()
	 * @return Número de users
	 */
	@GetMapping("/users/count")
	public ResponseEntity<CountDTO> count(){ //Con Long, no devuelve un Json, lo envolvemos en una clase CountDTO
		log.info("REST request recover users number");
		return ResponseEntity.ok(new CountDTO(this.userService.count()));
		
	}
	
	/**
	 * 
	 * findById
	 * http://localhost:8080/api/users/{id}
	 * 
	 * @return a response entity with user
	 */
	@GetMapping("/users/{id}")
	public ResponseEntity<User> findById(@ApiParam("Busca un user por su id")@PathVariable("id") Long id) {
		log.info("REST request to find one user");
		
		// Optional envuelve un resultado q puede ser un objeto o valor nulo
		Optional<User> userOpt = this.userService.findById(id);
		
		if (userOpt.isPresent()) // Comprueba si existe un objeto de tipo User
			return ResponseEntity.ok(userOpt.get()); // Con get sacamos el objeto del envoltorio
		return ResponseEntity.notFound().build();

	}
	
	/**
	 * 
	 * findAll
	 * http://localhost:8080/api/users
	 * 
	 * @return a List with all users
	 */
	@GetMapping("/users")
	public List<User> findAll() {
		
		log.info("Executing UserController method from logger");
		return this.userService.findAll();
	}
	
	/**
	 * 
	 * createOne/save
	 * http://localhost:8080/api/users
	 * 
	 * @return a response entity with user
	 */
	@PostMapping("/users")
	public ResponseEntity<User> createOne(@RequestBody User user) {
		log.info("REST request to create a new user");
			
		if (user.getId() != null ) { // Ya existe
			log.warn("Trying to create a new user with existent id");
			return ResponseEntity.badRequest().build();
		}
		
		
		return ResponseEntity.ok(this.userService.save(user));
	}
	
	/**
	 * 
	 * updateOne
	 * http://localhost:8080/api/users
	 * 
	 * @return a response entity with user
	 */
	@PutMapping("/users")
	public ResponseEntity<User> updateOne(@RequestBody User user) {
		log.info("REST request to update a new user");
			
		if (user.getId() == null ) { // No existe el usuario
			log.warn("Trying to update a non existent user ");
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(this.userService.save(user));
	}
	
	/**
	 * 
	 * deleteById
	 * http://localhost:8080/api/users/1L
	 * 
	 * @return a response entity with user
	 */
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
		log.info("REST request to delete one user");
		this.userService.deleteById(id);
        return ResponseEntity.noContent().build();
    	
	}
	
	/**
	 * 
	 * deleteAll
	 * http://localhost:8080/api/users/
	 * 
	 * @return a response entity with all users deleted
	 */
	@DeleteMapping("/users")
	public ResponseEntity<User> deleteAll() {
		log.info("REST request to delete all users");
		this.userService.deleteAll();
        return ResponseEntity.noContent().build();
    	
	}
	
	/**
	 * 
	 * Se puede utilizar @PostMapping
	 * deleteMany
	 * @Param Lista de users (ids sólo)
	 * http://localhost:8080/api/users/
	 * 
	 * @return a response entity with all listed users deleted
	 */
	@DeleteMapping("/users/deletemany")
	public ResponseEntity<User> deleteMany(@RequestBody UserListDTO userListDto) {
		log.info("REST request to delete a list of users");
		
		this.userService.deleteAll(userListDto.getUsers());
        
		return ResponseEntity.noContent().build();
    	
	}
	
	
/* ============== CUSTOM CRUD METHODS ============= */
	
	/**
	 * 
	 * findByUsername
	 * http://localhost:8080/api/users/usernames
	 * 
	 * @return a users list
	 */

	@GetMapping("/users/usernames/{username}")
	@ApiOperation("Busca users por username")
	public List<User> findByUsername(@PathVariable("username") String username) {
		log.info("REST request to find users by username");
		
		return this.userService.findByUsername(username);   	
	}
	
	/**
	 * 
	 * findByEmail
	 * http://localhost:8080/api/users/emails
	 * 
	 * @return a users list
	 */
	@GetMapping("/users/emails/{email}")
	@ApiOperation("Busca users por email")
	public List<User> findByEmail(@PathVariable String email) {
		log.info("REST request to find users by email");
		
		return this.userService.findByEmail(email);   	
	}
		
	
}
