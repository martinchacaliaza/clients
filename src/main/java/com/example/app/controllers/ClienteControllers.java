package com.example.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.app.models.Bank;
import com.example.app.models.Client;

import com.example.app.models.TypeClient;
import com.example.app.service.ClienteService;
import com.example.app.service.TipoClienteService;
import com.sistema.app.exception.RequestException;

import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/api/Clientes")
@RestController
public class ClienteControllers {

	@Autowired
	private ClienteService clientService;

	@Autowired
	private TipoClienteService tipoClienteService;


	@ApiOperation(value = "LISTA TODOS LOS CLIENTES", notes = "")
	@GetMapping
	public Mono<ResponseEntity<Flux<Client>>> findAll() {
		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(clientService.findAllClientePersonal())

		);
	}

	@ApiOperation(value = "LISTA CLIENTE POR ID", notes = "")
	@GetMapping("/{id}")
	public Mono<ResponseEntity<Client>> viewId(@PathVariable String id) {
		return clientService.findByIdClientePersonal(id)
				.map(p -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@ApiOperation(value = "LISTA CLIENTE POR DNI", notes = "")
	@GetMapping("/dni/{dni}")
	public Mono<ResponseEntity<Client>> viewId2(@PathVariable String dni) {

		return clientService.viewDniCliente(dni)
				.map(p -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

	@ApiOperation(value = "ACTUALIZA CLIENTE POR ID", notes = "")
	@PutMapping
	public Mono<Client> updateClientePersonal(@RequestBody Client cliente) {
		System.out.println(cliente.toString());
		return clientService.saveClientePersonal(cliente);
	}

	@ApiOperation(value = "GUARDA CLIENTE VALIDANDO SI EL [TIPO CLIENTE] EXISTE", notes = "")
	@PostMapping
	public Mono<Client> guardarCliente(@RequestBody Client cli) {
		Mono<TypeClient> tipo = this.tipoClienteService.viewidTipoProducto(cli.getTipoCliente().getIdTipo());
		return tipo.defaultIfEmpty(new TypeClient()).flatMap(c -> {
			if (c.getIdTipo() == null) {
				
				Mono.error(new InterruptedException("El tipo de cliente no existe"));	
			
				//cli.setDni("11");
			}
			return Mono.just(c);
		}).flatMap(t -> {
			cli.setTipoCliente(t);
			
			return clientService.saveClientePersonal(cli);
		});
	}

	@ApiOperation(value = "ELIMINA CLIENTE POR ID", notes = "")
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> deleteBanco(@PathVariable String id) {
		return clientService.findByIdClientePersonal(id).flatMap(s -> {
			return clientService.deleteCliente(s).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
		}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NO_CONTENT));
	}

}
