package com.admazsshipping.fretes.entities;

import com.admazsshipping.fretes.entities.enums.EnumTipoEndereco;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
//@Table(name = "tb_destino")
@DiscriminatorValue("2")
public class Destino extends Endereco{

	
	private static final long serialVersionUID = 1L;

	public Destino() {
		super();
		setTipoEndereco();
	}


	public Destino(Long id, String nomeDonoEndereco, String enderecoCompleto, String cep, String documento,
			Integer numero) {
		super(id, nomeDonoEndereco, enderecoCompleto, cep, documento, numero, EnumTipoEndereco.DESTINO);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void setTipoEndereco() {
		//this.tipo
		this.tipoEndereco = EnumTipoEndereco.DESTINO;
	}
	

}
