package com.admazsshipping.fretes.entities;

import java.io.Serializable;
import java.util.Objects;

import com.admazsshipping.fretes.entities.enums.EnumTipoEndereco;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;


@Entity(name="tb_endereco")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_endereco_disc", 
  discriminatorType = DiscriminatorType.INTEGER)
public abstract class Endereco implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nomeDonoEndereco;
	
	private String enderecoCompleto;
	
	private String cep;
	
	private String documento;
	
	private Integer numero;
	
	protected EnumTipoEndereco tipoEndereco;	


	public Endereco() {		
	}

	public Endereco(Long id, String nomeDonoEndereco, String enderecoCompleto, String cep, String documento,
			Integer numero, EnumTipoEndereco tipoEndereco) {		
		this.id = id;
		this.nomeDonoEndereco = nomeDonoEndereco;
		this.enderecoCompleto = enderecoCompleto;
		this.cep = cep;
		this.documento = documento;
		this.numero = numero;
		this.tipoEndereco = tipoEndereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeDonoEndereco() {
		return nomeDonoEndereco;
	}

	public void setNomeDonoEndereco(String nomeDonoEndereco) {
		this.nomeDonoEndereco = nomeDonoEndereco;
	}

	public String getEnderecoCompleto() {
		return enderecoCompleto;
	}

	public void setEnderecoCompleto(String enderecoCompleto) {
		this.enderecoCompleto = enderecoCompleto;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	

	public EnumTipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}
	
	public abstract void setTipoEndereco();

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
		Endereco other = (Endereco) obj;
		return Objects.equals(id, other.id);
	}
		
	

}
