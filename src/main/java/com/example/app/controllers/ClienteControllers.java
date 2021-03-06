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
import com.example.app.models.Client;
import com.example.app.service.ClienteService;

import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/api/Clientes")
@RestController
public class ClienteControllers {

	@Autowired
	private ClienteService clientService;



	@ApiOperation(value = "LISTA TODOS LOS CLIENTES", notes = "")
	@GetMapping
	public Mono<ResponseEntity<Flux<Client>>> findAll() {
		return Mono.just(ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(clientService.findAllCliente())

		);
	}
	@ApiOperation(value = "LISTA CLIENTE POR ID", notes = "")
	@GetMapping("/{id}")
	public Mono<ResponseEntity<Client>> viewId(@PathVariable String id) {
		return clientService.findByIdClientePersonal(id)
				.map(p -> ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@ApiOperation(value = "LISTA CLIENTE POR DNI", notes = "")
	@GetMapping("/dni/{dni}")
	public Mono<ResponseEntity<Client>> viewId2(@PathVariable String dni) {
		return clientService.viewDniCliente(dni)
				.map(p -> ResponseEntity.ok().body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

	@ApiOperation(value = "ACTUALIZA CLIENTE POR ID", notes = "")
	@PutMapping
	public Mono<Client> updateClientePersonal(@RequestBody Client cliente) {
		System.out.println(cliente.toString());
		return clientService.saveCliente(cliente);
	}

	@ApiOperation(value = "GUARDA CLIENTE VALIDANDO SI EL [TIPO CLIENTE] EXISTE", notes = "")
	@PostMapping
	public Mono<Client> guardarCliente(@RequestBody Client cli) {
		
		return clientService.saveCliente(cli);

	}

	@ApiOperation(value = "ELIMINA CLIENTE POR ID", notes = "")
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> deleteBanco(@PathVariable String id) {
		return clientService.findByIdClientePersonal(id).flatMap(s -> {
			return clientService.deleteCliente(s).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
		}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NO_CONTENT));
	}

}
