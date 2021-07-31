package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="app_users")
public class User {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty("Clave primaria id Long autoincremental")
	private Long id;
	@ApiModelProperty("Nombre en formato texto")
	private String username;
	@ApiModelProperty("Email en formato texto")
	private String email;
	@ApiModelProperty("Password en formato texto")
	private String password;
	@ApiModelProperty("CIF en formato texto")
	private String cif;
	@ApiModelProperty("Dirección en formato texto")
	private String address;
	
	
	public User() {}

	
	

	public User(String username, String email, String password, String cif, String address) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.cif = cif;
		this.address = address;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getCif() {
		return cif;
	}




	public void setCif(String cif) {
		this.cif = cif;
	}




	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", cif="
				+ cif + ", address=" + address + "]";
	}
	
	
	

}


	