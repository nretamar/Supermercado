package com.nicolasretamar.entrevista.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nicolasretamar.entrevista.models.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long>{

	Optional<Carrito> findByUserDni(Integer dni);
}
