package com.example.app.dao;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.app.models.Client;
import com.example.app.models.TypeClient;

import reactor.core.publisher.Mono;

public interface TipoClienteDao extends ReactiveMongoRepository<TypeClient, String> {

	@Query("{ 'idTipo' : ?0}")
	Mono<TypeClient> viewidTipo(String id);
}
