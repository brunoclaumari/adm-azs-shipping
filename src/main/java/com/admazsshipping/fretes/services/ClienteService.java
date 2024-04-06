package com.admazsshipping.fretes.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
		
		//List<FieldMessage> list = new ArrayList<>();
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

//	@Transactional
//	public MovieDTO update(Long id, MovieDTO dto) {
//		try {
//			Movie entity = repository.getOne(id);
//			copyDtoToEntity(dto, entity);
//			entity = repository.save(entity);
//			return new MovieDTO(entity);
//
//		} catch (EntityNotFoundException e) {
//			throw new ResourceNotFoundException("Id not found: " + id);
//		}
//
//	}

	// Único sem Transactional, pois tem que capturar uma exceção e o transactional
	// não deixaria
//	public void delete(Long id) {
//		String message = "";
//		try {
//			repository.deleteById(id);
//		} catch (EmptyResultDataAccessException e) {
//			message = "Id not found: " + id;
//			logger.error(message);
//			throw new ResourceNotFoundException(message);
//		} catch (DataIntegrityViolationException e) {
//			message = "Integrity database Violation!";
//			throw new DatabaseException(message);
//		}	
//	}
	

}
