package com.admazsshipping.fretes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admazsshipping.fretes.entities.Frete;

@Repository
public interface FreteRepository extends JpaRepository<Frete, Long> {

}

