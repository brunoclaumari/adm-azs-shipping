package com.admazsshipping.fretes.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admazsshipping.fretes.entities.Destino;
import com.admazsshipping.fretes.entities.Frete;
import com.admazsshipping.fretes.entities.Origem;
import com.admazsshipping.fretes.repositories.FreteRepository;

@RestController
@RequestMapping(value = "/fretes")
public class FreteController {
	
	@Autowired
	private FreteRepository repository;
	
	
	@GetMapping(value = "/teste")
	public ResponseEntity<Frete> findTeste() {

		Frete frete = new Frete();
		//frete.setId(1L);
		frete.setDataEntrega(Instant.parse("2020-07-13T20:50:07.12345Z"));
		frete.setOrigem(new Origem(null, "Zé do transporte", "Rua do maricá", "09974-345", "30305271822", 114));
		frete.setDestino(new Destino(null, "Clint eastwood", "Rua do bang", "09974-320", "35898734856", 304));
		
		var retorno = repository.save(frete);
		
		return ResponseEntity.ok().body(retorno);
	}

}
