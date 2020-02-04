package com.example.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.dao.ClienteDao;
import com.example.app.models.Client;
import com.example.app.service.ClienteService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	public ClienteDao clienteDao;
	
	@Override
	public Flux<Client> findAllClientePersonal()
	{
	return clienteDao.findAll();
	
	}
	@Override
	public Mono<Client> findByIdClientePersonal(String id)
	{
	return clienteDao.findById(id);
	
	}
	
	@Override
	public Mono<Client> viewDniCliente(String dni)
	{
	return clienteDao.findByDni(dni);
	
	}
	
	@Override
	public Mono<Client> saveClientePersonal(Client clientePersonal)
	{
	return clienteDao.save(clientePersonal);
	}
	
	@Override
	public Mono<Void> deleteCliente(Client cliente) {
		return clienteDao.delete(cliente);
	}
	
}
