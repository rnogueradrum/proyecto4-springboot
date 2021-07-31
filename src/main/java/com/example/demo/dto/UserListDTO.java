package com.example.demo.dto;

import java.util.List;

import com.example.demo.domain.User;



public class UserListDTO {
	
	private List<User> users;

	
	
	public UserListDTO() {
		super();
	}

	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	

}
