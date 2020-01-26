package com.example.app.dao;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.app.models.Client;
import reactor.core.publisher.Mono;


public interface ClienteDao extends ReactiveMongoRepository<Client, String> {

	
	@Query("{ 'dni' : ?0}")
	Mono<Client> viewDniCliente(String dni);
	
	
}
