package com.example.app.repository;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.app.models.Client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ClienteDao extends ReactiveMongoRepository<Client, String> {

	Mono<Client> findByDni(String dni);	
	@Query("{ 'dni' : ?0 , 'codigo_bancario': ?1 }")
	Flux<Client> findByDniAndCodigobancario(String dni, String codigo_bancario);
	
}
