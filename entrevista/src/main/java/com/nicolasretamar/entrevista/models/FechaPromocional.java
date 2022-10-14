package com.nicolasretamar.entrevista.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fecha_promocional")
public class FechaPromocional {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate fecha;
	
	
	
	
	public FechaPromocional() {
		super();
	}

	public FechaPromocional(Long id, LocalDate fecha) {
		super();
		this.id = id;
		this.fecha = fecha;
	}
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	
}
