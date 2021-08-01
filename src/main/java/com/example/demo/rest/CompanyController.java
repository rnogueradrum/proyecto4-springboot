package com.example.demo.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Company;
import com.example.demo.dto.CompanyListDTO;
import com.example.demo.dto.CountDTO;
import com.example.demo.service.CompanyService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RequestMapping("/api") 
@RestController
public class CompanyController {
	
private final Logger log = LoggerFactory.getLogger(CompanyController.class);
	
	/**
	 * 
	 * Inyección por constructor del Servicio
	 * 
	 */
	private final CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	
	/**
	 * Long count()
	 * @return Número de companies
	 */
	@GetMapping("/companies/count")
	@ApiOperation("Devuelve el número de companies")
	public ResponseEntity<CountDTO> count(){ //Con Long, no devuelve un Json, lo envolvemos en una clase CountDTO")
		log.info("REST request recover companies number");
		return ResponseEntity.ok(new CountDTO(this.companyService.count()));
		
	}
	
	/**
	 * 
	 * findById
	 * http://localhost:8080/api/companies/{id}
	 * 
	 * @return a response entity with company
	 */
	@GetMapping("/companies/{id}")
	@ApiOperation("Busca un user por su id")
	public ResponseEntity<Company> findById(@ApiParam("Busca una company por su id")@PathVariable("id") Long id) {
		log.info("REST request to find one user");
		
		// Optional envuelve un resultado q puede ser un objeto o valor nulo
		Optional<Company> companyOpt = this.companyService.findById(id);
		
		if (companyOpt.isPresent()) // Comprueba si existe un objeto de tipo Company
			return ResponseEntity.ok(companyOpt.get()); // Con get sacamos el objeto del envoltorio
		return ResponseEntity.notFound().build();

	}
	
	/**
	 * 
	 * findAll
	 * http://localhost:8080/api/companies
	 * 
	 * @return a List with all companies
	 */
	@GetMapping("/companies")
	@ApiOperation("Busca todas las companies")
	public List<Company> findAll() {
		
		log.info("Executing CompanyController method from logger");
		return this.companyService.findAll();
	}
	
	/**
	 * 
	 * createOne/save
	 * http://localhost:8080/api/users
	 * 
	 * @return a response entity with user
	 * @throws URISyntaxException 
	 */
	@PostMapping("/companies")
	@ApiOperation("Crea una company")
	public ResponseEntity<Company> createOne(@RequestBody Company company) throws URISyntaxException {
		log.info("REST request to create a new user");
			
		if (company.getId() != null ) { // Ya existe
			log.warn("Trying to create a new company with existent id {}", company.getId());
			return ResponseEntity.badRequest().build();
		}
		
		Company companyDB = companyService.save(company);
		return ResponseEntity
				.created(new URI("/api/companies/" + companyDB.getId()))
				.body(companyDB);
		//return ResponseEntity.ok(this.companyService.save(company));
	}
	
	/**
	 * 
	 * updateOne
	 * http://localhost:8080/api/companies
	 * 
	 * @return a response entity with company
	 */
	@PutMapping("/companies")
	@ApiOperation("Actualiza una company")
	public ResponseEntity<Company> updateOne(@RequestBody Company company) {
		log.info("REST request to update a new company");
			
		if (company.getId() == null ) { // No existe la company
			log.warn("Trying to update a non existent company ");
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(this.companyService.save(company));
	}
	
	/**
	 * 
	 * deleteById
	 * http://localhost:8080/api/companies/1L
	 * 
	 * @return a response entity with company
	 */
	@DeleteMapping("/companies/{id}")
	@ApiOperation("Borra una company por su id")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
		log.info("REST request to delete one company by id {}", id);
		this.companyService.deleteById(id);
        return ResponseEntity.noContent().build();
    	
	}
	
	/**
	 * 
	 * deleteAll
	 * http://localhost:8080/api/companies/
	 * 
	 * @return a response entity with all companies deleted
	 */
	@DeleteMapping("/companies")
	@ApiOperation("Borra todas las companies")
	public ResponseEntity<Company> deleteAll() {
		log.info("REST request to delete all companies");
		this.companyService.deleteAll();
        return ResponseEntity.noContent().build();
    	
	}
	
	/**
	 * 
	 * Se puede utilizar @PostMapping
	 * deleteMany
	 * @Param Lista de companies (ids sólo)
	 * http://localhost:8080/api/companies/
	 * 
	 * @return a response entity with all listed companies deleted
	 */
	@DeleteMapping("/companies/deletemany")
	@ApiOperation("Borra las companies pasadas en una lista")
	public ResponseEntity<Company> deleteMany(@RequestBody CompanyListDTO companyListDto) {
		log.info("REST request to delete a list of users");
		
		this.companyService.deleteAll(companyListDto.getCompanies());
        
		return ResponseEntity.noContent().build();
    	
	}
	
/* ============== CUSTOM CRUD METHODS ============= */
	
	/**
	 * 
	 * findByCompanyname
	 * http://localhost:8080/api/companies/companiesnames
	 * 
	 * @return a companies list
	 */

	@GetMapping("/companies/companiesnames/{companyname}")
	@ApiOperation("Busca companies por companyname")
	public List<Company> findByCompanyname(@PathVariable("companyname") String companyname) {
		log.info("REST request to find companies by companyname");
		
		return this.companyService.findByCompanyname(companyname);   	
	}
	
	/**
	 * 
	 * findByCif
	 * http://localhost:8080/api/companies/cif
	 * 
	 * @return a companies list
	 */
	@GetMapping("/companies/cifs/{cif}")
	@ApiOperation("Busca companies por cif")
	public List<Company> findByCif(@PathVariable String cif) {
		log.info("REST request to find companies by cif");
		
		return this.companyService.findByCif(cif);   	
	}
	
	
	
    /**
     * 
     * CALCULAR FACTURACIÓN
     * 
     *   1 Recibe el id de la empresa sobre el que calcular la facturación
     *   2 Calcula facturación
     *   3 Persiste la empresa con la nueva facturación en base de datos
     *   4 Devuelve la empresa con la facturación calculada
     * 
     */
			
    @GetMapping("/companies/calculate-turnover/{id}")
    public ResponseEntity<Company> calculateTurnover(@PathVariable Long id){
        return companyService.processTurnover(id)
        		.map(company -> ResponseEntity.ok().body(company)                )
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
