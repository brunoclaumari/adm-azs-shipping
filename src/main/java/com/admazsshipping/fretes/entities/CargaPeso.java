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
@Table(name = "tb_peso")
public class CargaPeso extends Carga implements IConvertible<CargaGeralDTO>{


	private static final long serialVersionUID = 1L;
	
	private Double peso;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "cargas", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})	
	private Set<FretePeso> fretePeso = new HashSet<>();

	public CargaPeso() {
		super();		
	}

	public CargaPeso(Long id, String descricao, Integer quantidade, Double peso) {
		super(id, descricao, quantidade);
		this.peso = peso;
	}
		

	public CargaPeso(Double peso) {
		super();
		this.peso = peso;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	@Override
	public CargaGeralDTO convert() {		
		return new CargaGeralDTO(this);
	}

}
