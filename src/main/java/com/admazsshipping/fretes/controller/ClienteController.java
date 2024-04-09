package com.admazsshipping.fretes.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.admazsshipping.fretes.entities.Cliente;
import com.admazsshipping.fretes.services.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {
	

	
	@Autowired
	private ClienteService service;
	
	@GetMapping
	public ResponseEntity<Page<Cliente>> findAllClients(Pageable pageable){		

		PageRequest pageRequest = PageRequest.of(
				pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
		Page<Cliente> list = service.findAllPaged(pageRequest);		
		
		return ResponseEntity.ok().body(list);
	}

	
	@PostMapping	
	public ResponseEntity<Cliente> insert(@Valid @RequestBody Cliente entidade) {
				
		Cliente retorno = service.insert(entidade);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(retorno.getId()).toUri();
		
		return ResponseEntity.created(uri).body(retorno);
	}

}
