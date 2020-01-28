package com.example.app.models;


import javax.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class Bank {
	
	private String id;
	private String codigo_banco;
	private String ruc;
	private String razon_social;
	private String direccion;
	private String telefono;
	
}










