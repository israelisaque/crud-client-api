package com.devsuperior.dsClient.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsClient.dto.ClientDTO;
import com.devsuperior.dsClient.entities.Client;
import com.devsuperior.dsClient.repositories.ClientRepository;
import com.devsuperior.dsClient.services.exceptions.EntityNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	public ClientRepository clientRepository;
	
	@Transactional(readOnly = true)
	public List<ClientDTO> findAll() {
		List<Client> list = clientRepository.findAll();
		return list.stream()
				.map(client -> new ClientDTO(client))
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = clientRepository.findById(id);
		Client client = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		return new ClientDTO(client);
	}

	@Transactional(readOnly = true)
	public ClientDTO insert(ClientDTO clientDTO) {
		Client client = new Client();
		client.setName(clientDTO.getName());
		client.setCpf(clientDTO.getCpf());
		client.setIncome(clientDTO.getIncome());
		client.setBirthDate(clientDTO.getBirthDate());
		client.setChidren(clientDTO.getChildren());
		client = clientRepository.save(client);
		return new ClientDTO(client);
	}
}
