package com.example.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.app.models.Client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ClienteDao extends ReactiveMongoRepository<Client, String> {

	Mono<Client> findByDni(String dni);	
	Flux<Client> findByDniAndCodigoBancario(String dni, String codigo_bancario);
	
}
