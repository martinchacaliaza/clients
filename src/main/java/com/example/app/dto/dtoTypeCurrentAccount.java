package com.example.app.dto;

import javax.validation.constraints.NotEmpty;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class dtoTypeCurrentAccount {
	@NotEmpty
	private String idTipo;
	@NotEmpty
	private String descripcion;
	
}
