package com.admazsshipping.fretes.entities;

import java.io.Serializable;
import java.util.Objects;

import com.admazsshipping.fretes.entities.enums.EnumTipoCarga;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_cliente")
public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nomeRazaoSocial;	
	
	@Column(unique = true)
	private String documento;
	
	@Column(unique = true)
	private String email;
	
	private String telefone;
	
	private EnumTipoCarga tipoCarga;	
	

	public Cliente() {		
	}


	public Cliente(Long id, String nomeRazaoSocial, String documento, String email, String telefone,
			EnumTipoCarga tipoCarga) {		
		this.id = id;
		this.nomeRazaoSocial = nomeRazaoSocial;
		this.documento = documento;
		this.email = email;
		this.telefone = telefone;
		this.tipoCarga = tipoCarga;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNomeRazaoSocial() {
		return nomeRazaoSocial;
	}


	public void setNomeRazaoSocial(String nomeRazaoSocial) {
		this.nomeRazaoSocial = nomeRazaoSocial;
	}


	public String getDocumento() {
		return documento;
	}


	public void setDocumento(String documento) {
		this.documento = documento;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public EnumTipoCarga getTipoCarga() {
		return tipoCarga;
	}


	public void setTipoCarga(EnumTipoCarga tipoCarga) {
		this.tipoCarga = tipoCarga;
	}


	@Override
	public int hashCode() {
		return Objects.hash(documento, id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(documento, other.documento) && Objects.equals(id, other.id);
	}
	

}
