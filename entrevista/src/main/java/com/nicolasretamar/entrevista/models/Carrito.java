package com.nicolasretamar.entrevista.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.nicolasretamar.entrevista.payload.CarritoPayload;


@Entity
@Table(name = "carrito")
public class Carrito {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@OrderBy("dni ASC")
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<ItemCarrito> items;
	
	private LocalDate fechaCreadaCarrito;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private EnumTiposCarrito tipoCarrito;
	
	//private boolean compraFinalizada;
	
	private BigDecimal total;

	public Carrito() {
		super();
		items = new ArrayList<ItemCarrito>();
		//compraFinalizada = false;
		this.total = this.getTotalCarrito();
		this.fechaCreadaCarrito = LocalDate.now();
	}
	
	



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public List<ItemCarrito> getItems() {
		return items;
	}





	public void setItems(List<ItemCarrito> items) {
		this.items = items;
		if(items != null) {
			this.total = this.getTotalCarrito();
		}			
		else {
			this.items = new ArrayList<ItemCarrito>();
			this.total = this.getTotalCarrito();
		}
			
			
		
	}






	public LocalDate getFechaCreadaCarrito() {
		return fechaCreadaCarrito;
	}





	public void setFechaCreadaCarrito(LocalDate fechaCreadaCarrito) {
		this.fechaCreadaCarrito = fechaCreadaCarrito;
	}





	public EnumTiposCarrito getTipoCarrito() {
		return tipoCarrito;
	}



	public void setTipoCarrito(EnumTiposCarrito tipoCarrito) {
		this.tipoCarrito = tipoCarrito;
	}



	/*public boolean isCompraFinalizada() {
		return compraFinalizada;
	}





	public void setCompraFinalizada(boolean compraFinalizada) {
		this.compraFinalizada = compraFinalizada;
	}*/





	public BigDecimal getTotal() {
		return total;
	}





	public void setTotal(BigDecimal total) {
		this.total = total;
	}





	public void agregarAlCarrito(Producto p) {
		// Si existe, +1
		for(ItemCarrito item: items) {
			if(item.getProducto().equals(p)) {
				item.sumarCantidad((long) 1);
				this.total = this.getTotalCarrito();
				return;
			}
		}
		// Si no existe, agregar producto y cantidad = 1
		ItemCarrito item = new ItemCarrito();
		item.setProducto(p);
		item.setCantidad((long) 1);
		items.add(item);
		this.total = this.getTotalCarrito();
	}

	public void eliminarUnProducto(Producto p) {
		// Si no existe el producto, no hacer nada
		for(ItemCarrito item: items) {
			if(item.getProducto().equals(p)) {
				// Si existe el producto y cantidad 1 o mas al restar
				item.restarCantidad((long) 1);
				this.total = this.getTotalCarrito();
				if(item.estoyVacio()) {
					// Si existe el producto y cantidad 0 al restar, eliminar
					items.remove(item);
				}
				// Si existe el producto y cantidad 1 o mas al restar, no remover
				return;
			}
		}
		//No olvidarse de hacer un save del carrito al repository (en service).

	}
	
	
	public CarritoPayload toPayload() {
		CarritoPayload p = new CarritoPayload();
		p.setId(id);
		
		for(ItemCarrito i : items) {
			p.agregarItemAlCarrito(i.toPayload());
		}
		p.setTotal(total);
		return p;
	}
	
	public BigDecimal getTotalSinDescuento() {
		BigDecimal total = BigDecimal.ZERO;
		for(ItemCarrito i : items) {
			total = total.add(i.getProducto().getPrecio().multiply(new BigDecimal(i.getCantidad())));
		}
		return total;
	}
	
	public Long getCantidadProductos() {
		Long cantidad = Long.valueOf(0);
		for(ItemCarrito i : items) {
			cantidad = cantidad + i.getCantidad();
		}
		return cantidad;
	}
	
	public BigDecimal getPrecioProductoMasBarato() {
		if(items.size() > 0) {
			BigDecimal masBarato = items.get(0).getProducto().getPrecio();
			for(ItemCarrito i : items) {
				if(1 == masBarato.compareTo(i.getProducto().getPrecio())) {//1 significa "masBarato es mayor al item"
					masBarato = i.getProducto().getPrecio();
				}
			}
			return masBarato;
		}
		else {
			return BigDecimal.ZERO;
		}
		
	}
	
	public BigDecimal getTotalCarrito() {
		BigDecimal total = this.getTotalSinDescuento();
		Long cantidad = this.getCantidadProductos();
		
		//Si se compran exactamente 10, es 10% de dto del total a pagar
		if(cantidad == 10) {
			total = total.multiply(new BigDecimal("0.90"));
		}
		else if(cantidad > 5) {
			//b2
			if(this.getTipoCarrito().equals(EnumTiposCarrito.CLIENTE_VIP)) {
				total = total.subtract(this.getPrecioProductoMasBarato());
				//Si el total es mayor a 700, descontar
				if(total.compareTo(new BigDecimal(700)) == 1 || total.compareTo(new BigDecimal(700)) == 0) {
					total = total.subtract(new BigDecimal(700));
				}
			}
			else if(this.getTipoCarrito().equals(EnumTiposCarrito.FECHA_PROMOCIONAL)) {
				if(total.compareTo(new BigDecimal(700)) == 1 || total.compareTo(new BigDecimal(500)) == 0) {
					total = total.subtract(new BigDecimal(500));
				}
			}
		}
		return total;
		
	}

}
