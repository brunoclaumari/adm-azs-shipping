package com.admazsshipping.fretes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admazsshipping.fretes.entities.Cliente;
import com.admazsshipping.fretes.entities.enums.EnumTipoCarga;
import com.admazsshipping.fretes.repositories.ClienteRepository;
import com.admazsshipping.fretes.services.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private ClienteService clienteService;
	
	
	@GetMapping(value = "/teste")
	@Validated
	public ResponseEntity<Cliente> findTeste() {
		
		/*		 
		 94.236.136/0001-49		
		43.227.700/0001-26		
		91.945.283/0001-08		
		29.452.999/0001-91		
		69.446.812/0001-61
		 * */
		Cliente cliente = new Cliente(
				null, 
				"Cliente 1", 
				"94.236.136/0001-49", 
				"cliente1@gmail.com", 
				"11 99999-9999", 
				EnumTipoCarga.POR_CUBAGEM
				);
		
		Cliente retorno = repository.save(cliente);
		
		return ResponseEntity.ok().body(retorno);
	}
	
	@PostMapping(value = "/teste")	
	public ResponseEntity<Cliente> insert(@Valid @RequestBody Cliente entidade) {
				
		Cliente retorno = clienteService.insert(entidade);
		
		return ResponseEntity.ok().body(retorno);
	}

}
