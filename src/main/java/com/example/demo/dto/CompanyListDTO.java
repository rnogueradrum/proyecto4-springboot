package com.example.demo.dto;

import java.util.List;

import com.example.demo.domain.Company;
import io.swagger.annotations.ApiModelProperty;



public class CompanyListDTO {
	@ApiModelProperty("List envuelto en objeto UserListDTO con getUser y setUser")
	private List<Company> companies;

	
	
	public CompanyListDTO() {
		super();
	}

	
	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
	
	

}
