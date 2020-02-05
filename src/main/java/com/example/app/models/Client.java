package com.example.app.models;


import javax.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Document(collection ="Clientes")
public class Client {
	
	@Id
	@NotEmpty
	private String id;
	@NotEmpty
	private String dni;
	@NotEmpty
	private String nombres;
	@NotEmpty
	private String apellidos;
	@NotEmpty
	private String sexo;
	@NotEmpty
	private String telefono;
	@NotEmpty
	private String edad;
	@NotEmpty
	private String correo;
	@NotEmpty
	private TypeClient tipoCliente;
	@NotEmpty
	private String codigoBancario;
	
}










