package com.admazsshipping.fretes.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.admazsshipping.fretes.dtos.CargaGeralDTO;
import com.admazsshipping.fretes.dtos.FreteDTO;
import com.admazsshipping.fretes.entities.Carga;
import com.admazsshipping.fretes.entities.CargaCubagem;
import com.admazsshipping.fretes.entities.CargaPeso;
import com.admazsshipping.fretes.entities.Frete;
import com.admazsshipping.fretes.entities.FreteCubagem;
import com.admazsshipping.fretes.entities.FretePeso;
import com.admazsshipping.fretes.entities.enums.EnumTipoCarga;

@Component
public class FreteHelper<F extends Frete<?>, C extends Carga, DTO> {
	
	
	

	@SuppressWarnings("unchecked")
	
	public F TrataTipoCargaDTOParaEntidade(F entity, EnumTipoCarga tipoCarga, FreteDTO dto) {

		switch (tipoCarga) {
		case POR_CUBAGEM:
			//entity = (F) new FreteCubagem();
			FreteCubagem cubagem = (FreteCubagem) entity;
			converteCargaCubagem(dto.getCargas(), cubagem);
			//cubagem.setCargas(converteCargaCubagem(dto.getCargas()));
			entity = (F) cubagem;
			break;
		case POR_PESO:
			FretePeso fPeso = (FretePeso) entity;
			converteCargaPeso(dto.getCargas(), fPeso);
			//fPeso.setCargas(converteCargaPeso(dto.getCargas()));	
			entity = (F) fPeso;
			break;
		}
		
		return entity;

	}
	
	
	public void converteCargaCubagem(List<CargaGeralDTO> list, FreteCubagem freteCubagem) {	
		//Set<CargaCubagem> listaCubagem = new HashSet<>();
		List<CargaCubagem> listaCubagem = new ArrayList<>();
		List<CargaGeralDTO> copiaListaDTO = new ArrayList<>(list); 
		if(copiaListaDTO != null && copiaListaDTO.size() > 0) {			
			Iterator<CargaGeralDTO> iterator = copiaListaDTO.iterator();
			while (iterator.hasNext()) {
				freteCubagem.getCargas().add(cargaDtoParaCargaCubagem(iterator.next()));
	        }
			listaCubagem.stream().forEach(cg->freteCubagem.getCargas().add(cg));
		}		

		//list.stream().forEach(dto -> listaCubagem.add(cargaDtoParaCargaCubagem(dto)));	
		
	}
	
	
	public void converteCargaPeso(List<CargaGeralDTO> list, FretePeso fretePeso) {

		List<CargaPeso> listaPeso = new ArrayList<>();
		//Set<CargaPeso> listaPeso = new HashSet<>();
		List<CargaGeralDTO> copiaListaDTO = new ArrayList<>(list); 
		if(copiaListaDTO != null && copiaListaDTO.size() > 0) {			
			Iterator<CargaGeralDTO> iterator = copiaListaDTO.iterator();
			while (iterator.hasNext()) {
				//fretePeso.getCargas().add(cargaDtoParaCargaPeso(iterator.next()));
				listaPeso.add(cargaDtoParaCargaPeso(iterator.next()));				
	        }
			listaPeso.stream().forEach(cg->fretePeso.getCargas().add(cg));
		}
		
		//list.stream().forEach(dto -> listaPeso.add(cargaDtoParaCargaPeso(dto)));		
		
	}
	
	public CargaCubagem cargaDtoParaCargaCubagem(CargaGeralDTO cargaDTO) {
		CargaCubagem cargaCubagem = new CargaCubagem();
		cargaCubagem.setId(cargaDTO.getId());
		cargaCubagem.setDescricao(cargaDTO.getDescricao());
		cargaCubagem.setQuantidade(cargaDTO.getQuantidade());
		cargaCubagem.setAltura(cargaDTO.getAltura());
		cargaCubagem.setComprimento(cargaDTO.getComprimento());
		cargaCubagem.setLargura(cargaDTO.getLargura());
		
		return cargaCubagem;
	}
	
	public CargaPeso cargaDtoParaCargaPeso(CargaGeralDTO cargaDTO) {
		CargaPeso cargaPeso = new CargaPeso();
		cargaPeso.setId(cargaDTO.getId());
		cargaPeso.setDescricao(cargaDTO.getDescricao());
		cargaPeso.setQuantidade(cargaDTO.getQuantidade());
		cargaPeso.setPeso(cargaDTO.getPeso());
		
		return cargaPeso;
	}


}
