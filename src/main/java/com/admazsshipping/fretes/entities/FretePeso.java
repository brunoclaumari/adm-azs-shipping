package com.admazsshipping.fretes.entities;

import java.util.HashSet;
import java.util.Set;

import com.admazsshipping.fretes.dtos.FreteDTO;
import com.admazsshipping.fretes.services.utils.IConvertible;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
@DiscriminatorValue("PESO")
public class FretePeso extends Frete<CargaPeso> implements IConvertible<FreteDTO>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST})	
	@JoinTable(name = "tb_frete_peso", 
	joinColumns = @JoinColumn(name = "frete_id", foreignKey = @ForeignKey(name="fk_peso_frete")), 
	inverseJoinColumns = @JoinColumn(name = "peso_id", foreignKey = @ForeignKey(name="fk_peso")))
	private Set<CargaPeso> cargas = new HashSet<>();
	
	@Override
	public Set<CargaPeso> getCargas() {
		
		return this.cargas;
	}

	
	public void setCargas(Set<CargaPeso> carga) {		
		this.cargas = carga;
	}

	@Override
	public FreteDTO convert() {		
		return new FreteDTO(this);
	}	
	

}
