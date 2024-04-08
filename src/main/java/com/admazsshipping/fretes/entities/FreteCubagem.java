package com.admazsshipping.fretes.entities;

import java.util.HashSet;
import java.util.Set;

import com.admazsshipping.fretes.dtos.FreteDTO;
import com.admazsshipping.fretes.services.utils.IConvertible;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
@DiscriminatorValue("CUBAGEM")
public class FreteCubagem extends Frete<CargaCubagem> implements IConvertible<FreteDTO>{

	private static final long serialVersionUID = 1L;
	
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST})	
	@JoinTable(name = "tb_frete_cubagem", 
	joinColumns = @JoinColumn(name = "frete_id"), 
	inverseJoinColumns = @JoinColumn(name = "cubagem_id"))	
	private Set<CargaCubagem> cargas = new HashSet<>();	
	
	@Override
	public Set<CargaCubagem> getCargas() {
		
		return this.cargas;
	}
	
	public void setCargas(Set<CargaCubagem> carga) {		
		this.cargas = carga;
	}

	@Override
	public FreteDTO convert() {		
		return new FreteDTO(this);
	}

}
