package com.example.demo.dto;

import java.util.List;

import com.example.demo.domain.User;

import io.swagger.annotations.ApiModelProperty;



public class UserListDTO {
	@ApiModelProperty("List envuelto en objeto UserListDTO con getUser y setUser")
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
