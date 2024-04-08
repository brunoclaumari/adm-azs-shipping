package com.admazsshipping.fretes.dtos;

import java.io.Serializable;
import java.util.Objects;

import com.admazsshipping.fretes.entities.Carga;
import com.admazsshipping.fretes.entities.CargaCubagem;
import com.admazsshipping.fretes.entities.CargaPeso;

//@MappedSuperclass
//@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CargaGeralDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	
	protected Long id;
	
	private String descricao;
	
	private Integer quantidade;
	
	private Double largura;
	
	private Double comprimento;
	
	private Double altura;
	
	private Double peso;		
	

	public CargaGeralDTO() {
		
	}

	public CargaGeralDTO(Long id, String descricao, Integer quantidade, Double largura, Double comprimento,
			Double altura, Double peso) {
		
		this.id = id;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.largura = largura;
		this.comprimento = comprimento;
		this.altura = altura;
		this.peso = peso;
	}
	
	public CargaGeralDTO(Carga carga) {		
		
		if(carga instanceof CargaCubagem) {
			this.id = ((CargaCubagem) carga).getId();
			this.descricao = ((CargaCubagem) carga).getDescricao();
			this.quantidade = ((CargaCubagem) carga).getQuantidade();
			this.altura = ((CargaCubagem) carga).getAltura();
			this.comprimento = ((CargaCubagem) carga).getComprimento();
			this.largura = ((CargaCubagem) carga).getLargura();
		}
		else if(carga instanceof CargaPeso) {
			this.id = ((CargaPeso) carga).getId();
			this.descricao = ((CargaPeso) carga).getDescricao();
			this.quantidade = ((CargaPeso) carga).getQuantidade();
			this.peso = ((CargaPeso) carga).getPeso();
		}
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
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

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
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
		CargaGeralDTO other = (CargaGeralDTO) obj;
		return Objects.equals(id, other.id);
	}
	
	
	

}
