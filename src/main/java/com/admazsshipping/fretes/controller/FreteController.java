package com.admazsshipping.fretes.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.admazsshipping.fretes.entities.Frete;
import com.admazsshipping.fretes.services.FreteService;

import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/fretes")
public class FreteController {

	
	@Autowired
	private FreteService service;
	
	@GetMapping
	public ResponseEntity<Page<Frete>> findAll(Pageable pageable) {
		PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
		Page<Frete> list = service.findAllPaged(pageRequest);		
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<Frete> insert(@Valid @RequestBody Frete body) {
		body = service.insert(body);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(body.getId()).toUri();

		return ResponseEntity.created(uri).body(body);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Frete> update(@PathVariable Long id,@Valid @RequestBody Frete body) {
		body = service.update(id, body);

		return ResponseEntity.ok().body(body);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Frete> delete(@PathVariable Long id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
		// vai retornar 204 que é que deu certo e o corpo da
		// resposta está vazio.
	}
	
	
//	@GetMapping(value = "/teste")
//	public ResponseEntity<Frete> findTeste() {
//
//		Frete frete = new Frete();
//		//frete.setId(1L);
//		frete.setDataEntrega(Instant.parse("2020-07-13T20:50:07.12345Z"));
//		frete.setOrigem(new Origem(null, "Zé do transporte", "Rua do maricá", "09974-345", "30305271822", 114));
//		frete.setDestino(new Destino(null, "Clint eastwood", "Rua do bang", "09974-320", "35898734856", 304));
//		frete.setCliente(new Cliente(1L, null, null, null, null, null));
//		
//		var retorno = service.insert(frete);
//		
//		return ResponseEntity.ok().body(retorno);
//	}

}
