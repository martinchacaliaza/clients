package com.example.app;

import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.app.dto.dtoBank;
import com.example.app.models.Client;
import com.example.app.models.TypeClient;
import com.example.app.service.ClienteService;

import reactor.core.publisher.Mono;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {
	@Autowired
	private WebTestClient client; 
	
	@Autowired
	private ClienteService service;
	
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void listClient() {
		client.get().uri("/api/Clientes/")
		.accept(MediaType.APPLICATION_JSON_UTF8)
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
		.expectBodyList(Client.class).consumeWith(response -> {
			List<Client> cli = response.getResponseBody();
			
			cli.forEach(p -> {
				System.out.println(p.getDni());
			});
			
			Assertions.assertThat(cli.size()>0).isTrue();
		});;
	}
	
	@Test
	public void findByIdClient() {
		Client cli = service.findByIdClientePersonal("5e333cfcc3dbd20f24b094f8").block();
		client.get().uri("/api/Clientes/{id}", Collections.singletonMap("id", cli.getId()))
		.accept(MediaType.APPLICATION_JSON_UTF8)
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8);
	}
	
	@Test
	void saveClient() {	
		Client bank = new Client();
		bank.setDni("989898989");
		bank.setNombres("Pablo Alfonso");
		bank.setApellidos("Rivera Perales");
		bank.setSexo("M");
		bank.setTelefono("992246096");
		bank.setEdad("56");
		bank.setCorreo("privera@hialpesa.com");
		
		TypeClient type=new TypeClient();
		type.setIdTipo("1");
		type.setDescripcion("Personal");
		
		bank.setTipoCliente(type);
		bank.setCodigo_bancario("123");
		
		client.post()
		.uri("api/Clientes")
		.body(Mono.just(bank), dtoBank.class)
		.exchange()
		.expectStatus().isOk()
		.expectBody(Client.class)
		.consumeWith(response -> {
			Client b = response.getResponseBody();
			
			System.out.println("[CLIENTE REGISTRADO] " + bank);
			Assertions.assertThat(b.getDni()).isNotEmpty().isEqualTo("989898989");
			Assertions.assertThat(b.getNombres()).isNotEmpty().isEqualTo("Pablo Alfonso");
			Assertions.assertThat(b.getApellidos()).isNotEmpty().isEqualTo("Rivera Perales");	
			Assertions.assertThat(b.getSexo()).isNotEmpty().isEqualTo("M");
			Assertions.assertThat(b.getTelefono()).isNotEmpty().isEqualTo("992246096");
			Assertions.assertThat(b.getEdad()).isNotEmpty().isEqualTo("56");
			Assertions.assertThat(b.getCorreo()).isNotEmpty().isEqualTo("privera@hialpesa.com");
			Assertions.assertThat(b.getTipoCliente().getIdTipo()).isNotEmpty().isEqualTo("1");
			Assertions.assertThat(b.getTipoCliente().getDescripcion()).isNotEmpty().isEqualTo("Personal");
			Assertions.assertThat(b.getCodigo_bancario()).isNotEmpty().isEqualTo("123");
		});
	}
	
	@Test
	void updateClient() {
		Client clients = service.viewDniCliente("9898989894").block();
		clients.setDni("9898989897");
		clients.setNombres("Pablo Alfonso");

		
		client.put()
		.uri("api/Clientes")
		.body(Mono.just(clients), Client.class)
		.exchange()
		.expectStatus().isOk()
		.expectBody(Client.class)
		.consumeWith(response -> {
			Client b = response.getResponseBody();
			System.out.println("[CLIENTE REGISTRADO] " + clients);
			Assertions.assertThat(b.getDni()).isNotEmpty().isEqualTo("9898989897");
			Assertions.assertThat(b.getNombres()).isNotEmpty().isEqualTo("Pablo Alfonso");
		});
	}

	@Test
	void deleteClient() {
		Client cli= service.viewDniCliente("989898989").block();	
		client.delete()
		.uri("api/Clientes" + "/{id}", Collections.singletonMap("id", cli.getId()))
		.exchange()
		.expectStatus().isNoContent()
		.expectBody()
		.isEmpty();
	}	
	
	

}
