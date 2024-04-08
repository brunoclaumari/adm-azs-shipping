package com.admazsshipping.fretes.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.admazsshipping.fretes.dtos.CargaGeralDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;





//@MappedSuperclass
@Entity(name = "tb_frete")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_carga", 
  discriminatorType = DiscriminatorType.STRING)
public abstract class Frete<T extends Carga> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(
			name = "cliente_id", nullable = false,
			foreignKey = @ForeignKey(name="fk_cliente_frete"))
	private Cliente cliente;	
	

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "origem_id", referencedColumnName = "id")
	private Origem origem;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destino_id", referencedColumnName = "id")
	private Destino destino;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant dataEntrega;

	public Frete(Long id, Cliente cliente, Origem origem, Destino destino, Instant dataEntrega) {		
		this.id = id;
		this.cliente = cliente;
		this.origem = origem;
		this.destino = destino;
		this.dataEntrega = dataEntrega;		
	}

	public Frete() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Origem getOrigem() {
		return origem;
	}

	public void setOrigem(Origem origem) {
		this.origem = origem;
	}

	public Destino getDestino() {
		return destino;
	}

	public void setDestino(Destino destino) {
		this.destino = destino;
	}

	public Instant getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Instant dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public abstract Set<? extends Carga> getCargas();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Frete other = (Frete) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
