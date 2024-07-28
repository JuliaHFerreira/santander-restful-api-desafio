package me.dio.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import me.dio.model.Client;
import me.dio.repository.ClientRepository;

@Service
public class ClientService {
	
	private ClientRepository clientRepository;
	
	public ClientService(ClientRepository clientRepository) {
		
		this.clientRepository = clientRepository; 
		
	}

	public Client create(Client client) {

		if (clientRepository.existsByEmail(client.getEmail())) {
			throw new IllegalArgumentException("This client email already exists.");
		}

		return clientRepository.save(client);
	}

	public Client findById(Long id) {
		return clientRepository.findById(id).orElseThrow(NoSuchElementException::new);
	}

	public List<Client> findAll() {
		return clientRepository.findAll();
	}

}
