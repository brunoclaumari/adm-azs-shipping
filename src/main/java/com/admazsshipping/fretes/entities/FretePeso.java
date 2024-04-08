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
@DiscriminatorValue("P")
public class FretePeso extends Frete<CargaPeso> implements IConvertible<FreteDTO>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	@OneToMany(fetch = FetchType.LAZY)
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST})	
	@JoinTable(name = "tb_frete_peso", 
	joinColumns = @JoinColumn(name = "frete_id"), 
	inverseJoinColumns = @JoinColumn(name = "peso_id"))
	private Set<CargaPeso> cargas = new HashSet<>();

//	@Override
//	//@ManyToMany(targetEntity=Frete.class)
//	@OneToMany(targetEntity = FretePeso.class)
//	@JoinTable(name = "tb_frete_peso", 
//	joinColumns = @JoinColumn(name = "frete_id"), 
//	inverseJoinColumns = @JoinColumn(name = "peso_id"))
	
	

	//@OneToMany(fetch = FetchType.LAZY)
	@Override
	public Set<CargaPeso> getCargas() {
		// TODO Auto-generated method stub
		return this.cargas;
	}

	
	public void setCargas(Set<CargaPeso> carga) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FreteDTO convert() {
		// TODO Auto-generated method stub
		return new FreteDTO(this);
	}
	
		
//	@ManyToMany
//	@JoinTable(name = "tb_frete_peso", 
//	joinColumns = @JoinColumn(name = "frete_id"), 
//	inverseJoinColumns = @JoinColumn(name = "peso_id"))
//	Set<CargaPeso> cargas = new HashSet<>();
//
//
//	public Set<CargaPeso> getCargas() {
//		return cargas;
//	}
//
//
//	public void setCargas(Set<CargaPeso> cargas) {
//		this.cargas = cargas;
//	}
	
	

}
