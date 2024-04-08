package com.admazsshipping.fretes.entities;

import com.admazsshipping.fretes.entities.enums.EnumTipoEndereco;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue("DESTINO")
public class Destino extends Endereco {
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore	
	@OneToOne(mappedBy = "destino")
	private Frete<?> frete;

	public Destino() {
		super();
		setTipoEndereco();
	}


	public Destino(Long id, String nomeDonoEndereco, String enderecoCompleto, String cep, String documento,
			Integer numero) {
		super(id, nomeDonoEndereco, enderecoCompleto, cep, documento, numero, EnumTipoEndereco.DESTINO);
		
	}


	@Override
	public void setTipoEndereco() {		
		this.tipoEndereco = EnumTipoEndereco.DESTINO;
	}
	
	public Frete<?> getFrete() {
		return frete;
	}


	public void setFrete(Frete<?> frete) {
		this.frete = frete;
	}
	

}
