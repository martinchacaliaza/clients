package com.example.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.app.models.TypeClient;

import reactor.core.publisher.Mono;

public interface TipoClienteDao extends ReactiveMongoRepository<TypeClient, String> {

	
	Mono<TypeClient> findByIdTipo(String id);
}
