package com.admazsshipping.fretes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admazsshipping.fretes.entities.Frete;

//public interface FreteRepository extends JpaRepository<Frete, Long> {
//public interface FreteRepository<F extends Frete<?>> extends JpaRepository<Frete<?>, Long> {
@Repository
public interface FreteRepository<F extends Frete<?>> extends JpaRepository<F, Long> {
	
//	@Query("SELECT DISTINCT ft FROM Movie ft "
//			+ "INNER JOIN mov.genre gen WHERE "
//			+ "(COALESCE(:genres) IS NULL OR gen IN :genres)")
//	Page<Frete> findWithFilterClienteId(List<Genre> genres, Pageable pageable);
	
	

}

