package com.example.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.app.models.TypeClient;
import com.example.app.service.TipoClienteService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/api/TipoCliente")
@RestController
public class TipoClienteControllers {

	
	@Autowired
	private TipoClienteService tipoClienteService;
	
	
	
	
	@GetMapping
	public Mono<ResponseEntity<Flux<TypeClient>>> findAll() 
	{
		return Mono.just(
				ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(tipoClienteService.findAllTipoCliente())
				);
	}
	
	
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<TypeClient>> viewId(@PathVariable String id){
		return tipoClienteService.findByIdTipoCliente(id).map(p-> ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());	
	}
	
	@PutMapping
	public Mono<TypeClient> updateClientePersonal(@RequestBody TypeClient tipoCliente)
	{
		//System.out.println(cliente.toString());
		return tipoClienteService.saveTipoCliente(tipoCliente);
	}	
	
	/*@PostMapping
	public Mono<tipoCliente> saveClientePersonal(@RequestBody tipoCliente tipoCliente)
	{
		return tipoClienteService.saveTipoCliente(tipoCliente);
	}	*/


	
}
