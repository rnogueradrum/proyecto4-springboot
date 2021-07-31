package com.example.demo.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * Con Long, no se devuelve un Json, por ello lo envolvemos en 
 * una clase CountDTO para que al ser un objeto, ya se devuelva en formato Json
 *
 */

public class CountDTO {
	@ApiModelProperty("Long envuelto en objeto CountDTO para que se pueda devolver un JSON")
	private Long count;
	
	public CountDTO() {}
	
	public CountDTO(Long count) {
		
		this.count = count;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	
	

}
