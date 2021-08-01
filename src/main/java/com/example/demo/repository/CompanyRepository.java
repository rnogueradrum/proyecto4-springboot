package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	List<Company> findByCompanyname(String companyname);
	
	List<Company> findByCif(String cif);
	
//	Boolean existsByCompanyname(String companyname);
//	
//	Boolean existsByCif(String cif);

}
