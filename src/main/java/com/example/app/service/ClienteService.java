package com.example.app.service;





import com.example.app.models.Client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClienteService {

	Flux<Client> findAllCliente();
	Mono<Client> findByIdClientePersonal(String id);
	Mono<Client> saveCliente(Client clientePersonal);
	Mono<Void> deleteCliente(Client cliente);
	Mono<Client> viewDniCliente(String dni);
	
}
