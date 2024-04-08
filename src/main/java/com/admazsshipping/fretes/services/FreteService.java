package com.admazsshipping.fretes.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
import com.admazsshipping.fretes.dtos.FreteDTO;
import com.admazsshipping.fretes.entities.Carga;
import com.admazsshipping.fretes.entities.CargaCubagem;
import com.admazsshipping.fretes.entities.Cliente;
import com.admazsshipping.fretes.entities.Frete;
import com.admazsshipping.fretes.entities.FreteCubagem;
import com.admazsshipping.fretes.entities.FretePeso;
import com.admazsshipping.fretes.entities.enums.EnumTipoCarga;
import com.admazsshipping.fretes.personal_exceptions.DataIntegrityException;
import com.admazsshipping.fretes.personal_exceptions.DatabaseException;
import com.admazsshipping.fretes.personal_exceptions.InesperedException;
import com.admazsshipping.fretes.personal_exceptions.ResourceNotFoundException;
import com.admazsshipping.fretes.repositories.ClienteRepository;
import com.admazsshipping.fretes.repositories.FreteRepository;
import com.admazsshipping.fretes.services.utils.IConvertible;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FreteService {

	private List<FieldMessage> listaErros;

	private static Logger logger = LoggerFactory.getLogger(FreteService.class);

	@Autowired
	private FreteRepository<FretePeso> repositoryFretePeso;
	
	@Autowired
	private FreteRepository<FreteCubagem> repositoryFreteCubagem;
	
	@Autowired
	private FreteRepository<?> repository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional(readOnly = true)
	public Page<FreteDTO> findAllPaged(PageRequest pageRequest) {

		@SuppressWarnings("unchecked")
		Page<Frete<?>> list = (Page<Frete<?>>) repository.findAll(pageRequest);

		return list.map(x-> new FreteDTO(x));
	}

	@Transactional(readOnly = true)
	public FreteDTO findById(Long id) {		
		
		var obj = repository.findById(id);

		Frete<?> entity = obj.orElseThrow(() -> new ResourceNotFoundException(String.format("Frete solicitado não encontrado. Id: %s",id)));

		return new FreteDTO(entity);		
	}


	@Transactional
	public FreteDTO insertGeneric(FreteDTO dto) {
		
		FreteDTO retornoDTO = new FreteDTO();
		
		try {
			if (isValid(dto)) {
				EnumTipoCarga tipoCarga = dto.getCliente().getTipoCarga();
				
				retornoDTO = TrataDTOParaEntidade(tipoCarga, dto);				

			} else {
				throw new DataIntegrityException("Dados inválidos. Verifique os erros!", listaErros);				
			}
			
		} catch (Exception e) {
			throw new InesperedException("Ocorreu um erro inesperado! Verifique os dados enviados");
		}
		
		
		return retornoDTO;
	}

	@Transactional
	public FreteDTO update(Long id, FreteDTO freteDTO) {
		FreteDTO retornoDTO = new FreteDTO();
		try {
			Frete<?> retorno = repository.findById(id).get();
			freteDTO.setId(id);
			if (isValid(freteDTO)) {
				EnumTipoCarga tipoCarga = freteDTO.getCliente().getTipoCarga();
				retornoDTO = TrataDTOParaEntidade(tipoCarga, freteDTO);				
			}	
			
			return retornoDTO;			

		} 
		catch (EntityNotFoundException | NoSuchElementException e) {
			throw new ResourceNotFoundException("Id do frete não encontrado: " + id);
		}
	}

	public void delete(Long id) {
		String message = "";
		try {
			var encontrou = repository.findById(id);
			repository.deleteById(encontrou.get().getId());
		} catch (EmptyResultDataAccessException | NoSuchElementException e) {
			message = "Id do frete não encontrado: " + id;
			logger.error(message);
			throw new ResourceNotFoundException(message);
		} catch (DataIntegrityViolationException e) {
			message = "Violação da integridade dos dados!";
			throw new DatabaseException(message);
		}

	}

	public boolean isValid(FreteDTO frete) {

		listaErros = new ArrayList<>();
		Cliente clienteDoFrete = frete.getCliente();

		if (clienteDoFrete == null) {
			listaErros.add(new FieldMessage("cliente",
					"Dados do cliente não fornecidos. Por favor, informe os dados corretos"));
		} else if (clienteDoFrete.getId() == null) {

			listaErros.add(new FieldMessage("cliente", "Identificador do cliente não fornecido. Dado obrigatório!"));
		} else {
			var clienteVerificado = clienteRepository.findById(clienteDoFrete.getId());
			if (clienteVerificado == null) {
				Long clienteId = clienteDoFrete.getId();

				listaErros.add(new FieldMessage("client_id",
						String.format("Cliente com id '%s' não cadastrado. Verifique os dados enviados!", clienteId)));
			}else {				
				frete.setCliente(clienteVerificado.get());
			}
			
		}

		return listaErros.isEmpty();
	}

	public Frete<?> instanciaTipoFrete(FreteDTO dto) {
		if (dto.getCargas() instanceof CargaCubagem) {
			return new FreteCubagem();
		} else
			return new FretePeso();
	}
	
	private FreteDTO TrataDTOParaEntidade(EnumTipoCarga tipoCarga, FreteDTO dto) {
		
		FreteDTO retornoDTO = new FreteDTO();
		
		switch (tipoCarga) {
		case POR_CUBAGEM:
			FreteCubagem entidadeCub = new FreteCubagem();
			entidadeCub = RetornaFreteConformeTipoCarga((FreteCubagem)entidadeCub, tipoCarga, dto);
			var objetoSalvo = repositoryFreteCubagem.save(entidadeCub);
			retornoDTO = objetoSalvo.convert();
			break;
		case POR_PESO:
			FretePeso entidadePeso = new FretePeso();
			entidadePeso = RetornaFreteConformeTipoCarga((FretePeso)entidadePeso, tipoCarga, dto);
			var objetoSalvo2 = repositoryFretePeso.save(entidadePeso);
			retornoDTO = objetoSalvo2.convert();
			break;
		}
		
		return retornoDTO;
		
	}
	
	
	@SuppressWarnings({ "unchecked", "unused" })
	private 
	<F extends Frete<?>, 
	FF extends IConvertible<FreteDTO>, 
	C extends Carga> 
	FreteDTO InstanciaConformeTipoCarga(F entidade, EnumTipoCarga tipoCarga, FreteDTO dto){
		FreteHelper<F, C, FreteDTO> helper = new FreteHelper<F, C, FreteDTO>();
		
		entidade.setId(dto.getId());
		entidade.setCliente(dto.getCliente());
		entidade.setDataEntrega(dto.getDataEntrega());
		entidade.setDestino(dto.getDestino());
		entidade.setOrigem(dto.getOrigem());
		
		entidade = helper.TrataTipoCargaDTOParaEntidade((F) entidade, tipoCarga, dto);
		return ((FF)entidade).convert();
	}

	private <F extends Frete<?>, FF extends IConvertible<FreteDTO>,	C extends Carga> 
	F RetornaFreteConformeTipoCarga(F entidade, EnumTipoCarga tipoCarga, FreteDTO dto){
		FreteHelper<F, C, FreteDTO> helper = new FreteHelper<F, C, FreteDTO>();
		
		entidade.setId(dto.getId());
		entidade.setCliente(dto.getCliente());
		entidade.setDataEntrega(dto.getDataEntrega());
		entidade.setDestino(dto.getDestino());
		entidade.setOrigem(dto.getOrigem());
		
		entidade = helper.TrataTipoCargaDTOParaEntidade((F) entidade, tipoCarga, dto);
		return entidade;
	}

}
