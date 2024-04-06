package com.admazsshipping.fretes.entities;

import com.admazsshipping.fretes.entities.enums.EnumTipoEndereco;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
//@Table(name = "tb_destino")
@DiscriminatorValue("2")
public class Destino extends Endereco{

	
	private static final long serialVersionUID = 1L;
	
	@OneToOne(mappedBy = "tb_endereco")
	private Frete frete;

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
	
	public Frete getFrete() {
		return frete;
	}


	public void setFrete(Frete frete) {
		this.frete = frete;
	}
	

}
