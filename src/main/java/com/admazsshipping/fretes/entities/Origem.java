package com.admazsshipping.fretes.entities;

import com.admazsshipping.fretes.entities.enums.EnumTipoEndereco;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue("ORIGEM")
public class Origem extends Endereco {
	
	private static final long serialVersionUID = 1L;	
	
	@JsonIgnore
	@OneToOne(mappedBy = "origem")
	private Frete<?> frete;


	public Origem() {
		super();
		setTipoEndereco();
	}


	public Origem(Long id, String nomeDonoEndereco, String enderecoCompleto, String cep, String documento,
			Integer numero) {
		super(id, nomeDonoEndereco, enderecoCompleto, cep, documento, numero, EnumTipoEndereco.ORIGEM);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void setTipoEndereco() {
		//this.tipo
		this.tipoEndereco = EnumTipoEndereco.ORIGEM;
	}
	
	public Frete<?> getFrete() {
		return frete;
	}


	public void setFrete(Frete<?> frete) {
		this.frete = frete;
	}
	

}
