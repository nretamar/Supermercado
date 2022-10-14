package com.nicolasretamar.entrevista.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nicolasretamar.entrevista.payload.ProductoPayload;


@Entity
@Table(name = "producto")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	private BigDecimal precio; //Precio unitario
	
	
	

	public Producto() {
		super();
	}

	public Producto(Long id, String nombre, BigDecimal precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
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
	
	public ProductoPayload toPayload() {
		ProductoPayload p = new ProductoPayload();
		p.setId(id);
		p.setNombre(nombre);
		p.setPrecio(precio);
		return p;
	}
	
}
