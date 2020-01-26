package com.example.app.service;


import com.example.app.models.TypeClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TipoClienteService {
	
	Flux<TypeClient> findAllTipoCliente();
	Mono<TypeClient> findByIdTipoCliente(String id);
	Mono<TypeClient> saveTipoCliente(TypeClient tipoCliente);
}
