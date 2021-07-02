package com.devsuperior.dsClient.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsClient.dto.ClientDTO;
import com.devsuperior.dsClient.services.ClientService;

@RestController
@RequestMapping(path = "/clients")
public class ClientResource {
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll() {
		
		List<ClientDTO> list = clientService.findAll();
				
		return ResponseEntity.ok().body(list);
	}
}
