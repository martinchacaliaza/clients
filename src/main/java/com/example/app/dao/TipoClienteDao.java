package com.example.app.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.app.models.TypeClient;

public interface TipoClienteDao extends ReactiveMongoRepository<TypeClient, String> {

}
