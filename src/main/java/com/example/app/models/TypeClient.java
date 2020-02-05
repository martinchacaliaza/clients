package com.example.app.models;

import javax.validation.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "TipoCliente")
public class TypeClient {

	
	@NotEmpty
	private String idTipo;
	@NotEmpty
	private String descripcion;
}
