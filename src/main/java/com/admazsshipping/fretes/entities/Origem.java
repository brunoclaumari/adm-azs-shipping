package com.admazsshipping.fretes.entities;

import com.admazsshipping.fretes.entities.enums.EnumTipoEndereco;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
//@Table(name = "tb_origem")
@DiscriminatorValue("1")
public class Origem extends Endereco{

	
	private static final long serialVersionUID = 1L;

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
	

}
