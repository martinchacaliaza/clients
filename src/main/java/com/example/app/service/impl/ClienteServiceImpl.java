package com.example.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.app.exception.RequestException;
import com.example.app.models.Client;
import com.example.app.models.TypeClient;
import com.example.app.repository.ClienteDao;
import com.example.app.repository.TipoClienteDao;
import com.example.app.service.ClienteService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Value("${com.bootcamp.gateway.url}")
	String valor;

	@Autowired
	public ClienteDao clienteDao;
	
	@Autowired
	public TipoClienteDao typeClient;
	
	@Override
	public Flux<Client> findAllCliente()
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
	public Mono<Void> deleteCliente(Client cliente) {
		return clienteDao.delete(cliente);
	}
	
	@Override
	public Mono<Client> saveCliente(Client cli) {
		Mono<TypeClient> tipo = this.typeClient.findByIdTipo(cli.getTipoCliente().getIdTipo());
		return tipo.defaultIfEmpty(new TypeClient()).flatMap(c -> {
			if (c.getIdTipo() == null) {	
			throw new RequestException("El tipo de cliente no existe");
				
			}
			return Mono.just(c);
		}).flatMap(t -> {
			cli.setTipoCliente(t);
	
			Mono<Long> cl = clienteDao.findByDniAndCodigoBancario(cli.getDni(), cli.getCodigoBancario()).count();
			return cl.flatMap(a -> {
				if (a>0) {
				
					throw new RequestException("Cliente ya existe para esta entidad bancaria");
					
				}else {
					return clienteDao.save(cli);
				}	
		});
		});
		}
	
}
