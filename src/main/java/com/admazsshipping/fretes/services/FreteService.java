package com.admazsshipping.fretes.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.admazsshipping.fretes.controller.exceptions.FieldMessage;
import com.admazsshipping.fretes.entities.Cliente;
import com.admazsshipping.fretes.entities.Frete;
import com.admazsshipping.fretes.personal_exceptions.DataIntegrityException;
import com.admazsshipping.fretes.personal_exceptions.DatabaseException;
import com.admazsshipping.fretes.personal_exceptions.ResourceNotFoundException;
import com.admazsshipping.fretes.repositories.ClienteRepository;
import com.admazsshipping.fretes.repositories.FreteRepository;


import jakarta.persistence.EntityNotFoundException;


@Service
public class FreteService {

	private List<FieldMessage> listaErros;
	
	private static Logger logger = LoggerFactory.getLogger(FreteService.class);

	@Autowired
	private FreteRepository repository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional(readOnly = true)
	public Page<Frete> findAllPaged(PageRequest pageRequest) {
		// List<Category> categories = (categoryId == 0) ? null :
		// Arrays.asList(categoryRepository.getOne(categoryId));

		Page<Frete> list = repository.findAll(pageRequest);
		// Usando 'expressão lambda' para transferir Product para ProductDTO
		// return list.map(x -> new ProductDTO(x));
		return list;
	}
	
	@Transactional(readOnly = true)
	public Frete findById(Long id) {

		Optional<Frete> obj = repository.findById(id);

		Frete entity = obj.orElseThrow(() -> new ResourceNotFoundException(String.format("Frete solicitado não encontrado. Id: %s",id)));

		return entity;
		//return new MovieDTO(entity, entity.getReviews());
	}
	

	@Transactional
	public Frete insert(Frete entidade) {
		Frete retorno = new Frete();		

		if (isValid(entidade)) {
			retorno = repository.save(entidade);
		} else {
			throw new DataIntegrityException("Dados inválidos. Verifique os erros!", listaErros);
			//throw new ResourceNotFoundException("Cliente");
		}

		return retorno;
	}
	
	@Transactional
	public Frete update(Long id, Frete entidade) {
		try {
			Frete retorno = repository.findById(id).get();
			entidade.setId(id);
			//copyDtoToEntity(dto, entity);
			retorno = repository.save(entidade);			
			
			return retorno;
			//return new MovieDTO(entity);

		} 
		catch (EntityNotFoundException | NoSuchElementException e) {
			throw new ResourceNotFoundException("Id do frete não encontrado: " + id);
		}
	}
	
	public void delete(Long id) {
		String message = "";
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			message = "Id do frete não encontrado: " + id;
			logger.error(message);
			throw new ResourceNotFoundException(message);
		} catch (DataIntegrityViolationException e) {
			message = "Violação da integridade dos dados!";
			throw new DatabaseException(message);
		}

	}
	
	
	public boolean isValid(Frete frete) {		
		
		listaErros = new ArrayList<>();
		Cliente clienteDoFrete = frete.getCliente();
		
		if(clienteDoFrete == null) {
			listaErros.add(new FieldMessage("cliente", "Dados do cliente não fornecidos. Por favor, informe os dados corretos"));
		}
		else if(clienteDoFrete.getId() == null) {
			
			listaErros.add(new FieldMessage("cliente", "Identificador do cliente não fornecido. Dado obrigatório!"));
		}
		else {
			var clienteVerificado = clienteRepository.getReferenceById(clienteDoFrete.getId());
			if(clienteVerificado == null) {
				Long clienteId = clienteDoFrete.getId();
				
				listaErros.add(new FieldMessage("client_id", String.format("Cliente com id '%s' não cadastrado. Verifique os dados enviados!", clienteId)));
			}			
		}		


		return listaErros.isEmpty();
	}

}
