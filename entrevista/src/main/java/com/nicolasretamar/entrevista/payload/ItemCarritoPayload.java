package com.nicolasretamar.entrevista.payload;

public class ItemCarritoPayload {

	
	private Long id;
	

	private ProductoPayload producto;
	
	private Long cantidad;

	public ItemCarritoPayload(Long id, ProductoPayload producto, Long cantidad) {
		super();
		this.id = id;
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public ItemCarritoPayload() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductoPayload getProducto() {
		return producto;
	}

	public void setProducto(ProductoPayload producto) {
		this.producto = producto;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
	
}
