package com.nicolasretamar.entrevista.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nicolasretamar.entrevista.models.FechaPromocional;

@Repository
public interface FechaPromocionalRepository extends JpaRepository<FechaPromocional, Long> {
	
}
