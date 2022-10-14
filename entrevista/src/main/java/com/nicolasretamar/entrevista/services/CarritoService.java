package com.nicolasretamar.entrevista.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicolasretamar.entrevista.models.Carrito;
import com.nicolasretamar.entrevista.models.EnumTiposCarrito;
import com.nicolasretamar.entrevista.models.FechaPromocional;
import com.nicolasretamar.entrevista.models.ItemCarrito;
import com.nicolasretamar.entrevista.models.Producto;
import com.nicolasretamar.entrevista.models.User;
import com.nicolasretamar.entrevista.payload.CarritoPayload;
import com.nicolasretamar.entrevista.payload.ItemCarritoPayload;
import com.nicolasretamar.entrevista.payload.ProductoPayload;
import com.nicolasretamar.entrevista.repositories.CarritoRepository;
import com.nicolasretamar.entrevista.repositories.FechaPromocionalRepository;
import com.nicolasretamar.entrevista.repositories.ItemCarritoRepository;
import com.nicolasretamar.entrevista.repositories.ProductoRepository;
import com.nicolasretamar.entrevista.repositories.UserRepository;

@Service
public class CarritoService {

	@Autowired
	private CarritoRepository carritoRepository;
	
	@Autowired
	private ItemCarritoRepository itemCarritoRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private FechaPromocionalRepository fechaPromocionalRepository;
	
	public Long altaCarrito(Integer dni) {
		Carrito carrito = new Carrito();
		User user = userRepository.findByDni(dni).get();
		//Solo podes dar un alta carrito si o solo si todos tus carritos esten finalizados
		//boolean existeCarritoSinFinalizar = getCarritoSinFinalizar(dni) != null;
		if(user != null) {// && !existeCarritoSinFinalizar) {
			carrito.setUser(user);
			asignarTipoCarrito(carrito);
			Carrito carritoGuardadoEnBd = carritoRepository.save(carrito);
			return carritoGuardadoEnBd.getId();
		}
		return null;
	}
	
	public void eliminarUnCarrito(Long id) {
		if(!carritoRepository.existsById(id)) {
			return;
		}
		Carrito carrito = carritoRepository.findById(id).get();
		if(carrito != null) {
			List<ItemCarrito> itemsABorrar = carrito.getItems();
			List<Long> idsItemsABorrar = new ArrayList<>();
			for(ItemCarrito item: itemsABorrar) {
				idsItemsABorrar.add(item.getId());
			}
			carrito.setItems(null);
			carrito.setUser(null);
			carritoRepository.save(carrito);
			carritoRepository.delete(carrito);
			for(Long idABorrar: idsItemsABorrar) {
				itemCarritoRepository.deleteById(idABorrar);
			}
		}
			
	}
	
	
	public CarritoPayload agregarProductoAlCarrito(Long idProducto, Long idCarrito) {
		if(!existeProductoYCarrito(idProducto, idCarrito)) {
			return null;
		}
		Carrito carrito = carritoRepository.findById(idCarrito).get();
		Producto producto = productoRepository.findById(idProducto).get();
		if(carrito!=null && producto!=null) {
			carrito.agregarAlCarrito(producto);
			Carrito carritoGuardado = carritoRepository.save(carrito);
			CarritoPayload carritoPayload = carritoGuardado.toPayload();
			//Devolver el carrito payload
			return carritoPayload;
		}
		return null;
	}
	
	private boolean existeProductoYCarrito(Long idProducto, Long idCarrito) {
		boolean existeCarrito = carritoRepository.existsById(idCarrito);
		boolean existeProducto = productoRepository.existsById(idProducto);
		return existeCarrito && existeProducto;
	}
	
	
	public CarritoPayload eliminarProductoDelCarrito(Long idProducto, Long idCarrito) {
		if(!existeProductoYCarrito(idProducto, idCarrito)) {
			return null;
		}
		Carrito carrito = carritoRepository.findById(idCarrito).get();
		Producto producto = productoRepository.findById(idProducto).get();
		if(carrito!=null && producto!=null) {
			carrito.eliminarUnProducto(producto);
			Carrito carritoGuardado = carritoRepository.save(carrito);
			CarritoPayload carritoPayload = carritoGuardado.toPayload();
			//Devolver el carrito payload
			return carritoPayload;
		}
		return null;
	}
	
	public List<ProductoPayload> obtenerCuatroProductosMasCaros(Integer dni) {
		//Me trae todos los carritos
		List<CarritoPayload> carritos = carritoRepository.findByUserDni(dni).stream().map(e -> toPayload(e)).collect(Collectors.toList());
		
		List<ProductoPayload> todosLosProductos = new ArrayList<>(); 
		
		//Me traigo todos los productos uno por uno
		for (CarritoPayload c : carritos) {
			for(ItemCarritoPayload i: c.getItems()) {
				ProductoPayload producto = i.getProducto();
				Long cantidad = i.getCantidad();
				while(cantidad > 0) {
					todosLosProductos.add(producto);
					cantidad--;
				}
			}
		}
		
		List<ProductoPayload> productosMasAltos = new ArrayList<>();
		
		//Los 4 productos mas caros que compró el cliente
		for(int i=0; i<4; i++) {
			ProductoPayload productoMasAltoEnLista = traerMasAlto(todosLosProductos);
			if(productoMasAltoEnLista != null) {
				productosMasAltos.add(productoMasAltoEnLista);
				//Quito 1 producto mas alto en "todosLosProductos"
				todosLosProductos = eliminarProducto(productoMasAltoEnLista, todosLosProductos);
			}
			else {
				//Ya no quedan productos en "todosLosProductos"
				return productosMasAltos;
			}
			
		}
		
		return productosMasAltos;
		
	}
	
	private ProductoPayload traerMasAlto(List<ProductoPayload> todosLosProductos) {
		//Busco 1 producto mas alto
		ProductoPayload masAlto = new ProductoPayload();
		if(todosLosProductos.size() > 0) {
			masAlto = todosLosProductos.get(0);
			for(ProductoPayload p: todosLosProductos) {
				if(p.getPrecio().compareTo(masAlto.getPrecio()) == 1) {
					masAlto = p;
				}
			}
			return masAlto;
		}
		else {
			return null;
		}
		
		
		
	}
	
	private List<ProductoPayload> eliminarProducto(ProductoPayload productoAEliminar, List<ProductoPayload> productos) {
		//Eliminar producto de la lista
		for(int i = 0; i<productos.size(); i++) {
			if(productoAEliminar.equals(productos.get(i))) {
				//Si encuentro el producto, lo elimino y devuelvo la lista
				productos.remove(i);
				return productos;
			}
		}
		return productos;
	}
	
	
	
	
	
	
	
	
	
	private Carrito asignarTipoCarrito(Carrito c) {
		if(c.getUser() != null && c != null) {
			if(c.getUser().isVip()) {
				c.setTipoCarrito(EnumTiposCarrito.CLIENTE_VIP);
			}
			else if(isFechaPromocional()) {
				c.setTipoCarrito(EnumTiposCarrito.FECHA_PROMOCIONAL);
			}
			else {
				c.setTipoCarrito(EnumTiposCarrito.COMUN);
			}
		}
		return c;
	}
	
	private boolean isFechaPromocional() {
		List<FechaPromocional> fechas =  fechaPromocionalRepository.findAll();
		
		for(FechaPromocional f: fechas) {
			if(f.getFecha().equals(LocalDate.now())) {
				return true;
			}
		}
		return false;
	}
	
	
	private CarritoPayload toPayload(Carrito m) {
		CarritoPayload p = new CarritoPayload();
		p.setId(m.getId());
		
		for(ItemCarrito i : m.getItems()) {
			p.agregarItemAlCarrito(i.toPayload());
		}
		p.setTotal(m.getTotal());
		return p;
	}
	
	
	
	
	
}
