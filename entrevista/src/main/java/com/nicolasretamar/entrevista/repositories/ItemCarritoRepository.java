package com.nicolasretamar.entrevista.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nicolasretamar.entrevista.models.ItemCarrito;

@Repository
public interface ItemCarritoRepository extends JpaRepository<ItemCarrito, Long>{
}
