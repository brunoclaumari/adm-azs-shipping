package com.admazsshipping.fretes.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.admazsshipping.fretes.controller.exceptions.FieldMessage;
import com.admazsshipping.fretes.entities.Cliente;
import com.admazsshipping.fretes.personal_exceptions.ConstraintUniqueException;
import com.admazsshipping.fretes.repositories.ClienteRepository;


@Service
public class ClienteService {
	
	//private static Logger logger = LoggerFactory.getLogger(ClienteTransportadorService.class);
	
	private List<FieldMessage> listaErros;

	@Autowired
	private ClienteRepository repository;	

	
	@Transactional
	public Cliente insert(Cliente entidade){
		Cliente retorno = new Cliente();
		
		if(isValid(entidade)) {
			retorno = repository.save(entidade);
		}
		else {
			throw new ConstraintUniqueException("Erro de valores únicos", listaErros);			
		}	
		
		return retorno;
	}
	
	public boolean isValid(Cliente clienteInsert) {		
		
		listaErros = new ArrayList<>();
		
		// Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista
		Cliente retorno = repository.findByEmail(clienteInsert.getEmail());
		if(retorno != null) {
			listaErros.add(new FieldMessage("email", String.format("Email %s já existe", clienteInsert.getEmail())));
		}
		
		retorno = repository.findByDocumento(clienteInsert.getDocumento());
		if(retorno != null) {
			listaErros.add(new FieldMessage("documento", String.format("CNPJ %s já existe", clienteInsert.getDocumento())));
		}
		

		return listaErros.isEmpty();
	}

	

}
