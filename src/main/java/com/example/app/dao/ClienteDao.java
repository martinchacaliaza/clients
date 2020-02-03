package com.example.app.dao;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.app.models.Client;
import reactor.core.publisher.Mono;


public interface ClienteDao extends ReactiveMongoRepository<Client, String> {

	Mono<Client> findByDni(String dni);
	
}
