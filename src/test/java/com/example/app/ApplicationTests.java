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

import com.example.app.models.Client;
import com.example.app.service.ClienteService;



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
		client.get().uri("/api/ClientePersonal/dni/")
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
	public void findByClient() {
		Client cli = service.viewDniCliente("72739839").block();
		client.get().uri("/api/ClientePersonal/{id}", Collections.singletonMap("id", cli.getId()))
		.accept(MediaType.APPLICATION_JSON_UTF8)
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8);
	}

}
