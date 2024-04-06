package com.admazsshipping.fretes.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;




@Entity
@Table(name = "tb_frete")
public class Frete implements Serializable{
	
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

	public Frete(Long id, Origem origem, Destino destino, Instant dataEntrega) {		
		this.id = id;
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return Objects.equals(id, other.id);
	}
	
	
	
//	private String enderecoOrigem;
//	
//	private String cepOrigem;
//	
//	private Integer numeroOrigem;
	

	
	

}
