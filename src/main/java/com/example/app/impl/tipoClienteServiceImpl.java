package com.example.app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.app.dao.TipoClienteDao;
import com.example.app.models.TypeClient;
import com.example.app.service.TipoClienteService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class tipoClienteServiceImpl implements TipoClienteService{

	
	@Autowired
	public TipoClienteDao tipoClienteDao;
	
	@Override
	public Flux<TypeClient> findAllTipoCliente()
	{
	return tipoClienteDao.findAll();
	
	}
	@Override
	public Mono<TypeClient> findByIdTipoCliente(String id)
	{
	return tipoClienteDao.findById(id);
	
	}
	
	@Override
	public Mono<TypeClient> saveTipoCliente(TypeClient tipoCliente)
	{
	return tipoClienteDao.save(tipoCliente);
	}
}
