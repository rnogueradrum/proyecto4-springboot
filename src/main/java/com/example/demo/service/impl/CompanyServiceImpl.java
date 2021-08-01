package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.demo.domain.Company;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.rest.CompanyController;
import com.example.demo.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	private final Logger log = LoggerFactory.getLogger(CompanyController.class);
	
	private final CompanyRepository companyRepository;

	/**
	 * 
	 * Inyección por constructor del Servicio
	 * 
	 */
	public CompanyServiceImpl(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}
	
	/**
	 * 
	 * save
	 * 
	 * @return a saved company
	 */
	@Override
	public Company save(Company company) {
		log.info("REST request to create a new company");
		if (company == null) {
			log.warn("Trying to create null company");
			return null;
		}
		Company companyDB = this.companyRepository.save(company);
		return companyDB;
	}

	/**
	 * 
	 * findAll
	 * 
	 * @return an companies list
	 */
	@Override
	public List<Company> findAll() {
		log.info("Executing findAll companies");
		return this.companyRepository.findAll();
	}

	/**
	 * 
	 * findById
	 * 
	 * @return an Optional with a company on it
	 */
	@Override
	public Optional<Company> findById(Long id) {
		log.info("Executing findById");
		return this.companyRepository.findById(id);
	}

	@Override
	public Long count() {
		log.info("Get Total number of companies");
		return this.companyRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		log.info("Deleting company by id");
		if (id == null || id < 0 || id == 0)
			return;
		
		this.companyRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		log.info("Deleting all companies");
		this.companyRepository.deleteAll();
	}

	/**
	 * 
	 * deleteAll
	 * 
	 * @param list of companies
	 * 
	 */
	@Override
	public void deleteAll(List<Company> companies) {
		if (CollectionUtils.isEmpty(companies)) {
			log.warn("Trying to delete an invalid companies list");
			return;
		}
		
		this.companyRepository.deleteAll(companies);
		
	}
	
	/**
	 * 
	 * findByCompanyname
	 * 
	 * @return an companies list
	 */
	@Override
	public List<Company> findByCompanyname(String companyname) {
		if(companyname.isBlank() || companyname.isEmpty()) {
			log.warn("Trying to search an invalid companyname");
			return new ArrayList<>();
		}
		return this.companyRepository.findByCompanyname(companyname);
	}
	
	/**
	 * 
	 * findByCif
	 * 
	 * @return an companies list
	 */
	@Override
	public List<Company> findByCif(String cif) {
		if(cif.isBlank() || cif.isEmpty()) {
			log.warn("Trying to search an invalid cif");
			return new ArrayList<>();
		}
		return this.companyRepository.findByCif(cif);
	}

	
	/**
     * Turnover process
     * @param company
     */
	@Override
    public Optional<Company> processTurnover(Long id) {
        log.info("Request to process turnover for company with id {}", id);
        Optional<Company> companyOptional = this.findById(id);

        if(companyOptional.isEmpty() || companyOptional.get().getSeniority() == null)
            return companyOptional;

        Company company = companyOptional.get();

        computeTurnover(company);

        companyRepository.save(company);

        return Optional.of(company);
    }

    /**
     * compute Turnover
     * @param company
     */
    private void computeTurnover(Company company) {
        company.setTurnover(company.getArticlesnumber() 
        					* company.getEmployeesnumber() 
        					* company.getSeniority());
    }
}
