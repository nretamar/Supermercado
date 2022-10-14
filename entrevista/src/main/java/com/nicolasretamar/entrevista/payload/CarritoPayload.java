package com.nicolasretamar.entrevista.payload;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CarritoPayload {
	
	private Long id;
	
	private List<ItemCarritoPayload> items;
	
	private BigDecimal total;

	public CarritoPayload(Long id, List<ItemCarritoPayload> items, BigDecimal total) {
		super();
		this.id = id;
		this.items = items;
		this.total = total;
	}

	public CarritoPayload() {
		super();
		items = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ItemCarritoPayload> getItems() {
		return items;
	}

	public void setItems(List<ItemCarritoPayload> items) {
		this.items = items;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	
	public void agregarItemAlCarrito(ItemCarritoPayload p) {
		items.add(p);
	}
	
}
