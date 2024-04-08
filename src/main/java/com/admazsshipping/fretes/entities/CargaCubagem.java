package com.admazsshipping.fretes.entities;

import java.util.HashSet;
import java.util.Set;

import com.admazsshipping.fretes.dtos.CargaGeralDTO;
import com.admazsshipping.fretes.services.utils.IConvertible;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cubagem")
public class CargaCubagem extends Carga implements IConvertible<CargaGeralDTO>{


	private static final long serialVersionUID = 1L;

	
	private Double largura;
	
	private Double comprimento;
	
	private Double altura;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "cargas", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})	
	private Set<FreteCubagem> freteCubagem = new HashSet<>();
	
	public CargaCubagem() {
		
	}
	
	public CargaCubagem(Long id, String descricao, Integer quantidade, Double largura, Double comprimento, Double altura) {
		super(id, descricao, quantidade);
		this.largura = largura;
		this.comprimento = comprimento;
		this.altura = altura;
	}


	public Double getLargura() {
		return largura;
	}


	public void setLargura(Double largura) {
		this.largura = largura;
	}


	public Double getComprimento() {
		return comprimento;
	}


	public void setComprimento(Double comprimento) {
		this.comprimento = comprimento;
	}


	public Double getAltura() {
		return altura;
	}


	public void setAltura(Double altura) {
		this.altura = altura;
	}

	@Override
	public CargaGeralDTO convert() {
		// TODO Auto-generated method stub
		return new CargaGeralDTO(this);
	}	
		
	

}
