package com.nicolasretamar.entrevista.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.nicolasretamar.entrevista.payload.ItemCarritoPayload;

@Entity
@Table(name = "item_carrito")
public class ItemCarrito {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@OrderBy("nombre ASC")
	private Producto producto;
	
	private Long cantidad;
	
	
	public ItemCarrito() {
		super();
		cantidad = (long) 0;
	}
	
	

	public ItemCarrito(Long id, Producto producto, Long cantidad) {
		super();
		this.id = id;
		this.producto = producto;
		this.cantidad = cantidad;
	}
	
	public void sumarCantidad(Long cantidad) {
		this.cantidad = this.cantidad + cantidad;
	}
	
	public void restarCantidad(Long cantidad) {
		this.cantidad = this.cantidad - cantidad;
	}
	
	public boolean estoyVacio() {
		return cantidad < 1;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	
	public ItemCarritoPayload toPayload() {
		ItemCarritoPayload p = new ItemCarritoPayload();
		p.setId(id);
		p.setProducto(producto.toPayload());
		p.setCantidad(cantidad);
		return p;
	}
	
}
