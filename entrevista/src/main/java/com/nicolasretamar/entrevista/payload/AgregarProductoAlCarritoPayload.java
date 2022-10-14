package com.nicolasretamar.entrevista.payload;

public class AgregarProductoAlCarritoPayload {

	private long idProducto;
	
	private long idCarrito;

	public AgregarProductoAlCarritoPayload() {
		super();
	}

	public AgregarProductoAlCarritoPayload(long idProducto, long idCarrito) {
		super();
		this.idProducto = idProducto;
		this.idCarrito = idCarrito;
	}

	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public long getIdCarrito() {
		return idCarrito;
	}

	public void setIdCarrito(long idCarrito) {
		this.idCarrito = idCarrito;
	}

	
	
	
	
	
}
