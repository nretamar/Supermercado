package com.nicolasretamar.entrevista.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nicolasretamar.entrevista.models.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

	
	
}
