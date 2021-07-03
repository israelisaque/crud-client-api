package com.devsuperior.dsClient.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsClient.dto.ClientDTO;
import com.devsuperior.dsClient.entities.Client;
import com.devsuperior.dsClient.repositories.ClientRepository;
import com.devsuperior.dsClient.services.exceptions.DatabaseException;
import com.devsuperior.dsClient.services.exceptions.ResourceNotFoundException;

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
		Client client = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ClientDTO(client);
	}

	@Transactional
	public ClientDTO insert(ClientDTO clientDTO) {
		Client client = new Client();
		client.setName(clientDTO.getName());
		client.setCpf(clientDTO.getCpf());
		client.setIncome(clientDTO.getIncome());
		client.setBirthDate(clientDTO.getBirthDate());
		client.setChildren(clientDTO.getChildren());
		client = clientRepository.save(client);
		return new ClientDTO(client);
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO clientDTO) {
		try {
			Client client = clientRepository.getOne(id);
			client.setName(clientDTO.getName());
			client.setCpf(clientDTO.getCpf());
			client.setIncome(clientDTO.getIncome());
			client.setBirthDate(clientDTO.getBirthDate());
			client.setChildren(clientDTO.getChildren());
			client = clientRepository.save(client);
			return new ClientDTO(client);		
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id nnot found " + id);
		}
	}

	public void delete(Long id) {
		try {
			clientRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
}













