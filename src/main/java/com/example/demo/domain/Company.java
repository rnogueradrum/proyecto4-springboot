package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="companies")
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty("Clave primaria id Long autoincremental")
	private Long id;
	@ApiModelProperty("Nombre en formato texto")
	private String companyname;
	@ApiModelProperty("CIF en formato texto")
	private String cif;
	@ApiModelProperty("Número de empleados en formato double")
	private Double employeesnumber;
	@ApiModelProperty("Número de artículos en formato double")
	private Double articlesnumber;
	@ApiModelProperty("AntigUedad de la empresa")
	private Integer seniority;
	@ApiModelProperty("Facturación calculada de la empresa")
	private Double turnover;
	
	public Company() {
		
	}


	public Company(Long id, String companyname, String cif, Double employeesnumber, Double articlesnumber,
			Integer seniority, Double turnover) {
		super();
		this.id = id;
		this.companyname = companyname;
		this.cif = cif;
		this.employeesnumber = employeesnumber;
		this.articlesnumber = articlesnumber;
		this.seniority = seniority;
		this.turnover = turnover;
	}





	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCompanyname() {
		return companyname;
	}


	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}


	public String getCif() {
		return cif;
	}


	public void setCif(String cif) {
		this.cif = cif;
	}


	public Double getEmployeesnumber() {
		return employeesnumber;
	}


	public void setEmployeesnumber(Double employeesnumber) {
		this.employeesnumber = employeesnumber;
	}


	public Double getArticlesnumber() {
		return articlesnumber;
	}


	public void setArticlesnumber(Double articlesnumber) {
		this.articlesnumber = articlesnumber;
	}


	public Integer getSeniority() {
		return seniority;
	}


	public void setSeniority(Integer seniority) {
		this.seniority = seniority;
	}
	
	


	public Double getTurnover() {
		return turnover;
	}


	public void setTurnover(Double turnover) {
		this.turnover = turnover;
	}


	@Override
	public String toString() {
		return "Company [id=" + id + ", companyname=" + companyname + ", cif=" + cif + ", employeesnumber="
				+ employeesnumber + ", articlesnumber=" + articlesnumber + ", seniority=" + seniority + ", turnover="
				+ turnover + "]";
	}


	
	
	
	
	

}
