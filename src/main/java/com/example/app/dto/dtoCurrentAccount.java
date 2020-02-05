package com.example.app.dto;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class dtoCurrentAccount {
	
	private String id;
	private String dni;
	private String numero_cuenta;
	private dtoTypeCurrentAccount tipoProducto;
	private String fecha_afiliacion;
	private String fecha_caducidad;
	private double saldo;

	
	//private tipoProducto tipoCliente;
}










