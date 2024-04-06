package com.admazsshipping.fretes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admazsshipping.fretes.entities.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	
	Cliente findByDocumento(String documento);
	
	Cliente findByEmail(String email);

}

