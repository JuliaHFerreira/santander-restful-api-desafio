package me.dio.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import me.dio.model.Client;
import me.dio.service.ClientService;

@RestController()
@RequestMapping("/client")
public class ClientController {
	
	private ClientService clientService;
	
	public ClientController(ClientService clientService) {		
		
		this.clientService = clientService; 
	}
	
	@PostMapping
	private ResponseEntity<Client> create(@RequestBody Client client){
		
		Client clientCreated = clientService.create(client);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
	                .path("/{id}")
	                .buildAndExpand(clientCreated.getId())
	                .toUri();
		
		return ResponseEntity.created(location).body(client);
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Client> findById(@PathVariable(value = "id") Long id){
		
		Client client = clientService.findById(id);
		
		return ResponseEntity.ok(client);
	}
	
	@GetMapping
	private ResponseEntity<List<Client>> findAll(){
		
		List<Client> clientList = clientService.findAll();
		
		return ResponseEntity.ok().body(clientList);
	}


}
