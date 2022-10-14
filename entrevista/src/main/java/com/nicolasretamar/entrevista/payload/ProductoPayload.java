package com.nicolasretamar.entrevista.payload;

import java.math.BigDecimal;

public class ProductoPayload {
	
	private Long id;
	
	private String nombre;
	
	private BigDecimal precio; //Precio unitario

	public ProductoPayload(Long id, String nombre, BigDecimal precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
	}

	public ProductoPayload() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	
	

}
