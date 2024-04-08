package com.admazsshipping.fretes.dtos;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.admazsshipping.fretes.entities.Carga;
import com.admazsshipping.fretes.entities.Cliente;
import com.admazsshipping.fretes.entities.Destino;
import com.admazsshipping.fretes.entities.Frete;
import com.admazsshipping.fretes.entities.FreteCubagem;
import com.admazsshipping.fretes.entities.FretePeso;
import com.admazsshipping.fretes.entities.Origem;



public class FreteDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	

	private Long id;

	private Cliente cliente;

	private Origem origem;

	private Destino destino;	
	
	private Instant dataEntrega;
	
	//Set<CargaCubagem> cargas = new HashSet<>();
	private List<CargaGeralDTO> cargas = new ArrayList<>();
	

	public FreteDTO(Long id, Cliente cliente, Origem origem, Destino destino, Instant dataEntrega, List<CargaGeralDTO> cargas) {		
		this.id = id;
		this.cliente = cliente;
		this.origem = origem;
		this.destino = destino;
		this.dataEntrega = dataEntrega;	
		this.cargas = cargas;
	}

	public FreteDTO() {
		
	}
	
	//public FreteDTO(Frete<? extends Carga> freteAny) {
	public FreteDTO(Frete<?> freteAny) {
		this.id = freteAny.getId();		
		this.cliente = freteAny.getCliente();
		this.origem = freteAny.getOrigem();
		this.destino = freteAny.getDestino();
		this.dataEntrega = freteAny.getDataEntrega();	
		//this.cargas = new Arr
		if(freteAny instanceof FreteCubagem ) {				
			((FreteCubagem)freteAny).getCargas().forEach(x->{
				this.cargas.add(new CargaGeralDTO(x));
			});
			
		}
		else if(freteAny instanceof FretePeso ) {
			//this.cargas = ((FretePeso)freteAny).getCargas().stream().toList();
			transformaCargas((FretePeso)freteAny);
		}		
		
	}
	
	private <CG extends Carga, FR extends Frete<CG>>void transformaCargas(FR freteAny){
		((FR)freteAny).getCargas().forEach(x->{
			this.cargas.add(new CargaGeralDTO(x));
		});
		
	}
	
//	public FreteDTO(FretePeso fretePeso) {
//		
//	}

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

	public List<CargaGeralDTO> getCargas() {
		return cargas;
	}

	public void setCargas(List<CargaGeralDTO> cargas) {
		this.cargas = cargas;
	}
	
//	public void converte<T extends Carga>(List<T> cargas) {
//		this.cargas = cargas;
//	}

	
	

}
