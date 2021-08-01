package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.Company;



public interface CompanyService {
	
	// Spring repository methods
	Company save(Company  company);
				
	List<Company> findAll();
				
	Optional<Company> findById(Long id);
				
	Long count();
			
	void deleteById(Long id);
				
	void deleteAll();
				
	void deleteAll(List<Company> companies);
				
	// Custom methods
				
	List<Company> findByCompanyname(String companyname);
	
	List<Company> findByCif(String cif);

	Optional<Company> processTurnover(Long id);


}
