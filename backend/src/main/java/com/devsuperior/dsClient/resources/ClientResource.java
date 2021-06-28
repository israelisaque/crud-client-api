package com.devsuperior.dsClient.resources;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsClient.entities.Client;

@RestController
@RequestMapping(path = "/clients")
public class ClientResource {
	
	@GetMapping
	public ResponseEntity<List<Client>> findAll() {
		
		List<Client> list = new ArrayList<>();
		list.add(new Client(1L, "Israel Oliveira", "123.456.789-00", 3000.00, Instant.parse("1992-09-17T00:00:00Z"), 0));
		list.add(new Client(1L, "Ana Carolina", "123.456.789-00", 3000.00, Instant.parse("1996-12-01T00:00:00Z"), 0));
		
		return ResponseEntity.ok().body(list);
	}
}
